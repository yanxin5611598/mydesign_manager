package com.yx.terminate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.utils.JedisPoolUtils;


public class MyGPRSServer{
	public volatile static boolean flag = true;
	public volatile static boolean startFlag = false;
	public static final int PORT=8088;  
	private static Jedis jedis;
	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		MyGPRSServer.flag = flag;
	}

	public static boolean isStartFlag() {
		return startFlag;
	}

	public static void setStartFlag(boolean startFlag) {
		MyGPRSServer.startFlag = startFlag;
	}
	public static void startClientThread(Socket client) {
		//TODO 将来需要加入手机端的连接判断
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//输入流读取客户端返回的数据
					InputStream inputStream = null;
					BufferedReader br = null;
					inputStream = client.getInputStream();//获取客户端传递的信息
					br = new BufferedReader(new InputStreamReader(inputStream));//将字节流转换成带有缓冲区的字符流
					String deviceID = br.readLine().trim();
					//阻塞线程---直至jedis的key为onlineDevice的对应值中有deviceID
					while(!jedis.exists("onlineDevice")){
						//当前没有保存在线设备的set类型的集合onlineDevice
						Thread.sleep(1000);
					}
					while(!jedis.sismember("onlineDevice",deviceID)){
						//保存在线设备的set类型的集合onlineDevice中不存在该deviceID
						Thread.sleep(1000);
					}
					System.out.println("在线设备:"+jedis.smembers("onlineDevice"));
					//输出流向客户端发送数据
					PrintWriter pw1 = new PrintWriter(client.getOutputStream());//将输出流包装成打印流
					pw1.print("ON");//开启检测使能
					pw1.flush();
					System.out.println("发送使能信号到下位机");
					/*pw.print("ON");//开启检测使能
					pw.flush();*/
					
					String line="";
					Double PM25 = 0.0;
					Double PM10 = 0.0;
					Double hcho = 0.0;
					String[] split;
					
					while(jedis.sismember("onlineDevice",deviceID) && ((line = br.readLine()) != null)){
						if(line.contains("D") && (split=line.split("\\s+")).length==6){
							deviceID = split[0];
							hcho = Double.parseDouble(split[3]);
							PM25 = Double.parseDouble(split[4]);
							PM10 = Double.parseDouble(split[5]);
							if(hcho>5 || PM25>800 || PM10 >1000){
								//被认为是异常值，不做存储
								continue;
							}
							//第一种实现方式：将line加入缓存（缺点是需要使用HTTP进行轮询操作，耗时并且使用redis比较耗费服务器的内存资源）
							jedis.set(deviceID, line);
							System.out.println("redis信息"+jedis.get(deviceID));
							//第二种实现方式将服务器接收的数据直接发送给客户端（不经过database），这之间的实现是通过Socket的，但是这又存在一些问题，比如我的客户端请求是需要服务器做一些数据存储和更新操作
						}
					}
					System.out.println("在线设备"+deviceID+"被移除，或者接收到的数据为null");
					PrintWriter pw2= new PrintWriter(client.getOutputStream());//将输出流包装成打印流
					pw2.print("OFF");//关闭检测使能
					pw2.flush();
					System.out.println("发送关闭信号到下位机");
					/*br.close();
					inputStream.close();*/
					client.close();
					System.out.println("关闭客户端");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static void main(String[] args){
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("服务端已经启动，等待客户端连接。。。");
			//启动redis连接
			jedis = JedisPoolUtils.getJedis();
			int i = 0;
			while(true){
				System.out.println("正在监听客户端连接。。。");
				Socket sclient = server.accept();//获取到连接
				System.out.println("客户端"+(++i)+"连接进来了。。。");
				//启动一个线程
				startClientThread(sclient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.yx.terminate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.utils.JedisPoolUtils;


public class CopyOfMyGPRSServer{
	public volatile static boolean flag = true;
	public static final int PORT=8088;  
	private static Jedis jedis;
	public static void startClientThread(Socket client) {
		//TODO 将来需要加入手机端的连接判断
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//输出流向客户端发送数据
					PrintWriter pw = new PrintWriter(client.getOutputStream());//将输出流包装成打印流
					pw.print("ON");//开启检测使能
					pw.flush();
					System.out.println("发送使能信号到下位机");
					/*pw.print("ON");//开启检测使能
					pw.flush();*/
					//输入流读取客户端返回的数据
					InputStream inputStream = null;
					BufferedReader br = null;
					String line="";
					String deviceID = "";
					Double PM25 = 0.0;
					Double PM10 = 0.0;
					Double hcho = 0.0;
					String[] split;
					inputStream = client.getInputStream();//获取客户端传递的信息
					br = new BufferedReader(new InputStreamReader(inputStream));//将字节流转换成带有缓冲区的字符流
					
					if(br.readLine() != null){
						client.setSoTimeout(10000);//设置10000ms的超时
						System.out.println("设置了10s的超时时间");
					}
					while(flag){
						try{
							while((line = br.readLine()) != null){
//								System.out.println(line);
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
							
						}catch(Exception e){
							System.out.println("发生了阻塞");
							//不能直接关闭流对象，否则会导致socket客户端流不可用
							br = new BufferedReader(new InputStreamReader(inputStream));//重新获取输入流
							System.out.println("已重新获取流对象");
						}
					}
					
					pw.print("OFF");//关闭检测使能
					pw.flush();
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

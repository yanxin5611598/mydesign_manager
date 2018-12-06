package com.yx.terminate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.utils.JedisPoolUtils;


public class WebSocketServer{
	private static PrintWriter pw = null;
	private static BufferedReader br = null;
	
	public WebSocketServer() {
	}
	public static final int PORT=8088;  
	private static Jedis jedis;
	private static String userSession;
	private static Map<String, Socket> map = new HashMap<String, Socket>();
	private static Integer countId = 0;
	public static void handleClient(final Socket client){
		
		new Thread(){
			@Override
			public void run() {
				//TODO 将来需要加入手机端的连接判断
				countId++;
				try {
					//输出流向客户端发送数据
					pw = new PrintWriter(client.getOutputStream());//将输出流包装成打印流
					//等待手机端发送检测使能信息
					String getData = readStringFromClient(client);
					if(getData!=null){
						//读取到Socket客户端发送的数据
						String[] res = getData.split("-");
						if (res[0].equals("user")) {//注册userID
							userSession = res[0]+String.valueOf(countId);
							map.put(userSession, client);
							System.out.println("添加session成功");
							//手机端Socket的数据
							if(res[1].equals("start")){
								//向下位机发送检测使能信号
//								send
							}else if(res[1].equals("stop")){
								//向下位机发送检测中断信号
							}
							
						}
					}
					pw.print("1");//开启检测使能
					pw.flush();
					System.out.println("发送使能信号到下位机");
					//输入流读取客户端返回的数据
					InputStream inputStream = client.getInputStream();//获取客户端传递的信息
					br = new BufferedReader(new InputStreamReader(inputStream));//将字节流转换成带有缓冲区的字符流
					String line="";
					String deviceID = "";
					Double PM25 = 0.0;
					Double PM10 = 0.0;
					Double hcho = 0.0;
					String[] split;
					while((line = br.readLine())!=null && line.split("\t").length==6){
						split = line.split("\t");
						deviceID = split[0].split("=")[1];
						hcho = Double.parseDouble(split[3].split("=")[1]);
						PM25 = Double.parseDouble(split[4].split("=")[1]);
						PM10 = Double.parseDouble(split[5].split("=")[1]);
						if(hcho>5 || PM25>800 || PM10 >1000){
							//被认为是异常值，不做存储
							continue;
						}
						//第一种实现方式：将line加入缓存（缺点是需要使用HTTP进行轮询操作，耗时并且使用redis比较耗费服务器的内存资源）
						jedis.set(deviceID, line);
		//				jedis.append("device", deviceID);//向device追加设备编号信息
						System.out.println("redis信息"+jedis.get(deviceID));
						//第二种实现方式将服务器接收的数据直接发送给客户端（不经过database），这之间的实现是通过Socket的，但是这又存在一些问题，比如我的客户端请求是需要服务器做一些数据存储和更新操作
						
					}
					br.close();
					inputStream.close();
					client.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		}.start();
	}
	/**
	 * 获取安卓端发送的使能信号*/
	private static String readStringFromClient(Socket client) {
		try{
			InputStream is = client.getInputStream();
			int len = is.available();//在读写操作前先得知数据流里有多少个字节可以读取
			if (len != 0) {
				byte[] buffer = new byte[len];
				is.read(buffer);
				//printHex(buffer);
				System.out.println("r:" + new String(buffer));
				return new String(buffer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args){
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("服务端已经启动，等待客户端连接。。。");
			//启动redis连接
			jedis = JedisPoolUtils.getJedis();
			System.out.println("redis数据库已连接");
			while(true){
				Socket s = server.accept();//获取到连接
				System.out.println("有客户端连接进来了。。。");
				handleClient(s);//启动一个线程
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

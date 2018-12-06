package com.yx.terminate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.utils.JedisPoolUtils;


public class MySocketServer extends Thread{
/*	@Autowired
	private ReceiveDataMapper receiveDataMapper;*/
	private static PrintWriter pw = null;
	public volatile String info;
	public volatile boolean flag = true;
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	public Socket client;
	public MySocketServer(Socket client){
		this.client = client;
	}
	public MySocketServer() {
	}
	public static final int PORT=8088;  
	private static Jedis jedis;
	@Override
	public void run() {
		//TODO 将来需要加入手机端的连接判断
		try {
			//输出流向客户端发送数据
			pw = new PrintWriter(client.getOutputStream());//将输出流包装成打印流
			
			pw.print("ON");//开启检测使能
			pw.flush();
			System.out.println("发送使能信号到下位机");
			Thread.sleep(1000);
			/*pw.print("ON");//开启检测使能
			pw.flush();*/
			//输入流读取客户端返回的数据
			InputStream inputStream = client.getInputStream();//获取客户端传递的信息
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));//将字节流转换成带有缓冲区的字符流
			System.out.println("读取到的数据："+br.readLine());
			String line="";
			String deviceID = "";
			Double PM25 = 0.0;
			Double PM10 = 0.0;
			Double hcho = 0.0;
			String[] split;
			while(flag && br.readLine()!=null){
				if((line = br.readLine())!=null && line.split("\t").length==6){
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
					System.out.println("redis信息"+jedis.get(deviceID));
					//第二种实现方式将服务器接收的数据直接发送给客户端（不经过database），这之间的实现是通过Socket的，但是这又存在一些问题，比如我的客户端请求是需要服务器做一些数据存储和更新操作
				}else{
					continue;
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
	
	
	public static void main(String[] args){
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("服务端已经启动，等待客户端连接。。。");
			//启动redis连接
			jedis = JedisPoolUtils.getJedis();
			while(true){
				Socket s = server.accept();//获取到连接
				System.out.println("有客户端连接进来了。。。");
				new MySocketServer(s).start();//启动一个线程
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

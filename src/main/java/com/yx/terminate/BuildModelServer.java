package com.yx.terminate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


import com.yx.mydesign.alg.Fuzzy;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.OtherDaoImpl;


public class BuildModelServer{
	public volatile static boolean flag = true;
	public static final int PORT=8088;  
	private static Fuzzy fuzzy;

    private static OtherDao modelDao = new OtherDaoImpl();
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
					Double PM25 = 0.0;
					Double PM10 = 0.0;
					Double hcho = 0.0;
					String[] split;
					String other = null;
					int result = 0;
					inputStream = client.getInputStream();//获取客户端传递的信息
					br = new BufferedReader(new InputStreamReader(inputStream));//将字节流转换成带有缓冲区的字符流
					Fuzzy.preFuzzyMethod();
					fuzzy = Fuzzy.getInstance();
					while(flag && ((line = br.readLine()) != null)){
						if(line.contains("D") && (split=line.split("\\s+")).length==6){
							hcho = Double.parseDouble(split[3]);
							PM25 = Double.parseDouble(split[4]);
							PM10 = Double.parseDouble(split[5]);
							/*if(hcho>5 || PM25>800 || PM10 >1000){
								//被认为是异常值，不做存储
								continue;
							}*/
							other = fuzzy.fuzzyMethodForModel(line);
							System.out.println(line + " "+ other);
							//存储数据
							result = modelDao.insertTrain(line + " "+ other);
							if(result == 0){
								System.out.println("insert failed");
							}
							else{
								System.out.println("insert success");
							}
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

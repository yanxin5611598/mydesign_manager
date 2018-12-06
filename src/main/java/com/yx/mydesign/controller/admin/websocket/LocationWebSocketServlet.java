package com.yx.mydesign.controller.admin.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.thread.DeviceMapThread;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.utils.OtherDaoImpl;
/**
 * 这个类实现了带有Websocket功能的前端ECharts显示在线设备的位置信息.
 */
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket，类似Servlet的注解mapping
@ServerEndpoint(value="/locationDisplayWebsocket")
public class LocationWebSocketServlet extends HttpServlet{
	private static final long serialVersionUID = -8311982195019592778L;
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<LocationWebSocketServlet> webSocketSet = new CopyOnWriteArraySet<LocationWebSocketServlet>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
	private  javax.websocket.Session session=null;
    private static Jedis jedis = JedisPoolUtils.getJedis();
    private OtherDao deviceDao = null;
    private Set<String> deviceSet = null;
    String onlineTudeInfo = null;
    //线程用于监控在线设备数量
    DeviceMapThread thread1=new DeviceMapThread();
    Thread thread=new Thread(thread1);
	//连接上的时候执行
	@OnOpen
	public void onOpen(Session session) throws Exception{
        System.out.println("Session " + session.getId() + " has opened a connection");
        this.session=session;
		webSocketSet.add(this);     //加入set中
		deviceDao = new OtherDaoImpl();
		//首从redis中读取设备信息（通过判断当前redis中存储的设备在线的信息）
        deviceSet = jedis.keys("D*");//查找redis中的所有关于设备的键
        int nums = deviceSet.size();
        System.out.println("当前在线的设备数为："+nums);
        session.getBasicRemote().sendText("deviceNums="+nums+"-"+deviceSet);
		//开启一个线程对数据库中的数据进行轮询
		thread.start();
		System.out.println("onOpen was executed");
    }
	/**
     * 接收到客户端消息时使用
     */
	@OnMessage 
    public void onMessage(Session session,String message){
		
        System.out.println("Message from " + session.getId() + ": " + message);
        message = message.trim();
        if(message.startsWith("D")){
        	try {
        		//从mysql数据库中读取当前在线的设备的经纬度信息
                //根据在线设备获取其经纬度信息
                onlineTudeInfo = deviceDao.selectTudeAboutOnlineDevice(message);
                System.out.println("onlineTudeInfo:"+onlineTudeInfo);
				session.getBasicRemote().sendText(onlineTudeInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
	/**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @throws IOException
     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     */
    public void sendMessage(String message) throws IOException{
        //群发消息
        for(LocationWebSocketServlet item: webSocketSet){
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    /**
     * 关闭连接时调用
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);  //从set中删除
        thread1.stopMe();
        System.out.println("Session " +session.getId()+" has closed!");
    }
    /**
     * 注意: OnError() 只能出现一次.   其中的参数都是可选的。
     * @param session
     * @param t
     */
    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
}

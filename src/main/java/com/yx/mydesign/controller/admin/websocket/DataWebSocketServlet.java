package com.yx.mydesign.controller.admin.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.thread.DeviceDataThread;
import com.yx.mydesign.utils.JedisPoolUtils;


/*import com.yx.mydesign.service.ReceiveDataService;

import redis.clients.jedis.Jedis;*/

/**
 * 这个类实现了带有Websocket功能的前端ECharts显示redis数据库数据.
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket，类似Servlet的注解mapping；
@ServerEndpoint(value="/dataDisplayWebsocket")
public class DataWebSocketServlet{
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<DataWebSocketServlet> webSocketSet = new CopyOnWriteArraySet<DataWebSocketServlet>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
	private  javax.websocket.Session session=null;
    private static Jedis jedis = JedisPoolUtils.getJedis();
    private Set<String> deviceSet = null;
    //线程用于监控在线设备数量
    DeviceDataThread thread2=new DeviceDataThread();
    Thread thread=new Thread(thread2);
//    RedisDataThread thread1 = null;
    //初始化一个List集合保存已经发送过消息的设备编号
    List<String> list = new ArrayList<String>();
	//连接上的时候执行
	@OnOpen
	public void onOpen(Session session){
        System.out.println("Session " + session.getId() + " has opened a connection");
        try {
        	this.session=session;
            webSocketSet.add(this);     //加入set中
            //从redis中读取设备信息（通过判断当前redis中存储的设备在线的信息）
            deviceSet = jedis.keys("D*");//查找redis中的所有关于设备的键
            int nums = deviceSet.size();
            System.out.println("当前在线的设备数为："+nums);
            session.getBasicRemote().sendText("deviceNums="+nums+"-"+deviceSet);
            //开启一个线程对数据库中的数据进行轮询
    		thread.start();
    		System.out.println("onOpen was executed");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	/**
     * 接收到客户端消息时使用，这个例子里没用
     */
	@OnMessage 
    public void onMessage(Session session,String message){
        System.out.println("Message from " + session.getId() + ": " + message);
        message = message.trim();
        if(message.startsWith("D") && !list.contains(message)){
        	list.add(message);
        	//启动一个守护线程去监控redis中是否存在该键，如果存在则将该键对应的value发送给前台页面
        	//该线程用于监控redis中的数据
//            thread1 = new RedisDataThread();
//            thread1.setMessage(message);
//            Thread redisThread = new Thread(thread1);
//        	redisThread.start();
        }
    }
    /**
     * 关闭连接时调用
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);  //从set中删除
        thread2.stopMe();
//        thread1.stopMe();
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
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @throws IOException
     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     */
    public void sendMessage(String message) throws IOException{
        //群发消息
        for(DataWebSocketServlet item: webSocketSet){
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}

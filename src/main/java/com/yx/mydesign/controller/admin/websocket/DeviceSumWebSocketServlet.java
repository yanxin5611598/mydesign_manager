package com.yx.mydesign.controller.admin.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;




import redis.clients.jedis.Jedis;

import com.yx.mydesign.thread.DataSumThread;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.OtherDaoImpl;


/*import com.yx.mydesign.service.ReceiveDataService;

import redis.clients.jedis.Jedis;*/

/**
 * 这个类实现了带有Websocket功能的统计在线设备数、总设备数等相关信息.
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket，类似Servlet的注解mapping；
@ServerEndpoint(value="/dataSumWebsocket")
public class DeviceSumWebSocketServlet{
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<DeviceSumWebSocketServlet> webSocketSet = new CopyOnWriteArraySet<DeviceSumWebSocketServlet>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
	private  javax.websocket.Session session=null;
    //线程用于监控在线设备数量
    DataSumThread thread2=new DataSumThread();
    Thread thread=new Thread(thread2);
//    RedisDataThread thread1 = null;
    //初始化一个List集合保存已经发送过消息的设备编号
    List<String> list = new ArrayList<String>();
	//连接上的时候执行
	@OnOpen
	public void onOpen(Session session){
        System.out.println("Session " + session.getId() + " has opened a connection");
    	this.session=session;
        webSocketSet.add(this);     //加入set中
        Jedis jedis = JedisPoolUtils.getJedis();
        OtherDao otherDao = new OtherDaoImpl();
        try {
			sendMessage(""+otherDao.getDeviceNum()+"-"+jedis.keys("D*").size()+"-"+otherDao.getUserNum()+"-"+otherDao.getRecordNum()+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //开启一个线程对数据库中的数据进行轮询
		thread.start();
		System.out.println("onOpen was executed");
    }
	/**
     * 接收到客户端消息时使用，这个例子里没用
     */
	@OnMessage 
    public void onMessage(Session session,String message){
        System.out.println("Message from " + session.getId() + ": " + message);
    }
    /**
     * 关闭连接时调用
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);  //从set中删除
        thread2.stopMe();
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
        for(DeviceSumWebSocketServlet item: webSocketSet){
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}

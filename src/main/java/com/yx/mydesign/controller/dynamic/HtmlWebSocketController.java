package com.yx.mydesign.controller.dynamic;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import redis.clients.jedis.Jedis;

import com.yx.mydesign.utils.JedisPoolUtils;


/*import com.yx.mydesign.service.ReceiveDataService;

import redis.clients.jedis.Jedis;*/

/**
 * 这个类实现了带有Websocket功能的前端ECharts显示数据库数据.
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket，类似Servlet的注解mapping；
@ServerEndpoint(value="/viewDataWebsocket")
public class HtmlWebSocketController{
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<HtmlWebSocketController> webSocketSet = new CopyOnWriteArraySet<HtmlWebSocketController>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
    @SuppressWarnings("unused")
	private  javax.websocket.Session session=null;
    private Jedis jedis;
	//连接上的时候执行
	@OnOpen
	public void onOpen(Session session){
        System.out.println("Session " + session.getId() + " has opened a connection");
        this.session=session;
        webSocketSet.add(this);     //加入set中
		jedis = JedisPoolUtils.getJedis();
		System.out.println("获取到jedis");
    }
	/**
     * 接收到客户端消息时使用，这个例子里没用
     */
	@OnMessage 
    public void onMessage(Session session,String message){
        System.out.println("Message from " + session.getId() + ": " + message);
        if(message.length()==0)
			return;
		String param = "";
		String deviceID = "";
        System.out.println(message);
        deviceID = message.split("-")[0];
    	param = message.split("-")[1];
    	int paramIndex = param.equals("温度")?1:param.equals("湿度")?2:param.equals("甲醛")?3:param.equals("PM2.5")?4:param.equals("PM10")?5:6;
    	//deviceID=1	Tem=26.0	Hum=58.0	CHOH=0.013	PM2.5= 4.7	PM10=11.0
    	param = param.equals("温度")?"Tem":param.equals("湿度")?"Hum":param.equals("甲醛")?"CHOH":param.equals("PM2.5")?"PM2.5":param.equals("PM10")?"PM10":"CO2";
    	System.out.println(param);
       	//判断数据库中是否存在该deviceID(即判断当前redis数据库中是否有deviceID的key)
    	try{
    		if(!jedis.exists(deviceID)){
        		System.out.println("不存在该键值");
        		session.getBasicRemote().sendText("sorry");
        	}
        	else{
        		System.out.println("存在该键值");
        		String resultInfoMessage;
        		//查询数据库
                while(true){
                	Thread.sleep(2000);
                	//TODO 这里先模拟输出一样的数据，后面加上jedis中键是否存在的判断，退出循环即可
            		resultInfoMessage = jedis.get(deviceID).split("\t")[paramIndex].split("=")[1];
                	System.out.println(resultInfoMessage);
                	session.getBasicRemote().sendText(resultInfoMessage);;
                	System.out.println("发送了一条数据");
            	}
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    /**
     * 关闭连接时调用
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);  //从set中删除
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

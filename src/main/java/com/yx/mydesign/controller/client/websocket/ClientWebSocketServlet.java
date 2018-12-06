package com.yx.mydesign.controller.client.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.ehcache.store.chm.ConcurrentHashMap;


import redis.clients.jedis.Jedis;

import com.yx.mydesign.alg.Fuzzy;
import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.User;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.OtherDaoImpl;


/*import com.yx.mydesign.service.ReceiveDataService;

import redis.clients.jedis.Jedis;*/

/**
 * 这个类实现了带有Websocket功能的向Android客户端提供实时数据的需求
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket，类似Servlet的注解mapping；
@ServerEndpoint(value="/websocket")
public class ClientWebSocketServlet{
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	//用于保存HttpSession与WebSocketSession的映射关系
		public static final Map<String, Session> userSocketSessionMap;
	static {
		userSocketSessionMap = new ConcurrentHashMap<String, Session>();
	}
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
	private  Session session=null;
	public volatile static boolean flag = true;//标识用户断开连接
	private Fuzzy fuzzy;
	private Jedis jedis;
	private String values;
	private String deviceID = "";
	private OtherDao otherDao = new OtherDaoImpl();
	//连接上的时候执行
	@OnOpen
	public void onOpen(Session session){
    	this.session=session;
    	String uid = session.getId();
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
		}
		System.out.println("connect to the websocket success......当前数量:"+userSocketSessionMap.size());
    	flag = true;
        System.out.println("flag="+flag);
    	
		jedis = JedisPoolUtils.getJedis();
		System.out.println("获取到jedis");
		Fuzzy.preFuzzyMethod();
		fuzzy = Fuzzy.getInstance();
		System.out.println("算法模型初始化成功");
    }
	/**
     * 接收到客户端消息时使用
     */
	@OnMessage 
    public void onMessage(Session session,String message){
		if(message.length()==0)
			return;
		String params = message.trim();
        System.out.println(params);
        if(params.contains("--")){
        	//将数据库更新
            String username = params.split("--")[0];
    		String GPSInfo = params.split("--")[1];
    		deviceID = params.split("--")[2];
    		User user = otherDao.getUserByUsername(username);
    		//首先根据二维码中设备的ID,查询出对应的设备
    		Device device = otherDao.getDeviceByDeviceID(deviceID);
    		//将数据表中的GPS信息、用户相关信息、以及使用时间等信息更新
    		System.out.println("gps_result=================="+GPSInfo);
    		String[] gps = GPSInfo.split("\t");
    		if(device.getPlacecurrent() != gps[0]){
    			device.setPlacecurrent(gps[0]);
    		}
    		device.setLongitude(gps[1]);
    		device.setLatitude(gps[2]);
    		device.setTimecurrent(new Date());
    		device.setUsername(user.getUsername());
    		device.setPhone(user.getPhone());
    		otherDao.updateDeviceInfo(device,deviceID);
    		System.out.println("更新设备表成功");
        }
        else if(params.trim().contains("D")){
        	deviceID = params;
        }
        String resultInfoMessage;
        System.out.println("设置开启连接");
        jedis.sadd("onlineDevice",deviceID);//将该deviceID存入redis的set类型键(避免重复)：onlineDevice中表征该deviceID所对应的app已经连接
        System.out.println("在线设备："+jedis.smembers("onlineDevice"));
        String rank = null;
        //查询数据库
        while(jedis.sismember("onlineDevice",deviceID)){
        	if(jedis.exists(deviceID)){
        		values = jedis.get(deviceID);
        		//TODO 这里以后需要加上算法实现的模型，进行评价操作（已经实现）
        		rank = fuzzy.fuzzyMethodForModel(values);
        		resultInfoMessage = values+"\t"+rank;
        		System.out.println("values:"+values+"\t"+rank);
        		try {
					sendMessageToUser(session.getId(), resultInfoMessage);
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	/*if(jedis.get("phoneState") == "0"||jedis.get("phoneState").equals("0")){
        		closeMessage = new TextMessage("0");
            	sendMessageToUser(session.getId(), closeMessage);
            	break;
        	}*/
        }
    	//客户端请求关闭websocket连接
    	try {
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //删除set中该deviceID(离线)
//        jedis.srem("onlineDevice", deviceID);
//        System.out.println("在置flag为false之后，删除了在线设备中的该设备："+deviceID);
        //删除onlineDevice的前10个value，value为deviceID（之所以是10，将该值设大点防止测试过程中该key有多个value均为deviceID,将其全部删除）
        System.out.println("在置flag为false之后，删除了该键值对："+deviceID);
	}
	
    /**
     * 关闭连接时调用
     */
    @OnClose
    public void onClose(Session session){
    	String username= (String) session.getId();
        System.out.println("用户"+username+"已退出！");
        //将在线设备中的value为deviceID的删除
        jedis.srem("onlineDevice", deviceID);
        System.out.println("已移除在线设备："+deviceID);
        if(jedis.exists(deviceID)){
        	jedis.del(deviceID);
        	System.out.println("已移除键值对："+deviceID);
        }
        Iterator<Entry<String, Session>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前用户的Socket会话
		while (it.hasNext()) {
			Entry<String, Session> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
        System.out.println("剩余在线用户"+userSocketSessionMap.size());
    }
    /**
     * 注意: OnError() 只能出现一次.   其中的参数都是可选的。
     * @param session
     * @param t
     */
    @OnError
    public void onError(Session session, Throwable t) {
    	//销毁session
    	try {
	    	if(session.isOpen()){
				session.close();
			} 
	        System.out.println("websocket connection closed......");
	        if (session.isOpen()) {
				session.close();
			}
    	}catch (IOException e) {
			e.printStackTrace();
    	}
        //将在线设备中的value为deviceID的删除
        jedis.srem("onlineDevice", deviceID);
        System.out.println("已移除在线设备："+deviceID);
		Iterator<Entry<String, Session>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前抛出异常用户的Socket会话
		while (it.hasNext()) {
			Entry<String, Session> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
    }
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     * @throws IOException 
     */
    public void sendMessageToUser(String uid, String message) throws IOException {
//    	Session session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.getBasicRemote().sendText(message);
		}
    }
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(final String message) {
    	Iterator<Entry<String, Session>> it = userSocketSessionMap.entrySet().iterator();

		//多线程群发
		while (it.hasNext()) {

			final Entry<String, Session> entry = it.next();

			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().getBasicRemote().sendText(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}

		}
    }
}

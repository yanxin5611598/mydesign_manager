package com.yx.mydesign.controller.client.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.ehcache.store.chm.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.alg.Fuzzy;
import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.User;
import com.yx.mydesign.service.client.DeviceService;
import com.yx.mydesign.service.client.UserClientService;
import com.yx.mydesign.utils.JedisPoolUtils;


@Controller(value="copywebsocket")
@RequestMapping("copywebsocket")
public class CopyOfWebSocketHandler implements org.springframework.web.socket.WebSocketHandler {
	
	@Autowired
	private UserClientService userClientService;
	@Autowired
	private DeviceService deviceService;
	public static boolean flag = true;//标识用户断开连接
	private Fuzzy fuzzy;
	private Jedis jedis;
	private String values;
	//用于保存HttpSession与WebSocketSession的映射关系
	public static final Map<String, WebSocketSession> userSocketSessionMap;
	
	static {
		userSocketSessionMap = new ConcurrentHashMap<String, WebSocketSession>();
	}


    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("flag="+flag);
    	System.out.println("connect to the websocket success......当前数量:"+userSocketSessionMap.size());
        String uid = session.getId();
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
		}
		jedis = JedisPoolUtils.getJedis();
		System.out.println("获取到jedis");
		Fuzzy.preFuzzyMethod();
		fuzzy = Fuzzy.getInstance();
		System.out.println("算法模型初始化成功");
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
//        TextMessage returnMessage = new TextMessage("你将收到的离线");
//        session.sendMessage(returnMessage);
    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username= (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        System.out.println("用户"+username+"已退出！");
        Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前用户的Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
        System.out.println("剩余在线用户"+userSocketSessionMap.size());
    }

    /**
     * android调用client.send方法时候，会调用该方法
     */
    /**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
    @ResponseBody
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if(message.getPayloadLength()==0)
			return;
		String deviceID = "";
		String params = message.getPayload().toString();
        System.out.println(params);
        if(params.contains("--")){
        	//将数据库更新
            String username = params.split("--")[0];
    		String GPSInfo = params.split("--")[1];
    		deviceID = params.split("--")[2];
    		User user = userClientService.getUserByUsername(username);
    		//首先根据二维码中设备的ID,查询出对应的设备
    		Device device = deviceService.getDeviceByDeviceID(deviceID);
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
    		deviceService.updateDeviceInfo(device);
    		System.out.println("更新设备表成功");
        }
        else{
        	deviceID = params;
        }
        TextMessage resultInfoMessage,closeMessage;
        //查询数据库
        while(flag){
        	if(jedis.exists(deviceID)){
        		jedis.set("phoneState","1");
        		//TODO 这里以后需要加上算法实现的模型，进行评价操作
        		values = jedis.get(deviceID);
        		System.out.println("values:"+values);
        		resultInfoMessage = new TextMessage(values+"\t"+"range="+fuzzy.fuzzyMethod(values));
            	sendMessageToUser(session.getId(), resultInfoMessage);
            	
            	//System.out.println("发送了一条数据");
            	//删除该键值对
            	jedis.del(deviceID);
//            	System.out.println("删除了该条数据");
        	}
        	if(jedis.get("phoneState") == "0"||jedis.get("phoneState").equals("0")){
        		closeMessage = new TextMessage("0");
            	sendMessageToUser(session.getId(), closeMessage);
            	break;
        	}
        }
	}

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //销毁session
    	if(session.isOpen()){session.close();}
        System.out.println("websocket connection closed......");
        if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前抛出异常用户的Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
    }
    //处理拆分消息
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     * @throws IOException 
     */
    public void sendMessageToUser(String uid, TextMessage message) throws IOException {
    	WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(final TextMessage message) {
    	Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();

		//多线程群发
		while (it.hasNext()) {

			final Entry<String, WebSocketSession> entry = it.next();

			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
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

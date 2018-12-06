package com.yx.mydesign.controller.dynamic;

import java.io.IOException;
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

import com.yx.mydesign.service.client.DeviceService;
import com.yx.mydesign.service.client.UserClientService;
import com.yx.mydesign.utils.JedisPoolUtils;


@Controller(value="dynamicwebsocket")
@RequestMapping("dynamicwebsocket")
public class DynamicWebSocketHandler implements org.springframework.web.socket.WebSocketHandler {
	
	@Autowired
	private UserClientService userClientService;
	@Autowired
	private DeviceService deviceService;
	public static boolean flag = true;//标识用户断开连接
	Jedis jedis;
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
		String param = "";
		String deviceID = "";
		String info = message.getPayload().toString();
        System.out.println(info);
        deviceID = info.split("-")[0];
    	param = info.split("-")[1];
    	//deviceID=1	Tem=26.0	Hum=58.0	CHOH=0.013	PM2.5= 4.7	PM10=11.0
    	param = param.equals("温度")?"Tem":param.equals("湿度")?"Hum":param.equals("甲醛")?"CHOH":param.equals("PM2.5")?"PM2.5":param.equals("PM10")?"PM10":"CO2";
    	System.out.println(param);
    	int paramIndex = param.equals("温度")?1:param.equals("湿度")?2:param.equals("甲醛")?3:param.equals("PM2.5")?4:param.equals("PM10")?5:6;
    	//判断数据库中是否存在该deviceID(即判断当前redis数据库中是否有deviceID的key)
    	if(!jedis.exists(deviceID)){
    		System.out.println("不存在该键值");
    		TextMessage deviceIDInfoMessage = new TextMessage("sorry");;
    		sendMessageToUser(session.getId(), deviceIDInfoMessage);
    	}
    	else{
    		System.out.println("存在该键值");
    		TextMessage resultInfoMessage;
    		//查询数据库
            while(flag){
            	Thread.sleep(2000);
            	//TODO 这里先模拟输出一样的数据，后面加上jedis中键是否存在的判断，退出循环即可
        		resultInfoMessage = new TextMessage(jedis.get(deviceID).split("\t")[paramIndex].split("=")[1]);
            	System.out.println(resultInfoMessage);
        		sendMessageToUser(session.getId(), resultInfoMessage);
            	System.out.println("发送了一条数据");
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

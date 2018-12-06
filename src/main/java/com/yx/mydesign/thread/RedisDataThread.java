package com.yx.mydesign.thread;

import java.io.IOException;
import redis.clients.jedis.Jedis;

import com.yx.mydesign.controller.admin.websocket.DataWebSocketServlet;
import com.yx.mydesign.utils.JedisPoolUtils;
/**
 * 节点数据显示监控用守护线程*/
public class RedisDataThread implements Runnable{
    private boolean stopMe = true;  
    private String key;
    public void stopMe() {  
        stopMe = false;  
    }  
    public void setMessage(String message){
    	this.key = message;
    }
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()  {
        Jedis jedis = JedisPoolUtils.getJedis();
        DataWebSocketServlet wbs=new DataWebSocketServlet();
    	//从redis中读取设备信息（通过判断当前redis中存储的设备在线的信息）
        while(stopMe && jedis.get(key) != null){
        	//redis中有该设备信息---那么就将该键所对应的值返回给前端
        	StringBuffer sb = new StringBuffer();
        	String[] data = jedis.get(key).split("\t");
        	for(int i = 1;i < data.length;i++){
        		sb.append(data[i].split("=")[1]);
        		if(i < data.length - 1){
            		sb.append(",");	
        		}
        	}
			try {
				wbs.sendMessage(sb.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

package com.yx.mydesign.thread;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.controller.admin.websocket.DataWebSocketServlet;
import com.yx.mydesign.utils.JedisPoolUtils;
/**
 * 节点数据显示监控用守护线程*/
public class DeviceDataThread implements Runnable{
    private int num;
    private int new_num;
    private boolean stopMe = true;  
    public void stopMe() {  
        stopMe = false;  
    }  

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()  {
        Jedis jedis = JedisPoolUtils.getJedis();
        num = jedis.keys("D*").size();
        Set<String> deviceSet = null;
        DataWebSocketServlet wbs=new DataWebSocketServlet();
        while(stopMe){
        	//从redis中读取设备信息（通过判断当前redis中存储的设备在线的信息）
            deviceSet = jedis.keys("D*");//查找redis中的所有关于设备的键
            new_num = deviceSet.size();
            if(num != new_num){
                System.out.println("change");
                num=new_num;
                try {
					wbs.sendMessage("deviceNums="+new_num+"-"+deviceSet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        jedis.del("D*");
    }
}

package com.yx.mydesign.thread;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.controller.admin.websocket.DeviceSumWebSocketServlet;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.OtherDaoImpl;
/**
 * 系统在线设备数、总设备数、总人数等监控用守护线程
 * 用一个线程完成对在线设备数、设备总数、系统用户人数的监控，只要有一个事件发生改变即触发*/
public class DataSumThread implements Runnable{
    private int deviceNum,newDeviceNum;
    private int onlineDeviceNum,newOnlineDeviceNum;
    private int userNum,newUserNum;
    private int recordNum,newRecordNum;
    private boolean stopMe = true;  
    Jedis jedis = JedisPoolUtils.getJedis();
    OtherDao otherDao = new OtherDaoImpl();
    public void stopMe() {  
        stopMe = false; 
        otherDao.closeConnect();//释放掉连接
    }  

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()  {
        onlineDeviceNum = jedis.keys("D*").size();
        deviceNum = otherDao.getDeviceNum();
        userNum = otherDao.getUserNum();
        recordNum = otherDao.getRecordNum();
       /* System.out.println("onlineDeviceNum="+onlineDeviceNum);
        System.out.println("deviceNum="+deviceNum);
        System.out.println("userNum="+userNum);
        System.out.println("recordNum="+recordNum);*/
        Set<String> deviceSet = null;
        DeviceSumWebSocketServlet wbs=new DeviceSumWebSocketServlet();
        System.out.println("DataSumThread线程开始监控");
        while(stopMe){
        	//从redis中读取设备信息（通过判断当前redis中存储的设备在线的信息）
            deviceSet = jedis.keys("D*");//查找redis中的所有关于设备的键
            newOnlineDeviceNum = deviceSet.size();
            newDeviceNum = otherDao.getDeviceNum();
            newUserNum = otherDao.getUserNum();
            newRecordNum = otherDao.getRecordNum();
           /* System.out.println("newOnlineDeviceNum="+newOnlineDeviceNum);
            System.out.println("newDeviceNum="+newDeviceNum);
            System.out.println("newUserNum="+newUserNum);
            System.out.println("newRecordNum="+newRecordNum);*/
            if(onlineDeviceNum != newOnlineDeviceNum || deviceNum != newDeviceNum || userNum != newUserNum || recordNum != newRecordNum){
                System.out.println("dataSum change");
                onlineDeviceNum=newOnlineDeviceNum;
                deviceNum = newDeviceNum;
                userNum = newUserNum;
                recordNum = newRecordNum;
                try {
					wbs.sendMessage(""+deviceNum+"-"+onlineDeviceNum+"-"+userNum+"-"+recordNum+"");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("DataSumThread线程断开连接");
    }
}

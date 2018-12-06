package com.yx.mydesign.utils;

import java.util.List;

import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.MyTrain;
import com.yx.mydesign.bean.User;


public interface OtherDao {

	//读取在线设备的经纬度信息,一个一个去查询
	public String selectTudeAboutOnlineDevice(String device);
	//获取设备总数
	public Integer getDeviceNum();
	//获取总人数
	public Integer getUserNum();
	//获取总上传记录数
	public Integer getRecordNum();
	//训练集数据的采集
	public int insertTrain(String values);
	public int insertTrainTest(String values);
	//查找出所有的训练集数据
	public List<MyTrain> getAllTrain();
	//重心法结果插入
	public int updateToTrainForNewAlg(Integer id,String value);
	//根据用户名返回用户信息
	public User getUserByUsername(String username);
	//根据deviceID唯一标示获取device信息
	public Device getDeviceByDeviceID(String deviceID);
	//更新device
	public int updateDeviceInfo(Device device,String deviceID);
	//释放连接
	public void closeConnect();
}

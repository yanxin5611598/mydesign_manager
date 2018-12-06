package com.yx.mydesign.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
 * 封装设备的相关信息*/








import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.DeviceExample;
import com.yx.mydesign.bean.DeviceExample.Criteria;
import com.yx.mydesign.mapper.DeviceMapper;
@Service
public class DeviceService {
	@Autowired
	private DeviceMapper deviceMapper;
	public Device getDeviceByDeviceID(String deviceID){
		DeviceExample deviceExample = new DeviceExample();
		Criteria criteria = deviceExample.createCriteria();
		criteria.andDeviceidEqualTo(deviceID);
		List<Device> list = deviceMapper.selectByExample(deviceExample);
		return list.get(0);
	}
	public void updateDeviceInfo(Device device){
		//关于updateByPrimaryKeySelective（不用更新所有属性）等的用法：
		//https://blog.csdn.net/paincupid/article/details/50921110
		deviceMapper.updateByPrimaryKeySelective(device);
	}
	/**获取数据库中device表信息*/
	public List<Device> getAllDevice() {
		return deviceMapper.selectByExample(null);
	}
	/**获取数据库中device表的deviceID信息*/
	public List<String> getAllDeviceID() {
		return deviceMapper.selectAllDeviceID();
	}
	/**
	 * 根据用户输入的信息模糊查询device信息*/
	public List<Device> selectByUserInput(String userInput) {
		return deviceMapper.selectByUserInput(userInput);
	}
	/**
	 * 获取在线设备的经纬度信息*//*
	public List<String> selectTudeAboutOnlineDevice(List<String> devicesList) {
		List<String> resultList = new ArrayList<String>();
		for(String device:devicesList){
			resultList.add(deviceMapper.selectTudeAboutOnlineDevice(device));
		}
		return resultList;
	}*/
}

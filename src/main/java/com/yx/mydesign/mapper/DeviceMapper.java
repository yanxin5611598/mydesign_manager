package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.DeviceExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    int countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
    
    List<Device> selectByUserInput(String userInput);
    List<String> selectAllDeviceID();
    /*//读取在线设备的经纬度信息,一个一个去查询
	String selectTudeAboutOnlineDevice(String device);*/
}
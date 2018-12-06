package com.yx.mydesign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.mydesign.bean.ReceiveData;
import com.yx.mydesign.bean.ReceiveDataExample;
import com.yx.mydesign.bean.ReceiveDataExample.Criteria;
import com.yx.mydesign.mapper.ReceiveDataMapper;
/**
 * 与上位机接收掉的下位机数据相关的业务逻辑代码*/
@Service
public class ReceiveDataService {
	@Autowired
	private ReceiveDataMapper receiveDataMapper;
	/**
	 * 根据id更新接收到的数据*/
	public void update(ReceiveData data){
		ReceiveDataExample example = new ReceiveDataExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeviceidEqualTo(data.getDeviceid());
		receiveDataMapper.updateByExample(data, example);
	}
	/**
	 * 根据deceiveID查询到一条数据*/
	public ReceiveData getReceiveByDeviceID(String deviceID){
		ReceiveDataExample example = new ReceiveDataExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeviceidEqualTo(deviceID);
		List<ReceiveData> list = receiveDataMapper.selectByExample(example);
		return list.get(0);
	}
	//将redis中的数据插入到MySQL
	public boolean insertDataFromRedis(String data) {
		String[] datas = data.split("\t");
		ReceiveData rdata = new ReceiveData();
		rdata.setDeviceid(datas[0]);
		rdata.setTempvalue(datas[1]);
		rdata.setHumvalue(datas[2]);
		rdata.setHchovalue(datas[3]);
		rdata.setPm25value(datas[4]);
		rdata.setPm10value(datas[5]);
		int insert = receiveDataMapper.insert(rdata);
		if(insert!=0){
			return true;
		}
		return false;
	}
}

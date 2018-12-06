package com.yx.mydesign.service.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.mydesign.bean.UserCheckResult;
import com.yx.mydesign.bean.UserCheckResultExample;
import com.yx.mydesign.bean.UserCheckResultExample.Criteria;
import com.yx.mydesign.mapper.UserCheckResultMapper;

/*处理与用户检测的结果数据相关的业务逻辑*/
@Service
public class UserCheckResultService {
	@Autowired
	private UserCheckResultMapper userCheckResultMapper;
	
	/**
	 * 增加数据
	 * */
	public int insert(UserCheckResult result){
		int insert = userCheckResultMapper.insert(result);
		System.out.println("insert:"+insert);
		return insert;
	}
	/**
	 * 更新数据*/
	public int update(UserCheckResult data,int id){
		int i = userCheckResultMapper.updateByPrimaryKey(data);
		System.out.println("update:"+i);
		return i;
	}
	/**
	 * 获取所有的数据*/
	public List<UserCheckResult> getAllData(){
		return userCheckResultMapper.getAllData();
	}
	/**
	 * 获取按照时间去重后的数据----即获取数据表中所有不同地点的最后一条数据
	 * */
	public List<UserCheckResult> getDataByAddress(){
		return userCheckResultMapper.getDataByAddress();
	}
	/**
	 * 按照gps_info的部分值获取集合（刑如地址:*****）
	 * */
	public List<String> getRankAndTimeList(String location){
		List<String> list = new ArrayList<String>();
		for(String str:userCheckResultMapper.getRankAndTimeList(location)){
			String[] split = str.split("\t");
			list.add(split[0]+"\t"+split[1].substring(0,4)+split[1].substring(5,7)+split[1].substring(8,10));
		}
		return list;
	}
	/**
	 * 根据用户名获取用户上传数据的记录*/
	public List<UserCheckResult> getDataByUsername(String username) {
		UserCheckResultExample example = new UserCheckResultExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		return userCheckResultMapper.selectByExample(example);
	}
	/**
	 * 根据设备ID、用户名、以及使用地点进行模糊查询*/
	public List<UserCheckResult> getDataByItemsLike(String deviceID,
			String username, String place) {
		return userCheckResultMapper.selectByItemsLike(deviceID,username,place);
	}
	public void deleteDataById(Integer id) {
		userCheckResultMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据id获取数据*/
	public UserCheckResult getResultByID(Integer id) {
		return userCheckResultMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据用户名查找记录，条件是除了用户名和图片信息的路径非空，其它都为空
	 * 为保证代码的鲁棒性，这里返回类型设置为List<UserCheckResult>,之后去最后一条即可
	 * */
	public List<UserCheckResult> getSpecialRecordByUsername(String username){
		UserCheckResultExample example = new UserCheckResultExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andAirrankIsNull().andChohIsNull().andContentevaluateIsNull().andDeviceinfoIsNull().andGpsinfoIsNull().andHumIsNull().andImageinfoIsNotNull().andImagepathIsNotNull().andPm10IsNull().andPm25IsNull().andTimeIsNull().andRoominfoIsNull();
		return userCheckResultMapper.selectByExample(example);
	}
	/**
	 * 根据用户名和时间信息获取雷达图到客户端*/
	public UserCheckResult getImageInfoByUsernameAndTime(String username,
			String time) {
		UserCheckResultExample example = new UserCheckResultExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andTimeEqualTo(time);
		return userCheckResultMapper.selectByExample(example).get(0);
	}
}

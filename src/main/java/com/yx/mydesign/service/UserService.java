package com.yx.mydesign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.mydesign.mapper.UserMapper;
import com.yx.mydesign.bean.User;
/**/
@Service
public class UserService {
	@Autowired 
	UserMapper userMapper;
	public List<User> getAllUser(){
		return userMapper.selectByExample(null);
	}
	/**根据用户名称进行模糊查询*/
	public List<User> searchLikeByUserName(User user) {
		List<User> result = new ArrayList<User>();
		List<User> userByNameList = userMapper.selectLikeByUserName(user);
		for (User u : userByNameList) {
			result.add(u);
		}
		return result;
	}
	/**
	 * 根据ID值获取到对应的user信息*/
	public User getUserById(Integer userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		if(user!=null){
			return user;
		}
		return null;
	}
	/**
	 * 根据ID值删除对应的user信息*/
	public void deleteUserById(Integer userid) {
		userMapper.deleteByPrimaryKey(userid);
	}
}

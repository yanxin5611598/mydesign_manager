package com.yx.mydesign.service.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.mydesign.bean.User;
import com.yx.mydesign.bean.UserExample;
import com.yx.mydesign.bean.UserExample.Criteria;
import com.yx.mydesign.mapper.UserMapper;

@Service
/*该service处理与用户前端用户相关的注册、登录等逻辑*/
public class UserClientService {
	@Autowired 
	private UserMapper userMapper;
	/*注册，返回结果码*/
	public Map<String, Object> userRegister(User user){
		Map<String, Object> result = new HashMap<String,Object>();
		//需要对数据库中的用户信息进行判断
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());//通过用户名进行查找
		List<User> userListByName = userMapper.selectByExample(example);
		if(userListByName.size()==0){
			//查询到的用户名对应数据库中的用户信息为空，说明数据库中不存在该用户，可以进行注册
			if(user.getUsername().equals("") || user.getAge().equals("") || user.getPassword().equals("")){
				result.put("result_code", 2);// 用户名，密码，年龄三项不能为空
			}
			else{
				user.setRewardpoint(0);
				user.setIsvip(0);//用户刚刚注册，身份是非会员
				userMapper.insert(user);
				System.out.println(user.getUsername()+"注册成功");
				result.put("result_code", 0);// 注册成功！请登录
			}
		}
		else{
			//已存在，不能注册
			result.put("result_code", 1);// 用户名已存在！请重新输入
		}
		return result;
	}
	/*登录信息，返回结果码，以及获取到的用户全部信息*/
	public Map<String, Object> userLogin(String username,String password) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);//通过用户名进行查找
		List<User> userListByName = userMapper.selectByExample(example);
		if(userListByName.size()==0){
			//数据库中没有该名称对应的用户信息
			resultMap.put("result_code", 2);
		}
		else{
			User userByName = userListByName.get(0);
			String passwordByName = userByName.getPassword();//数据库中的密码信息
			if(!passwordByName.equals(password)){
				//登录的时候密码信息错误
				resultMap.put("result_code", 1);
			}
			else{
				if(passwordByName.equals(password)){
					//密码正确
					resultMap.put("result_code", 0);
					resultMap.put("username", userByName.getUsername());
					resultMap.put("gender", userByName.getGender());
					resultMap.put("age", userByName.getAge());
					resultMap.put("phone", userByName.getPhone());
					resultMap.put("email", userByName.getEmail());
					resultMap.put("isVIP", userByName.getIsvip());
					resultMap.put("rewardPoint", userByName.getRewardpoint());
				}
			}
		}
		System.out.println("===========================");
		System.out.println(resultMap.get("result_code"));

		return resultMap;
	}
	/**
	 * 判断用户是否是VIP会员====在没有交费的情况下，默认是非会员===数据表中标识为0*/
	public User getUserByUsername(String username) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(userExample);
		return list.get(0);
	}
	/**
	 * 更新数据库中的用户信息,用户积分减2*/
	public int update(User user,Integer rewardPoint){
		user.setRewardpoint(user.getRewardpoint() - rewardPoint);
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		System.out.println("减少积分2个成功");
		return userMapper.updateByExample(user, example);
	}
	/**
	 * 更新数据库中的用户信息*/
	public int updateUser(User user){
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		System.out.println("更新用户成功");
		return userMapper.updateByExample(user, example);
	}
}

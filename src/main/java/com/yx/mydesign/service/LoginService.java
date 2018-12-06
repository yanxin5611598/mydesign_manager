package com.yx.mydesign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yx.mydesign.mapper.EquipManagerMapper;
import com.yx.mydesign.mapper.SuperManagerMapper;
import com.yx.mydesign.mapper.UserManagerMapper;
import com.yx.mydesign.bean.EquipManager;
import com.yx.mydesign.bean.EquipManagerExample;
import com.yx.mydesign.bean.SuperManager;
import com.yx.mydesign.bean.SuperManagerExample;
import com.yx.mydesign.bean.UserManager;
import com.yx.mydesign.bean.UserManagerExample;

/*这里采用的是声明式事务配置-------非注解的形式*/
@Service
public class LoginService {
	@Autowired(required=false)
	UserManagerMapper userManagerMapper;
	@Autowired
    @Qualifier("equipManagerMapper")
	EquipManagerMapper equipManagerMapper;
	@Autowired
    @Qualifier("superManagerMapper")
	SuperManagerMapper superManagerMapper;
	/**
	 * 判断当前登录的管理员是否在数据库中存在
	 * @param managerType:管理员类别
	 * @return true:存在  false:不存在
	 * */
	public boolean checkManager(String managerType,String name,String password){
		if(managerType.equals("管理员")){
			UserManagerExample example = new UserManagerExample();
			com.yx.mydesign.bean.UserManagerExample.Criteria createCriteria = example.createCriteria();//创建查询条件
			createCriteria.andUsermanagernameEqualTo(name);
			createCriteria.andUsermanagerpasswordEqualTo(password);
			List<UserManager> userManagerList = userManagerMapper.selectByExample(example);
			return userManagerList.size()==0?false:true;
		}
		else if(managerType.equals("设备管理员")){
			EquipManagerExample example = new EquipManagerExample();
			com.yx.mydesign.bean.EquipManagerExample.Criteria createCriteria = example.createCriteria();//创建查询条件
			createCriteria.andEquipmanagernameEqualTo(name);
			createCriteria.andEquipmanagerpasswordEqualTo(password);
			List<EquipManager> equipManagerList = equipManagerMapper.selectByExample(example);
			return equipManagerList.size()==0?false:true;
		}
		else{
			SuperManagerExample example = new SuperManagerExample();
			com.yx.mydesign.bean.SuperManagerExample.Criteria createCriteria = example.createCriteria();//创建查询条件
			createCriteria.andSupermanagernameEqualTo(name);
			createCriteria.andSupermanagerpasswordEqualTo(password);
			List<SuperManager> superManagerList = superManagerMapper.selectByExample(example);
			return superManagerList.size()==0?false:true;
		}
	}
}

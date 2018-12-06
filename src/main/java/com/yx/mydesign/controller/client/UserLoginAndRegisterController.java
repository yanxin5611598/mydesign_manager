package com.yx.mydesign.controller.client;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yx.mydesign.bean.User;
import com.yx.mydesign.service.client.UserClientService;
/**
 * 该类用于处理与安卓客户端用户相关的登录和注册的后台操作*/
@Controller
@RequestMapping(value="/userlogin_register")
public class UserLoginAndRegisterController {
	@Autowired
	private UserClientService userClientService;
	@RequestMapping(value="/register")
	@ResponseBody
	/*处理安卓客户端的注册请求，并设置以json数据格式返回结果信息*/
	public Map<String,Object> register(@ModelAttribute User user){
		System.out.println(user.getUsername()+":"+user.getPassword());
		//正确进入该请求之后，对客户端用户输入的用户名等信息进行后台的注册逻辑
		Map<String,Object> result = userClientService.userRegister(user);
		System.out.println(result);
		return result;
	}
	@RequestMapping(value="/login")
	@ResponseBody
	/*处理安卓客户端的登录请求，并设置以json数据格式返回结果信息*/
	public Map<String, Object> login(@RequestParam("username")String username,@RequestParam("password")String password){
		System.out.println("login");
		System.out.println(username+":"+password);
		//正确进入该请求之后，对客户端用户输入的用户名等信息进行后台的登录逻辑
		Map<String, Object> userresult = userClientService.userLogin(username,password);
//		Gson gson = new Gson();
		//此外，还需要获取到设备相关的信息
		// 封装设备的使用情况信息，发送到手机端
//		List<Map<String, Object>> list = equipmentClientService.getEquipmentUse();
//		list.add(userresult);
//		System.out.println(list);
		/*String jsonList = gson.toJson(list+userresult);
		System.out.println(userresult);*/
		return userresult;
	}
}

package com.yx.mydesign.controller.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yx.mydesign.service.LoginService;

@Controller
public class LoginOrModifyController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginOrModifyController.class);
	@Autowired 
	private LoginService loginService;
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置一个Session，用于保存当前登录成功的用户名。。。。以在页面进行显示
//		System.out.println("come in login controller");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String managername = request.getParameter("managername").trim();//用户名
		String password = request.getParameter("password").trim();//密码
		String managerGroup = request.getParameter("managerGroup");//管理员类别
		boolean ismanagerValid = loginService.checkManager(managerGroup, managername, password);
		if(ismanagerValid){
			//数据库中存在
			session.setAttribute("managerName", managername);
			session.setAttribute("managerGroup", managerGroup);
//			if(managerGroup.equals("管理员")){
			return "managermain";//*****man**.jsp
//			}
			/*else if(managerGroup.equals("设备管理员")){
				return "equipmanagermain";
			}
			else return "supermanagermain";*/
		}
		else{
			request.setAttribute("error", "用户不存在或密码错误!");
			return "mylogin";
		}
	}
	/**
	 * 前台页面请求查看传感器参数的实时显示图---做一个重定向*/
	@RequestMapping("/viewSensorData")
	public String viewSensorData(HttpServletRequest request,Model model){
		String deviceID = request.getParameter("deviceID");
		String username = request.getParameter("username");
		//关闭连接
		System.out.println("deviceID="+deviceID);
		System.out.println("username="+username);
		model.addAttribute("deviceID", deviceID);
		model.addAttribute("username", username);
		return "display";
	}
}

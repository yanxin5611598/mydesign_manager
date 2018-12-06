package com.yx.mydesign.controller.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yx.mydesign.bean.User;
import com.yx.mydesign.bean.UserOrder;
import com.yx.mydesign.service.client.UserOrderService;
import com.yx.mydesign.service.client.UserClientService;

/**
 * 处理与订单信息相关的Controller
 * */
@Controller
@RequestMapping("/userorder")
public class UserOrderController {
	@Autowired
	private UserClientService userClientService;
	@Autowired
	private UserOrderService orderService;
	@RequestMapping("saveOrderAndAddPoint")
	@ResponseBody
	public Map<String, Object> saveOrderAndAddPoint(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("saveOrderAndAddPoint comein");
		//首先获取用户名和需要充值的积分个数
		String username = request.getParameter("username");
		Integer pointNums = Integer.parseInt(request.getParameter("pointNums"));
		String outtradeno = request.getParameter("outtradeno");
		String tradename = request.getParameter("tradename");
		String amount = request.getParameter("amount");
		User user = userClientService.getUserByUsername(username);
		if(user == null || pointNums<=0){
			map.put("result_code", 0);//失败
			return map;
		}
		//首先将用户的积分做增加操作
		user.setRewardpoint(user.getRewardpoint()+ pointNums);
		int i = userClientService.updateUser(user);
		if(i == 0){
			map.put("result_code", -1);//更新用户积分失败
			return map;
		}
		//然后生成该用户的一个新订单
		UserOrder order = new UserOrder();
		order.setUsername(username);
		order.setTradename(tradename);
		order.setOuttradeno(outtradeno);
		order.setAmount(amount);
		order.setTime(new Date());
		int j = orderService.addOrder(order);
		if(j == 0){
			map.put("result_code", -2);//新增用户订单失败
			return map;
		}
		map.put("result_code", 1);//成功
		return map;
	}
	@ResponseBody
	@RequestMapping("/generateVIPOrder")
	public Map<String, Object> generateVIPOrder(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("generateVIPOrder comein");
		String username = request.getParameter("username");
		String outtradeno = request.getParameter("outtradeno");
		String tradename = request.getParameter("tradename");
		String amount = request.getParameter("amount");
		System.out.println("amount============================="+amount);
		User user = userClientService.getUserByUsername(username);
		if(user == null || outtradeno==null || tradename==null || amount==null){
			map.put("result_code", 0);//失败
			return map;
		}else{
			//更新数据库中的用户的VIP状态
			user.setIsvip(1);
			userClientService.updateUser(user);
		}
		//生成该用户的一个VIP订单
		UserOrder order = new UserOrder();
		order.setUsername(username);
		order.setTradename(tradename);
		order.setOuttradeno(outtradeno);
		order.setAmount(amount);
		order.setTime(new Date());
		int j = orderService.addOrder(order);
		if(j == 0){
			map.put("result_code", -2);//新增用户VIP订单失败
			return map;
		}
		map.put("result_code", 1);//新增用户VIP成功
		return map;
	}
}

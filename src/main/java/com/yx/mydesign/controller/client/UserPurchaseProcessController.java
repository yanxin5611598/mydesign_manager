package com.yx.mydesign.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yx.mydesign.bean.Purchase;
import com.yx.mydesign.service.client.PurchaseClientService;

/**该类用于处理与订单相关的后台处理操作*/
@Controller
@RequestMapping("/userpurchase")
public class UserPurchaseProcessController{
	@Autowired
	private PurchaseClientService purchaseClientService;
	@RequestMapping("prepurchase")
	@ResponseBody
	/**用于处理用户在执行<开始检测>的时候对订单信息查询的反馈====返回服务器中是否有当前用户的未完成订单(订单没有结束时间就说明是未完成的)*/
	public Map<String, Object> prePurchase(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		List<Purchase> list = purchaseClientService.getUnfinishPurchaseByUsername(username);
		HashMap<String, Object> result = new HashMap<String, Object>();
		//未完成的订单数为0的时候返回result_code为0,否则为1
		result.put("result_code", (list.size()==0)?0:1);
		System.out.println(result+"ByServer");
		return result;
	}
	@RequestMapping("postpurchase")
	@ResponseBody
	/**用于处理用户在检测完毕之后或者再此次订单发起之前有未完成的订单，对这些未完成的订单进行最终金额的计算(相当于是给未完成的订单添加结束时间，使之成为完整的订单，进而进行支付)*/
	public Map<String, Object> postPurchase(HttpServletRequest request,HttpServletResponse response){
		System.out.println("完成订单的Controller");
		Map<String, Object> result = new HashMap<String, Object>();
		Purchase purchase = purchaseClientService.getUnfinishPurchaseByUsername(request.getParameter("username")).get(0);
		System.out.println(purchase.getUsername());
		//执行更新订单信息的操作====这里是根据订单的用户更新订单的结束时间
		int rowByUpdate = purchaseClientService.updatePurchase(purchase);
		if(rowByUpdate != 0){
			System.out.println("更新成功");
			//受影响的行数为1行===说明更新成功
			//返回更新后该订单的一些信息，包括订单对象中的起始时间、结束时间以及消费规则cost中的起始价格、每小时的价格
			result = purchaseClientService.getPurchaseAndCostInfosToPay(purchase);
		}
		else{
			System.out.println("更新失败");
		}
		return result;
	}
	@RequestMapping("/addstoptimetobeforepurchase")
	@ResponseBody
	/**
	 * 用户在检测完毕之后提交用户名*/
	public Map<String,Object> addStoptimeToBeforePurchase(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String, Object>();
		
		
		return result;
	}
}

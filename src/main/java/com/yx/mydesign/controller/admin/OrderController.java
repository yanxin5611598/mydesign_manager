package com.yx.mydesign.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.mydesign.service.client.UserOrderService;
import com.yx.mydesign.bean.UserOrder;

@Controller
public class OrderController {
	@Autowired
	UserOrderService orderService;
	@RequestMapping(value="/getAllOrder",method=RequestMethod.GET)
	public String getAllPurchase(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			Model model){
		PageHelper.startPage(pageNumber, 6);//每行显示6条数据
		List<UserOrder> orderList = orderService.getAllOrder();
		PageInfo<UserOrder> page = new PageInfo<UserOrder>(orderList);
		model.addAttribute("page", page);
		return "order/orderList";
	}
	/**
	 * 查询
	 */
	@RequestMapping("/orderSearch")
	public String search(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model,@ModelAttribute UserOrder order) {
		System.out.println(order.getUsername());
		PageHelper.startPage(pageNumber, 6);
		List<UserOrder> orderList = orderService.searchByPage(order);
		PageInfo<UserOrder> page = new PageInfo<UserOrder>(orderList);
		model.addAttribute("page", page);
		System.out.println("查询到的条数："+ orderList.size());
		return "order/orderList";
	}
}

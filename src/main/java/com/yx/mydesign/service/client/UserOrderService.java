package com.yx.mydesign.service.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.mydesign.bean.UserOrder;
import com.yx.mydesign.mapper.UserOrderMapper;
//订单信息相关的业务逻辑类
@Service
public class UserOrderService {
	@Autowired
	private UserOrderMapper userOrderMapper;
	public int addOrder(UserOrder order){
		return userOrderMapper.insert(order);
	}
	/**
	 * 获取数据库中的所有订单信息*/
	public List<UserOrder> getAllOrder(){
		return userOrderMapper.selectByExample(null);
	}
	public List<UserOrder> searchByPage(UserOrder order) {
		List<UserOrder> result = new ArrayList<UserOrder>();
		List<UserOrder> orderList = userOrderMapper.selectByPage(order);
		for (UserOrder o : orderList) {
			result.add(o);
		}
		return result;
	}
	/**
	 * 根据月份得到所有订单金额*/
	public Integer getPricesByMonth(int month) {
		return userOrderMapper.getPriceSumsByMoth(month);
	}
	/**
	 * 根据月份得到积分订单金额*/
	public Integer getRepointPricesByMonth(int month) {
		return userOrderMapper.getPriceRepointSumsByMonth(month);
	}
	/**
	 * 根据月份得到VIP订单金额*/
	public Integer getVIPPricesByMonth(int month) {
		return userOrderMapper.getPriceVIPSumsByMonth(month);
	}
}

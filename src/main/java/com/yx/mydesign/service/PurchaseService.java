package com.yx.mydesign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*订单相关的查询、等处理操作*/







import com.yx.mydesign.mapper.PurchaseMapper;
import com.yx.mydesign.bean.Purchase;
@Service
public class PurchaseService {
	@Autowired
	PurchaseMapper purchaseMapper;
	/**
	 * 用于获取到数据库中的所有订单信息
	 * @return 订单信息集合*/
	public List<Purchase> getAllPurchase(){
		return purchaseMapper.selectByExample(null);
	}
	public Integer getPricesByMonth(int monthValue) {
		Integer prices = purchaseMapper.getPriceSumByMonth(monthValue);
		System.out.println(prices);
		return prices;
	}
	public List<Purchase> searchByPage(Purchase purchase) {
		List<Purchase> result = new ArrayList<Purchase>();
		List<Purchase> purchaseList = purchaseMapper.selectByPage(purchase);
		for (Purchase pc : purchaseList) {
			result.add(pc);
		}
		return result;
	}
}

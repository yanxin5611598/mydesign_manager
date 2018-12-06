package com.yx.mydesign.service.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.mydesign.bean.Purchase;
import com.yx.mydesign.bean.PurchaseExample;
import com.yx.mydesign.bean.PurchaseExample.Criteria;
import com.yx.mydesign.mapper.PurchaseMapper;

@Service
/**用于处理安卓端对于订单的请求处理逻辑*/
public class PurchaseClientService {
	@Autowired 
	private PurchaseMapper purchaseMapper;
	/**
	 * 获取当前请求的安卓用户是否有未完成的订单集合===其实正常情况下某一个用户只可能存在0个或者1个未完成的订单数*/
	public List<Purchase> getUnfinishPurchaseByUsername(String username){
		PurchaseExample example = new PurchaseExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andStoptimeIsNull();
		List<Purchase> list = purchaseMapper.selectByExample(example);
		System.out.println("当前用户======="+username+"未完成地额订单数::::::::"+list.size());
		return list;
	}
	/**
	 * 插入记录到purchase中===注意，这里分为两步，年月日时分秒
	 * 首先是安卓用户开始检测提交用户名信息，后台保存期用户名信息和起始时间
	 * 然后用户结束检测，后台将保存其结束时间信息*/
	public Map<String, Object> inserDataToPurchase(String username, Date starttime, Date stoptime) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		Purchase purchase = new Purchase();
		purchase.setUsername(username);
		if(starttime!=null){
			//开始检测===保存用户名和开始检测时间信息
			purchase.setStarttime(starttime);
		}else{
			purchase.setStarttime(null);
		}
		if(stoptime!=null){
			//开始检测===保存用户名和开始检测时间信息
			purchase.setStoptime(stoptime);
		}else{
			purchase.setStoptime(null);
		}
		purchaseMapper.insert(purchase);
		result.put("result_code", "载入中");
		System.out.println(result);
		return result;
	}
	/**更新订单信息*/
	public int updatePurchase(Purchase purchase) {
		// TODO Auto-generated method stub
		return purchaseMapper.updateStopTimeByUserName(purchase);
	}
	/**返回更新后该订单的一些信息，
	 * 包括订单对象中的起始时间、结束时间以及消费规则cost中的起始价格、每小时的价格
	 * ===注意：返回的数据以List集合形式表示。*/
	public Map<String, Object> getPurchaseAndCostInfosToPay(Purchase purchase) {
		// TODO Auto-generated method stub
//		Map<String,Object> maptest = new HashMap<String,Object>();
		Map<String, Object> result = purchaseMapper.getPurchaseAndCostInfosToPay(purchase).get(0);
		//由于每一次查询出来的list，实际上只有一条数据，因此这里直接将其
		System.out.println(result.get("startprice")+"<===>"+result.get("perhourprice")+"<===>"+result.get("starttime"));
		/*for(int i = 0;i<list.size();i++){
			maptest = list.get(i);
			System.out.println("startprice===="+maptest.get("startprice"));
			System.out.println("perhourprice===="+maptest.get("perhourprice"));
			System.out.println(maptest.get("starttime"));
			System.out.println(maptest.get("stoptime"));
		}*/
		result.put("result_code", 1);
//		list.add(maptest);
		System.out.println("支付测试");
		return result;
	}
}

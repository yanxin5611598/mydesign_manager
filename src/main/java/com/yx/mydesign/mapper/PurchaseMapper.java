package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.Purchase;
import com.yx.mydesign.bean.PurchaseExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PurchaseMapper {
    int countByExample(PurchaseExample example);

    int deleteByExample(PurchaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    List<Purchase> selectByExample(PurchaseExample example);

    Purchase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Purchase record, @Param("example") PurchaseExample example);

    int updateByExample(@Param("record") Purchase record, @Param("example") PurchaseExample example);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);
    
    int updateStopTimeByUserName(Purchase purchase);
    
    int getPriceSumByMonth(Integer monthValue);
    //按照页查询出订单信息
	List<Purchase> selectByPage(Purchase purchase); 
	//查询订单对象中的起始时间、结束时间以及消费规则cost中的起始价格、每小时的价格
	List<Map<String,Object>> getPurchaseAndCostInfosToPay(Purchase purchase);
}
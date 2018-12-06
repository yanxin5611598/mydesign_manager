package com.yx.mydesign.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.service.client.UserOrderService;
import com.yx.mydesign.utils.DeviceToDisplayChart;
import com.yx.mydesign.utils.EChartData;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.utils.Series;

@Controller
public class ChartController{
	@Autowired
	private UserOrderService orderService;
	public static Jedis jedis = JedisPoolUtils.getJedis(); 
	String price = "";
	@RequestMapping(value="/orderinfodataplay")
	@ResponseBody
	public EChartData orderinfodataplay(HttpServletRequest request){
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"月订单金额"}));//数据分组  
        List<String> category = new ArrayList<String>(Arrays.asList(new String []{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"}));//横坐标  
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
        for(int i = 1; i < 13 ; i++){
        	price = orderService.getPricesByMonth(i).toString();//数据库中的价格信息
        	System.out.println(price);
        	dataList.add(price);
        }
        series.add(new Series("订单金额", "line", dataList));
        
        EChartData data = new EChartData(legend, category, series);
		return data;
	}
	@RequestMapping(value="/orderinforepointdataplay")
	@ResponseBody
	public EChartData orderinforepointdataplay(HttpServletRequest request){
		System.out.println("orderinforepointdataplay");
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"月订单金额"}));//数据分组  
        List<String> category = new ArrayList<String>(Arrays.asList(new String []{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"}));//横坐标  
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
        for(int i = 1; i < 13 ; i++){
        	price = orderService.getRepointPricesByMonth(i).toString();//数据库中的价格信息
        	System.out.println(price);
        	dataList.add(price);
        }
        series.add(new Series("订单金额", "line", dataList));
        
        EChartData data = new EChartData(legend, category, series);
		return data;
	}
	@RequestMapping(value="/orderinfovipdataplay")
	@ResponseBody
	public EChartData orderinfovipdataplay(HttpServletRequest request){
		System.out.println("orderinfovipdataplay");
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"月订单金额"}));//数据分组  
        List<String> category = new ArrayList<String>(Arrays.asList(new String []{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"}));//横坐标  
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
        for(int i = 1; i < 13 ; i++){
        	price = orderService.getVIPPricesByMonth(i).toString();//数据库中的价格信息
        	System.out.println(price);
        	dataList.add(price);
        }
        series.add(new Series("订单金额", "line", dataList));
        
        EChartData data = new EChartData(legend, category, series);
		return data;
	}
	@RequestMapping(value="/deviceInfoDisplay")
	public DeviceToDisplayChart deviceInfoDisplay(){
		System.out.println("deviceInfoDisplay");
		@SuppressWarnings("unused")
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"温度","湿度","甲醛","PM2.5","PM10"}));//数据分组  
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
        //TODO 做测试。先写死
        @SuppressWarnings("unused")
		String deviceNums = "1";
        List<String> deviceInfoList = new ArrayList<String>();
        deviceInfoList.add("1111");
        for(int i = 1; i < 13 ; i++){
        	price = orderService.getVIPPricesByMonth(i).toString();//数据库中的价格信息
        	System.out.println(price);
        	dataList.add(price);
        }
        series.add(new Series("订单金额", "line", dataList));
		DeviceToDisplayChart data/* = new DeviceToDisplayChart(legend, series, deviceInfoList)*/ = null;
		return data;
	}
	@RequestMapping("/nodeinfodataplay")
	//返回的是json数据
	@ResponseBody
	public EChartData nodeInfoDataPlay(HttpServletRequest request){
		//EChartData data = new EChartData(legend, xcategory, yseries);
		return null;
	}
	/*
	 * 实时从redis中获取在线设备的数据*/
	@RequestMapping("/getRuntimeData")
	@ResponseBody
	public Map<String, Object> getRuntimeData(@RequestParam(value="item")String item){
		Map<String,Object> map = new HashMap<String, Object>();
		//从redis中读取设备信息（通过判断当前redis中存储的设备在线的信息）
        while(jedis.get(item.trim()) != null){
        	//redis中有该设备信息---那么就将该键所对应的值返回给前端
//        	StringBuffer sb = new StringBuffer();
        	String data = jedis.get(item.trim());
        	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        	Date date = new Date();
        	map.put("dateTime",format.format(date));
        	map.put("data", data);
    		break;
        }
		return map;
	}
}

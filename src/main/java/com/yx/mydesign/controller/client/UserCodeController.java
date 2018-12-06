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

import redis.clients.jedis.Jedis;

import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.User;
import com.yx.mydesign.service.client.DeviceService;
import com.yx.mydesign.service.client.UserClientService;
import com.yx.mydesign.service.client.UserCodeService;
import com.yx.mydesign.utils.JedisPoolUtils;

/*
 * 用户扫描了二维码之后，会将用户自己的唯一标示用户名、车辆信息（如车辆编号等）、
 * 当前设备的GPS信息（可以通过读取手机的GPS信息得知）
 * 一并发送给后台，后台收到请求后，首先会对用户信息进行验证，判断其是否是VIP（即已交押金会员），
 * 		如果不是，返回给APP显示，提示其交会员费；
 * 		如果是，后台服务器在更新设备的GPS信息、用户相关信息、以及使用时间等后，将下位机的检测结果信息返回给APP显示*/
@Controller
@RequestMapping(value="/usercode")
public class UserCodeController {
	@Autowired
	private UserCodeService userCodeService;
	@Autowired
	private UserClientService userClientService;
	@Autowired
	private DeviceService deviceService;
	@RequestMapping("/getSenorData")
	@ResponseBody
	/**
	 * 处理用户安卓端扫描二维码的请求*/
	public Map<String, Object> getSenorData(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		String username = request.getParameter("username");
		String GPSInfo = request.getParameter("gps_info");
		String deviceID = request.getParameter("device_info");
		if(GPSInfo == "" || GPSInfo.equals("")){
			result.put("result_code", 0);
			return result;
		}
		User user = userClientService.getUserByUsername(username);
		//首先根据二维码中设备的ID,查询出对应的设备
		Device device = deviceService.getDeviceByDeviceID(deviceID);
		//将数据表中的GPS信息、用户相关信息、以及使用时间等信息更新
		System.out.println("gps_result=================="+GPSInfo);
		if(device.getPlacecurrent() != GPSInfo){
			device.setPlacecurrent(GPSInfo);
		}
		device.setTimecurrent(new Date());
		device.setUsername(user.getUsername());
		device.setPhone(user.getPhone());
		deviceService.updateDeviceInfo(device);
		System.out.println("更新设备表成功");
		result.put("result_code", 1);
		//获取下位机返回的数据并返回给APP
//			String data = "";
//			MySocketServer myss = new MySocketServer();
		/*do {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while ((data = myss.getInfo())!=null || (data=myss.getInfo())!="");*/
//			ReceiveData data = receiveDataService.getReceiveByDeviceID(deviceID);
		Jedis jedis = JedisPoolUtils.getJedis();
		String data = "";
		while(jedis.exists(deviceID)){
			//如果不存在，则一直循环直到redis中保存了下位机的数据
			data = jedis.get(deviceID);
			break;
		}
		
		System.out.println("info============================="+data);
		//将redis中保存的该key删除掉
		jedis.del(deviceID);
		result.put("result_info", data);
		return result;
	}
}

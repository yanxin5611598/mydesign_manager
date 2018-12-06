package com.yx.mydesign.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import redis.clients.jedis.Jedis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.mydesign.service.client.DeviceService;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.child.DeviceState;

/*处理与设备相关的逻辑*/
@Controller
public class DeviceController {
	@Autowired 
	DeviceService deviceService;
	Jedis jedis = JedisPoolUtils.getJedis();
	@RequestMapping(value="/getAllDevice")
	public String getAllDevice(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model){
		System.out.println("pageNumber========"+pageNumber);
		//指定默认的参数值  起始的查询页数为1
		//引入分页查询插件PageHelper   需要传入页码和每页的结果数
		PageHelper.startPage(pageNumber, 6);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Device> deviceList = deviceService.getAllDevice();
		//使用PageInfo对结果进行包装
		//封装了详细的分页信息,将其传递给页面,在页面进行显示即可
		PageInfo<Device> pageInfo = (PageInfo<Device>)new PageInfo<Device>(deviceList);//显示的指定连续显示的页数为5页
		model.addAttribute("page",pageInfo);
		return "/device/deviceList";
	}
	@RequestMapping(value="/getAllDeviceState")
	public String getAllDeviceState(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model){
		//指定默认的参数值  起始的查询页数为1
		//引入分页查询插件PageHelper   需要传入页码和每页的结果数
		PageHelper.startPage(pageNumber, 6);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<DeviceState> deviceIDList = new ArrayList<DeviceState>();//保存了设备ID以及其在线或离线状态
		
		for(String deviceID:deviceService.getAllDeviceID()){
			DeviceState ds = new DeviceState();
			ds.setDeviceID(deviceID);
			//去redis中查找是否有相关的键值对
			if(jedis.get(deviceID)!=null){
				ds.setDeviceState("1");//在线
			}else{
				ds.setDeviceState("0");//li线
			}
			System.out.println(ds.getDeviceID()+"===="+ds.getDeviceState());
			deviceIDList.add(ds);
		}
		//使用PageInfo对结果进行包装
		//封装了详细的分页信息,将其传递给页面,在页面进行显示即可
		PageInfo<DeviceState> pageInfo = (PageInfo<DeviceState>)new PageInfo<DeviceState>(deviceIDList);//显示的指定连续显示的页数为5页
		model.addAttribute("page",pageInfo);
		return "/device/deviceState";
	}
	@RequestMapping("/deviceSearch")
	public String deviceSearch(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model,HttpServletRequest request){
		String userInput = request.getParameter("userinput");
		System.out.println(userInput);
		//引入分页查询插件PageHelper   需要传入页码和每页的结果数
		PageHelper.startPage(pageNumber, 6);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Device> deviceList = deviceService.selectByUserInput(userInput);
		System.out.println("符合要求的设备信息数量："+deviceList.size());
		//使用PageInfo包装结果
		PageInfo<Device> pageInfo = new PageInfo<Device>(deviceList);
		model.addAttribute("page",pageInfo);
		return "/device/deviceList";
	}
}
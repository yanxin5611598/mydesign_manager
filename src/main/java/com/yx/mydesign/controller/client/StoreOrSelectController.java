package com.yx.mydesign.controller.client;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.User;
import com.yx.mydesign.bean.UserCheckResult;
import com.yx.mydesign.controller.client.websocket.WebSocketHandler;
import com.yx.mydesign.service.client.DeviceService;
import com.yx.mydesign.service.client.StoreOrSelectService;
import com.yx.mydesign.service.client.UserCheckResultService;
import com.yx.mydesign.service.client.UserClientService;
import com.yx.mydesign.utils.EChartData;
import com.yx.mydesign.utils.JedisPoolUtils;
import com.yx.mydesign.utils.Series;
import com.yx.terminate.MyGPRSServer;

/*
 * 处理用户于服务器的数据库之间的交互的Controller(存储或者查询、上传或者下载)*/
@Controller
@RequestMapping("/userUpDown")
public class StoreOrSelectController {
	@Autowired
	private UserCheckResultService userCheckResultService;
	@Autowired
	private UserClientService userClientService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private StoreOrSelectService storeOrSelectService;
	private Jedis jedis;
	private String temList = null;
	private String humList = null;
	private String chohList = null;
	private String pm25List = null;
	private String pm10List = null;
//	private String co2List = null;
	private String timeList = null;
	private String rangeList = null;
	@ResponseBody
	@RequestMapping("/uploadCheckData")
	public Map<String, Object> uploadCheckData(HttpServletRequest request,HttpServletResponse response){
		System.out.println("uploadCheckData is come in");
		//用户上传数据时应该讲手机状态置为0
		jedis = JedisPoolUtils.getJedis();
		jedis.del("phoneState");//手机端不再请求传感器数据
		MyGPRSServer.flag = false;
		System.out.println("手机端不再请求传感器数据");
		Map<String,Object> map = new HashMap<String, Object>();
		String result = request.getParameter("data");
		String roomNum = request.getParameter("roomNum");
		String contentEvaluate = request.getParameter("contentEvaluate");
		if(result==null || result ==""){
			map.put("upload_middle_code", 0);//请求数据错误
		}
		System.out.println(result);
		/*
		 * 
		 * tem=25.0, hum=55.0, choh=0.011, pm25=10.9, pm10=13.2, range=良, 
		 * username=yanxin, gpsInfo=地址:四川省成都市郫都区精勤路999号靠近信息科学与技术学院
		 *, deviceInfo=1111, time=2018年04月26日 15:51:18*/
		String[] splits = result.split(",");
		//在webview中，用户可能已经保存了图片信息，那么首先需要找到这条记录，然后插入其他数据。找不到说明没有保存，新建插入即可
		List<UserCheckResult> list = userCheckResultService.getSpecialRecordByUsername(splits[6]);
		for(UserCheckResult u:list){
			System.out.println(u.toString());
		}
		UserCheckResult data = null;
		if(list.size() != 0){
			data = list.get(list.size() - 1);//取出最后一条数据
		}else{
			data = new UserCheckResult();
			data.setUsername(splits[6]);
			//用户没有保存雷达图信息，所以置为空
		}
		data.setTem(splits[0]);
		data.setHum(splits[1]);
		data.setChoh(splits[2]);
		data.setPm25(splits[3]);
		data.setPm10(splits[4]);
		data.setAirrank(splits[5]);
		data.setGpsinfo(splits[7]);
		data.setDeviceinfo(splits[8]);
		data.setTime(splits[9]);
		data.setRoominfo(roomNum);
		data.setContentevaluate(contentEvaluate);
		System.out.println(data.toString());
		int i = userCheckResultService.update(data, data.getId());
		if(i!=0){
			map.put("result_code", "1");//上传成功
		}
		else{
			map.put("result_code", "0");//上传失败
		}
		return map;
	}
	@RequestMapping("/uploadParamList")
	@ResponseBody
	public Map<String,Object> uploadParamList(HttpServletRequest request,HttpServletResponse response){
		System.out.println("come in uploadParamList");
		//关闭websocket连接
		WebSocketHandler.flag = false;
		//关闭Socket客户端
		MyGPRSServer.flag = false;
		Map<String,Object> map = new HashMap<String, Object>();
		String deviceID = request.getParameter("deviceID").trim();
		System.out.println("uploadParamList deviceID="+deviceID);
		//将在线设备中的value为deviceID的删除
		jedis = JedisPoolUtils.getJedis();
        jedis.srem("onlineDevice", deviceID);
		temList = request.getParameter("temList");
		humList = request.getParameter("humList");
		chohList = request.getParameter("chohList");
		pm25List = request.getParameter("pm25List");
		pm10List = request.getParameter("pm10List");
		timeList = request.getParameter("timeList");
		rangeList = request.getParameter("rankList");
//		co2List = request.getParameter("co2List");
		System.out.println("temList="+temList);
		System.out.println("timeList="+timeList);
		System.out.println("humList="+humList);
		System.out.println("chohList="+chohList);
		System.out.println("pm25List="+pm25List);
		System.out.println("pm10List="+pm10List);
		System.out.println("rangeList="+rangeList);
		//TODO 沒有加co2的判斷
		if(timeList == null || temList == null || humList == null || chohList == null || pm25List == null || pm10List == null || rangeList == null){
			map.put("result_code", "0");//数据上传有问题
		}
		else{
			map.put("result_code", "1");//上传成功
		}
		return map;
	}
	@RequestMapping("/viewPublicData")
	@ResponseBody
	public Map<String, Object> viewPublicData(HttpServletRequest request,HttpServletResponse response){
		System.out.println("viewPublicData is come in");
		Map<String,Object> map = new HashMap<String, Object>();
		String username = request.getParameter("username");
		Integer requestNum = Integer.parseInt(request.getParameter("request_num"));
		if(username==null || username ==""){
			map.put("view_middle_code", 0);//请求数据错误
		}else{
			map.put("view_middle_code", 1);//请求数据正确
			System.out.println(username);
			//根据用户名获取MySQL中的用户信息
			User user = userClientService.getUserByUsername(username);
			//请求次数加一
			user.setRequestviewnum(requestNum);
			userClientService.updateUser(user);
			if(user.getRewardpoint()<2){
				//用户当前的积分不足以查看共享平台的数据
				map.put("result_code", 0);
			}
			else{
				//用户当前的积分足以查看数据
				map.put("result_code", 1);
				//获取数据
				List<UserCheckResult> dataList = userCheckResultService.getDataByAddress();
				System.out.println("第"+user.getRequestviewnum()+"次");
				if(user.getRequestviewnum()==0){
					//用户是第一次请求数据
					//扣除积分，
					userClientService.update(user, 2);//积分减少2个
				}
				map.put("result_info", dataList);
			}
		}
		return map;
	}
	/**
	 * 根据地址返回所有的数据集合
	 * */
	@RequestMapping("/getRankAndTimeList")
	@ResponseBody
	public Map<String,Object> getRankAndTimeList(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String location = request.getParameter("address");
		System.out.println("location:"+location);
		if(location == null || location.equals("")){
			map.put("result_code", 0);
		}else{
			List<String> list = userCheckResultService.getRankAndTimeList(location.trim());
			for(String str:list){
				System.out.println(str);
			}
			if(list.size() <= 1){
				map.put("result_code", 0);
			}else{
				map.put("result_code", 1);
				map.put("result_info", list);
			}
		}
		return map;
	}
	/**
	 * 根据用户名和时间信息获取雷达图到客户端*/
	@RequestMapping("/getImageInfo")
	public void getImageInfo(HttpServletRequest request,HttpServletResponse response){
		System.out.println("come in getImageInfo method===="+request.getParameter("username"));
		//首先根据用户名和时间信息获取到对应的图片
		String imagePath = userCheckResultService.getImageInfoByUsernameAndTime(request.getParameter("username"),request.getParameter("time")).getImagepath();
		if(imagePath == null){
			return;
		}
		System.out.println(imagePath);
		System.out.println(request.getParameter("time"));
		FileInputStream in;
		OutputStream out;
		try {
			in = new FileInputStream(imagePath);
			out = response.getOutputStream();
			int a;
			while((a = in.read()) != -1){
				out.write(a);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库中的历史数据记录*/
	@ResponseBody
	@RequestMapping("/getHistoryData")
	public Map<String,Object> getHistoryData(HttpServletRequest request){
		System.out.println("getHistoryData is come in");
		Map<String,Object> result = new HashMap<String,Object>();
		String username = request.getParameter("username");
		List<UserCheckResult> dataList = userCheckResultService.getDataByUsername(username); 
		if(dataList.size()>0){
			result.put("result_info", dataList);
		}
		return result;
	}
	/**
	 * 用户积分做增加操作*/
	@ResponseBody
	@RequestMapping("/addUserPoint")
	public Map<String,Object> addUserPoint(HttpServletRequest request){
		System.out.println("addUserPoint is come in");
		Map<String,Object> result = new HashMap<String,Object>();
		String username = request.getParameter("username");
		Integer point = Integer.parseInt(request.getParameter("pointnums"));
		User user = userClientService.getUserByUsername(username);
		user.setRewardpoint(user.getRewardpoint()+point);
		int i = userClientService.updateUser(user);
		if(i!=0){
			result.put("result_code",1);//成功
			result.put("reward_point", user.getRewardpoint());
		}
		else{
			result.put("result_code",0);//失败
		}
		return result;
	}
	@RequestMapping("/getDeviceInfo")
	@ResponseBody
	public Map<String,Object> getDeviceInfo(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		//获取数据库中的设备相关的信息并返回给android
		List<Device> list = deviceService.getAllDevice();
		result.put("result_info", list);
		return result;
	}
	@RequestMapping("/generateRadarChart")
	@ResponseBody
	public EChartData generateRadarChart(HttpServletRequest request){
		System.out.println("generateRadarChart");
		//TODO 这里的位置信息暂时以成都代替，后面可以直接从数据库中读取
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"成都"}));//数据分组  
        List<String> category = new ArrayList<String>();//横坐标
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
        List<String> newTimeList = new ArrayList<String>();
        //TODO 没有连接下位机时候的简单测试
        //timeList = "[2018年07月01日 21:10:07,2018年07月01日 21:10:09, 2018年07月01日 21:10:11, 2018年07月01日 21:10:13, 2018年07月01日 21:10:15,]";
        String newTimeString = timeList.substring(1, timeList.indexOf("]"));
		String[] timeListSplit = newTimeString.split(",");
		String[] timeSplit;
		for(int i = 0;i < timeListSplit.length;i++){
			timeSplit = timeListSplit[i].trim().split(" ")[1].split(":");
			newTimeList.add(String.valueOf(Integer.parseInt(timeSplit[0])+Integer.parseInt(timeSplit[1])+Integer.parseInt(timeSplit[2])));
        }
		/*temList= "[29.0, 29.0, 29.0, 29.0, 28.0]";
        humList= "[29.0, 29.0, 29.0, 29.0, 29.0]";
        chohList= "[0.008,0.011,0.012,0.010,0.009]";
        pm25List= "[29.0, 29.0, 219.0, 29.0, 29.0]";
        pm10List= "[29.0, 29.0, 29.0, 291.0, 29.0]";*/
        //co2List= "[129.0, 229.0, 129.0, 229.0]";
        String newTemList = temList.substring(1, temList.indexOf("]"));
		String[] temListSplit = newTemList.split(",");
		String newHumList = humList.substring(1, humList.indexOf("]"));
		String[] humListSplit = newHumList.split(",");
		String newChohList = chohList.substring(1, chohList.indexOf("]"));
		String[] chohListSplit = newChohList.split(",");
		String newPM25List = pm25List.substring(1, pm25List.indexOf("]"));
		String[] pm25ListSplit = newPM25List.split(",");
		String newPM10List = pm10List.substring(1, pm10List.indexOf("]"));
		String[] pm10ListSplit = newPM10List.split(",");
		String newRangeList = rangeList.substring(1, rangeList.indexOf("]"));
		String[] rangeListSplit = newRangeList.split(",");
		String mydata = "";
		for(int i = 0;i < humListSplit.length;i++){
			mydata = chohListSplit[i].trim()+","+temListSplit[i].trim()+","+humListSplit[i].trim()+","+rangeListSplit[i].trim()+","+pm25ListSplit[i].trim()+","+pm10ListSplit[i].trim();
			System.out.println("mydata======"+mydata);
			dataList.add(mydata);
        }
        series.add(new Series("各个空气参数-AQI", "radar", dataList));
        EChartData data = new EChartData(legend, category, series);
		return data;
	}
	
	
	@RequestMapping("/generateMeanRadarChart")
	@ResponseBody
	public EChartData generateMeanRadarChart(HttpServletRequest request){
		System.out.println("generateMeanRadarChart");
		//TODO 这里的位置信息暂时以成都代替，后面可以直接从数据库中读取
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{""}));//数据分组  
        List<String> category = new ArrayList<String>();//横坐标
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
		String newTemList = temList.substring(1, temList.indexOf("]"));
		String[] temListSplit = newTemList.split(",");
		String newHumList = humList.substring(1, humList.indexOf("]"));
		String[] humListSplit = newHumList.split(",");
		String newChohList = chohList.substring(1, chohList.indexOf("]"));
		String[] chohListSplit = newChohList.split(",");
		String newPM25List = pm25List.substring(1, pm25List.indexOf("]"));
		String[] pm25ListSplit = newPM25List.split(",");
		String newPM10List = pm10List.substring(1, pm10List.indexOf("]"));
		String[] pm10ListSplit = newPM10List.split(",");
//		String newCO2List = co2List.substring(1, co2List.indexOf("]"));
//		String[] co2ListSplit = newCO2List.split(",");
		String mydata = "";
		double chohSum = 0.00, temSum = 0.00 ,humSum = 0.00,pm25Sum = 0.00,pm10Sum = 0.00;
		int n = humListSplit.length;
		for(int i = 0;i < n;i++){
			chohSum += Double.parseDouble(chohListSplit[i]);
			temSum += Double.parseDouble(temListSplit[i]);
			humSum += Double.parseDouble(humListSplit[i]);
			pm25Sum += Double.parseDouble(pm25ListSplit[i]);
			pm10Sum += Double.parseDouble(pm10ListSplit[i]);
//			co2Sum += Double.parseDouble(co2ListSplit[i]);
        }
		mydata = new BigDecimal(chohSum/n).setScale(3, RoundingMode.UP).doubleValue()+","+
				new BigDecimal(temSum/n).setScale(0, BigDecimal.ROUND_HALF_UP)+","+
				new BigDecimal(humSum/n).setScale(0, BigDecimal.ROUND_HALF_UP)+","+
				new BigDecimal(pm25Sum/n).setScale(0, BigDecimal.ROUND_HALF_UP)+","+
				new BigDecimal(pm10Sum/n).setScale(0, BigDecimal.ROUND_HALF_UP);
		System.out.println("mydata======"+mydata);
		dataList.add(mydata);
        series.add(new Series("各个空气参数-AQI", "radar", dataList));
        EChartData data = new EChartData(legend, category, series);
		return data;
	}
	
	
	
	@RequestMapping("/generateChart")
	@ResponseBody
	/*timeList=[2018年07月01日 21:10:05, 2018年07月01日 21:10:05, 2018年07月01日 21:10:07, 2018年07月01日 21:10:08, 2018年07月01日 21:10:09, 2018年07月01日 21:10:10, 2018年07月01日 21:10:10, 2018年07月01日 21:10:11, 2018年07月01日 21:10:12, 2018年07月01日 21:10:13, 2018年07月01日 21:10:14, 2018年07月01日 21:10:15, 2018年07月01日 21:10:16, 2018年07月01日 21:10:17, 2018年07月01日 21:10:19, 2018年07月01日 21:10:20, 2018年07月01日 21:10:21, 2018年07月01日 21:10:22, 2018年07月01日 21:10:23, 2018年07月01日 21:10:24, 2018年07月01日 21:10:25, 2018年07月01日 21:10:26, 2018年07月01日 21:10:27]
	  chohList=[29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 28.0]
	 * */
	public EChartData generateChart(HttpServletRequest request){
		String param = request.getParameter("class");
		System.out.println("generateChart:"+param);
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{param+"曲线"}));//数据分组  
        List<String> category = new ArrayList<String>();//横坐标
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<String> dataList = new ArrayList<String>();
        //TODO 没有连接下位机时候的简单测试
//        timeList = "[2018年07月01日 21:10:07,2018年07月01日 21:10:09, 2018年07月01日 21:10:11, 2018年07月01日 21:10:13, 2018年07月01日 21:10:15,]";
//        temList= "[29.0, 29.0, 29.0, 29.0, 28.0]";
//        humList= "[29.0, 29.0, 29.0, 29.0, 29.0]";
//        chohList= "[0.008,0.011,0.012,0.010,0.009]";
//        pm25List= "[29.0, 29.0, 219.0, 29.0, 29.0]";
//        pm10List= "[29.0, 29.0, 29.0, 291.0, 29.0]";
//        co2List= "[129.0, 229.0, 129.0, 229.0,120.0]";
        //时间作为X坐标轴单独进行处理
        String newTimeList = timeList.substring(1, timeList.indexOf("]"));
		String[] timeListSplit = newTimeList.split(",");
		for(int i = 0;i < timeListSplit.length;i++){
        	category.add(timeListSplit[i]);
        }
		//各个参数分别作为Y轴进行处理（根据前端传的参数进行解析）
        switch (param) {
		case "温度":
			String newTemList = temList.substring(1, temList.indexOf("]"));
			String[] temListSplit = newTemList.split(",");
			for(int i = 0;i < temListSplit.length;i++){
				dataList.add(temListSplit[i]);
	        }
			break;
		case "湿度":
			String newHumList = humList.substring(1, humList.indexOf("]"));
			String[] humListSplit = newHumList.split(",");
			for(int i = 0;i < humListSplit.length;i++){
				dataList.add(humListSplit[i]);
	        }
			break;
		case "甲醛":
			String newChohList = chohList.substring(1, chohList.indexOf("]"));
			String[] chohListSplit = newChohList.split(",");
			for(int i = 0;i < chohListSplit.length;i++){
				dataList.add(chohListSplit[i]);
	        }
			break;
		case "PM2.5":
			String newPM25List = pm25List.substring(1, pm25List.indexOf("]"));
			String[] pm25ListSplit = newPM25List.split(",");
			for(int i = 0;i < pm25ListSplit.length;i++){
				dataList.add(pm25ListSplit[i]);
	        }
			break;
		case "PM10":
			String newPM10List = pm10List.substring(1, pm10List.indexOf("]"));
			String[] pm10ListSplit = newPM10List.split(",");
			for(int i = 0;i < pm10ListSplit.length;i++){
				dataList.add(pm10ListSplit[i]);
	        }
			break;
		case "等级":
			System.out.println("come in range");
			String newRangeList = rangeList.substring(1, rangeList.indexOf("]"));
			String[] rangeListSplit = newRangeList.split(",");
			for(int i = 0;i < rangeListSplit.length;i++){
				dataList.add(rangeListSplit[i]);
	        }
			break;
		default:
			break;
		}
        series.add(new Series(param, "line", dataList));
        EChartData data = new EChartData(legend, category, series);
		return data;
	}
	
	@RequestMapping(value="saveRadarImage",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveRadarImage(String picInfo,@RequestParam("imageInfo")String imageInfo, @RequestParam("username") String username,HttpServletRequest request){
		if(StringUtils.isBlank(picInfo)){
			System.out.println("picInfo为空,未从前台获取到base64图片信息!");
	        return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		String imageName = request.getParameter("imageName");
		//保存了图片到tomcat服务器
		boolean saveRadarImage = storeOrSelectService.saveRadarImage(picInfo,imageName);
		//为该用户创建一条记录，保存用户名和雷达图所在服务器的路径信息
		boolean createRecord = storeOrSelectService.createRecord(username,imageName,imageInfo);
		if(saveRadarImage && createRecord){
			map.put("result_code", 1);
		}
		else{
			map.put("result_code", 0);
		}
		return map;
	}
}

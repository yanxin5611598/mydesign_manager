package com.yx.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amap.api.maps.model.LatLng;
import com.yx.mydesign.bean.MyTrain;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.OtherDaoImpl;


public class Test1 {
	private static OtherDao otherDao = new OtherDaoImpl();
	/**
     * 获取不规则多边形重心点
     *
     * @param mPoints
     * @return
     */
    public static String getCenterOfGravityPoint(Map<Integer,Double> mPoints,String rank1,String rank2) {
        double area = 0.0;//多边形面积
        double Gx = 0.0, Gy = 0.0;// 重心的x、y
        for (int i = 1; i <= mPoints.size(); i++) {
            double x = i % mPoints.size();
            double y = mPoints.get(i % mPoints.size());
            double nextX = i-1;
            double nextY = mPoints.get(i - 1);
            double temp = Math.abs((x * nextY - y * nextX)) / 2.0;
            area += temp;
            Gx += temp * (x + nextX) / 3.0;
            Gy += temp * (y + nextY) / 3.0;
        }
        Gx = Gx / area;
        Gy = Gy / area;
        String result;
        
        if(Gx <= 1.5){
        	result = "优";
        }else if(Gx <= 2.5){
        	result = "良";
        }else if(Gx <= 3.5){
        	result = "中";
        }else if(Gx <= 4.5){
        	result = "差";
        }else{
        	result = "重度";
        }
        System.out.println(Gx + "----"+Gy+"----"+rank1+"----"+rank2+"---"+result);
        return result;
    }
	public static void main(String[] args) {
		/*String rank1 = "优";
		String rank2 = "差";
		int r1 = (rank1 == "优")?3:(rank1 == "良")?2:(rank1 == "中")?1:0;
        int r2 = (rank2 == "优")?3:(rank2 == "良")?2:(rank2 == "中")?1:0;
        System.out.println("r1="+r1);
        System.out.println("r2="+r2);*/
		/*String message = "0-[]";
		String[] devices = message.substring(message.indexOf("[")+1,message.lastIndexOf("]")).trim().split(",");
		System.out.println(devices.length);
		System.out.println(devices[0]+"===="+devices[1]);*/
		/*for(String str:"D0001 18.0 59.0 0.011  8.1 10.8".split("\\s+")){
			System.out.println(str);
		}
		System.out.println();*/
		List<MyTrain> list = otherDao.getAllTrain();
		System.out.println(list.size());
		for(MyTrain train:list){
			Map<Integer,Double> map = new HashMap<Integer, Double>();
			map.put(0,0.0);
			map.put(1,train.getYou());
			map.put(2,train.getLiang());
			map.put(3,train.getZhong());
			map.put(4,train.getCha());
			map.put(5,train.getYanzhong());
			map.put(6,0.0);
			System.out.println("id:"+train.getId());
			int i = otherDao.updateToTrainForNewAlg(train.getId(),getCenterOfGravityPoint(map,train.getRankMax(),train.getRankCenter()));
//			getCenterOfGravityPoint(map);
			System.out.println(i != 0 ? "update success":"failed");
		}
		
	}
}

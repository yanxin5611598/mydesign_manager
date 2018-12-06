package com.yx.mydesign.alg;
import java.math.BigDecimal;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

//模糊数学综合评价方法
public class Fuzzy {
	public static Matrix denseModel = DenseMatrix.Factory.zeros(3, 5);
	public static Matrix denseValue = DenseMatrix.Factory.zeros(1, 3);
	//初始化权重矩阵
	public static Matrix denseA = DenseMatrix.Factory.zeros(1, 3);
	//初始化等级
	public static String[] range = new String[]{"优","良","中","差","重度"};
//	private static OtherDao otherDao = new OtherDaoImpl();
	// 单例  
    private static final Fuzzy fuzzy = new Fuzzy();  
    public static Fuzzy getInstance(){
    	return fuzzy;
    }
	//预加载模型
	public static void preFuzzyMethod(){
		
        denseModel.setAsDouble(0.05, 0,0);
        denseModel.setAsDouble(0.10, 0,1);
        denseModel.setAsDouble(0.20, 0,2);
        denseModel.setAsDouble(0.30, 0,3);
        denseModel.setAsDouble(0.40, 0,4);

        denseModel.setAsDouble(35, 1,0);
        denseModel.setAsDouble(75, 1,1);
        denseModel.setAsDouble(150, 1,2);
        denseModel.setAsDouble(250, 1,3);
        denseModel.setAsDouble(400, 1,4);
        
        denseModel.setAsDouble(50, 2,0);
        denseModel.setAsDouble(150, 2,1);
        denseModel.setAsDouble(250, 2,2);
        denseModel.setAsDouble(350, 2,3);
        denseModel.setAsDouble(500, 2,4);
        
        //按照论文某地下超市室内空气质量评价及数值模拟对CO2重新定义标准
//        denseModel.setAsDouble(400, 3,0);
//        denseModel.setAsDouble(650, 3,1);
//        denseModel.setAsDouble(1000, 3,2);
//        denseModel.setAsDouble(1600, 3,3);
//        denseModel.setAsDouble(2500, 3,4);
        
        /*
        denseModel.setAsDouble(450, 3,0);
        denseModel.setAsDouble(1000, 3,1);
        denseModel.setAsDouble(2000, 3,2);
        denseModel.setAsDouble(5000, 3,3);
        denseModel.setAsDouble(10000, 3,4);
        */
        //初始化权重向量加载计算
  		double[] weightAMatrix = AHPDemo.getWeightMatrix();
  		for(int i = 0;i < weightAMatrix.length;i++){
  			denseA.setAsDouble(weightAMatrix[i],0, i);
  		}
  		System.out.println("权重关系矩阵A："+denseA);
	}
	public String fuzzyMethod(String values){
		//values格式已更新为deviceID tem hum choh pm2.5 pm10的格式
		//初始化模糊关系矩阵
		Matrix denseR = DenseMatrix.Factory.zeros(3, 5);
		//初始化隶属度矩阵
		Matrix denseB = DenseMatrix.Factory.zeros(3,5);
		String[] valueSplit = values.split("\\s+");
		System.out.println(values);
		Double CHOH = Double.valueOf(valueSplit[3]);//甲醛
		Double PM25 = Double.valueOf(valueSplit[4]);//PM2.5
		Double PM10 = Double.valueOf(valueSplit[5]);//PM10
		//Double CO2 = Double.valueOf(valueSplit[6].split("=")[1]);//CO2
		
		denseValue.setAsDouble(CHOH, 0,0);
		denseValue.setAsDouble(PM25, 0,1);
		denseValue.setAsDouble(PM10, 0,2);
		//denseValue.setAsDouble(CO2, 0,3);
		
		
		for(int i = 0;i < 3;i++){
			for(int j = 0;j < 5;j++){
				double value = 0.0;
				if(j == 0){
					if(denseValue.getAsDouble(0,i) <= denseModel.getAsDouble(i,j)){
						value = 1.0000;
					}else if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j+1)){
						value = 0.0000;
					}else{
						value = (denseModel.getAsDouble(i,j+1) - denseValue.getAsDouble(0,i))/(denseModel.getAsDouble(i,j+1) - denseModel.getAsDouble(i,j));
					}
				}else if(j == 5){
					if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j-1)){
						value = 1.0000;
					}else if(denseValue.getAsDouble(0,i) < denseModel.getAsDouble(i,j-1)){
						value = 0.0000;
					}else{
						value = (denseModel.getAsDouble(i,j-1) - denseValue.getAsDouble(0,i))/(denseModel.getAsDouble(i,j-1) - denseModel.getAsDouble(i,j-2));
					}
				}
				else{
					if(denseValue.getAsDouble(0,i) >= denseModel.getAsDouble(i,j-1) && denseValue.getAsDouble(0,i) <= denseModel.getAsDouble(i,j)){
						value = (denseValue.getAsDouble(0,i) - denseModel.getAsDouble(i,j-1))/(denseModel.getAsDouble(i,j) - denseModel.getAsDouble(i,j-1));
					}else if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j) && denseValue.getAsDouble(0,i) < denseModel.getAsDouble(i,j+1)){
						value = (denseModel.getAsDouble(i,j+1) - denseValue.getAsDouble(0,i))/(denseModel.getAsDouble(i,j+1) - denseModel.getAsDouble(i,j));
					}else{
						value = 0.0000;
					}
				}
				denseR.setAsDouble(value, i,j);
			}
		}
		System.out.println("模糊关系矩阵R："+denseR);
		//计算隶属度矩阵
		denseB = denseA.mtimes(denseR);
		System.out.println("隶属度矩阵B："+denseB);
		//TODO 隶属度计算，首先根据最大隶属度原则进行计算，后续可以使用重心法（先拟合，再计算曲线的重心）改进
		double max = denseB.getAsDouble(0,0);
		int result = 0;
		for(int i = 1;i < range.length;i++){
			if(denseB.getAsDouble(0,i) > max){
				max = denseB.getAsDouble(0,i);
				result = i;
			}
		}
		return range[result];
	}
	//计算隶属度向量的最大值与次大值之间的间隔值
	public Double getDivMaxAndSecond(Matrix dense){
		Double nMax = dense.getAsDouble(0,0);
	    Double nSecondMax = dense.getAsDouble(0,0);
	    for (int i = 0;i < 5 ;i++)
	    {
	        if (nMax < dense.getAsDouble(0,i))
	        {
	            nSecondMax = nMax;
	            nMax = dense.getAsDouble(0,i);
	        }
	        else if (nSecondMax < dense.getAsDouble(0,i))
	        {
	            nSecondMax = dense.getAsDouble(0,i);
	        }
	    }
	    return nMax - nSecondMax;
	}
	public String fuzzyMethodForModel(String values){
		//values格式已更新为deviceID tem hum choh pm2.5 pm10的格式
		//初始化模糊关系矩阵
		Matrix denseR = DenseMatrix.Factory.zeros(3, 5);
		//初始化隶属度矩阵
		Matrix denseB = DenseMatrix.Factory.zeros(3,5);
		String[] valueSplit = values.split("\\s+");
		Double CHOH = Double.valueOf(valueSplit[3]);//甲醛
		Double PM25 = Double.valueOf(valueSplit[4]);//PM2.5
		Double PM10 = Double.valueOf(valueSplit[5]);//PM10
		//Double CO2 = Double.valueOf(valueSplit[6].split("=")[1]);//CO2
		
		denseValue.setAsDouble(CHOH, 0,0);
		denseValue.setAsDouble(PM25, 0,1);
		denseValue.setAsDouble(PM10, 0,2);
		//denseValue.setAsDouble(CO2, 0,3);
		
		System.out.println("valunes="+values);
		for(int i = 0;i < 3;i++){
			for(int j = 0;j < 5;j++){
				double value = 0.0;
				if(j == 0){
					if(denseValue.getAsDouble(0,i) <= denseModel.getAsDouble(i,j)){
						value = 1;
					}else if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j+1)){
						value = 0;
					}else{
						value = (denseModel.getAsDouble(i,j+1) - denseValue.getAsDouble(0,i))/(denseModel.getAsDouble(i,j+1) - denseModel.getAsDouble(i,j));
					}
				}else if(j == 4){
					if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j)){
						value = 1;
					}else if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j-1) && denseValue.getAsDouble(0,i) < denseModel.getAsDouble(i,j)){
						value = (denseValue.getAsDouble(0,i) - denseModel.getAsDouble(i,j-1))/(denseModel.getAsDouble(i,j) - denseModel.getAsDouble(i,j-1));
					}else{
						value = 0;
					}
				}
				else{
					if(denseValue.getAsDouble(0,i) >= denseModel.getAsDouble(i,j-1) && denseValue.getAsDouble(0,i) <= denseModel.getAsDouble(i,j)){
						value = (denseValue.getAsDouble(0,i) - denseModel.getAsDouble(i,j-1))/(denseModel.getAsDouble(i,j) - denseModel.getAsDouble(i,j-1));
					}else if(denseValue.getAsDouble(0,i) > denseModel.getAsDouble(i,j) && denseValue.getAsDouble(0,i) < denseModel.getAsDouble(i,j+1)){
						value = (denseModel.getAsDouble(i,j+1) - denseValue.getAsDouble(0,i))/(denseModel.getAsDouble(i,j+1) - denseModel.getAsDouble(i,j));
					}else{
						value = 0;
					}
				}
				denseR.setAsDouble(value, i,j);
			}
		}
		System.out.println("模糊关系矩阵R："+denseR);
		//计算隶属度矩阵
		denseB = denseA.mtimes(denseR);
		//TODO 隶属度计算，首先根据最大隶属度原则进行计算，后续可以使用重心法（先拟合，再计算曲线的重心）改进
		double max = denseB.getAsDouble(0,0);
		int result = 0;
		for(int i = 1;i < range.length;i++){
			if(denseB.getAsDouble(0,i) > max){
				max = denseB.getAsDouble(0,i);
				result = i;
			}
		}
		System.out.println("隶属矩阵B："+denseB);
		if(getDivMaxAndSecond(denseB) >= 0.1){
			System.out.println("使用最大隶属度评价");
			return range[result];
		}else{
			System.out.println("使用面积中心法评价");
			//TODO 使用面积中心法计算
			double areaSum = denseB.getAsDouble(0,0)/2 + denseB.getAsDouble(0,range.length-1)/2;
			for(int i = 1;i < range.length;i++){
				//中间的认为都是梯形（矩形也是一种特殊的梯形）
				areaSum += (denseB.getAsDouble(0,i) + denseB.getAsDouble(0,i-1))/2;
			}
			System.out.println(areaSum);
			double area = denseB.getAsDouble(0,0)/2;
			int i;
			for(i = 0;i<range.length - 1;i++){
				if(area >= areaSum/2){
					break;
				}
				area += (denseB.getAsDouble(0,i+1) + denseB.getAsDouble(0,i))/2;
			}
			if(i == 0){
	//			return denseB.getAsString(0,0) +" " +denseB.getAsString(0,1)+" " +denseB.getAsString(0,2)+" " +denseB.getAsString(0,3)+" " +denseB.getAsString(0,4)+" "+range[result] + " " + range[i];
				return range[i];
			}
			int s = ((area - areaSum/2) > ((denseB.getAsDouble(0,i) + denseB.getAsDouble(0,i - 1))/2 - (area - areaSum/2))) ? i-1 : i;
	 		System.out.println("i="+i);
	 		System.out.println("s="+s);
	//		return round(denseB.getAsDouble(0,0),4) +" " +round(denseB.getAsDouble(0,1),4)+" " +round(denseB.getAsDouble(0,2),4)+" " +round(denseB.getAsDouble(0,3),4)+" " +round(denseB.getAsDouble(0,4),4)+" "+range[result] + " " + range[i];
			return range[s];
			//return ;
		}
	}
	/** 
     * 四舍五入 
     *  
     * @param v 
     * @param scale 
     * @return 
     */  
    public double round(double v, int scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b = new BigDecimal(Double.toString(v));  
        BigDecimal one = new BigDecimal("1");  
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    } 
	public static void main(String[] args) {
		preFuzzyMethod();
		Fuzzy fuzzy = new Fuzzy();
		/*List<MyTrain> list = otherDao.getAllTrain();
		System.out.println(list.size());
		for(MyTrain train:list){
			System.out.println("id="+train.getId());
			int iss = otherDao.updateToTrainForNewAlg(train.getId(),fuzzy.fuzzyMethodForModel("D0001"+" "+train.getHum()+" "+train.getHum()+" "+train.getChoh()+" "+train.getPm25()+" "+train.getPm10()));
			System.out.println(iss != 0 ? "update success":"failed");
		}*/
		System.out.println(fuzzy.fuzzyMethodForModel("D0001 21.0 51.0 0.116 57.6 64.7"));
	}
}

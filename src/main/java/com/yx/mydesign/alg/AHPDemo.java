package com.yx.mydesign.alg;

import java.math.BigDecimal;
import java.util.Arrays;

public class AHPDemo {
	public static double[] getWeightMatrix() {  
        /** a为N*N矩阵 */  
		//计算人、物、环境等不同因素对目标层即确定权重的影响
		double[][] a = new double[][]{{1,2,3},{0.5,1,1.5},{0.3333,0.6667,1}};
        //计算由人造成的空气中CHOH,PM2.5，PM10，CO2的含量的影响
		//double[][] a1 = new double[][]{{1,0.333,0.5},{3,1,2},{2,0.5,1}};
		double[][] a1 = new double[][]{{1,0.2,0.3333},{5,1,3},{3,0.3333,1}};
		//计算由物造成的空气中CHOH,PM2.5，PM10，CO2的含量的影响
		//double[][] a2 = new double[][]{{1,2,3},{0.5,1,1.5},{0.333,0.667,1}};
		double[][] a2 = new double[][]{{1,3,5},{0.3333,1,3},{0.2,0.3333,1}};
		//计算由环境污染造成的空气中CHOH,PM2.5，PM10，CO2的含量的影响
		//double[][] a3 = new double[][]{{1,0.3333,0.5},{3,1,2},{2,0.5,1}};
		double[][] a3 = new double[][]{{1,0.2,0.3333},{5,1,3},{3,0.3333,1}};
		int N = a[0].length;  
		int M = a1[0].length;
        double[] weight = new double[N];//准则层各个因素对目标层权重值的计算  
        double[] weight1 = new double[M];//方案层中各个因素由准则层中人为因素引起的权重计算  
        double[] weight2 = new double[M];  //方案层中各个因素由准则层中物的因素引起的权重计算
        double[] weight3 = new double[M];//方案层中各个因素由准则层中环境因素引起的权重计算
        double[] weightsum = new double[M]; //方案层中各个因素对目标层整体权重的计算
        AHPDemo instance = AHPDemo.getInstance();  
        instance.weight(a, weight, N);
        instance.weight(a1, weight1, M);
        instance.weight(a2, weight2, M);
        instance.weight(a3, weight3, M);
        //打印出各个权重值
        System.out.println("准则层对目标层:"+Arrays.toString(weight));
        System.out.println("方案层对准则层（人）:"+Arrays.toString(weight1));
        System.out.println("方案层对准则层（物）:"+Arrays.toString(weight2));
        System.out.println("方案层对准则层（环境）:"+Arrays.toString(weight3));
        
        weightsum[0] = round(weight[0]*weight1[0]+weight[1]*weight2[0]+weight[2]*weight3[0],5);
        weightsum[1] = round(weight[0]*weight1[1]+weight[1]*weight2[1]+weight[2]*weight3[1],5);
        weightsum[2] = round(weight[0]*weight1[2]+weight[1]*weight2[2]+weight[2]*weight3[2],5);
//        weightsum[3] = round(weight[0]*weight1[3]+weight[1]*weight2[3]+weight[2]*weight3[3],4);
        System.out.println("方案层对目标层权重（甲醛、PM2.5,PM10）:"+Arrays.toString(weightsum));
        return weightsum;
    }
    // 单例  
    private static final AHPDemo acw = new AHPDemo();  
  
    // 平均随机一致性指针  
    private double[] RI = { 0.00, 0.00, 0.58, 0.90, 1.12, 1.21, 1.32, 1.41,  
            1.45, 1.49 };  
  
    // 随机一致性比率  
    private double CR = 0.0;  
  
    // 最大特征值  
    private double lamta = 0.0;  
  
    /** 
     * 私有构造 
     */  
    private AHPDemo() {  
  
    }  
  
    /** 
     * 返回单例 
     *  
     * @return 
     */  
    public static AHPDemo getInstance() {  
        return acw;  
    }  
  
    /** 
     * 计算权重 
     *  
     * @param a 
     * @param weight 
     * @param N 
     */  
    public void weight(double[][] a, double[] weight, int N) {  
          
        double[] w1 = new double[N];          
        double[] w2 = new double[N];  
        double sum = 0.0;     
        System.out.println("按行求和1");  
        //按行求和  
        for (int j = 0; j < N; j++) {  
            double t = 0.0;  
            for (int l = 0; l < N; l++)  
                t += a[l][j];  
            w1[j] = t;  
            System.out.println(w1[j]);
        }  
        System.out.println("按行求和2");
        //按行归一化，然后按列求和，最后得到特征向量w2  
        for (int k = 0; k < N; k++) {  
            sum = 0;  
            for (int i = 0; i < N; i++) {  
                sum = sum + a[k][i] / w1[i];  
            }  
            w2[k] = sum / N;  
            System.out.println(w2[k]);
        }  
          
        lamta = 0.0;  
          
        //求矩阵和特征向量的积,然后求出特征值  
        for (int k = 0; k < N; k++) {  
            sum = 0;  
            for (int i = 0; i < N; i++) {  
                sum = sum + a[k][i] * w2[i];  
            }  
            w1[k] = sum;  
            lamta = lamta + w1[k] / w2[k];  
        }  
          
        // 计算矩阵最大特征值lamta，CI，RI  
        lamta = lamta / N;  
        System.out.println("lamta="+lamta);
        double CI = (lamta - N) / (N - 1);  
  
        if (RI[N - 1] != 0) {  
            CR = CI / RI[N - 1];  
        }  
  
        // 四舍五入处理  
        lamta = round(lamta, 3);  
        CI = round(CI, 3);  
        CR = round(CR, 3);  
        
        for (int i = 0; i < N; i++) {  
            w1[i] = round(w1[i],5);  
            w2[i] = round(w2[i], 5);  
        }  
        // 控制台打印输出  
  
        System.out.println("lamta=" + lamta);  
        System.out.println("CI=" + CI);  
        System.out.println("CR=" + CR);  
  
        // 控制台打印权重  
        System.out.println("");  
  
        System.out.println("w1[]=");  
        for (int i = 0; i < N; i++) {  
            System.out.print(w1[i] + " ");  
        }  
        System.out.println("");  
  
        System.out.println("w2[]=");  
        for (int i = 0; i < N; i++) {  
            weight[i] = w2[i];  
            System.out.print(weight[i] + " ");  
        }  
        System.out.println("");  
    }  
  
    /** 
     * 四舍五入 
     *  
     * @param v 
     * @param scale 
     * @return 
     */  
    public static double round(double v, int scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b = new BigDecimal(Double.toString(v));  
        BigDecimal one = new BigDecimal("1");  
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    }  
  
    /** 
     * 返回随机一致性比率 
     *  
     * @return 
     */  
    public double getCR() {  
        return CR;  
    }  
}

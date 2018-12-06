package com.yx.mydesign.alg;

import java.math.BigDecimal;
import java.util.Arrays;


public class AHP {
	private static final int N = 3;
	public static void main(String[] args) {
		double[][] a = new double[][] { 
				{ 1.0,2.0,3.0 }, 
				{ 0.5,1.0,2.0 },  
                { 1/3,0.5,1.0 } };  //准则层的三个因素：人、物、环境
        double[] weight = new double[N];
        AHP instance = AHP.getInstance(); 
        instance.weight(a, weight, N);
        System.out.println(Arrays.toString(weight));
    }
    // 单例
    private static final AHP acw = new AHP();  
  
    // 平均随机一致性指针  10个
    private double[] RI = { 0.00, 0.00, 0.58, 0.90, 1.12, 1.21, 1.32, 1.41,  
            1.45, 1.49 };  
  
    // 随机一致性比率  
    private double CR = 0.0;  
  
    // 最大特征值  
    private double lamta = 0.0;  
  
    /** 
     * 私有构造 
     */  
    private AHP() {  
  
    }  
  
    /** 
     * 返回单例 
     *  
     * @return 
     */  
    public static AHP getInstance() {  
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
          
        //按行求和  
        for (int j = 0; j < N; j++) {  
            double t = 0.0;  
            for (int l = 0; l < N; l++)  
                t += a[l][j];  
            w1[j] = t;  
            System.out.println("w1[demo]="+w1[j]);
        }  
  
        //按行归一化，然后按列求和，最后得到特征向量w2  
        for (int k = 0; k < N; k++) {  
            sum = 0;  
            for (int i = 0; i < N; i++) {  
                sum = sum + a[k][i] / w1[i];  
            }  
            System.out.println("sum="+sum);
            w2[k] = sum / N;  
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
            w1[i] = round(w1[i], 4);  
            w2[i] = round(w2[i], 4);  
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
    public double round(double v, int scale) {  
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

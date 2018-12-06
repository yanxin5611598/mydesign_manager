package com.yx.test;

import com.yx.mydesign.alg.Fuzzy;
import com.yx.mydesign.utils.OtherDao;
import com.yx.mydesign.utils.OtherDaoImpl;

public class Test {
	private static OtherDao other = new OtherDaoImpl();
	private static Fuzzy fuzzy = Fuzzy.getInstance();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fuzzy.preFuzzyMethod();
		String values = "D0001 19.0 53.0 0.289 42.8 54.5";
		String result = values + " " + fuzzy.fuzzyMethodForModel(values);
		System.out.println(result);
		other.insertTrainTest(result);
	}
}

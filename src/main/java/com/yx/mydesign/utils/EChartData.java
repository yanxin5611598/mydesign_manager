package com.yx.mydesign.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * 该类封装了Echarts图表前后台数据交互的一些必要参数*/
public class EChartData {
	private List<String> legend = new ArrayList<String>();
	private List<String> xcategory = new ArrayList<String>();
	private List<Series> yseries = new ArrayList<Series>();
	public List<String> getLegend() {
		return legend;
	}
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	public List<String> getXcategory() {
		return xcategory;
	}
	public void setXcategory(List<String> xcategory) {
		this.xcategory = xcategory;
	}
	public List<Series> getYseries() {
		return yseries;
	}
	public void setYseries(List<Series> yseries) {
		this.yseries = yseries;
	}
	public EChartData() {
	}
	public EChartData(List<String> legend, List<String> xcategory,
			List<Series> yseries) {
		super();
		this.legend = legend;
		this.xcategory = xcategory;
		this.yseries = yseries;
	}
	
}

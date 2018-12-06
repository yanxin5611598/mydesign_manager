package com.yx.mydesign.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * 该类封装了Echarts图表前后台数据交互的一些必要参数*/
public class DeviceToDisplayChart {
	private List<String> legend = new ArrayList<String>();
	private List<Series> yseries = new ArrayList<Series>();
	private String deviceInfo;//设备的信息
	
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public List<String> getLegend() {
		return legend;
	}
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	public List<Series> getYseries() {
		return yseries;
	}
	public void setYseries(List<Series> yseries) {
		this.yseries = yseries;
	}
	public DeviceToDisplayChart() {
	}
	public DeviceToDisplayChart(List<String> legend,
			List<Series> yseries, String deviceInfo) {
		super();
		this.legend = legend;
		this.yseries = yseries;
		this.deviceInfo = deviceInfo;
	}
	
	
}

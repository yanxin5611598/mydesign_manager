package com.yx.mydesign.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Echarts图表的前后端数据交互数据*/
public class Series {
	private String name;
	private String type;
	private List<String> data = new ArrayList<String>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public Series(String name, String type, List<String> data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}
	public Series() {
	}
	
	
}

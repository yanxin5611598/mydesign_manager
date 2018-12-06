package com.yx.mydesign.constant;
/*用于封装页面的编码信息-----常量---使用枚举类型*/
public enum PageCodeEnum {
	//定义一个有参的构造函数之后，需要对枚举类型成员相应的使用该构造方法，
	ADD_SUCCESS(1000,"新增成功！"),
    ADD_FAIL(1001,"新增失败！"),
	REMOVE_SUCCESS(2000,"删除成功"),
	REMOVE_FAIL(2001,"删除失败"),
	MODIFY_SUCCESS(3000,"更新成功"),
	MODIFY_FAIL(3001,"更新失败");
	private Integer code;
	private String msg;
	public static final String KEY = "pageCode";
	//PageCodeEnum的构造函数
	private PageCodeEnum(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	//由于是常量，故不能提供set方法给外部，以免外部对常量进行修改
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}

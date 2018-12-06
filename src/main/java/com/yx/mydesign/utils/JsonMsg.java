package com.yx.mydesign.utils;

import java.util.HashMap;
import java.util.Map;


/**由于该后台系统需要和手机端进行交互、也为了在后台系统之间交互过程中能够使用ajax
 * 这里封装了返回json数据的一些变量*/
public class JsonMsg {
	private Integer code;//返回到前端的状态码   200表示成功   404表示失败
	private String message;//返回到前端的消息提示
	private Map<String , Object> extend = new HashMap<String, Object>();
	//成功
	public static JsonMsg success(){
		JsonMsg jsonMsg = new JsonMsg();
		jsonMsg.setCode(200);
		jsonMsg.setMessage("成功");
		return jsonMsg;
	} 
	
	//失败
	public static JsonMsg fail(){
		JsonMsg jsonMsg = new JsonMsg();
		jsonMsg.setCode(404);
		jsonMsg.setMessage("失败");
		return jsonMsg;
	}
	/**此方法相当于是setAttribute()方法。。。。。。*/
	public JsonMsg add(String key,Object value){
		this.extend.put(key, value);
		return this;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getMap() {
		return extend;
	}
	public void setMap(Map<String, Object> map) {
		this.extend = map;
	}
	
}

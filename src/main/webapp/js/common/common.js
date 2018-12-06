var common = window.common || {};//自定义的命名空间函数，避免与其他同名函数冲突
/**
 * 展示指定的消息内容。-----在content目录下的adAdd.js文件中进行调用
 */
common.showMessage = function(msg) {
	if(msg) {
		alert(msg);
	}
}

/**
 * 对jQuery的ajax方法的二次封装
 */
common.ajax = function(param) {
	var mergeParam = $.extend({
		timeout : 10000
	} , param , {
		complete : function(response) {
			var url = response.getResponseHeader("url");
			if(url) {
				location.href = url;
			} else {
				if(param.complete && typeof param.complete == "function") {
					param.complete();
				}
			}
		}
	});
	$.ajax(mergeParam);
}

/**
 * 页面返回码定义，与后台PageCode定义对应
 */
common.pageCode = {
		"ADD_SUCCESS" : 1000,
		"MODIFY_SUCCESS" : 1100,
		"REMOVE_SUCCESS" : 1200
}

common.menuPrefix = {
		"PREFIX_MENU" : "MENU_",
		"PREFIX_ACTION" : "ACTION_"
}
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>空气质量检测管理登录界面</title>
<script type="text/javascript" src="static/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="static/js/login.js"></script>
<link href="css/login_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function(){
		//2.如果是必填的，则加红星标识.
		$("form :input.required").each(function(){
	        var $required = $("<strong class='high'> *</strong>"); //创建元素
	        $(this).parent().append($required); //然后将它追加到文档中
		});
		//为表单元素添加失去焦点事件
		 $("form :input").blur(function(){
			 //alert("lose");
			 var $parent = $(this).parent();
			 //验证名称
			 if($(this).is("#name")){
			  if($.trim(this.value) == "" || $.trim(this.value).length < 6){
			  var errorMsg = " 请输入至少6位的名称！";
			  //class='msg onError' 中间的空格是层叠样式的格式
			  $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
			  }
			  else{
			  var okMsg=" 输入正确";
			  $parent.find(".high").remove();
			  $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
			  }
		 	}
		 });
	});
</script> 
</head>
<body>


<div class="login">
    <div class="box png">
		<div class="logo png"></div>
		<div class="input">
			<!--login是虚拟路径，在web.xml文件中配置  -->
		<form id="loginForm" action="login" method="post">
			<div class="log">
				<div class="name">
					<label>用户名</label><input type="text" class="text1" id="managername" placeholder="用户名" name="managername" tabindex="1"></input>
				</div>
				<div class="pwd">
					<label>密　码</label><input type="password" class="text1" id="password" placeholder="密码" name="password" tabindex="2"></input>
				</div>
				<div class="category">
					  	<label>类　别</label>
					    
					      <select class="text1" id="managerGroup" name="managerGroup">
						  <option>管理员</option>
						</select>  	  
				</div>
				<div align="center">
		            <font color="red">${error}</font>  <!-- 该error属性在loginservlet中设置为“用户不存在” -->
		        </div>
				<div class="check"></div>
				<input type="submit" class="submit" tabindex="3" value="登录"></input>
				<input type="button" class="register" tabindex="3" value="注册"></input>
					
				<div class="tip"></div>
			</div>
		</form>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>


<script type="text/javascript" src="js/fun.base.js"></script>
<script type="text/javascript" src="js/script.js"></script>


<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->
<div style="text-align:center;margin:50px 0; font:normal 18px/24px 'MicroSoft YaHei';color:green">
<p>更快、更好的方便您进行管理！</p>
</div>
</body>
</html>
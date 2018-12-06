$(function() {   
	   $(".input").blur(function() {
		var inputName = $(this).attr("name");
		alert(inputName);
		invokeValidateFunction(inputName);
	    });
	    
	     $(".input").focus(function() {
		 var inputName = $(this).attr("name");
         var str=inputName.replace(/loginUser./,"");
		 $("#" + str + "Error").css("display", "none");
	     });

	 	$(".labelError").each(function() {
	 		if($(this).text()) {
	 			$(this).css("display", "");
	 		} else {
	 			$(this).css("display", "none");
	 		}
	 	});
	    
	 } ) ; 

    function invokeValidateFunction(inputName) {
    	var str=inputName.replace(/loginUser./,"");
    	var functionName = "validate" + str;
    	return eval(functionName + "()");	
    }

    function validateusername() {
     var bool=true;
   	 var value = $("#username").val();
   	 if(!value) {
   		 alert("null");
   	    $("#usernameError").css("display", "");
   		$("#usernameError").text("用户名不能为空！");
   		bool=false;
   	 } else if(value.length < 3 || value.length > 20) {
   		$("#usernameError").css("display", "");
   		$("#usernameError").text("用户名长度在3 ~ 20之间！");
   		bool=false;
   	}
   	  return bool;
    } 
    function validatepassword() {
    	var bool = true;
    	$("#passwordError").css("display", "none");
    	var value = $("#password").val();
    	if(!value) {
    		$("#passwordError").css("display", "");    //css是jQuery的一个类，设置属性display的值为空字符
    		$("#passwordError").text("密码不能为空！");	   //设置文本内容
    		bool = false;
    	} else if(value.length < 3 || value.length > 20) {
    		$("#passwordError").css("display", "");
    		$("#passwordError").text("密码长度在3 ~ 20之间！");
    		bool = false;
    	}
    	return bool;
    }
    
     function check(){
    		var bool = true;
    		$(".input").each(function() {
    			var inputName = $(this).attr("name");
    			if(!invokeValidateFunction(inputName)) {
    				bool = false;
    			}
    		})
    		return bool;
       }
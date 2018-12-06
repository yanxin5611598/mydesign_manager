<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>

<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css"/>  
<link href="${pageContext.request.contextPath}/css/all.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/pop.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
<script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>  
<script src="${pageContext.request.contextPath}/js/common/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/json.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/system/index.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script type="text/javascript">
	/**
	* 打开密码修改弹出层
	*/
	function openAddDiv(){
		alert("hello");
		$("#mengban").css("visibility","visible");
		$(".wishlistBox").show();
		$(".wishlistBox").find(".persongRightTit").html("&nbsp;&nbsp;修改密码");
		$("#submitVal").show();
	}
	</script>
	<body>
		<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img width="100%" src="${pageContext.request.contextPath}/images/top_01.jpg">
				</td>

				<td width="100%" background="${pageContext.request.contextPath}/images/top_10.jpg">
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" valign="bottom" background="${pageContext.request.contextPath}/images/mis_01.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="75%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- 这一部分用于实时显示时间 -->
								<font color="#000000"> 
								<script language="JavaScript">
								<!--
								tmpDate = new Date;
								date = tmpDate.getDate();
								month= tmpDate.getMonth()+1;
								year= tmpDate.getFullYear();
								document.write(year);
								document.write("年");
								document.write(month);
								document.write("月");
								document.write(date);
								document.write("日 ");
								
								myArray=new Array(6);
								myArray[0]="星期日"
								myArray[1]="星期一"
								myArray[2]="星期二"
								myArray[3]="星期三"
								myArray[4]="星期四"
								myArray[5]="星期五"
								myArray[6]="星期六"
								weekday=tmpDate.getDay();
								if (weekday==0 | weekday==6)
								{
								document.write(myArray[weekday])
								}
								else
								{document.write(myArray[weekday])
								};
								// -->
								</script> </font>
							</td>
							<td width="25%">
								欢迎您! &nbsp; <font color="blue"><%=session.getAttribute("managerName") %></font>
								[类别]:<font color="blue"><%=session.getAttribute("managerGroup") %></font>
								&nbsp;
								<a href="javascript:void(0);" onclick="openAddDiv();">[修改密码]</a>
								&nbsp;
								<a href="">[退出登录]</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		<!-- 蒙版DIV -->
		<div id="mengban" style="display:none"></div>
		<input type="hidden" id="basePath" value="${basePath}"/>
		<div class="wishlistBox" style="display: none;left:550px;top:200px;">
		    <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
		        <div class="persongRightTit" style="width:480px;">&nbsp;&nbsp;修改密码</div>
		        <div class="persongRigCon">
		            <form name="redisAddOrEditForm" action="#" method="post">
		                <table class="x-form-table">
		                    <tbody>
		                    <tr class="line">
		                        <td class="left" width="10%"><em class="required">*</em><label>原始密码：</label></td>
		                        <td width="90%">
		                            <input class="normal-input" name="oldPassword" id="oldPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><label><em class="required">*</em>新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPassword" id="newPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><em class="required">*</em><label>确认新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPasswordAgain" id="newPasswordAgain" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="left"></td>
		                        <td class="submit">
		                            <input id="submitVal" class="tabSub" value="提交" onclick="checkForm('${basePath}/comment');" type="button"/>
		                            <input class="tabSub" value="关闭" onclick="closeDiv();" type="reset"/>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
		</div>
	</body>
</HTML>

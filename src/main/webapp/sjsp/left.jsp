<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<!--引入dtree.js文件-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0101','01','用户管理');
		d.add('010101','0101','用户管理','${pageContext.request.contextPath}/getAllUser?pageNumber=1','','mainFrame');
		d.add('0102','01','订单管理');
		d.add('010201','0102','订单管理','${pageContext.request.contextPath}/getAllPurchase?pageNumber=1','','mainFrame');//在右侧打开
		d.add('0103','01','广告管理');
		d.add('010301','0103','广告管理','${pageContext.request.contextPath}/getAllAdvert?pageNumber=1','','mainFrame');
		d.add('0104','01','报表统计管理');
		d.add('010401','0104','报表统计管理','${pageContext.request.contextPath}/infoChartDisplay.jsp','','mainFrame');
		document.write(d);//相当于HTML的显示
		//-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>

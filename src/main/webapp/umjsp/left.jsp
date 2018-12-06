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
		
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0101','01','监控中心');
		d.add('010101','0101','节点总览','${pageContext.request.contextPath}/chart/deviceSumDisplay.jsp','','mainFrame');//在右侧打开
		d.add('010102','0101','节点分布','${pageContext.request.contextPath}/chart/deviceLocationDisplay.jsp','','mainFrame');//在右侧打开
		d.add('010103','0101','动态监测','${pageContext.request.contextPath}/chart/deviceDataDisplay.jsp','','mainFrame');//在右侧打开
		d.add('0102','01','设备管理');
		d.add('010202','0102','设备状态列表','${pageContext.request.contextPath}/getAllDeviceState?pageNumber=1','','mainFrame');
		d.add('010202','0102','设备使用情况','${pageContext.request.contextPath}/getAllDevice?pageNumber=1','','mainFrame');
		d.add('010203','0102','设备监测记录','${pageContext.request.contextPath}/getAllCheckResult?pageNumber=1','','mainFrame');
		d.add('0103','01','用户管理');
		d.add('010301','0103','用户管理','${pageContext.request.contextPath}/getAllUser?pageNumber=1','','mainFrame');
		d.add('0104','01','订单管理');
		d.add('010401','0104','订单管理','${pageContext.request.contextPath}/getAllOrder?pageNumber=1','','mainFrame');//在右侧打开
		//d.add('0105','01','广告管理');
		//d.add('010501','0105','广告管理','${pageContext.request.contextPath}/getAllAdvert?pageNumber=1','','mainFrame');
		d.add('0106','01','报表统计管理');
		d.add('010601','0106','总订单','${pageContext.request.contextPath}/chart/orderInfoDisplay.jsp','','mainFrame');
		d.add('010602','0106','积分订单','${pageContext.request.contextPath}/chart/orderRepointInfoDisplay.jsp','','mainFrame');
		d.add('010603','0106','VIP订单','${pageContext.request.contextPath}/chart/orderVIPInfoDisplay.jsp','','mainFrame');
		document.write(d);//相当于HTML的显示
		//
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>E-MAN 电子书推荐社区</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/3.3.7/css/bootstrap.min.css'/>">
<script src="<c:url value='/resources/jquery/2.1.1/jquery.min.js'/>"></script>
<script
	src="<c:url value='/resources/bootstrap/3.3.7/js/bootstrap.min.js'/>"></script>
<script>
var uid = '<%=session.getAttribute("uid")%>';
var state = '<%=session.getAttribute("state")%>';

	$(document).ready(function() {
		// 开始写 jQuery 代码...

	});
</script>
<span style="font-size:18px;"></span>
<span style="font-size:24px;">
	<meta http-equiv="refresh" content="3;URL=http://localhost:8080/EMAN">
</span>  
</head>
  
  <body>
    success. 3秒后跳转到主页 <br>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    Welcome,Spring MVC. <br>
    <table border="1">
    	<thead>
    		<tr>
    			<td>部门id</td>
    			<td>部门名称</td>
    			<td>所在城市</td>
    			<td>工资总额</td>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${list}" var="dept">
    			<tr>
    				<td>${dept.deptno}</td>
    				<td>${dept.dname}</td>
    				<td>${dept.loc}</td>
    				<td>${dept.salary}</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
    <c:forEach items="${deptList }" var="str">
    	${str }<br/>
    </c:forEach>
  </body>
</html>

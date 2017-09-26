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
    			<td>eid</td>
    			<td>ISBN</td>
    			<td>name</td>
    			<td>author</td>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${list}" var="ebook">
    			<tr>
    				<td>${ebook.eid}</td>
    				<td>${ebook.ISBN}</td>
    				<td>${ebook.name}</td>
    				<td>${ebook.author}</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
    <c:forEach items="${deptList}" var="str">
    	${str }<br/>
    </c:forEach>
  </body>
</html>

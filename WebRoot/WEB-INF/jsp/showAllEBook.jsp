<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script>
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	function nextPage(){
		var start = GetQueryString("start");
		start+=20;
		location.href=window.location.href + "start=" + start;
	}
</script>
</head>

<body>
	Welcome,全部电子书列表.
	<br>
	<table border="1">
		<thead>
			<tr>
				<td>eid</td>
				<td>eid</td>
				<td>ISBN</td>
				<td>ename</td>
				<td>author</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="ebook">
				<tr>
					<td><img src="${ebook.imgAddress}" alt="${ebook.imgAddress}"></img></td>
					<td>${ebook.eid}</td>
					<td>${ebook.ISBN}</td>
					<td>${ebook.ename}</td>
					<td>${ebook.author}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="ebook/queryAllEBookLimit.htm?start=${start}">下一页</a>
</body>
</html>

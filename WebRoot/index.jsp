<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    This is my JSP page. <br>
    <a href="dept/queryAllDept.htm">dept/queryAllDept.htm</a><br/>
	<p>dept/queryDept.htm</p>
    <form name="form1" action="dept/queryDept.htm" method="post">
    	<input type="text" name="deptno">
    	<input type="submit" value="提交"><br/>
    </form>
    <p>dept/editDeptSalary.htm</p>
    <form name="form2" action="dept/editDeptSalary.htm" method="post">
    	deptno<input type="text" name="deptno">
    	loc<input type="text" name="loc">
    	salary<input type="text" name="salary">
    	<input type="submit" value="提交"><br/>
    </form>
	This is my JSP page. <br>
	<a href="ebook/queryAllEBook.htm">ebook/queryAllEBook.htm</a><br/>
	<p>ebook/queryAllEBookLimit.htm</p>
	<form name="form3" action="ebook/queryAllEBookLimit.htm" method="get">
    	<input type="text" name="start">
    	<input type="submit" value="提交"><br/>
    </form>
  </body>
</html>

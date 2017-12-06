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
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
</head>

<body>
	Welcome,全部电子书列表.
	<input id="eid" type="text" />
	<button id="btget">获取</button>
	<br>
	<table border="1">
		<thead>
			<tr>
				<td>eid</td>
				<td>ISBN</td>
				<td>ename</td>
				<td>author</td>
				<td>publishingHouse</td>
			</tr>
		</thead>
		<tbody id="tb">
			<img id="imgad"></img>
		</tbody>
	</table>
	
<script>
	$('#btget').on('click', function(){
		var val = $('#eid').val();
		getData(val);
	})
	
	function getData(value){
		$.ajax({
		url: "http://localhost:8080/EMAN/ebook/query.htm?eid=" + value,
		methods: "get",
		acceptType: "application/json",
		success: function(data){
			var obj = $.parseJSON(data);
			$('#imgad').attr('src', 'http://localhost:8080/EMANImgs/'+obj.imgAddress);
			$('#tb').html('<tr><td>' + obj.eid +  '</td><td>' + obj.ISBN +  '</td><td>' + obj.ename +  '</td><td>' + obj.author +  '</td><td>' + obj.publishingHouse +  '</td></tr>');
		},
		error: function(xhr){
			console.log(xhr);
		}
	
	})
	
	}

</script>
</body>
</html>

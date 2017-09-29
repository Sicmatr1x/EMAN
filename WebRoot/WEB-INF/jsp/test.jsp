<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta charset="UTF-8">
<title>Basic DataGrid - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/easyui/themes/icon.css'/>">
<script type="text/javascript"
	src="<c:url value='/resources/easyui/jquery.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/easyui/jquery.easyui.min.js'/>"></script>


</head>

<body>
	Welcome,test.
	<br>
	<div id="p" class="easyui-panel"
		style="width:500px;height:200px;padding:10px;" title="My Panel"
		iconCls="icon-save" collapsible="true">The panel content</div>

	<p>Welcome,全部电子书列表.</p>
	<table class="easyui-datagrid" title="Basic DataGrid"
		style="width:700px;height:250px"
		data-options="singleSelect:true,collapsible:true,url:'queryAllEBookLimitJson.htm?start=0',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'eid',width:100">eid</th>
				<th data-options="field:'name',width:100">name</th>
				<th data-options="field:'author',width:100,align:'center'">author</th>
				<th data-options="field:'publishingHouse',width:100,align:'right'">publishingHouse</th>
				<th data-options="field:'words',width:100">words</th>
				<th data-options="field:'ratingValue',width:100,align:'center'">ratingValue</th>
			</tr>
		</thead>
	</table>

</body>
</html>

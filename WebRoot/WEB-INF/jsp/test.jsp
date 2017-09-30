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
		style="width:1000px;height:500px"
		data-options="singleSelect:true,collapsible:true,url:'queryAllEBookLimitJson.htm?start=0',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'eid',width:100">eid</th>
				<th data-options="field:'name',width:200">name</th>
				<th data-options="field:'author',width:100,align:'center'">author</th>
				<th data-options="field:'publishingHouse',width:200,align:'right'">publishingHouse</th>
				<th data-options="field:'words',width:100">words</th>
				<th data-options="field:'ratingValue',width:100,align:'center'">ratingValue</th>
			</tr>
		</thead>
	</table>
	<p>Welcome,全部电子书列表.</p>
	<table id="dg" title="Client Side Pagination" style="width:700px;height:300px" data-options="
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,
				url:'queryAllEBookLimitJson.htm?start=0',
				method:'get'">
		<thead>
			<tr>
				<th field="eid" width="80">eid</th>
				<th field="name" width="100">name</th>
				<th field="author" width="80">author</th>
				<th field="publishingHouse" width="80" align="right">publishingHouse</th>
				<th field="words" width="80" align="right">words</th>
				<th field="ratingValue" width="100" align="right">ratingValue</th>
				<th field="reviewCount" width="110">reviewCount</th>
			</tr>
		</thead>
	</table>
	<script>
		function getData(){
			var rows = [];
			for(var i=1; i<=800; i++){
				var amount = Math.floor(Math.random()*1000);
				var price = Math.floor(Math.random()*1000);
				rows.push({
					inv: 'Inv No '+i,
					date: $.fn.datebox.defaults.formatter(new Date()),
					name: 'Name '+i,
					amount: amount,
					price: price,
					cost: amount*price,
					note: 'Note '+i
				});
			}
			return rows;
		}
		
		function pagerFilter(data){
			if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
				data = {
					total: data.length,
					rows: data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage:function(pageNum, pageSize){
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh',{
						pageNumber:pageNum,
						pageSize:pageSize
					});
					dg.datagrid('loadData',data);
				}
			});
			if (!data.originalRows){
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}
		
		$(function(){
			$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
		});
	</script>
</body>
</html>

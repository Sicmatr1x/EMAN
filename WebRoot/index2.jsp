<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>E-MAN 电子书推荐社区</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		// 开始写 jQuery 代码...
		// $("#mouseenterActive").mouseenter(function () {
		// 	$("#mouseenterActive").addClass("active");
		// });

	});
</script>
</head>

<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<a class="navbar-brand" href="https://github.com/Sicmatr1x"><span
							class="glyphicon glyphicon-home"></span> Home</a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li>
								<!-- class="active" --> <a href="articleList.html">Article
									Type</a>
							</li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" id="question" class="form-control"
									placeholder="search stackoverflow"></input>
							</div>
							<button type="submit" id="bt-navbar-form-search"
								class="btn btn-default">Search</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">ToolBox<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li class="divider"></li>
									<li><a href="#">Separated link</a></li>
								</ul></li>
						</ul>
					</div>

				</nav>
				<!--navbar last change 20170123-->
				<div class="jumbotron">
					<h1>E-MAN</h1>
					<p>电子书推荐社区：读好书，好读书</p>
					<p>
						<a class="btn btn-primary btn-large"
							href="https://github.com/Sicmatr1x" data-toggle="tooltip"
							title="关于我">About Me</a>
					</p>
				</div>
			</div>
		</div>



		<div class="row clearfix">
			<div class="container">
				<h2>全区排行榜</h2>
				<p>.table-bordered 类为所有表格的单元格添加边框:</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>No.</th>
							<th>Firstname</th>
							<th>Firstname</th>
							<th>Firstname</th>
							<th>Firstname</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="ebook">
							<tr>
								<td><img src="${ebook.imgAddress}" alt="${ebook.imgAddress}"></img></td>
								<td>${ebook.eid}</td>
								<td>${ebook.ISBN}</td>
								<td>${ebook.name}</td>
								<td>${ebook.author}</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>


	</div>

	
</body>
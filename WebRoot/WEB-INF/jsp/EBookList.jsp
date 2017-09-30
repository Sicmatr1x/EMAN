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


<script type="text/javascript"
	src="<c:url value='/resources/easyui/jquery.min.js'/>"></script>
<!--<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>">-->
<!-- <script type="text/javascript"
	src="<c:url value='/resources/bootstrap/js/popper.js'/>"></script> -->
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap/bootstrap.min.js'/>"></script>


<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->

<script>
	
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
						<!-- <li>
								<a href="2">Link</a>
							</li> -->
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" data-toggle="tooltip" title="常用文章">Quick
								Visit<strong class="caret"></strong>
						</a>
							<ul class="dropdown-menu">
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/Hadoop/Hadoop.md">Hadoop
										技术栈</a></li>
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/Oracle_PL_SQL/Oracle_PL.md">Oracle
										PL</a></li>
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/Oracle_PL_SQL/Oracle_SQL.md">Oracle
										SQL</a></li>
								<li class="divider">
									<!-- 分割线 -->
								</li>
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/Bootstrap/Bootstrap.md">Bootstrap</a>
								</li>
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/jQuery/jQuery.md">jQuery</a>
								</li>
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/jQuery%20UI/jQuery%20UI.md">jQuery
										UI</a></li>
								<li class="divider">
									<!-- 分割线 -->
								</li>
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/blob/master/coding/Java/Java%E5%A4%8D%E4%B9%A0%E7%AC%94%E8%AE%B0.md">Java
										复习笔记</a></li>

							</ul></li>
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
						<!-- <li>
								<a href="#">HelpLink</a>
							</li> -->
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">HelpLink<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="http://www.w3school.com.cn/index.html"><span
										class="glyphicon glyphicon-book"></span> w3school</a></li>
								<li><a href="http://www.runoob.com/"><span
										class="glyphicon glyphicon-book"></span> runoob</a></li>
								<li class="divider"></li>
								<li><a href="https://github.com/"><span
										class="glyphicon glyphicon-home"></span> github</a></li>
								<li><a href="https://stackoverflow.com/"><span
										class="glyphicon glyphicon-home"></span> stackoverflow</a></li>
								<li class="divider">
								<li><a href="https://www.mysql.com/"><span
										class="glyphicon glyphicon-save"></span> mysql</a></li>
								<li><a
									href="http://www.oracle.com/technetwork/java/javase/downloads/index.html"><span
										class="glyphicon glyphicon-save"></span> JavaSE</a></li>
								<li><a
									href="http://www.oracle.com/technetwork/java/javaee/downloads/index.html"><span
										class="glyphicon glyphicon-save"></span> JavaEE</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">ToolBox<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a
									href="https://github.com/Sicmatr1x/Sicmatr1x.github.io/coding/easyUI/jquery-easyui-1.5.3/demo/datagrid/custompager.html">test
										easyUI demo</a></li>
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
					<h1>Sicmatr1x's Blog</h1>
					<p>This is a template for a simple marketing or informational
						website. It includes a large callout called the hero unit and
						three supporting pieces of content. Use it as a starting point to
						create something more unique.</p>
					<p>
						<a class="btn btn-primary btn-large"
							href="https://github.com/Sicmatr1x" data-toggle="tooltip"
							title="关于我">About Me</a>
					</p>
				</div>
			</div>
		</div>



		<div class="row clearfix">

			<div class="col-md-4 column">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="#">View details »</a>
				</p>
			</div>
			<div class="col-md-4 column">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="#">View details »</a>
				</p>
			</div>
			<div class="col-md-4 column">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="#">View details »</a>
				</p>
			</div>
		</div>


	</div>

	<script>
		$(function() {
			$('#bt-navbar-form-search').click(
					function() {
						if ($('#question').val() == ''
								|| $('#question').val() == null) {
							alert("illegal argument");
							return;
						}
						// location.href = 'https://stackoverflow.com/search?q=' + $('#question').val(); // 不知道为什么不行
						window.open('https://stackoverflow.com/search?q='
								+ $('#question').val());
					});

		});
	</script>
</body>
</html>

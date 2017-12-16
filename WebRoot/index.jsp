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

	});
</script>
</head>

<body>
	<div class="container">
		<!-- 导航栏 -->
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
			</div>
		</div>

		<!-- 轮播 -->
		<div id="myCarousel" class="carousel slide">
			<!-- 轮播（Carousel）指标 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="/wp-content/uploads/2014/07/slide1.png" alt="First slide">
					<div class="carousel-caption">标题 1</div>
				</div>
				<div class="item">
					<img src="/wp-content/uploads/2014/07/slide2.png"
						alt="Second slide">
					<div class="carousel-caption">标题 2</div>
				</div>
				<div class="item">
					<img src="/wp-content/uploads/2014/07/slide3.png" alt="Third slide">
					<div class="carousel-caption">标题 3</div>
				</div>
			</div>
			<!-- 轮播（Carousel）导航 -->
			<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
		</div>

	</div>
	<script>
		$(document).on('ready', function() {

		});
	</script>
</body>
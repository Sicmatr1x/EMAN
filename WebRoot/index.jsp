<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>E-MAN 电子书推荐社区</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/3.3.7/css/bootstrap.min.css'/>">
<script src="<c:url value='/resources/jquery/2.1.1/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/bootstrap/3.3.7/js/bootstrap.min.js'/>"></script>
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
						<a class="navbar-brand" href="#"><span
							class="glyphicon glyphicon-home"></span> EMAN</a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							
							
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" data-toggle="tooltip" title="类型"> 类型<strong class="caret"></strong>
							</a>
								<ul class="dropdown-menu">
									<li><a target="view_window"
										href="<c:url value='ebook/list.htm?classifyMain=小说&start=0'/>">小说</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=文学&start=0'/>">文学</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=人文社科&start=0'/>">人文社科</a></li>
									<li class="divider">
										<!-- 分割线 -->
									</li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=经济管理&start=0'/>">经济管理</a>
									</li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=科技科普&start=0'/>">科技科普</a>
									</li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=计算机与互联网&start=0'/>">计算机与互联网</a></li>
									<li class="divider">
										<!-- 分割线 -->
									</li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=成功励志&start=0'/>">成功励志</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=生活&start=0'/>">生活</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=少儿&start=0'/>">少儿</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=艺术设计&start=0'/>">艺术设计</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=漫画绘本&start=0'/>">漫画绘本</a></li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=教育考试&start=0'/>">教育考试</a></li>
									<li class="divider">
										<!-- 分割线 -->
									</li>
									<li><a
										href="<c:url value='ebook/list.htm?classifyMain=杂志&start=0'/>">杂志</a></li>

								</ul>
							</li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" id="keyword" class="form-control"
									placeholder="图书搜索"></input>
							</div>
							<button id="bt-navbar-form-search"
								class="btn btn-default">搜索</button>
						</form>
						
						
						<ul class="nav navbar-nav navbar-right">
							<!-- 用户登录模块 -->
							<li>
							<%
							String uname = (String)session.getAttribute("uname");
							String state = (String)session.getAttribute("state");
							if(state != null && state.equals("login")){
								out.println("<a href=\"user/logout.htm\">" + uname + "注销" + "</a>");
							}else{
								out.println("<a href=\"user/login.htm\">" + "登录" + "</a>");
							}
							%>
							</li>
						
							
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
			$("#bt-navbar-form-search").click(function(){
				var keyword = $("#keyword").val();
				console.log("keyword=" + keyword);
				//alert("<c:url value='/'/>" + "ebook/searchKeyword.htm?start=0&keyword=" + keyword);
				window.open("<c:url value='ebook/searchKeyword.htm?start=0&keyword='/>" + keyword);
		    });
		});
	</script>
</body>
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
		<jsp:include page="WEB-INF/jsp/head.jsp"/>
		

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
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>E-MAN 电子书推荐社区</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/3.3.7/css/bootstrap.min.css'/>">
<script src="<c:url value='/resources/jquery/2.1.1/jquery.min.js'/>"></script>
<script
	src="<c:url value='/resources/bootstrap/3.3.7/js/bootstrap.min.js'/>"></script>
<script>
	$(document).ready(function() {
		// 开始写 jQuery 代码...

	});
</script>
</head>

<body>
	<div class="container">

		<!-- 导航栏 -->
		<jsp:include page="WEB-INF/jsp/head.jsp" />


		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<ul class="nav nav-tabs nav-stacked">
						<li class="nav-header">分类</li>
						<hr>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=小说&start=0'/>">小说</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=文学&start=0'/>">文学</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=人文社科&start=0'/>">人文社科</a></li>
						<li class="divider">
							<!-- 分割线 -->
						</li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=经济管理&start=0'/>">经济管理</a>
						</li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=科技科普&start=0'/>">科技科普</a>
						</li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=计算机与互联网&start=0'/>">计算机与互联网</a></li>
						<li class="divider">
							<!-- 分割线 -->
						</li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=成功励志&start=0'/>">成功励志</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=生活&start=0'/>">生活</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=少儿&start=0'/>">少儿</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=艺术设计&start=0'/>">艺术设计</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=漫画绘本&start=0'/>">漫画绘本</a></li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=教育考试&start=0'/>">教育考试</a></li>
						<li class="divider">
							<!-- 分割线 -->
						</li>
						<li><a
							href="<c:url value='http://localhost:8080/EMAN/ebook/list.htm?classifyMain=杂志&start=0'/>">杂志</a></li>

					</ul>
				</div>

				<div class="col-md-7">
					<!-- 轮播面板 -->
					<div class="panel panel-default">
						<div class="panel-body">
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
										<img
											src="https://img3.doubanio.com/view/ark_campaign_pic/large/public/5392.jpg"
											alt="First slide" />
										<div class="carousel-caption">标题 1</div>
									</div>
									<div class="item">
										<img
											src="https://img3.doubanio.com/view/ark_campaign_pic/large/public/5401.jpg"
											alt="Second slide" />
										<div class="carousel-caption">标题 2</div>
									</div>
									<div class="item">
										<img
											src="https://img3.doubanio.com/view/ark_campaign_pic/large/public/5406.jpg"
											alt="Third slide" />
										<div class="carousel-caption">标题 3</div>
									</div>
								</div>
								<!-- 轮播（Carousel）导航 -->
								<a class="carousel-control left" href="#myCarousel"
									data-slide="prev">&lsaquo;</a> <a
									class="carousel-control right" href="#myCarousel"
									data-slide="next">&rsaquo;</a>
							</div>
						</div>
					</div>

					<h3>热门推荐</h3>
					<hr />
					<!-- 你的评论 -->
					<div class="panel panel-default" id="myDescribe-panel">
						<div class="panel-heading">
							<h3 class="panel-title">小说</h3>
						</div>
						<div class="panel-body" id="myDescribe-div">
							<div class="row">
								<div class="col-md-3">
									<div class="panel panel-default" id="myDescribe-panel">
										<div style="margin:0px auto">
											<img width="110px" height="164px"
												src="http://localhost:8080/EMANImgs/958945.jpg"
												onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
											<p>三体</p>
											<p>刘慈欣</p>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="panel panel-default" id="myDescribe-panel">
										<img width="110px" height="164px"
											src="http://localhost:8080/EMANImgs/958945.jpg"
											onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
										<p>三体</p>
										<p>刘慈欣</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="panel panel-default" id="myDescribe-panel">
										<img width="110px" height="164px"
											src="http://localhost:8080/EMANImgs/958945.jpg"
											onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
										<p>三体</p>
										<p>刘慈欣</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="panel panel-default" id="myDescribe-panel">
										<img width="110px" height="164px"
											src="http://localhost:8080/EMANImgs/958945.jpg"
											onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
										<p>三体</p>
										<p>刘慈欣</p>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>


			</div>


			<hr>

			<footer>
				<p>© Company 2018</p>
			</footer>

		</div>

	</div>
	<script>
		function getlist(classifyMain) {
			$.ajax({
				url : "/EMAN/ebook/getList.htm",
				type : "get",
				data : "classifyMain=小说",
				dataType : "json",
				success : function(data) {

				},
				error : function() {
					alert("ajax请求失败");
				}
			});
		}

		$(document).on('ready', function() {

		});
	</script>
</body>
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
<script src="<c:url value='/resources/chart/Chart.js'/>"
            type="text/javascript"></script>
<script src="<c:url value='/resources/chart/Chart.bundle.min.js'/>"
            type="text/javascript"></script>

<script>
	$(document).ready(function() {

	    // 冷门图书推荐
        $.ajax({
            url : "/EMAN/ebook/coldEBooks.htm",
            type : "get",
            // data : "classifyMain=小说",
            dataType : "json",
            success : function(data) {
                var listObject = data;
                var clone = example;
                $("#coldEBook-panel").empty();

                if (listObject.length < 1) { // 若无数据
                    $("#coldEBook-panel").append("<p>暂无推荐</p>");
                }

                for (var i = 0; i < listObject.length && i < 4; i++) {
                    var cloneDiv = clone.clone();
                    cloneDiv.attr("id", "book" + (i + 1));
                    cloneDiv.find("img").attr("src", "http://localhost:8080/EMANImgs/" + listObject[i].imgAddress);
                    cloneDiv.find("#ename").attr("href", "http://localhost:8080/EMAN/ebook/info.htm?eid=" + listObject[i].eid);
                    cloneDiv.find("#ename").text(listObject[i].ename);

                    cloneDiv.find("#author").text(listObject[i].author);

                    $("#coldEBook-panel").append(cloneDiv);
                }

            },
            error : function() {
                alert("冷门图书推荐ajax请求失败");
            }
        });

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
										<a href="http://localhost:8080/EMAN/ebook/info.htm?eid=31844036">
										<img src="http://localhost:8080/EMANImgs/lb-5559.jpg" alt="First slide" /></a>
										</div>
									<div class="item">
										<img
											src="http://localhost:8080/EMANImgs/lb-5556.jpg"
											alt="Second slide" />
									</div>
									<div class="item">
										<img
											src="http://localhost:8080/EMANImgs/lb-5558.jpg"
											alt="Third slide" />
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

					<h3>每日冷门佳作推荐</h3>
					<hr />
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row" id="coldEBook-panel">
								<p>暂无数据</p>
							</div>
						</div>
					</div>

					
					<h3>来自你感兴趣的分区...</h3>
					<hr />
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row" id="favorite-panel">
								<p>登录后显示</p>
							</div>
						</div>
					</div>

					<h3>分区推荐</h3>
					<hr />
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">小说</h3>
						</div>
						<div class="panel-body">
							<div class="row" id="xiaoshou-panel">
								<div class="col-md-3" id="book-example-div">
									<div class="panel panel-default">
										<div style="margin:0px auto">
											<img width="100%"
												 src="#"
												 onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
											<a href="#" id="ename"></a>
											<p id="author"></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">文学</h3>
						</div>
						<div class="panel-body">
							<div class="row" id="wenxue-panel">

							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">人文社科</h3>
						</div>
						<div class="panel-body">
							<div class="row" id="renwensheke-panel">

							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">经济管理</h3>
						</div>
						<div class="panel-body">
							<div class="row" id="jinjiguanli-panel">

							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">科技科普</h3>
						</div>
						<div class="panel-body">
							<div class="row" id="kejikepu-panel">

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
        var example = $("#book-example-div").clone();
        var uid = '<%=session.getAttribute("uid")%>';
        var state = '<%=session.getAttribute("state")%>';

		function getlist(args, panelId) {
			$.ajax({
				url : "/EMAN/ebook/getList.htm",
				type : "get",
				data : args,
				dataType : "json",
				success : function(data) {
					var listObject = data;
					var clone = example;
					$("#" + panelId).empty();
	
					if (listObject.length < 1) { // 若无数据
						$("#" + panelId).append("<p>暂无推荐</p>");
					}
	
					for (var i = 0; i < listObject.length && i < 4; i++) {
						var cloneDiv = clone.clone();
						cloneDiv.attr("id", "book" + (i + 1));
						cloneDiv.find("img").attr("src", "http://localhost:8080/EMANImgs/" + listObject[i].imgAddress);
						cloneDiv.find("#ename").attr("href", "http://localhost:8080/EMAN/ebook/info.htm?eid=" + listObject[i].eid);
						cloneDiv.find("#ename").text(listObject[i].ename);
	
						cloneDiv.find("#author").text(listObject[i].author);
	
						$("#" + panelId).append(cloneDiv);
					}
	
				},
				error : function() {
					alert("ajax请求失败");
				}
			});
		}
	
		$(document).on('ready', function() {
			getlist("classifyMain=小说&start=0&orderCondition=ratingValue&order=desc", "xiaoshou-panel");
			getlist("classifyMain=文学&start=0&orderCondition=ratingValue&order=desc", "wenxue-panel");
			getlist("classifyMain=人文社科&start=0&orderCondition=ratingValue&order=desc", "renwensheke-panel");
			getlist("classifyMain=经济管理&start=0&orderCondition=ratingValue&order=desc", "jinjiguanli-panel");
			getlist("classifyMain=科技科普&start=0&orderCondition=ratingValue&order=desc", "kejikepu-panel");
			if(uid !== 'null'){ // 若用户已登录
                getlist("uid="+uid, "favorite-panel");
			}

		});

	</script>
</body>
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
		/*<c:forEach items="${list}" var="ebook">
			$("${ebook.imgAddress}").load("${ebook.imgAddress}");
		</c:forEach>*/

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
				<h2>全区排行榜TOP20</h2>
				<table class="table table-bordered">
					<c:forEach items="${list}" var="ebook">

						<div class="panel panel-default">
							
							<div class="panel-heading">
								<h2 class="panel-title">${ebook.ename}</h2>
							</div>
							
							<div class="panel-body">
								<div style="float: left;">
									<img width="110px" height="164px" src="http://localhost:8080/EMANImgs/${ebook.imgAddress}"
										alt="10000464.jpg">
								</div>
								<div class="row">
									<div class="col-md-1">
										<h1>${ebook.ratingValue}</h1>
										<c:if test="${ebook.ratingValue == null}">		
												暂无评分
										</c:if>
									</div>
									<div class="col-md-3">
										<p>分类：${ebook.classifyMain}</p>
										<p>作者：${ebook.author}</p>
										<p>出版社：${ebook.publishingHouse}</p>
										<p>
											评分人数：
											<c:if test="${ebook.reviewCount != null}">
												<c:out value="${ebook.reviewCount}" />
											</c:if>
											<c:if test="${ebook.reviewCount == null}">		
												评分人数不足
											</c:if>
										</p>

									</div>
									<div class="progress" style="width: 30%;">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="30" aria-valuemin="0"
										aria-valuemax="50" style="width: 50%;">
										<span class="sr-only">90% 完成（成功）</span>
									</div>
								</div>
								</div>
								
							</div>
						</div>

					</c:forEach>
				</table>

			</div>
		</div>


	</div>


</body>
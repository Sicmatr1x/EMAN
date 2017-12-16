<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.entity.EBook" %>
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

<link href="<c:url value='/resources/star-rating/css/star-rating.min.css'/>" media="all" rel="stylesheet" type="text/css" />
<script src="<c:url value='/resources/star-rating/js/star-rating.min.js'/>" type="text/javascript"></script>

<script>
	$(document).ready(function() {
		// 开始写 jQuery 代码...

	});
</script>
</head>

<body>
	<!-- 面包屑导航栏 TODO: -->
	<ol class="breadcrumb">
		<li><a href="<c:url value='/'/>">主页</a></li>
		<li><a href="list.htm?classifyMain=${ebook.classifyMain}&start=0">${ebook.classifyMain}</a></li>
	</ol>

	<div class="container">
		<!-- 图书信息面板 -->
		<div class="panel panel-default"><!-- 带标题的面板 -->
			<div class="panel-heading">
				<h3 class="panel-title">${ebook.ename}</h3>
			</div>
			
			<div class="panel-body">
				<div style="float: left;">
					<img src="http://localhost:8080/EMANImgs/${ebook.imgAddress}"
						class="img-responsive"
						alt="http://localhost:8080/EMANImgs/${ebook.imgAddress}"
						width="170px" />
				</div>
				<div class="row">
					<div class="col-md-4">
						<p>作者:${ebook.author}</p>
						<p>
							类别:
							<c:if test="${ebook.category != 'null'}">
								<c:out value="${ebook.category}" />
							</c:if>
							<c:if test="${ebook.category == 'null'}">		
												暂无信息
							</c:if>
						</p>
						<p>
							出版社:
							<c:if test="${ebook.publishingHouse != 'null'}">
								<c:out value="${ebook.publishingHouse}" />
							</c:if>
							<c:if test="${ebook.publishingHouse == 'null'}">		
												暂无信息
							</c:if>
						</p>
						<p>
							提供方:
							<c:if test="${ebook.provider != 'null'}">
								<c:out value="${ebook.provider}" />
							</c:if>
							<c:if test="${ebook.provider == 'null'}">		
												暂无信息
							</c:if>
						</p>
						<p>
							字数:约
							<c:if test="${ebook.words != null}">
								<c:out value="${ebook.words}" />
							</c:if>
							<c:if test="${ebook.words == null}">		
												暂无信息
							</c:if>
							字
						</p>
						
						<p>
							ISBN:
							<c:if test="${ebook.ISBN != 'null'}">
								<c:out value="${ebook.ISBN}" />
							</c:if>
							<c:if test="${ebook.ISBN == 'null'}">		
												暂无信息
							</c:if>
						</p>
						<p>分类：${ebook.classifyMain}</p>

					</div>
					
					<div class="col-md-4">
						<input id="input-ratingValue" type="number" value="<%
							/*将0~10之间的评分化为0~5之间的评分*/
							EBook ebook = (EBook)request.getAttribute("ebook");
							double t = (ebook.getRatingValue()/2);
							int i = (int)t;
							double f = t - i;
							if(f >= 0.8)
								f=1;
							else if(f < 0.3)
								f=0;
							else
								f=0.5;
							double rating=i + f;
							out.print(rating);
						%>"  />
						
						<p>
							综合评分:
							<c:if test="${ebook.ratingValue != null}">
								<c:out value="${ebook.ratingValue}" />
							</c:if>
							<c:if test="${ebook.ratingValue == null}">		
												暂无信息
							</c:if>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<c:if test="${ebook.reviewCount != null}">
								<c:out value="${ebook.reviewCount}" />
								人评价
							</c:if>
							<c:if test="${ebook.reviewCount == null}">		
												评分人数不足
							</c:if>
						</p>
						
						<input id="rating-input" type="number" />
						
					</div>
				</div>
			</div>
		</div>
		
		<!-- 图书简介面板 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					作品信息
				</h3>
			</div>
			<div class="panel-body">
				${ebook.description}
			</div>
		</div>
		
		<!-- 搜索图书面板 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					搜索图书
				</h3>
			</div>
			
			<div class="row">
				<div class="col-md-1">
					<div class="panel-body">
						<button id="gojd-btn" class="btn btn-primary" type="button">前往京东</button>
					</div>
				</div>
				
				<div class="col-md-1">
					<div class="panel-body">
						<button id="gotaobao-btn" class="btn btn-primary" type="button">前往淘宝</button>
					</div>
				</div>
				
				<div class="col-md-1">
					<div class="panel-body">
						<button id="gotianmao-btn" class="btn btn-primary" type="button">前往天猫</button>
					</div>
				</div>
				
				<div class="col-md-1">
					<div class="panel-body">
						<button id="gozhangyue-btn" class="btn btn-primary" type="button">前往掌阅书库</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 推荐面板 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					猜你喜欢...
				</h3>
			</div>
			<div class="panel-body">
				推荐预留位
			</div>
		</div>
		
		<!-- 评论面板 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					评论
				</h3>
			</div>
			<div class="panel-body">
				评论预留位
			</div>
		</div>
		
	</div><!-- container -->
	
<script>
	$(document).on('ready', function () {
		/* 图书评分星星插件 */
		/*显示综合评分*/
    	$("#input-ratingValue").rating({
    		min: 0,
    		max:5,
    		step: 0.5,
    		size: 'xs',
    		readonly: true,
    		showClear: false
    	});
    	/*登录用户评分*/
    	$('#rating-input').rating({
            min: 0,
            max: 5,
            step: 0.5,
            size: 'xs',
            showClear: false
        });
    	/*评分修改触发函数*/
    	$('#rating-input').on('rating.change', function(event, value, caption) {
    		//检查用户是否登录,若登录且未评分则可以发送评分请求,否则弹出相应提示
    	    console.log(value);
    	    console.log(caption);
    	});
    	
    	
	});
	
    $(function() {
    	
    	/* 搜索图书面板按钮动作 */
        $("#gojd-btn").click(function(){
        	window.location.href = "https://search.jd.com/Search?keyword=" + "${ebook.ename}" + "&enc=utf-8";  
        });
        
        $("#gotaobao-btn").click(function(){
        	window.location.href = "https://s.taobao.com/search?q=" + "${ebook.ename}" + "&ie=utf8";
        });
        
        $("#gotianmao-btn").click(function(){
        	window.location.href = "https://list.tmall.com/search_product.htm?q=" + "${ebook.ename}";
        });
        
        $("#gozhangyue-btn").click(function(){
        	window.location.href = "http://www.ireader.com/index.php?ca=search.index&pca=bookdetail.index&keyword=" + "${ebook.ename}";
        });
        
    });  
</script>
</body>
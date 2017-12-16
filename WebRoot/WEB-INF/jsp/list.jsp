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

		<div class="row clearfix">
			<div class="container">
				<h2>${classifyMain}</h2>
				<table class="table table-bordered">
					<!-- 开始遍历打印列表 -->
					<c:forEach items="${list}" var="ebook">

						<!-- 图书信息面板 -->
						<div class="panel panel-default">
							<!-- 面板头 -->
							<div class="panel-heading">
								<h2 class="panel-title">${ebook.ename}</h2>
							</div>
							<!-- 面板体 -->
							<div class="panel-body">
								<!-- 图片URL使用的是绝对的 TODO: -->
								<div style="float: left;">
									<img width="110px" height="164px"
										src="http://localhost:8080/EMANImgs/${ebook.imgAddress}"
										alt="10000464.jpg">
								</div>
								<div class="row">
									
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
									
									<div class="col-md-1">
										<h1>${ebook.ratingValue}</h1>
										<c:if test="${ebook.ratingValue == null}">		
												暂无评分
										</c:if>
									</div>
									
								</div>
							</div><!-- 面板体 -->
						</div><!-- 图书信息面板 -->
					</c:forEach>
				</table>

				<button id="backpage-btn" class="btn btn-primary" type="button">上一页</button>
				<button id="nextpage-btn" class="btn btn-primary" type="button">下一页</button>

			</div>
		</div>
	</div>
<script>
$(document).on('ready', function () {
	/* 翻页按钮动作 */
	$("#backpage-btn").click(function(){
    	console.log(${start});
    	window.location.href = "<c:url value='/ebook'/>/list.htm?classifyMain=${classifyMain}&start=<%
    		int t = (int)request.getAttribute("start");
    		if(t-40<=0)
    			t=0;
    		else
    			t-=40;
    		out.print(t);
    	%>";
    });
    $("#nextpage-btn").click(function(){
    	console.log(${start});
    	window.location.href = "<c:url value='/ebook'/>/list.htm?classifyMain=${classifyMain}&start=${start}";  
    });
});
</script>
</body>
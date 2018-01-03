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
		
		// 评分排序
		$("#ratingValue-btn").click(function(){
			var curURL = window.location.href;
			var nextURL;
	    	console.log(curURL);
	    	// 设置排序条件
	    	var orderConditionBegIndex = curURL.indexOf("orderCondition=", 0);
	    	if(orderConditionBegIndex > 0){ // 若存在排序条件
	    		var orderConditionEndIndex = curURL.indexOf("&", orderConditionBegIndex);
	    		if(orderConditionEndIndex > 0){ // 若参数在URL中间
	    			var work1 = curURL.slice(0, orderConditionBegIndex + "orderCondition=".length);
		    		var work2 = curURL.slice(orderConditionEndIndex, curURL.length);
	    		}else{ // 若参数在URL末尾
	    			var work1 = curURL.slice(0, orderConditionBegIndex + "orderCondition=".length);
		    		var work2 = "";
	    		}
	    		nextURL = work1 + "ratingValue" + work2;
	    		
	    	}else{ // 若不存在排序条件
	    		nextURL = curURL + "&orderCondition=ratingValue";
	    	}
	    	
	    	// 设置排序顺序
	    	var finalURL;
	    	var orderBegIndex = nextURL.indexOf("order=", 0);
	    	if(orderBegIndex > 0){ // 若存在排序顺序
	    		var orderEndIndex = nextURL.indexOf("&", orderBegIndex);
	    		if(orderEndIndex > 0){ // 若参数在URL中间
	    			var orderType = nextURL.slice(orderBegIndex + "order=".length, orderEndIndex);
		    		if(orderType == "desc"){
		    			orderType = "asc";
		    		}else{
		    			orderType = "desc";
		    		}
		    		var work3 = nextURL.slice(0, orderBegIndex + "order=".length);
		    		var work4 = nextURL.slice(orderEndIndex, nextURL.length);
	    		}else{ // 若参数在URL末尾
	    			var orderType = nextURL.slice(orderBegIndex + "order=".length, nextURL.length);
		    		if(orderType == "desc"){
		    			orderType = "asc";
		    		}else{
		    			orderType = "desc";
		    		}
		    		var work3 = nextURL.slice(0, orderBegIndex + "order=".length);
		    		var work4 = "";
	    		}
	    		
	    		//alert("orderEndIndex=" + orderEndIndex + ",nextURL.length=" + nextURL.length);
	    		finalURL = work3 + orderType + work4;
	    		
	    	}else{ // 若不存在排序顺序
	    		finalURL = nextURL + "&order=desc";
	    	}

	    	window.location.href = finalURL;
	    });
		
		// 阅读排序
		$("#reviewCount-btn").click(function(){
			var curURL = window.location.href;
			var nextURL;
	    	console.log(curURL);
	    	// 设置排序条件
	    	var orderConditionBegIndex = curURL.indexOf("orderCondition=", 0);
	    	if(orderConditionBegIndex > 0){ // 若存在排序条件
	    		var orderConditionEndIndex = curURL.indexOf("&", orderConditionBegIndex);
	    		if(orderConditionEndIndex > 0){ // 若参数在URL中间
	    			var work1 = curURL.slice(0, orderConditionBegIndex + "orderCondition=".length);
		    		var work2 = curURL.slice(orderConditionEndIndex, curURL.length);
	    		}else{ // 若参数在URL末尾
	    			var work1 = curURL.slice(0, orderConditionBegIndex + "orderCondition=".length);
		    		var work2 = "";
	    		}
	    		nextURL = work1 + "reviewCount" + work2;
	    		
	    	}else{ // 若不存在排序条件
	    		nextURL = curURL + "&orderCondition=reviewCount";
	    	}
	    	
	    	// 设置排序顺序
	    	var finalURL;
	    	var orderBegIndex = nextURL.indexOf("order=", 0);
	    	if(orderBegIndex > 0){ // 若存在排序顺序
	    		var orderEndIndex = nextURL.indexOf("&", orderBegIndex);
	    		if(orderEndIndex > 0){ // 若参数在URL中间
	    			var orderType = nextURL.slice(orderBegIndex + "order=".length, orderEndIndex);
		    		if(orderType == "desc"){
		    			orderType = "asc";
		    		}else{
		    			orderType = "desc";
		    		}
		    		var work3 = nextURL.slice(0, orderBegIndex + "order=".length);
		    		var work4 = nextURL.slice(orderEndIndex, nextURL.length);
	    		}else{ // 若参数在URL末尾
	    			var orderType = nextURL.slice(orderBegIndex + "order=".length, nextURL.length);
		    		if(orderType == "desc"){
		    			orderType = "asc";
		    		}else{
		    			orderType = "desc";
		    		}
		    		var work3 = nextURL.slice(0, orderBegIndex + "order=".length);
		    		var work4 = "";
	    		}
	    		
	    		//alert("orderEndIndex=" + orderEndIndex + ",nextURL.length=" + nextURL.length);
	    		finalURL = work3 + orderType + work4;
	    		
	    	}else{ // 若不存在排序顺序
	    		finalURL = nextURL + "&order=desc";
	    	}

	    	window.location.href = finalURL;
	    });
		
		// 字数排序
		$("#words-btn").click(function(){
			var curURL = window.location.href;
			var nextURL;
	    	console.log(curURL);
	    	// 设置排序条件
	    	var orderConditionBegIndex = curURL.indexOf("orderCondition=", 0);
	    	if(orderConditionBegIndex > 0){ // 若存在排序条件
	    		var orderConditionEndIndex = curURL.indexOf("&", orderConditionBegIndex);
	    		if(orderConditionEndIndex > 0){ // 若参数在URL中间
	    			var work1 = curURL.slice(0, orderConditionBegIndex + "orderCondition=".length);
		    		var work2 = curURL.slice(orderConditionEndIndex, curURL.length);
	    		}else{ // 若参数在URL末尾
	    			var work1 = curURL.slice(0, orderConditionBegIndex + "orderCondition=".length);
		    		var work2 = "";
	    		}
	    		nextURL = work1 + "words" + work2;
	    		
	    	}else{ // 若不存在排序条件
	    		nextURL = curURL + "&orderCondition=words";
	    	}
	    	
	    	// 设置排序顺序
	    	var finalURL;
	    	var orderBegIndex = nextURL.indexOf("order=", 0);
	    	if(orderBegIndex > 0){ // 若存在排序顺序
	    		var orderEndIndex = nextURL.indexOf("&", orderBegIndex);
	    		if(orderEndIndex > 0){ // 若参数在URL中间
	    			var orderType = nextURL.slice(orderBegIndex + "order=".length, orderEndIndex);
		    		if(orderType == "desc"){
		    			orderType = "asc";
		    		}else{
		    			orderType = "desc";
		    		}
		    		var work3 = nextURL.slice(0, orderBegIndex + "order=".length);
		    		var work4 = nextURL.slice(orderEndIndex, nextURL.length);
	    		}else{ // 若参数在URL末尾
	    			var orderType = nextURL.slice(orderBegIndex + "order=".length, nextURL.length);
		    		if(orderType == "desc"){
		    			orderType = "asc";
		    		}else{
		    			orderType = "desc";
		    		}
		    		var work3 = nextURL.slice(0, orderBegIndex + "order=".length);
		    		var work4 = "";
	    		}
	    		
	    		//alert("orderEndIndex=" + orderEndIndex + ",nextURL.length=" + nextURL.length);
	    		finalURL = work3 + orderType + work4;
	    		
	    	}else{ // 若不存在排序顺序
	    		finalURL = nextURL + "&order=desc";
	    	}

	    	window.location.href = finalURL;
	    });
		

	});
</script>
</head>

<body>
	<div class="container">
		
		
		
		<div class="row clearfix">
			<div class="container">
				<h2>${classifyMain}</h2>
				<p>共找到 <span id="p-count">${count}</span> 条结果</p>
				<!-- 排序按钮 -->
				<div class="row" id="order-btn-list">
					<div class="col-md-1">
						<button type="button" class="btn btn-primary" id="ratingValue-btn">评分排序</button>
					</div>
					<div class="col-md-1">
						<button type="button" class="btn btn-primary" id="reviewCount-btn">阅读排序</button>
					</div>
					<div class="col-md-1">
						<button type="button" class="btn btn-primary" id="words-btn">字数排序</button>
					</div>
					
				</div>
				
				<br/>
				
				<table class="table table-bordered">
					<!-- 开始遍历打印列表 -->
					<c:forEach items="${list}" var="ebook">

						<!-- 图书信息面板 -->
						<div class="panel panel-default">
							<!-- 面板头 -->
							<div class="panel-heading">
								<a class="panel-title" target="view_window" href="<c:url value='/ebook'/>/info.htm?eid=${ebook.eid}">${ebook.ename}</a>
							</div>
							<!-- 面板体 -->
							<div class="panel-body">
								<!-- 图片URL使用的是绝对的 TODO: -->
								<div style="float: left;">
									<img width="110px" height="164px"
										src="http://localhost:8080/EMANImgs/${ebook.imgAddress}"
										onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
								</div>
								<div class="row">
									
									<div class="col-md-3">
										<p>分类：${ebook.classifyMain}</p>
										<p>作者：${ebook.author}</p>
										<p>出版社：${ebook.publishingHouse}</p>
										<p>字数 ：${ebook.words}</p>
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
									<!-- <div class="progress" style="width: 30%;">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="30" aria-valuemin="0"
											aria-valuemax="50" style="width: 10%;">
											<span class="sr-only">90% 完成（成功）</span>
										</div>
									</div>  -->
									
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
				
				<div class="row">
					<button id="backpage-btn" class="btn btn-primary" type="button">上一页</button>
					<!-- 快速翻页按钮位置 -->
					<span>&nbsp;&nbsp;</span>
					<button id="nextpage-btn" class="btn btn-primary" type="button">下一页</button>
				</div>

			</div>
		</div>
	</div>
<script>

var initBtn = function(i) {
	console.log(i);
	//alert(curURL.slice(0, begIndex + "start=".length) + (i*20) + curURL.slice(endIndex, curURL.length));
};


$(document).on('ready', function () {
	
	/*生成快速翻页按钮*/
	var sum = parseInt($("#p-count").text());
	var pageNum = parseInt(sum/20);
	if(pageNum % 20 != 0)
		pageNum++;
	//alert(sum + "," + pageNum);
	if(pageNum <= 6){ // 若少于6页则全部生成
		for(var i = pageNum; i > 0 ; i--){
			// 生成按钮代码
			var clone = $("#backpage-btn").clone();
			clone.attr("id","page-btn-"+i);
			clone.text(i);
			// 添加点击事件
			var curURL = window.location.href;
			var begIndex = curURL.indexOf("start=", 0);
	    	var endIndex = curURL.indexOf("&", begIndex);
	    	
	    	(function(myIndex){
	    		clone.click(function(){
	    			//alert(myIndex);
	    			window.location.href = curURL.slice(0, begIndex + "start=".length) + (myIndex*20) + curURL.slice(endIndex, curURL.length);
	    		});
	    	}(i));
	    	// 装载按钮
			$("#backpage-btn").after(clone);
			$("#backpage-btn").after("<span>&nbsp;&nbsp;</span>");
		}
	}else{ // 若多于6页则部分生成
		var i = pageNum;
		for(var j = 0; j < 3 ; j++, i--){
			// TODO
			// 生成按钮代码
			var clone = $("#backpage-btn").clone();
			clone.attr("id","page-btn-"+i);
			clone.text(i);
			// 添加点击事件
			var curURL = window.location.href;
			var begIndex = curURL.indexOf("start=", 0);
	    	var endIndex = curURL.indexOf("&", begIndex);
	    	
	    	(function(myIndex){
	    		clone.click(function(){
	    			//alert(myIndex);
	    			window.location.href = curURL.slice(0, begIndex + "start=".length) + (myIndex*20) + curURL.slice(endIndex, curURL.length);
	    		});
	    	}(i));
	    	// 装载按钮
			$("#backpage-btn").after(clone);
			$("#backpage-btn").after("<span>&nbsp;&nbsp;</span>");
		}
		$("#backpage-btn").after("<span>&nbsp;&nbsp;</span><span>...</span>");
		
		for(i = 3; i > 0; i--){
			// TODO
			// 生成按钮代码
			var clone = $("#backpage-btn").clone();
			clone.attr("id","page-btn-"+i);
			clone.text(i);
			// 添加点击事件
			var curURL = window.location.href;
			var begIndex = curURL.indexOf("start=", 0);
	    	var endIndex = curURL.indexOf("&", begIndex);
	    	
	    	(function(myIndex){
	    		clone.click(function(){
	    			//alert(myIndex);
	    			window.location.href = curURL.slice(0, begIndex + "start=".length) + (myIndex*20) + curURL.slice(endIndex, curURL.length);
	    		});
	    	}(i));
	    	// 装载按钮
			$("#backpage-btn").after(clone);
			$("#backpage-btn").after("<span>&nbsp;&nbsp;</span>");
		}
	}
	
	/* 翻页按钮动作 */
	$("#backpage-btn").click(function(){
		var curURL = window.location.href;
    	console.log(curURL);
    	var begIndex = curURL.indexOf("start=", 0);
    	var endIndex = curURL.indexOf("&", begIndex);
    	var curPage = curURL.slice(begIndex + "start=".length, endIndex);
    	var curPageNum = parseInt(curPage);
    	var nextPageNum;
    	if(curPageNum <= 20){
    		nextPageNum = 0;
    	}else{
    		nextPageNum = curPageNum - 20;
    	}
    	console.log("curPage=" + curPage);
    	console.log(curURL.slice(0, begIndex + "start=".length) + nextPageNum + curURL.slice(endIndex, curURL.length));
    	window.location.href = curURL.slice(0, begIndex + "start=".length) + nextPageNum + curURL.slice(endIndex, curURL.length);
    });
    $("#nextpage-btn").click(function(){
    	var curURL = window.location.href;
    	console.log(curURL);
    	var begIndex = curURL.indexOf("start=", 0);
    	var endIndex = curURL.indexOf("&", begIndex);
    	var curPage = curURL.slice(begIndex + "start=".length, endIndex);
    	var curPageNum = parseInt(curPage);
    	var nextPageNum = curPageNum + 20;
    	console.log("curPage=" + curPage);
    	console.log(curURL.slice(0, begIndex + "start=".length) + nextPageNum + curURL.slice(endIndex, curURL.length));
    	window.location.href = curURL.slice(0, begIndex + "start=".length) + nextPageNum + curURL.slice(endIndex, curURL.length);
    });
});
</script>
</body>
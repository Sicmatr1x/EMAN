<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
var uid = '<%=session.getAttribute("uid")%>';
var state = '<%=session.getAttribute("state")%>';

	$(document).ready(function() {
		// 开始写 jQuery 代码...

		// 根据用户uid查询用户喜爱分类
        $.ajax({
            url : "/EMAN/favorite/query.htm?",
            type : "get",
            data : "uid="+uid,
            dataType : "json",
            success : function(data) {
                console.log(data);
				for(var i = 0; i < data.length; i++){
				    $("#checkbox-"+data[i].classifyMain.toString()).attr("checked",true);
				}
            },
            error : function() {
                alert("用户喜爱分类ajax请求失败");
            }
        });
	});
</script>
</head>
  
<body>

	<div class="container">
		<table class="table table-bordered">
			<!-- 注册面板 -->
			<div class="panel panel-default">
				<!-- 面板头 -->
				<div class="panel-heading">
					<a href="http://localhost:8080/EMAN"><h1>EMAN</h1></a>
				</div>
				<!-- 面板体 -->
				<div class="panel-body">
					<div class="row">

						<div class="col-md-5">
							<div style="float: left;">
								<img width="500px"
									src="<c:url value='/resources/img/kindle.jpg'/>"
									onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
							</div>
						</div>

						<div class="col-md-5">
							<div style="padding: 100px 100px 10px;">
								<span><h3>选择你感兴趣的分类</h3></span>
								<form class="bs-example bs-example-form" role="form"
									method="post" action="http://localhost:8080/EMAN/favorite/edit.htm">

									<label>图书主分类</label>
									<input type="text" style="display:none" name="uid" value="<%=session.getAttribute("uid")%>"></input>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-0" name="classifyMain" value="0">小说</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-1" name="classifyMain" value="1">文学</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-2" name="classifyMain" value="2">人文社科</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-3" name="classifyMain" value="3">经济管理</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-4" name="classifyMain" value="4">科技科普</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-5" name="classifyMain" value="5">计算机与互联网</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-6" name="classifyMain" value="6">成功励志</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-7" name="classifyMain" value="7">生活</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-8" name="classifyMain" value="8">少儿</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-9" name="classifyMain" value="9">艺术设计</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-10" name="classifyMain" value="10">漫画绘本</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-11" name="classifyMain" value="11">教育考试</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" id="checkbox-12" name="classifyMain" value="12">杂志</label>
									</div>
									

									<br> <input type="submit" class="btn btn-info" value="确认" />

								</form>
							</div>
						</div>
					</div>
					<!-- "row" -->
				</div>
			</div>
			<!-- 注册面板 -->
		</table>


	</div>
	<!-- container -->

</body>



</html>

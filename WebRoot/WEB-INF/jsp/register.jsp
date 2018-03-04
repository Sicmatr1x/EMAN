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
								<img width="500px" src="<c:url value='/resources/img/kindle.jpg'/>"
									onerror="javascript:this.src='http://localhost:8080/EMANImgs/error.jpg'">
							</div>
						</div>

						<div class="col-md-5">
							<div style="padding: 100px 100px 10px;">
								<span><h3>用户注册</h3></span>
								<form class="bs-example bs-example-form" role="form"
									method="post" action="loginByUname.htm">
									
									<div class="input-group">
										<input type="text" class="form-control" placeholder="用户名" name="uname" />
									</div>
									
									<br>
									
									<div class="input-group">
										<input type="text" class="form-control" placeholder="邮箱" name="email" />
									</div>
									
									<br>
									
									<div class="input-group">
										<input type="text" class="form-control" placeholder="密码" name="password" />
									</div>
									
									<br> <input type="submit" class="btn btn-info" value="注册" />
									
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
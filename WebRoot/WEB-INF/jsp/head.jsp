<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
						
						
						<ul class="nav navbar-nav navbar-right" id="user-nav">
							<!-- 用户登录模块 -->
							<li>
							<%
							String uname = (String)session.getAttribute("uname");
							String state = (String)session.getAttribute("state");
							if(state != null && state.equals("login")){
								out.println("<a href=\"http://localhost:8080/EMAN/user/logout.htm\">" + uname + "，注销" + "</a>");
							}else{
								out.println("<a href=\"http://localhost:8080/EMAN/user/login.htm\">" + "登录" + "</a>");
							}
							%>
							</li>
						</ul>
					</div>

				</nav>
			</div>
		</div>

<script>
		$(document).on('ready', function() {
			$("#bt-navbar-form-search").click(function(){
				var keyword = $("#keyword").val();
				console.log("keyword=" + keyword);
				//window.location.href="http://www.baidu.com";
				window.open('http://localhost:8080/EMAN/ebook/searchKeyword.htm?start=0&keyword=' + keyword);
		    });
		});
</script>
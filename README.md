# EMAN
一个基于SSM框架的简单电子书推荐系统

## 页面列表

例子：
http://localhost:8080/EMAN/ebook/topList.htm?start=20

/EMAN

<ol>
<li>/ebook(图书相关页面)</li>
    <ol>
        <li>主页</li>
        <li>分类图书列表</li>
        <li>评分TOP20图书列表</li>
        <li>图书详情页</li>
    </ol>
<li>/user(用户相关页面)</li>
    <ol>
        <li>用户注册(模态框)</li>
        <li>用户登录(模态框)</li>
        <li>个人中心</li>
    </ol>
<li>/spider(爬虫相关页面)(待定)</li>
</ol>



## 备忘

```html
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Basic DataGrid - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/easyui/themes/icon.css'/>">
<script type="text/javascript"
	src="<c:url value='/resources/easyui/jquery.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/easyui/jquery.easyui.min.js'/>"></script>
```
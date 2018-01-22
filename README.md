# EMAN
一个基于SSM框架的简单电子书推荐系统

## 配置文件

setting.properties:用于控制图书爬虫的行为

user_spider_setting.properties:用于控制用户爬虫的行为

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
        <li>用户注册</li>
        <li>用户登录</li>
        <li>个人中心</li>
    </ol>
<li>/spider(爬虫相关页面)(待定)</li>
</ol>


## 爬虫相关

图书爬虫：

EBookListSpider爬取全部豆瓣电子书
BookInfoSpider爬取图书详情页
RatingValueListSpider从数据库读取图书信息并爬取图书的评分列表(含用户)

## 备忘

```html
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



```

<a href="http://hoohtml.com/jQuery/Layout-Interface/2015112221.html">星星组件</a>

---

电子书推荐的策略：

针对不同用户群体的推荐方法选择：

1.新用户策略，年龄、性别基础上推荐相关的畅销书籍。
2.老用户策略，用户点评所占权重50%
3.复杂的，和你类似人也看了……(依据)推荐的依据：下载量等数据的来源

http://book.163.com/17/0104/23/C9VMAIAP00923P3U.html  2016年度数字阅读报告

版权问题

# BUG

1. window.location.href = "https://search.jd.com/Search?keyword=" + "${ebook.ename}" + "&enc=utf-8";若图书名中出现"则会导致注入
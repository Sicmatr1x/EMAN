# EMAN
一个基于SSM框架的简单电子书推荐系统

## 摘要

阅读是通过视觉来从语言文字中获取信息、认识世界、发展思维、并获得审美体验的活动。
在阅读的过程中，根据所阅读的图书类别不同，人可以获得不同的能力提升或者获得美的享受。
阅读是一种理解、领悟、吸收、鉴赏、评价和探究文章的思维过程。

现在是一个信息爆炸的时代，如果你想在喧嚣的生活中静下心来进行阅读已属不易。要想从信息爆炸的互联网上找出适合自己阅读的书籍更是难上加难。
推荐系统是利用用户产生的数据使用算法进行分析之后帮助用户决定应该购买什么产品，个性化推荐是根据用户的兴趣特点和购买行为，向用户推荐用户感兴趣的信息和商品。
推荐系统的本质就是将内容结构化、标签化，同时将用户行为模型化，再用多种算法予以匹配。
对于企业来讲，推荐系统的目的很简单：提高用户停留时间、或者提高商品购买率，以辅助产品的主业务，完成相应的业绩目标。目前在许多方面都有应用，如商品推荐、短视频推荐、社交推荐等。
推荐系统是大数据时代下产生的新型产品，核心意义在于帮助用户在海量数据中更便捷的找到他们最感兴趣的内容。当内容资源越庞大时，推荐系统的存在意义就越大
一个良好的推荐系统会增加用户粘性，活跃度，及提高各种转化率，而一个推荐系统好坏，其内部算法起到决定性因素。
本系统会通过图书的分类、评分、用户喜好等来进行图书推荐。使得找到适合自己阅读的书籍不再是一项高成本的任务。

关键词：[Java Spring SpringMVC MyBatis AJAX JSP jQuery JavaScript Bootstrap MySQL 基于物品的协同过滤算法(ItemCF) 阅读社区 电纸书]

---

## 类与相关主要文件

>符号 &#8594; 表示该类为定期手动运行模块

<ol>
<li>com.controller</li>
    <ol>
        <li>EBookController：与图书相关的接口</li>
        <li>RatingListController：与用户评论、评分相关的接口</li>
        <li>StatisticsController：统计模块接口</li>
        <li>UserController：用户相关接口</li>
        <li>FavoriteController：用户喜爱分类接口</li>
        <li>ClassifyMainStatisticsController：分类统计相关接口</li>
    </ol>
<li>com.dao</li>
    <ol>
        <li>DBAccess：用于获取SqlSession数据库连接</li>
        <li>EBookDao：提供图书表的访问</li>
        <li>RatingListDao：提供评分评论表的访问</li>
        <li>UserDao：提供用户表的访问</li>
        <li>FavoriteDao：用户喜爱分类表的访问</li>
        <li>ClassifyMainStatisticsDao：分类统计相关数据表的访问</li>
        <li>MatrixCDao：提供同现矩阵C与余弦相似度矩阵W的访问</li>
        <li>EBookMapper.xml：提供图书表的访问的sql语句</li>
        <li>RatingListMapper.xml：提供评分评论表的访问的sql语句</li>
        <li>UserMapper.xml：提供用户表的访问的sql语句</li>
        <li>ClassifyMainStatisticsMapper.xml：提供主分区统计表的访问的sql语句</li>
        <li>FavoriteMapper.xml：用户喜爱分类表的访问的sql语句</li>
        <li>ClassifyMainStatisticsMapper.xml：分类统计相关数据表的访问的sql语句</li>
        <li>MatrixCMapper.xml：提供同现矩阵C与余弦相似度矩阵W的访问的sql语句</li>
    </ol>
<li>com.entity</li>
    <ol>
        <li>EBook：图书 -> ebook表</li>
        <li>RatingList：评论评分相 -> ratingList表</li>
        <li>ClassifyMainStatistics：主分区统计模块 -> classifyMainStatistics表</li>
        <li>User：用户 -> user表</li>
        <li>Favorite：用户喜爱分类实体 -> favorite表</li>
        <li>MatrixC：同现矩阵C与余弦相似度矩阵W -> matrixC表</li>
    </ol>
<li>com.service</li>
    <ol>
        <li>EBookService：图书模块</li>
        <li>RatingListService：评论评分模块</li>
        <li>UserService：用户模块</li>
        <li>FavoriteService：用户喜爱分类模块</li>
        <li>ClassifyMainStatisticsService：分类统计模块</li>
    </ol>
<li>com.statistics</li>
    <ol>
        <li>&#8594; ItemCollaborationFilter：用于计算基于物品的协同过滤推荐矩阵</li>
        <li>&#8594; StatisticsClassifyMain：统计主分类的各个分数的评分人数、平均评分、评分的方差</li>
        <li>&#8594; StatisticsRatingValue：图书评分信息统计：统计RatingValue表的数据并将统计结果写入到EBook表对应到图书上</li>
    </ol>
<li>com.util</li>
    <ol>
        <li>ChartDataJsonCreater：提供将数据转换为 Chart.js 插件专用的结构化 Json 数据格式</li>
        <li>JSONConverter：提供用于将 JavaBeans 对象直接转化为结构化 Json 数据</li>
        <li>RandomNumFactory：随机数生成工具类</li>
    </ol>
<li>spider(爬虫包)</li>
    <ol>
        <li>BookInfoSpider：爬取电子书详情页并存入数据库</li>
        <li>&#8594; EBookListSpider：爬取全部电子书</li>
        <li>HttpURLConnectionUtil：</li>
        <li>&#8594; RatingValueListSpider：爬取豆瓣图书评分列表与用户评论(从数据库读取图书信息并爬取图书的评分列表)</li>
    </ol>
<li>配置文件</li>
    <ol>
        <li>SSM框架配置文件</li>
        <ol>
            <li>applicationContext.xml：Spring 框架配置文件</li>
            <li>jdbc.properties：jdbc 配置文件</li>
            <li>mybatis-config.xml：MyBatis 框架配置文件</li>
            <li>web.xml：项目配置文件</li>
        </ol>
        <li>爬虫配置文件</li>
        <ol>
            <li>setting.properties：电子书爬虫(EBookListSpider)的配置文件</li>
            <li>user_spider_setting.properties：用户评论评分爬虫(RatingValueListSpider)的配置文件</li>
            <li>user_spider_exception.log：用户评论评分爬虫(RatingValueListSpider)的错误日志</li>
        </ol>
    </ol>
</ol>

---

## 页面列表

<ol>
<li>index.jsp：主页</li>
<li>head.jsp：导航栏</li>
<li>error.jsp：错误信息页(待实现)</li>
<li>success.jsp：成功操作信息跳转页</li>
<li>/ebook(图书相关页面)</li>
    <ol>
        <li>list.jsp：主分类图书列表</li>
        <li>info.jsp：图书详情页</li>
        <li>serrchResult.jsp：图书搜索结果页</li>
    </ol>
<li>/user(用户相关页面)</li>
    <ol>
        <li>login.jsp：用户登录</li>
        <li>register.jsp：用户注册</li>
        <li>home.jsp：用户喜爱分类选择</li>
    </ol>
</ol>

---

## 数据库表

<ol>
    <li>ebook：图书详情</li>
    <li>ratinglist：评分评论列表</li>
    <li>subclassify：图书副分类</li>
    <li>user：用户信息</li>
    <li>classifymainstatistics：主分类统计</li>
    <li>recommendhomepage：主页推荐书籍(待定)</li>
    <li>favorite：用户喜爱分类表</li>
    <li>matrixC：同现矩阵C与余弦相似度矩阵W表</li>
</ol>

---

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

2. 访问图书详情页的时候ajax请求雷达图com.controller.RatingListController.getEBookReviewCountRadarChartData()可能会导致以下错误：

```
### Error querying database.  Cause: java.lang.ClassCastException: org.apache.ibatis.executor.ExecutionPlaceholder cannot be cast to java.util.List
### The error may exist in com/dao/RatingListMapper.xml
### The error may involve com.dao.RatingList.selectRatingListByEid
### The error occurred while executing a query
### Cause: java.lang.ClassCastException: org.apache.ibatis.executor.ExecutionPlaceholder cannot be cast to java.util.List
	at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:30)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:122)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:113)
	at com.dao.impl.RatingListDaoImpl.selectRatingListByEid(RatingListDaoImpl.java:30)
	at com.service.impl.RatingListServiceImpl.selectRatingListByEid(RatingListServiceImpl.java:31)
	at com.controller.RatingListController.getEBookReviewCountRadarChartData(RatingListController.java:264)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.web.method.support.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:213)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:126)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:96)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:617)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:578)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:80)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:923)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:852)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:882)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:778)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:622)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
```
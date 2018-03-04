# EMAN
一个基于SSM框架的简单电子书推荐系统

## 配置文件

setting.properties:用于控制图书爬虫的行为

user_spider_setting.properties:用于控制用户爬虫的行为

---

## 类与相关主要文件

>符号 &#8594; 表示该类为定期手动运行模块

<ol>
<li>com.controller</li>
    <ol>
        <li>EBookController：与图书相关的接口</li>
        <li>RatingListController：与用户评论、评分相关的接口</li>
        <li>StatisticsController：统计模块接口(待定)</li>
        <li>UserController：用户相关接口</li>
    </ol>
<li>com.dao</li>
    <ol>
        <li>DBAccess：用于获取SqlSession数据库连接</li>
        <li>EBookDao：提供图书表的访问</li>
        <li>RatingListDao：提供评分评论表的访问</li>
        <li>UserDao：提供用户表的访问</li>
        <li>ClassifyMainStatisticsDao：提供主分区统计表的访问</li>
        <li>EBookMapper.xml：提供图书表的访问的sql语句</li>
        <li>RatingListMapper.xml：提供评分评论表的访问的sql语句</li>
        <li>UserMapper.xml：提供用户表的访问的sql语句</li>
        <li>ClassifyMainStatisticsMapper.xml：提供主分区统计表的访问的sql语句</li>
    </ol>
<li>com.entity</li>
    <ol>
        <li>EBook：图书 -> ebook表</li>
        <li>RatingList：评论评分相 -> ratingList表</li>
        <li>ClassifyMainStatistics：主分区统计模块 -> classifyMainStatistics表</li>
        <li>User：用户 -> user表</li>
        <li>EBookTuple：用于计算基于用户相似度时使用</li>
    </ol>
<li>com.service</li>
    <ol>
        <li>EBookService：图书模块</li>
        <li>RatingListService：评论评分模块</li>
        <li>UserService：用户模块</li>
    </ol>
<li>com.statistics</li>
    <ol>
        <li>&#8594; StatisticsClassifyMain：统计主分类的各个分数的评分人数、平均评分、评分的方差</li>
        <li>&#8594; StatisticsRatingValue：图书评分信息统计：统计RatingValue表的数据并将统计结果写入到EBook表对应到图书上</li>
    </ol>
<li>com.util</li>
    <ol>
        <li>ChartDataJsonCreater：提供将数据转换为 Chart.js 插件专用的结构化 Json 数据格式</li>
        <li>JSONConverter：提供用于将 JavaBeans 对象直接转化为结构化 Json 数据</li>
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
    </ol>
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
package com.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.EBook;
import com.service.EBookService;
import com.util.JSONConverter;

@Controller
@RequestMapping("/ebook")
public class EBookController {

	@Autowired
	private EBookService eBookService = null;

	public EBookService getEBookService() {
		return eBookService;
	}

	public void setEBookService(EBookService eBookService) {
		this.eBookService = eBookService;
	}
	
	/**
	 * 根据图书eid查询图书(测试用)
	 * http://localhost:8080/EMAN/ebook/query.htm?eid=958945
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/query.htm")
	@ResponseBody
	public void queryEBook(@RequestParam(value="eid")String eid, PrintWriter out, HttpServletRequest request){
		EBook eBook = eBookService.queryEBookByEid(eid);
		String json;
		json = JSONConverter.convertToJSONString(eBook);
		out.print(json);
		out.flush();
	}
	
	//------------------------------------------------------------------------------
	
	/**
	 * 图书详情页
	 * http://localhost:8080/EMAN/ebook/info.htm?eid=958945
	 * @param eid
	 * @param request
	 * @return
	 */
	@RequestMapping("/info.htm")
	public String info(@RequestParam(value="eid")String eid, HttpServletRequest request){
		EBook ebook = eBookService.queryEBookByEid(eid);
		request.setAttribute("ebook", ebook);
		return "info";
	}
	
	/**
	 * 各种图书的列表
	 * http://localhost:8080/EMAN/ebook/list.htm?start=0&classifyMain=小说
	 * @param start 显示结果下标
	 * @param classifyMain 图书分类
	 * @param orderCondition 用于排序属性
	 * @param order 排序顺序
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.htm")
	public String queryEBookLimitByClassifyMain(
			@RequestParam(value="start")int start,
			@RequestParam(value="classifyMain")String classifyMain,
			@RequestParam(value="orderCondition", required=false)String orderCondition,
			@RequestParam(value="order", required=false)String order,
			HttpServletRequest request){
		List<EBook> list = eBookService.queryEBookLimitByClassifyMain(classifyMain, start, orderCondition, order);
		// 查询结果条数
		int count = eBookService.queryEBookByClassifyMainCount(classifyMain, orderCondition, order);
		request.setAttribute("list", list);
		request.setAttribute("classifyMain", classifyMain);
		request.setAttribute("start", start+20);
		request.setAttribute("count", count);
		return "list";
	}
	
	/**
	 * 图书搜索,关键词查询(或条件)
	 * http://localhost:8080/EMAN/ebook/searchKeyword.htm?start=0&keyword=小说
	 * @param request
	 * @param keyword 关键词
	 * @param start 显示结果下标
	 * @param orderCondition 用于排序属性
	 * @param order 排序顺序
	 * @return
	 */
	@RequestMapping("/searchKeyword.htm")
	public String queryEBookByKeyword(HttpServletRequest request,
			@RequestParam(value="keyword")String keyword,
			@RequestParam(value="start")int start,
			@RequestParam(value="orderCondition", required=false)String orderCondition,
			@RequestParam(value="order", required=false)String order
			){
		
		List<EBook> result = eBookService.queryEBookByKeyword(keyword, start, orderCondition, order);
		// 查询结果条数
		int count = eBookService.queryEBookByKeywordCount(keyword, orderCondition, order);
		request.setAttribute("result", result);
		request.setAttribute("count", count);
		return "searchResult";
	}
	
	/**
	 * 图书高级搜索(且条件)
	 * http://localhost:8080/EMAN/ebook/search.htm?start=0&classifyMain=小说
	 * @param request
	 * @param eid
	 * @param ISBN
	 * @param ename
	 * @param author
	 * @param translator
	 * @param publishingHouse
	 * @param provider
	 * @param classifyMain
	 * @param start
	 * @param orderCondition
	 * @param order
	 * @return
	 */
	@RequestMapping("/search.htm")
	public String queryEBookByCondition(HttpServletRequest request,
			@RequestParam(value="eid", required=false)String eid,
			@RequestParam(value="ISBN", required=false)String ISBN,
			@RequestParam(value="ename", required=false)String ename,
			@RequestParam(value="author", required=false)String author,
			@RequestParam(value="translator", required=false)String translator,
			@RequestParam(value="publishingHouse", required=false)String publishingHouse,
			@RequestParam(value="provider", required=false)String provider,
			@RequestParam(value="classifyMain", required=false)String classifyMain,
			@RequestParam(value="start")int start,
			@RequestParam(value="orderCondition", required=false)String orderCondition,
			@RequestParam(value="order", required=false)String order
			){
		EBook condition = new EBook();
		condition.setEid(eid);
		condition.setISBN(ISBN);
		condition.setEname(ename);
		condition.setAuthor(author);
		condition.setTranslator(translator);
		condition.setPublishingHouse(publishingHouse);
		condition.setProvider(provider);
		condition.setClassifyMain(classifyMain);
		
		List<EBook> result = eBookService.queryEBookByCondition(condition, start, orderCondition, order);
		request.setAttribute("result", result);
		return "searchResult";
	}
	
	/**
	 * 首页轮播推荐书籍接口
	 * http://localhost:8080/EMAN/ebook/recommendHomePage.htm?eid=958945
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/recommendHomePage.htm")
	@ResponseBody
	public void recommendHomePage(@RequestParam(value="eid")String eid, PrintWriter out, HttpServletRequest request){
		EBook eBook = eBookService.queryEBookByEid(eid);
		String json;
		json = JSONConverter.convertToJSONString(eBook);
		out.print(json);
		out.flush();
	}
}

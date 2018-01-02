package com.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	 * http://localhost:8080/EMAN/ebook/EBookList.htm
	 * @param request
	 * @return
	 */
	@RequestMapping("/EBookList.htm")
	public String eBookList(HttpServletRequest request){
		return "EBookList";
	}
	
	/**
	 * http://localhost:8080/EMAN/ebook/queryAllEBook.htm
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryAllEBook.htm")
	public String queryAllEBook(HttpServletRequest request){
		List<EBook> list = eBookService.quertyAllEBook();
		request.setAttribute("list", list);
		return "showAllEBook";
	}
	
	@RequestMapping("/queryAllEBookLimit.htm")
	public String queryAllEBook(@RequestParam(value="start")int start, HttpServletRequest request){
		List<EBook> list = eBookService.queryAllEBookLimit(start);
		request.setAttribute("list", list);
		request.setAttribute("start", start+20);
		return "showAllEBook";
	}
	
	/**
	 * http://localhost:8080/EMAN/ebook/topList.htm?start=0
	 * @param start
	 * @param request
	 * @return
	 */
	@RequestMapping("/topList.htm")
	public String queryAllEBookLimit(@RequestParam(value="start")int start, HttpServletRequest request){
		List<EBook> list = eBookService.queryAllEBookLimit(start);
		request.setAttribute("list", list);
		request.setAttribute("start", start+20);
		return "topList";
	}
	
	/**
	 * http://localhost:8080/EMAN/ebook/queryAllEBookLimitJson.htm?start=
	 * @param start
	 * @param out
	 */
	@RequestMapping("/queryAllEBookLimitJson.htm")
	@ResponseBody
	public void queryAllEBookJson(@RequestParam(value="start")int start, PrintWriter out, HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("/queryAllEBookLimitJson.htm?start=" + start);
		List<EBook> list = eBookService.queryAllEBookLimit(start);
		String json;
		json = JSONConverter.convertToJSONString(list);
		System.out.println(json);
		out.print(json);
		out.flush();
	}
	
	
	/**
	 * http://localhost:8080/EMAN/ebook/testebookinfo.htm
	 * @param request
	 * @return
	 */
	@RequestMapping("/testeebookinfo.htm")
	public String testebookinfo(HttpServletRequest request){
		return "testebookinfo";
	}
	
	/**
	 * http://localhost:8080/EMAN/ebook/query.htm?eid=958945
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/query.htm")
	@ResponseBody
	public void queryEBook(@RequestParam(value="eid")int eid, PrintWriter out, HttpServletRequest request){
		EBook eBook = eBookService.queryEBookByEid(eid);
		String json;
		json = JSONConverter.convertToJSONString(eBook);
		out.print(json);
		out.flush();
	}
	//------------------------------------------------------------------------------
	/**
	 * 图书搜索
	 * http://localhost:8080/EMAN/ebook/searchKeyword.htm?start=0&keyword=小说
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/searchKeyword.htm")
	public String queryEBookByKeyword(HttpServletRequest request,
			@RequestParam(value="keyword")String keyword,
			@RequestParam(value="start")int start,
			@RequestParam(value="orderCondition", required=false)String orderCondition,
			@RequestParam(value="order", required=false)String order
			){
		
		List<EBook> result = eBookService.queryEBookByKeyword(keyword, start, orderCondition, order);
		int count = eBookService.queryEBookByKeywordCount(keyword, orderCondition, order);
		request.setAttribute("result", result);
		request.setAttribute("count", count);
		return "searchResult";
	}
	
	/**
	 * 图书高级搜索
	 * http://localhost:8080/EMAN/ebook/search.htm?start=0&classifyMain=小说
	 * @param eid
	 * @param out
	 * @param request
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
	 * 图书详情页
	 * http://localhost:8080/EMAN/ebook/info.htm?eid=958945
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/info.htm")
	public String info(@RequestParam(value="eid")int eid, HttpServletRequest request){
		EBook ebook = eBookService.queryEBookByEid(eid);
		request.setAttribute("ebook", ebook);
		return "info";
	}
	
	/**
	 * 各种图书的列表
	 * 评分降序
	 * http://localhost:8080/EMAN/ebook/list.htm?start=0&classifyMain=小说
	 * @param start
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.htm")
	public String queryEBookLimitByClassifyMain(@RequestParam(value="start")int start, @RequestParam(value="classifyMain")String classifyMain, HttpServletRequest request){
		List<EBook> list = eBookService.queryEBookLimitByClassifyMain(classifyMain, start);
		request.setAttribute("list", list);
		request.setAttribute("classifyMain", classifyMain);
		request.setAttribute("start", start+20);
		return "list";
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
	public void recommendHomePage(@RequestParam(value="eid")int eid, PrintWriter out, HttpServletRequest request){
		EBook eBook = eBookService.queryEBookByEid(eid);
		String json;
		json = JSONConverter.convertToJSONString(eBook);
		out.print(json);
		out.flush();
	}
}

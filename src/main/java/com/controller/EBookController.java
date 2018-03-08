package com.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.EBook;
import com.entity.Favorite;
import com.service.EBookService;
import com.service.FavoriteService;
import com.service.RatingListService;
import com.statistics.StatisticsRatingValue;
import com.util.JSONConverter;
import com.util.RandomNumFactory;

/*
 * 与图书相关的接口
 */
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
	
	@Autowired
	private RatingListService ratingListService = null;

	public RatingListService getRatingListService() {
		return ratingListService;
	}
	
	@Autowired
	private StatisticsRatingValue statisticsRatingValue = null;
	
	public StatisticsRatingValue getStatisticsRatingValue() {
		return statisticsRatingValue;
	}
	
	@Autowired
	private FavoriteService favoriteService = null;

	public FavoriteService getFavoriteService() {
		return favoriteService;
	}

	public void setFavoriteService(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
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
		int count = eBookService.queryEBookByClassifyMainCount(classifyMain);
		request.setAttribute("list", list);
		request.setAttribute("classifyMain", classifyMain);
		request.setAttribute("start", start+20);
		request.setAttribute("count", count);
		return "list";
	}
	
	/**
	 * 各种图书的列表(返回json)131952373
	 * http://localhost:8080/EMAN/ebook/getList.htm?start=0&classifyMain=小说
     * http://localhost:8080/EMAN/ebook/getList.htm?uid=131952373
	 * @param start 显示结果下标
	 * @param classifyMain 图书分类
	 * @param orderCondition 用于排序属性
	 * @param order 排序顺序
	 * @param request
	 * @return
	 */
	@RequestMapping("/getList.htm")
	public void getEBookLimitByClassifyMain(
			@RequestParam(value="start", required=false)Integer start,
			@RequestParam(value="classifyMain", required=false)String classifyMain,
			@RequestParam(value="orderCondition", required=false)String orderCondition,
			@RequestParam(value="order", required=false)String order,
			@RequestParam(value="uid", required=false)String uid,
			HttpServletRequest request,
			PrintWriter out){
		if(uid != null){ // 若uid参数不为空则根据该用户感兴趣的分类来随机返回图书
			List<Favorite> favoriteList = this.favoriteService.selectFavoriteByUid(uid);
			List<EBook> resultList = new ArrayList<>();
			for(int i = 0; i < favoriteList.size(); i++){
				List<EBook> list = eBookService.queryEBookLimitByClassifyMain(favoriteList.get(i).getClassifyMain(), 0, "reviewCount", "desc");
				resultList.add(list.get(RandomNumFactory.randomNum(0, list.size())));
			}
			String json;
			json = JSONConverter.convertToJSONString(resultList);
			System.out.println("/getList:uid="+uid+",json="+json);
			out.print(json);
			out.flush();
			return;
		}
		
		List<EBook> list = eBookService.queryEBookLimitByClassifyMain(classifyMain, start, orderCondition, order);
		// 查询结果条数
		int count = eBookService.queryEBookByClassifyMainCount(classifyMain);
		request.setAttribute("list", list);
		request.setAttribute("classifyMain", classifyMain);
		request.setAttribute("start", start+20);
		request.setAttribute("count", count);
		String json;
		json = JSONConverter.convertToJSONString(list);
		out.print(json);
		out.flush();
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
	
	/**
	 * 喜欢这本书的用户还喜欢接口
	 * http://localhost:8080/EMAN/ebook/likeThisBooksUserAlsoLike.htm?eid=958945
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/likeThisBooksUserAlsoLike.htm")
	@ResponseBody
	public void likeThisBooksUserAlsoLike(@RequestParam(value="eid")String eid, PrintWriter out, HttpServletRequest request){
//		List<EBookTuple> resultList = eBookService.likeThisBooksUserAlsoLike(eid);
//		if(resultList != null){
//			// 查询列表中所有图书的详细信息
//			for(EBookTuple et : resultList){
//				et.setEbook(eBookService.queryEBookByEid(et.getEid()));
//			}
//		}
		
		String json = "{}";
//		json = JSONConverter.convertToJSONString(resultList);
		out.print(json);
		out.flush();
	}
}

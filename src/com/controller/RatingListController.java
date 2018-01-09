package com.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestParam;

import com.entity.RatingList;
import com.service.RatingListService;
import com.util.JSONConverter;



@Controller
@RequestMapping("/ratinglist")
public class RatingListController {

	@Autowired
	private RatingListService ratingListService = null;

	public RatingListService getRatingListService() {
		return ratingListService;
	}

	public void setRatingListService(RatingListService ratingListService) {
		this.ratingListService = ratingListService;
	}

	/**
	 * 获取评论接口
	 * http://localhost:8080/EMAN/ratinglist/list.htm?eid=10245287
	 * @param eid
	 * @param request
	 */
	@RequestMapping("/list.htm")
	public void queryRatingListByEid(@RequestParam(value="eid")String eid, PrintWriter out, HttpServletRequest request){
		List<RatingList> list = ratingListService.selectRatingListByEid(eid);
		
		String json;
		json = JSONConverter.convertToJSONString(list);
		//System.out.println(json);
		out.print(json);
		out.flush();
	}
	
	/**
	 * 获取指定用户评论接口
	 * http://localhost:8080/EMAN/ratinglist/queryOne.htm?eid=930946&uid=63725827
	 * @param eid
	 * @param uid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/queryOne.htm")
	public void queryRatingListByEidAndUid(@RequestParam(value="eid")String eid, @RequestParam(value="uid")String uid, PrintWriter out, HttpServletRequest request){
		RatingList r = ratingListService.selectRatingListByEidAndUid(eid, uid);
		
		String json;
		json = JSONConverter.convertToJSONString(r);
		System.out.println(json);
		out.print(json);
		out.flush();
	}
	
	/**
	 * 用户评论评分接口
	 * 
	 * @param eid
	 * @param uid
	 * @param ratingValue
	 * @param rdescribe
	 * @param out
	 * @param request
	 */
	@RequestMapping("/insert.htm")
	public void insertRatingList(
			@RequestParam(value="eid")String eid,
			@RequestParam(value="uid")String uid,
			@RequestParam(value="ratingValue")Double ratingValue,
			@RequestParam(value="rdescribe")String rdescribe,
			PrintWriter out,
			HttpServletRequest request){
		RatingList rList = new RatingList();
		rList.setEid(eid);
		rList.setUid(uid);
		rList.setRatingValue(ratingValue);
		rList.setRdescribe(rdescribe);
		ratingListService.insertRatingList(rList);
		
		RatingList result = null;
		result = ratingListService.selectRatingListByEidAndUid(eid, uid);
		String json;
		json = JSONConverter.convertToJSONString(result);
		System.out.println(json);
		out.print(json);
		out.flush();
	}
}

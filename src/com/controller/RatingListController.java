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
	public void queryRatingListByEid(@RequestParam(value="eid")int eid, PrintWriter out, HttpServletRequest request){
		List<RatingList> list = ratingListService.selectRatingListByEid(eid);
		
		String json;
		json = JSONConverter.convertToJSONString(list);
		System.out.println(json);
		out.print(json);
		out.flush();
	}
}

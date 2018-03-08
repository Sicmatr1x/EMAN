package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestParam;

import com.dao.ClassifyMainStatisticsDao;
import com.entity.ClassifyMainStatistics;
import com.entity.EBook;
import com.entity.RatingList;
import com.service.EBookService;
import com.service.RatingListService;
import com.util.ChartDataJsonCreater;
import com.util.JSONConverter;


/*
 * 与用户评论、评分相关的接口
 */
@Controller
@RequestMapping("/ratinglist")
public class RatingListController {

	@Autowired
	private RatingListService ratingListService = null;

	public RatingListService getRatingListService() {
		return ratingListService;
	}
	
	@Autowired
	private EBookService eBookService = null;

	public EBookService getEBookService() {
		return eBookService;
	}

	public void setEBookService(EBookService eBookService) {
		this.eBookService = eBookService;
	}
	
	@Autowired
	private ClassifyMainStatisticsDao classifyMainStatisticsDao = null;

	public ClassifyMainStatisticsDao getClassifyMainStatisticsDao() {
		return classifyMainStatisticsDao;
	}

	public void setClassifyMainStatisticsDao(
			ClassifyMainStatisticsDao classifyMainStatisticsDao) {
		this.classifyMainStatisticsDao = classifyMainStatisticsDao;
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
	 * 获取评论接口
	 * http://localhost:8080/EMAN/ratinglist/listLimit.htm?eid=10245287&start=0
	 * @param eid
	 * @param request
	 */
	@RequestMapping("/listLimit.htm")
	public void queryRatingListLimitByEid(
			@RequestParam(value="eid")String eid,
			@RequestParam(value="start")Integer start,
			@RequestParam(value="orderCondition", required=false)String orderCondition,
			@RequestParam(value="order", required=false)String order,
			PrintWriter out,
			HttpServletRequest request){
		
		List<RatingList> list = ratingListService.selectRatingListLimitByEid(eid, start, orderCondition, order);
		
		String json;
		json = JSONConverter.convertToJSONString(list);
		//System.out.println(json);
		out.print(json);
		out.flush();
	}
	
	/**
	 * 获取评论总条数接口
	 * http://localhost:8080/EMAN/ratinglist/listLimitCount.htm?eid=10245287
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/listLimitCount.htm")
	public void queryRatingListLimitByEidCount(
			@RequestParam(value="eid")String eid,
			PrintWriter out,
			HttpServletRequest request){
		
		int num = ratingListService.selectRatingListLimitByEidCount(eid);
		
		String json;
		json = JSONConverter.convertToJSONString(num);
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
	
	//--------------------------------------------------------------------------------------
	/**
	 * 各个评分分别有多少人的饼图json数据接口
	 * http://localhost:8080/EMAN/ratinglist/getEBookReviewCountPieChartData.htm?eid=930946
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/getEBookReviewCountPieChartData.htm")
	public void getEBookReviewCountPieChartData(
			@RequestParam(value="eid")String eid,
			PrintWriter out,
			HttpServletRequest request){
		List<RatingList> list = ratingListService.selectRatingListByEid(eid);
		
		double[] data = {0,0,0,0,0};
		for(RatingList r : list){
			double value = r.getRatingValue();
			if(value > 4.0){
				data[4]++;
			}else if(value > 3.0){
				data[3]++;
			}else if(value > 2.0){
				data[2]++;
			}else if(value > 1.0){
				data[1]++;
			}else{
				data[0]++;
			}
		}
		String[] backgroundColor = {
				"rgba(255, 99, 132, 0.7)", // Red
				"rgba(255, 159, 64, 0.7)", // Orange
				"rgba(255, 205, 86, 0.7)", // Yellow
				"rgba(75, 192, 192, 0.7)", // Green
				"rgba(54, 162, 235, 0.7)"}; // Blue
		String[] labels = {
				"很差",
                "较差",
                "还行",
                "推荐",
                "力荐"
		};
		
		String json;
		try {
			json = ChartDataJsonCreater.getPieJson(data, backgroundColor, labels, "各评分人数占比饼图", "left");
//			json = ChartDataJsonCreater.getPolarAreaJson(data, backgroundColor, labels, "评分人数", "left");
			System.out.println(json);
			out.print(json);
			out.flush();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 各个评分分别有多少人的雷达图json数据接口
	 * http://localhost:8080/EMAN/ratinglist/getEBookReviewCountRadarChartData.htm?eid=930946
	 * @param eid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/getEBookReviewCountRadarChartData.htm")
	public void getEBookReviewCountRadarChartData(
			@RequestParam(value="eid")String eid,
			PrintWriter out,
			HttpServletRequest request){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<RatingList> list = ratingListService.selectRatingListByEid(eid);
		// 查询并计算该图书所属主要分类的各个评分的平均数
		EBook book = eBookService.queryEBookByEid(eid);
		int sum = eBookService.queryEBookByClassifyMainCountHasRatingValue(book.getClassifyMain());
		ClassifyMainStatistics classifyMainStatistics = classifyMainStatisticsDao.queryClassifyMainStatisticsByClassifyMain(book.getClassifyMain());
		double avgReviewCount5 = 1.0*classifyMainStatistics.getReviewCount5()/sum;
		double avgReviewCount4 = 1.0*classifyMainStatistics.getReviewCount4()/sum;
		double avgReviewCount3 = 1.0*classifyMainStatistics.getReviewCount3()/sum;
		double avgReviewCount2 = 1.0*classifyMainStatistics.getReviewCount2()/sum;
		double avgReviewCount1 = 1.0*classifyMainStatistics.getReviewCount1()/sum;
		System.out.println(classifyMainStatistics.getReviewCount5() + "/" + sum);
		
//		double[][] data = {{20,19,158,736,2525},{2,2,2,2,2}};
		double[][] data = {{0,0,0,0,0},{avgReviewCount1,avgReviewCount2,avgReviewCount3,avgReviewCount4,avgReviewCount5}};
		for(RatingList r : list){
			double value = r.getRatingValue();
			if(value > 4.0){
				data[0][4]++;
			}else if(value > 3.0){
				data[0][3]++;
			}else if(value > 2.0){
				data[0][2]++;
			}else if(value > 1.0){
				data[0][1]++;
			}else{
				data[0][0]++;
			}
		}
		String[] backgroundColor = {
				"rgba(255, 99, 132, 0.5)", // Red
				"rgba(54, 162, 235, 0.5)"}; // Blue
		String[] labels = {
				"1",
                "2",
                "3",
                "4",
                "5"
		};
		String[] dataLabels = {
				"本书各评分人数",
                "本分类中热门图书各评分人数均值" 
		};
		String json;
		try {
			json = ChartDataJsonCreater.getRadarJson(data, dataLabels, backgroundColor, backgroundColor, backgroundColor, labels, "各个评分人数雷达图", "left");
			System.out.println(json);
			out.print(json);
			out.flush();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

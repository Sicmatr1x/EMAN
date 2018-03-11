package com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.MatrixCDao;
import com.entity.MatrixC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EBookDao;
import com.entity.EBook;
import com.entity.EBookTuple;
import com.entity.RatingList;
import com.service.EBookService;
import com.service.RatingListService;
import com.statistics.StatisticsRatingValue;

@Service("eBookService")
public class EBookServiceImpl implements EBookService {
	
	@Autowired
	private EBookDao eBookDao = null;

	public EBookDao getEBookDao() {
		return eBookDao;
	}

	public void seteBookDao(EBookDao eBookDao) {
		this.eBookDao = eBookDao;
	}

	@Autowired
	private RatingListService ratingListService = null;

	public RatingListService getRatingListService() {
		return ratingListService;
	}
	
	public void setRatingListService(RatingListService ratingListService) {
		this.ratingListService = ratingListService;
	}

	@Autowired
	private MatrixCDao matrixCDao = null;

	public MatrixCDao getMatrixCDao() {
		return matrixCDao;
	}

	public void setMatrixCDao(MatrixCDao matrixCDao) {
		this.matrixCDao = matrixCDao;
	}

	@Autowired
	private StatisticsRatingValue statisticsRatingValue = null;
	
	public StatisticsRatingValue getStatisticsRatingValue() {
		return statisticsRatingValue;
	}
	
	public void setStatisticsRatingValue(StatisticsRatingValue statisticsRatingValue) {
		this.statisticsRatingValue = statisticsRatingValue;
	}

	/**
	 * 处理图书的图片地址为可用
	 * @param list
	 * @return
	 */
	private List<EBook> initEBookImgAddress(List<EBook> list){
		for(EBook book : list){
			if(book.getImgAddress()!=null){
				int beg = book.getImgAddress().indexOf("public/");
				int end = book.getImgAddress().indexOf("?v=");
				if(end==-1)
					continue;
				String tString = book.getImgAddress().substring(beg + "public/".length(), end);
				book.setImgAddress(tString);
			}
			
		}
		return list;
	}
	
	/**
	 * 处理图书的图片地址为可用
	 * @param book
	 * @return
	 */
	private EBook initEBookImgAddress(EBook book){
		if(book.getImgAddress()!=null){
			int beg = book.getImgAddress().indexOf("public/");
			int end = book.getImgAddress().indexOf("?v=");
			if(end==-1){
				
			}else{
				String tString = book.getImgAddress().substring(beg + "public/".length(), end);
				book.setImgAddress(tString);
			}
		}
		return book;
	}
	
	/**
	 * 实时统计图书平均分与总评分人数
	 * @param book
	 * @return
	 */
	private EBook statisticsRating(EBook book){
		double ratingValue = 2.0*statisticsRatingValue.statisticsRatingValue(book.getEid());
		book.setRatingValue(ratingValue);
		int reviewCount = ratingListService.selectRatingListLimitByEidCount(book.getEid());
		book.setReviewCount(reviewCount);
		
		return book;
	}
	
	/**
	 * 实时统计图书平均分与总评分人数
	 * @param list
	 * @return
	 */
	private List<EBook> statisticsRating(List<EBook> list){
		for(EBook book : list){
			double ratingValue = 2.0*statisticsRatingValue.statisticsRatingValue(book.getEid());
			book.setRatingValue(ratingValue);
			int reviewCount = ratingListService.selectRatingListLimitByEidCount(book.getEid());
			book.setReviewCount(reviewCount);
		}
		
		return list;
	}

	@Override
	public EBook queryEBookByEid(String eid) {
		EBook book = this.eBookDao.queryEBookByEid(eid);
		book = initEBookImgAddress(book);
		book = statisticsRating(book);
		return book;
	}
	
	@Override
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start, String orderCondition, String order) {
		List<EBook> list = this.eBookDao.queryEBookLimitByClassifyMain(classifyMain, start, orderCondition, order);
//		list = statisticsRating(list);
		return this.initEBookImgAddress(list);
	}
	
	@Override
	public int queryEBookByClassifyMainCount(String classifyMain){
		int num = this.eBookDao.queryEBookByClassifyMainCount(classifyMain);
		return num;
	}

	@Override
	public List<EBook> queryEBookByCondition(EBook condition, Integer start,
			String orderCondition, String order) {
		List<EBook> list = this.eBookDao.queryEBookByCondition(condition, start, orderCondition, order);
//		list = statisticsRating(list);
		return this.initEBookImgAddress(list);
	}

	@Override
	public List<EBook> queryEBookByKeyword(String Keyword, Integer start,
			String orderCondition, String order) {
		List<EBook> list = this.eBookDao.queryEBookByKeyword(Keyword, start, orderCondition, order);
//		list = statisticsRating(list);
		return this.initEBookImgAddress(list);
	}

	@Override
	public int queryEBookByKeywordCount(String Keyword, String orderCondition,
			String order) {
		int num = this.eBookDao.queryEBookByKeywordCount(Keyword, orderCondition, order);
		return num;
	}

	@Override
	public int queryEBookByClassifyMainCountHasRatingValue(String classifyMain) {
		int num = this.eBookDao.queryEBookByClassifyMainCountHasRatingValue(classifyMain);
		return num;
	}

	/**
	 * 与这本书相似的图书
	 */
	@Override
	public List<MatrixC> similarityEBooks(String eid) {
		// 获取与该图书同现的书籍列表
		List<MatrixC> cList = this.matrixCDao.selectMatrixCByEidAOrEidB(eid, eid);
		// 根据相似度进行排序
		Collections.sort(cList);
			

		// 选出前10的图书
		List<MatrixC> resultList = cList.subList(0, 9);
		return resultList;
	}
		


}

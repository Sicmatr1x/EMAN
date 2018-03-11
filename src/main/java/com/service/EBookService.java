package com.service;

import java.util.List;

import com.entity.EBook;
import com.entity.EBookTuple;
import com.entity.MatrixC;

public interface EBookService {
	
	public EBook queryEBookByEid(String eid);
	
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start, String orderCondition, String order);
	
	public int queryEBookByClassifyMainCount(String classifyMain);
	
	public int queryEBookByClassifyMainCountHasRatingValue(String classifyMain);
	
	public List<EBook> queryEBookByCondition(EBook orderCondition, Integer start, String orderConditon, String order);
	
	public List<EBook> queryEBookByKeyword(String Keyword, Integer start, String orderCondition, String order);
	
	public int queryEBookByKeywordCount(String Keyword,  String orderCondition, String order);
	
	/**
	 * 喜欢这本书的用户还喜欢
	 * @param eid 图书主键
	 * @return
	 */
	public List<MatrixC> similarityEBooks(String eid);
}

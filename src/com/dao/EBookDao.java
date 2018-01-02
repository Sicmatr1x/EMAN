package com.dao;

import java.util.List;

import com.entity.EBook;

public interface EBookDao {
	
	public EBook queryEBookByEid(int eid);
	
	public List<EBook> queryAllEBookLimit(Integer start);
	
	public List<EBook> quertyAllEBook();
	
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start);
	
	public List<EBook> queryEBookByCondition(EBook condition, Integer start, String orderConditon, String order);
	
	public List<EBook> queryEBookByKeyword(String Keyword, Integer start, String orderCondition, String order);
	
	public int queryEBookByKeywordCount(String Keyword,  String orderCondition, String order);
}

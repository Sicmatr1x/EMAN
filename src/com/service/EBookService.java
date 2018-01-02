package com.service;

import java.util.List;

import com.entity.EBook;

public interface EBookService {
	
	public EBook queryEBookByEid(int eid);
	
	public List<EBook> quertyAllEBook();
	
	public List<EBook> queryAllEBookLimit(int start);
	
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start);
	
	public List<EBook> queryEBookByCondition(EBook orderCondition, Integer start, String orderConditon, String order);
	
	public List<EBook> queryEBookByKeyword(String Keyword, Integer start, String orderCondition, String order);
	
	public int queryEBookByKeywordCount(String Keyword,  String orderCondition, String order);
}

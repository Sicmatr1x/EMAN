package com.dao;

import java.util.List;

import com.entity.EBook;

public interface EBookDao {
	
	/**
	 * 根据图书eid查询图书
	 * @param eid 图书主键
	 * @return
	 */
	public EBook queryEBookByEid(String eid);
	
	/**
	 * 根据图书分类查询图书
	 * @param classifyMain 图书分类
	 * @param start 显示结果下标
	 * @param orderCondition 用于排序属性
	 * @param order 排序顺序
	 * @return
	 */
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start, String orderCondition, String order);
	
	/**
	 * 显示根据图书分类查询图书的结果条数
	 * @param classifyMain 图书分类
	 * @param orderCondition 用于排序属性
	 * @param order 排序顺序
	 * @return
	 */
	public int queryEBookByClassifyMainCount(String classifyMain, String orderCondition, String order);
	
	/**
	 * 高级查询(且条件)
	 * @param condition 高级条件
	 * @param start 显示结果下标
	 * @param orderConditon 用于排序属性
	 * @param order 排序顺序
	 * @return
	 */
	public List<EBook> queryEBookByCondition(EBook condition, Integer start, String orderConditon, String order);
	
	/**
	 * 关键词查询(或条件)
	 * @param keyword 关键词
	 * @param start 显示结果下标
	 * @param orderCondition
	 * @param order 排序顺序
	 * @return
	 */
	public List<EBook> queryEBookByKeyword(String keyword, Integer start, String orderCondition, String order);
	
	/**
	 * 显示关键词查询(或条件)的结果条数
	 * @param keyword 关键词
	 * @param orderCondition 用于排序属性
	 * @param order 排序顺序
	 * @return
	 */
	public int queryEBookByKeywordCount(String keyword,  String orderCondition, String order);
}

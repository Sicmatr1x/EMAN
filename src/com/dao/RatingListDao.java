package com.dao;

import java.util.List;

import com.entity.RatingList;

/*
 * 提供评分评论表的访问
 */
public interface RatingListDao {
	
	/**
	 * 根据图书eid查询图书评分列表
	 * @param eid
	 * @return
	 */
	public List<RatingList> selectRatingListByEid(String eid);
	
	/**
	 * 根据图书eid查询图书评分列表,并且只选出评分高于ratingValue的结果
	 * @param eid
	 * @return
	 */
	public List<RatingList> selectRatingListByEidAndRatingValue(String eid, int ratingValue);
	
	/**
	 * 根据图书uid查询图书评分列表,并且只选出评分高于ratingValue的结果
	 * @param uid
	 * @return
	 */
	public List<RatingList> selectRatingListByUidAndRatingValue(String uid, int ratingValue);
	
	/**
	 * 根据图书eid查询图书评分列表(数据库分页)
	 * @param eid
	 * @return
	 */
	public List<RatingList> selectRatingListLimitByEid(String eid, Integer start, String orderCondition, String order);
	/**
	 * 显示根据图书eid查询图书评分列表的结果条数
	 * @param eid
	 * @param start
	 * @param orderCondition
	 * @param order
	 * @return
	 */
	public int selectRatingListLimitByEidCount(String eid);
	
	/**
	 * 根据图书eid与用户uid唯一查询图书单条评分
	 * @param eid 图书主键
	 * @param uid 用户主键
	 * @return
	 */
	public RatingList selectRatingListByEidAndUid(String eid, String uid);
	
	
	/**
	 * 新增评分
	 * @param rList
	 */
	public void insertRatingList(RatingList rList);
}

package com.dao;

import java.util.List;

import com.entity.RatingList;

public interface RatingListDao {
	
	/**
	 * 根据图书eid查询图书评分列表
	 * @param eid
	 * @return
	 */
	public List<RatingList> selectRatingListByEid(String eid);
	
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

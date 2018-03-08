package com.service;

import java.util.List;

import com.entity.RatingList;

public interface RatingListService {
	
	public void insertRatingList(RatingList rList);
	
	public List<RatingList> selectRatingListByEid(String eid);
	
	public List<RatingList> selectRatingListByEidAndRatingValue(String eid, int ratingValue);
	
	public List<RatingList> selectRatingListByUidAndRatingValue(String uid, int ratingValue);
	
	public RatingList selectRatingListByEidAndUid(String eid, String uid);
	
	public List<RatingList> selectRatingListLimitByEid(String eid, Integer start, String orderCondition, String order);
	
	public int selectRatingListLimitByEidCount(String eid);
}

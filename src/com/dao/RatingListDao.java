package com.dao;

import java.util.List;

import com.entity.RatingList;

public interface RatingListDao {
	
	public List<RatingList> selectRatingListByEid(String eid);
	
	public RatingList selectRatingListByEidAndUid(String eid, String uid);
	
	public void insertRatingList(RatingList rList);
}

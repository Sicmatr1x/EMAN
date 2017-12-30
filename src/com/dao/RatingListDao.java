package com.dao;

import java.util.List;

import com.entity.RatingList;

public interface RatingListDao {
	
	public List<RatingList> selectRatingListByEid(int eid);
	
	public RatingList selectRatingListByEidAndUid(int eid, int uid);
	
	public void insertRatingList(RatingList rList);
}

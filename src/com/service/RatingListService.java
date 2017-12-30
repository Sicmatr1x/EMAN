package com.service;

import java.util.List;

import com.entity.RatingList;

public interface RatingListService {
	
public List<RatingList> selectRatingListByEid(int eid);
	
	public RatingList selectRatingListByEidAndUid(int eid, int uid);
	
}

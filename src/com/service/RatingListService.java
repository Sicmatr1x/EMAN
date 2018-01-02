package com.service;

import java.util.List;

import com.entity.RatingList;

public interface RatingListService {
	
public List<RatingList> selectRatingListByEid(String eid);
	
	public RatingList selectRatingListByEidAndUid(String eid, String uid);
	
}

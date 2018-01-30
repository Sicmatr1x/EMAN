package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.RatingListDao;
import com.entity.RatingList;


@Repository("ratingListDao")
public class RatingListDaoImpl implements RatingListDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<RatingList> selectRatingListByEid(String eid) {
		List<RatingList> list = sqlSession.selectList("selectRatingListByEid", eid);
		return list;
	}

	@Override
	public RatingList selectRatingListByEidAndUid(String eid, String uid) {
		Map<String, String> args = new HashMap<String, String>();
		args.put("eid", eid);
		args.put("uid", uid);
		return sqlSession.selectOne("selectRatingListByEidAndUid", args);
	}

	@Override
	public void insertRatingList(RatingList rList) {
		this.sqlSession.insert("insertRatingList", rList);
		this.sqlSession.commit();
	}

	@Override
	public List<RatingList> selectRatingListLimitByEid(String eid, Integer start, String orderCondition, String order) {
		if(start < 0 || start == null)
			start = 0;
		if(orderCondition == null || orderCondition.equals(""))
			orderCondition = "rid";
		if(order == null || order.equals(""))
			order = "asc";
		
		Integer size = 20;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("eid", eid);
		args.put("orderCondition", orderCondition);
		args.put("order", order);
		args.put("start", start);
		args.put("size", size);
		List<RatingList> list = sqlSession.selectList("selectRatingListLimitByEid",args);
		return list;
	}

	@Override
	public int selectRatingListLimitByEidCount(String eid) {
		int num = sqlSession.selectOne("selectRatingListLimitByEidCount", eid);
		return num;
	}

	

}

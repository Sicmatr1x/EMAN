package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.entity.RatingList;

@Repository("statisticsRatingValue")
public class StatisticsRatingValue {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	/**
	 * 获得用户评分的平均数(0~5)
	 * @param eid
	 * @return 精确到小数点后一位
	 */
	public double statisticsRatingValue(String eid){
		List<RatingList> list = sqlSession.selectList("selectRatingListByEid", eid);
		double sum = 0;
		for(RatingList r : list){
			sum += r.getRatingValue();
		}
		return (double)(Math.round((sum / list.size())*10)/10.0);
	}
	
	public static void main(String[] args){
		StatisticsRatingValue statisticsRatingValue = new StatisticsRatingValue();
		
		System.out.println(statisticsRatingValue.statisticsRatingValue("930946"));
	}
}

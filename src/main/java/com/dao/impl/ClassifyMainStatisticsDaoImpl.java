package com.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.ClassifyMainStatisticsDao;
import com.dao.DBAccess;
import com.entity.ClassifyMainStatistics;

import java.util.List;

@Repository("classifyMainStatisticsDao")
public class ClassifyMainStatisticsDaoImpl implements ClassifyMainStatisticsDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Override
	public ClassifyMainStatistics queryClassifyMainStatisticsByClassifyMain(
			String classifyMain) {
		return sqlSession.selectOne("selectClassifyMainStatisticsByClassifyMain", classifyMain);
	}

	@Override
	public List<ClassifyMainStatistics> queryAllClassifyMainStatistics() {
	    // 不知道为什么这个方法老是抛出 java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for
		List<ClassifyMainStatistics> list = sqlSession.selectList("selectAllClassifyMainStatistics");
		return list;
	}

	@Override
	public int updateClassifyMainStatistics(
			ClassifyMainStatistics classifyMainStatistics) {
//		Map<String, Object> args = new HashMap<String, Object>();
//		args.put("reviewCount5", classifyMainStatistics.getReviewCount5());
//		args.put("reviewCount4", classifyMainStatistics.getReviewCount4());
//		args.put("reviewCount3", classifyMainStatistics.getReviewCount3());
//		args.put("reviewCount2", classifyMainStatistics.getReviewCount2());
//		args.put("reviewCount1", classifyMainStatistics.getReviewCount1());
//		args.put("varianceRatingValue", classifyMainStatistics.getVarianceRatingValue());
//		args.put("avgRatingValue", classifyMainStatistics.getAvgRatingValue());
		int result = sqlSession.update("updateClassifyMain", classifyMainStatistics);
		this.sqlSession.commit();
		return result;

	}

}

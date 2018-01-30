package com.dao;

import com.entity.ClassifyMainStatistics;

public interface ClassifyMainStatisticsDao {
	/**
	 * 根据统计数据图书主分类名查询统计数据
	 * @param classifyMain 图书主分类名
	 * @return
	 */
	public ClassifyMainStatistics queryClassifyMainStatisticsByClassifyMain(String classifyMain);
	
	
	/**
	 * 修改统计数据
	 * @param classifyMainStatistics
	 */
	public int updateClassifyMainStatistics(ClassifyMainStatistics classifyMainStatistics);
}

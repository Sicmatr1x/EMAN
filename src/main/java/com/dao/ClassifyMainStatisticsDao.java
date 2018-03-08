package com.dao;

import com.entity.ClassifyMainStatistics;

import java.util.List;

/*
 * 提供主分区统计表的访问
 */
public interface ClassifyMainStatisticsDao {
	/**
	 * 根据统计数据图书主分类名查询统计数据
	 * @param classifyMain 图书主分类名
	 * @return
	 */
	public ClassifyMainStatistics queryClassifyMainStatisticsByClassifyMain(String classifyMain);

	/**
	 * 查询全部统计数据
	 * @return
	 */
	public List<ClassifyMainStatistics> queryAllClassifyMainStatistics();
	
	
	/**
	 * 修改统计数据
	 * @param classifyMainStatistics
	 */
	public int updateClassifyMainStatistics(ClassifyMainStatistics classifyMainStatistics);
}

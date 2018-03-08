package com.service;

import com.entity.ClassifyMainStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classifyMainStatisticsService")
public interface ClassifyMainStatisticsService {

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

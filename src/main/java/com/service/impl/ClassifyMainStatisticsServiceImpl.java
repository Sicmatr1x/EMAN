package com.service.impl;

import com.dao.ClassifyMainStatisticsDao;
import com.entity.ClassifyMainStatistics;
import com.service.ClassifyMainStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classifyMainStatisticsService")
public class ClassifyMainStatisticsServiceImpl implements ClassifyMainStatisticsService {

    @Autowired
    ClassifyMainStatisticsDao classifyMainStatisticsDao = null;

    public ClassifyMainStatisticsDao getClassifyMainStatisticsDao() {
        return classifyMainStatisticsDao;
    }

    public void setClassifyMainStatisticsDao(ClassifyMainStatisticsDao classifyMainStatisticsDao) {
        this.classifyMainStatisticsDao = classifyMainStatisticsDao;
    }

    @Override
    public ClassifyMainStatistics queryClassifyMainStatisticsByClassifyMain(String classifyMain) {
        return this.classifyMainStatisticsDao.queryClassifyMainStatisticsByClassifyMain(classifyMain);
    }

    @Override
    public List<ClassifyMainStatistics> queryAllClassifyMainStatistics() {
        return this.classifyMainStatisticsDao.queryAllClassifyMainStatistics();
    }

    @Override
    public int updateClassifyMainStatistics(ClassifyMainStatistics classifyMainStatistics) {
        return this.classifyMainStatisticsDao.updateClassifyMainStatistics(classifyMainStatistics);
    }
}

package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.ClassifyMainStatisticsDao;

/*
 * 统计模块接口(待定)
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private ClassifyMainStatisticsDao classifyMainStatisticsDao = null;

	public ClassifyMainStatisticsDao getClassifyMainStatisticsDao() {
		return classifyMainStatisticsDao;
	}

	public void setClassifyMainStatisticsDao(
			ClassifyMainStatisticsDao classifyMainStatisticsDao) {
		this.classifyMainStatisticsDao = classifyMainStatisticsDao;
	}

	
}

package com.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.ClassifyMainStatisticsDao;
import com.entity.User;
import com.service.UserService;

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

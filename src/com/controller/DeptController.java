package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Dept;
import com.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private DeptService deptService = null;

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	
	@RequestMapping("/queryAllDept.htm")
	public String queryAllDept(HttpServletRequest request){
		List<Dept> list = deptService.quertyAllDept();
		request.setAttribute("list", list);
		return "showAllDept";
	}
	
	@RequestMapping("/queryDept.htm")
	public String queryDept(@RequestParam(value="deptno")int deptno, HttpServletRequest request){
		Dept dept = deptService.queryDept(deptno);
		request.setAttribute("dept", dept);
		return "showOneDept";
	}
	
	@RequestMapping("/addDept.htm")
	public String addDept(@RequestParam(value="deptno")int deptno, HttpServletRequest request){
		Dept dept = deptService.queryDept(deptno);
		request.setAttribute("dept", dept);
		return "showOneDept";
	}
	
	@RequestMapping("/editDeptSalary.htm")
	public String editDeptSalary(@RequestParam(value="deptno")int deptno,
			@RequestParam(value="salary")int salary,
			HttpServletRequest request){
		Dept dept = deptService.queryDept(deptno);
		dept.setSalary(salary);
		deptService.editDept(dept);
		request.setAttribute("dept", dept);
		return "showOneDept";
	}
	
	
}

package com.service;

import java.util.List;

import com.entity.Dept;

public interface DeptService {
	
	public Dept queryDept(int deptno);
	
	public int insertDept(Dept dept);
	
	public int editDept(Dept dept);
	
	public List<Dept> quertyAllDept();
}

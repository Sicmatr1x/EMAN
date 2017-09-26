package com.dao;

import java.util.List;

import com.entity.Dept;

public interface DeptDao {
	
	public Dept queryDept(int deptno);
	
	public int insertDept(Dept dept);
	
	public int editDept(Dept dept);
	
	public List<Dept> quertyAllDept();
}

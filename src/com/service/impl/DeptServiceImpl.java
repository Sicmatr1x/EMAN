package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DeptDao;
import com.entity.Dept;
import com.service.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao = null;

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public int insertDept(Dept dept) {
		return deptDao.insertDept(dept);
	}

	@Override
	public int editDept(Dept dept) {
		return deptDao.editDept(dept);
	}

	@Override
	public List<Dept> quertyAllDept() {
		return deptDao.quertyAllDept();
	}

	@Override
	public Dept queryDept(int deptno) {
		return deptDao.queryDept(deptno);
	}

}

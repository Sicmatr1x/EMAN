package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.DeptDao;
import com.entity.Dept;

@Repository("deptDao")
public class DeptDaoImpl implements DeptDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertDept(Dept dept) {
		return sqlSession.insert("insertDept", dept);
	}

	@Override
	public int editDept(Dept dept) {
		int result = sqlSession.update("updateDept", dept);
		sqlSession.commit();//提交事务
		return result;
	}

	@Override
	public List<Dept> quertyAllDept() {
		List<Dept> list = sqlSession.selectList("selectAllDept");
		return list;
	}

	@Override
	public Dept queryDept(int deptno) {
		return sqlSession.selectOne("selectDept", deptno);
	}



}

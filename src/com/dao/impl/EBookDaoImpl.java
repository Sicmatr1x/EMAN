package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.EBookDao;
import com.entity.EBook;

@Repository("eBookDao")
public class EBookDaoImpl implements EBookDao{
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public EBook queryEBookByEid(int eid) {
		return sqlSession.selectOne("selectEBookByEid", eid);
	}

	@Override
	public List<EBook> quertyAllEBook() {
		List<EBook> list = sqlSession.selectList("selectAllEBook");
		return list;
	}

	@Override
	public List<EBook> queryEBookSimpleInfo() {
		List<EBook> list = sqlSession.selectList("selectEBookSimpleInfo");
		return list;
	}

}

package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.UserDao;
import com.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Override
	public User queryUserByUid(String uid) {
		return sqlSession.selectOne("selectUserByUid", uid);
	}

	@Override
	public User queryUserByUname(String uname) {
		return sqlSession.selectOne("selectUserByUname", uname);
	}

	@Override
	public void insertUser(User user) {
		this.sqlSession.insert("insertUser", user);
		this.sqlSession.commit();
	}

	@Override
	public List<User> queryAllUser() {
		return sqlSession.selectList("selectAllUser");
	}

}

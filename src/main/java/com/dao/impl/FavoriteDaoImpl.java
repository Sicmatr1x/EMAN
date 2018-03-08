package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.FavoriteDao;
import com.entity.Favorite;

@Repository("favoriteDao")
public class FavoriteDaoImpl implements FavoriteDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int deleteFavoriteByUidAndclassifyMainAndclassifySub(Favorite favorite) {
		int result = this.sqlSession.delete("deleteFavoriteByUidAndclassifyMainAndclassifySub", favorite);
		this.sqlSession.commit();
		return result;
	}

	@Override
	public List<Favorite> selectFavoriteByUid(String uid) {
		return sqlSession.selectList("selectFavoriteByUid", uid);
	}

	@Override
	public void insertFavorite(Favorite favorite) {
		this.sqlSession.insert("insertFavorite", favorite);
		this.sqlSession.commit();

	}

}

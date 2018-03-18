package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FavoriteDao;
import com.entity.Favorite;
import com.service.FavoriteService;

@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
	
	@Autowired
	private FavoriteDao favoriteDao = null;

	public FavoriteDao getFavoriteDao() {
		return favoriteDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public int deleteFavoriteByUidAndclassifyMainAndclassifySub(Favorite favorite) {
		return favoriteDao.deleteFavoriteByUidAndclassifyMainAndclassifySub(favorite);
	}

	@Override
	public int deleteAllFavoriteByUid(String uid) {
		Favorite favorite = new Favorite();
		favorite.setUid(uid);
		return favoriteDao.deleteAllFavoriteByUid(favorite);
	}

	@Override
	public List<Favorite> selectFavoriteByUid(String uid) {
		return favoriteDao.selectFavoriteByUid(uid);
	}

	@Override
	public void insertFavorite(Favorite favorite) {
		favoriteDao.insertFavorite(favorite);

	}

}

package com.service;

import java.util.List;

import com.entity.Favorite;

public interface FavoriteService {
	/**
	 * 根据用户uid和分类名删除分类记录
	 * @param favorite
	 * @return
	 */
	public int deleteFavoriteByUidAndclassifyMainAndclassifySub(Favorite favorite);
	
	/**
	 * 根据用户uid查询用户喜爱分类
	 * @param uid
	 * @return
	 */
	public List<Favorite> selectFavoriteByUid(String uid);
	
	/**
	 * 新增用户喜爱分类数据
	 * @param favorite
	 */
	public void insertFavorite(Favorite favorite);
}

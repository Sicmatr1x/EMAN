package com.service;

import com.entity.EBook;
import com.entity.User;

import java.util.List;

public interface UserService {
	
	public User queryUserByUid(String uid);
	
	public User queryUserByUname(String uname);
	
	public void insertUser(User user);

	/**
	 * 根据物品的相似度和用户的历史行为给用户生成推荐列表
	 * @param uid
	 * @return
	 */
	public List<EBook> userRecommendedList(String uid);
}

package com.dao;

import com.entity.User;

public interface UserDao {
	public User queryUserByUid(int uid);
	
	public User queryUserByUname(String uname);
	
	public void insertUser(User user);
}

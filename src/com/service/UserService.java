package com.service;

import com.entity.User;

public interface UserService {
	
	public User queryUserByUid(String uid);
	
	public User queryUserByUname(String uname);
	
	public void insertUser(User user);
}

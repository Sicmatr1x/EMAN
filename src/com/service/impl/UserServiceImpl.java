package com.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao = null;

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	@Override
	public User queryUserByUid(int uid) {
		User user = this.userDao.queryUserByUid(uid);
		return user;
	}

	@Override
	public User queryUserByUname(String uname) {
		User user = this.userDao.queryUserByUname(uname);
		return user;
	}

	@Override
	public void insertUser(User user) {
		this.userDao.insertUser(user);
	}

	

}

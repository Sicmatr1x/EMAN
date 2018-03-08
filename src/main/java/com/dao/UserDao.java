package com.dao;

import java.util.List;

import com.entity.User;

/*
 * 提供用户表的访问
 */
public interface UserDao {
	/**
	 * 根据用户uid查询用户
	 * @param uid 用户主键
	 * @return
	 */
	public User queryUserByUid(String uid);
	
	/**
	 * 根据用户名查询用户
	 * @param uname 用户名
	 * @return
	 */
	public User queryUserByUname(String uname);
	
	/**
	 * 获取全部用户
	 * @param uname 用户名
	 * @return
	 */
	public List<User> queryAllUser();
	
	/**
	 * 新增用户数据
	 * @param user
	 */
	public void insertUser(User user);
}

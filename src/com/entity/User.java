package com.entity;

public class User {
	private String uid;
	private String uname;
	private String email;
	private String password;
	
	public User() {
		
	}
	
	public User(String uid, String uname, String email, String password) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.email = email;
		this.password = password;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

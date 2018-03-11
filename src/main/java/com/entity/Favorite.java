package com.entity;

/**
 * 用户喜爱分类实体
 */
public class Favorite {
	
	private Integer fid;
	private String uid;
	private String classifyMain;
	private String classifySub;
	
	
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getClassifyMain() {
		return classifyMain;
	}
	public void setClassifyMain(String classifyMain) {
		this.classifyMain = classifyMain;
	}
	public String getClassifySub() {
		return classifySub;
	}
	public void setClassifySub(String classifySub) {
		this.classifySub = classifySub;
	}
	
	
}

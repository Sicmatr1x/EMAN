package com.entity;

public class RatingList {
	/**
	 * 数据库自增主键禁止设值
	 */
	private Integer rid;
	/**
	 * 图书主键
	 */
	private String eid;
	/**
	 * 用户主键
	 */
	private String uid;
	/**
	 * 用户评分
	 */
	private Double ratingValue;
	/**
	 * 用户评价
	 */
	private String rdescribe;
	/**
	 * 用户对象(联级查询用)
	 */
	private User user;
	
	public RatingList() {

	}
	
	
	public void showField(){
		System.out.println("-----" + this.toString() + "-----");
		System.out.println("rid=" + this.rid);
		System.out.println("eid=" + this.eid);
		System.out.println("uid=" + this.uid);
		user.showField();
		System.out.println("ratingValue=" + this.ratingValue);
		System.out.println("rdescribe=" + this.rdescribe);
		System.out.println("----------");
	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Double getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(Double ratingValue) {
		this.ratingValue = ratingValue;
	}
	public String getRdescribe() {
		return rdescribe;
	}
	public void setRdescribe(String rdescribe) {
		this.rdescribe = rdescribe;
	}
	
	
}

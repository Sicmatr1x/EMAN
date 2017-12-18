package com.entity;

public class RatingList {
	/**
	 * 数据库自增主键禁止设值
	 */
	private Integer rid;
	private String eid;
	private String uid;
	private Double ratingValue;
	private String rdescribe;
	
	public RatingList() {

	}
	public RatingList(Integer rid, String eid, String uid, Double ratingValue,
			String rdescribe) {
		super();
		this.rid = rid;
		this.eid = eid;
		this.uid = uid;
		this.ratingValue = ratingValue;
		this.rdescribe = rdescribe;
	}
	
	public void showField(){
		System.out.println("-----" + this.toString() + "-----");
		System.out.println("rid=" + this.rid);
		System.out.println("eid=" + this.eid);
		System.out.println("uid=" + this.uid);
		System.out.println("ratingValue=" + this.ratingValue);
		System.out.println("rdescribe=" + this.rdescribe);
		System.out.println("----------");
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

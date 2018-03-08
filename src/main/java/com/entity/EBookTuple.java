package com.entity;

/*
 * 用于计算基于用户相似度时使用
 */
public class EBookTuple implements Comparable<EBookTuple>{
	/*
	 * 图书id
	 */
	private String eid;
	/*
	 * 有多少个用户喜欢
	 */
	private Integer counter;
	
	private EBook ebook;
	
	public EBookTuple() {

	}
	
	
	public EBookTuple(String eid, Integer counter) {
		this.eid = eid;
		this.counter = counter;
	}
	
	@Override  
    public int compareTo(EBookTuple e) {  
        int i = this.getCounter() - e.getCounter();
        return i;  
    }  


	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}


	public EBook getEbook() {
		return ebook;
	}


	public void setEbook(EBook ebook) {
		this.ebook = ebook;
	}
	
}

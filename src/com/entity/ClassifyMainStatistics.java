package com.entity;

public class ClassifyMainStatistics {
	private String classifyMain;
	private Integer reviewCount5;
	private Integer reviewCount4;
	private Integer reviewCount3;
	private Integer reviewCount2;
	private Integer reviewCount1;
	private Double varianceRatingValue;
	private Double avgRatingValue;
	
	public int getReviewCountSum(){
		return reviewCount5 + reviewCount4 + reviewCount3 + reviewCount2 + reviewCount1;
	}
	
	public String getClassifyMain() {
		return classifyMain;
	}
	public void setClassifyMain(String classifyMain) {
		this.classifyMain = classifyMain;
	}
	public int getReviewCount5() {
		return reviewCount5;
	}
	public void setReviewCount5(int reviewCount5) {
		this.reviewCount5 = reviewCount5;
	}
	public int getReviewCount4() {
		return reviewCount4;
	}
	public void setReviewCount4(int reviewCount4) {
		this.reviewCount4 = reviewCount4;
	}
	public int getReviewCount3() {
		return reviewCount3;
	}
	public void setReviewCount3(int reviewCount3) {
		this.reviewCount3 = reviewCount3;
	}
	public int getReviewCount2() {
		return reviewCount2;
	}
	public void setReviewCount2(int reviewCount2) {
		this.reviewCount2 = reviewCount2;
	}
	public int getReviewCount1() {
		return reviewCount1;
	}
	public void setReviewCount1(int reviewCount1) {
		this.reviewCount1 = reviewCount1;
	}
	public double getVarianceRatingValue() {
		return varianceRatingValue;
	}
	public void setVarianceRatingValue(double varianceRatingValue) {
		this.varianceRatingValue = varianceRatingValue;
	}
	public double getAvgRatingValue() {
		return avgRatingValue;
	}
	public void setAvgRatingValue(double avgRatingValue) {
		this.avgRatingValue = avgRatingValue;
	}
	
}

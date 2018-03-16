package com.entity;

public class Item implements Comparable<Item>{
    /**
     * 同现图书的eid
     */
    public String eid;
    /**
     * 用户喜爱的图书与该同现图书的余弦相似度数组
     */
    public double[] col;
    /**
     * 预测兴趣度
     */
    public double interestValue;

    public Item(String eid, double[] col) {
        this.eid = eid;
        this.col = col;
    }

    @Override
    public  boolean equals(Object obj){
        if(this.eid.equals(((Item)obj).eid))
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Item item) {
        double value = item.interestValue - this.interestValue;
        if(value > 0)
            return 1;
        else if(value < 0)
            return -1;
        else
            return 0;
    }
}

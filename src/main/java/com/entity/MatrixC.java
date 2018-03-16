package com.entity;

/**
 * 共现矩阵C与
 */
public class MatrixC implements Comparable<MatrixC>{
	private Integer cid;
	private String eida;
	private String eidb;
	private Integer counter;
	private Double cos_similarity;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getEida() {
		return eida;
	}
	public void setEida(String eida) {
		this.eida = eida;
	}
	public String getEidb() {
		return eidb;
	}
	public void setEidb(String eidb) {
		this.eidb = eidb;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	public Double getCos_similarity() {
		return cos_similarity;
	}
	public void setCos_similarity(Double cos_similarity) {
		this.cos_similarity = cos_similarity;
	}


	@Override
	public int compareTo(MatrixC c) {
		if(this.getCos_similarity() == null || c.getCos_similarity() == null){
			return this.counter - c.getCounter();
		}else{
			double value = c.getCos_similarity() - this.getCos_similarity();
			if(value > 0)
				return 1;
			else if(value < 0)
				return -1;
			else
				return 0;
		}

	}
}

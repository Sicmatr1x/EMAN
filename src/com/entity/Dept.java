package com.entity;


public class Dept {
	

	private int deptno;
	
	private String dname; //部门名字
	
	private String loc; //部门位置
	
	private int salary;
	
	public Dept() {
	}
	
	public Dept(int deptno, String dname, String loc, int salary) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
		this.salary = salary;
	}

	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc
				+ ", salary=" + salary + "]";
	}
	
	
}

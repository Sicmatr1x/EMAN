package com.dao;

import java.util.List;

import com.entity.EBook;

public interface EBookDao {
	
	public EBook queryEBookByEid(int eid);
	
	public List<EBook> queryAllEBookLimit(Integer start);
	
	public List<EBook> queryEBookSimpleInfo();
	
	public List<EBook> quertyAllEBook();
}

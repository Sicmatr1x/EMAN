package com.service;

import java.util.List;

import com.entity.EBook;

public interface EBookService {
	
	public EBook queryEBookByEid(int eid);
	
	public List<EBook> quertyAllEBook();
}

package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EBookDao;
import com.entity.EBook;
import com.service.EBookService;

@Service("eBookService")
public class EBookServiceImpl implements EBookService {
	
	@Autowired
	private EBookDao eBookDao = null;

	public EBookDao getEBookDao() {
		return eBookDao;
	}

	public void setEBookDao(EBookDao eBookDao) {
		this.eBookDao = eBookDao;
	}

	@Override
	public EBook queryEBookByEid(int eid) {
		return this.eBookDao.queryEBookByEid(eid);
	}

	@Override
	public List<EBook> quertyAllEBook() {
		return this.eBookDao.quertyAllEBook();
	}

}

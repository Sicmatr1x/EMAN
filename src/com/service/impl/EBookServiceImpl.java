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
		EBook book = this.eBookDao.queryEBookByEid(eid);
		if(book.getImgAddress()!=null){
			int beg = book.getImgAddress().indexOf("public/");
			int end = book.getImgAddress().indexOf("?v=");
			if(end==-1){
				
			}else{
				String tString = book.getImgAddress().substring(beg + "public/".length(), end);
				book.setImgAddress(tString);
			}
		}
		return book;
	}

	@Override
	public List<EBook> quertyAllEBook() {
		return this.eBookDao.quertyAllEBook();
	}

	@Override
	public List<EBook> queryAllEBookLimit(int start) {
		List<EBook> list = this.eBookDao.queryAllEBookLimit(start);
		for(EBook book : list){
			if(book.getImgAddress()!=null){
				int beg = book.getImgAddress().indexOf("public/");
				int end = book.getImgAddress().indexOf("?v=");
				if(end==-1)
					continue;
				String tString = book.getImgAddress().substring(beg + "public/".length(), end);
				book.setImgAddress(tString);
			}
			
		}
		return list;
	}

}

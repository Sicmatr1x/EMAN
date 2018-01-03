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
	public EBook queryEBookByEid(String eid) {
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
	
	/**
	 * 处理图书的图片地址为可用
	 * @param list
	 * @return
	 */
	private List<EBook> initEBookImgAddress(List<EBook> list){
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

	
	@Override
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start, String orderCondition, String order) {
		List<EBook> list = this.eBookDao.queryEBookLimitByClassifyMain(classifyMain, start, orderCondition, order);
		return this.initEBookImgAddress(list);
	}
	
	@Override
	public int queryEBookByClassifyMainCount(String classifyMain, String orderCondition, String order){
		int num = this.eBookDao.queryEBookByClassifyMainCount(classifyMain, orderCondition, order);
		return num;
	}

	@Override
	public List<EBook> queryEBookByCondition(EBook condition, Integer start,
			String orderCondition, String order) {
		List<EBook> list = this.eBookDao.queryEBookByCondition(condition, start, orderCondition, order);
		return this.initEBookImgAddress(list);
	}

	@Override
	public List<EBook> queryEBookByKeyword(String Keyword, Integer start,
			String orderCondition, String order) {
		List<EBook> list = this.eBookDao.queryEBookByKeyword(Keyword, start, orderCondition, order);
		return this.initEBookImgAddress(list);
	}

	@Override
	public int queryEBookByKeywordCount(String Keyword, String orderCondition,
			String order) {
		int num = this.eBookDao.queryEBookByKeywordCount(Keyword, orderCondition, order);
		return num;
	}

}

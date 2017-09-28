package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.EBook;
import com.service.EBookService;

@Controller
@RequestMapping("/ebook")
public class EBookController {

	@Autowired
	private EBookService eBookService = null;

	public EBookService getEBookService() {
		return eBookService;
	}

	public void setEBookService(EBookService eBookService) {
		this.eBookService = eBookService;
	}
	
	
	@RequestMapping("/queryAllEBook.htm")
	public String queryAllEBook(HttpServletRequest request){
		List<EBook> list = eBookService.quertyAllEBook();
		request.setAttribute("list", list);
		return "showAllEBook";
	}
	
	@RequestMapping("/queryAllEBookLimit.htm")
	public String queryAllEBook(@RequestParam(value="start")int start, HttpServletRequest request){
		List<EBook> list = eBookService.queryAllEBookLimit(start);
		request.setAttribute("list", list);
		request.setAttribute("start", start+20);
		return "showAllEBook";
	}
	
	@RequestMapping("/queryEBook.htm")
	public String queryEBook(@RequestParam(value="eid")int eid, HttpServletRequest request){
		EBook eBook = eBookService.queryEBookByEid(eid);
		request.setAttribute("EBook", eBook);
		return "showOneEBook";
	}
	
	
	
	
}

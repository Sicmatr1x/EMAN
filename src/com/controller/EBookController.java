package com.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.EBook;
import com.service.EBookService;
import com.util.JSONConverter;

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
	
	/**
	 * http://localhost:8080/EMAN/ebook/test.htm
	 * @param request
	 * @return
	 */
	@RequestMapping("/test.htm")
	public String test(HttpServletRequest request){
		return "test";
	}
	
	/**
	 * http://localhost:8080/EMAN/ebook/EBookList.htm
	 * @param request
	 * @return
	 */
	@RequestMapping("/EBookList.htm")
	public String eBookList(HttpServletRequest request){
		return "EBookList";
	}
	
	/**
	 * http://localhost:8080/EMAN/ebook/queryAllEBook.htm
	 * @param request
	 * @return
	 */
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
	
	/**
	 * http://localhost:8080/EMAN/ebook/queryAllEBookLimitJson.htm?start=
	 * @param start
	 * @param out
	 */
	@RequestMapping("/queryAllEBookLimitJson.htm")
	@ResponseBody
	public void queryAllEBookJson(@RequestParam(value="start")int start, PrintWriter out, HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("/queryAllEBookLimitJson.htm?start=" + start);
		List<EBook> list = eBookService.queryAllEBookLimit(start);
		String json;
		json = JSONConverter.convertToJSONString(list);
		System.out.println(json);
		out.print(json);
		out.flush();
	}
	
	@RequestMapping("/queryEBook.htm")
	public String queryEBook(@RequestParam(value="eid")int eid, HttpServletRequest request){
		EBook eBook = eBookService.queryEBookByEid(eid);
		request.setAttribute("EBook", eBook);
		return "showOneEBook";
	}
	
	
	
	
}

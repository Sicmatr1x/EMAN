package com.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/*
 * 提供用于将 JavaBeans 对象直接转化为结构化 Json 数据
 */
public class JSONConverter {
	
	public static String convertToJSONString(Object o){
		ObjectMapper mapper = new ObjectMapper();
		String json = "{}" ;
		try {
			json = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			System.out.println("---发生JsonGenerationException异常---");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("---发生JsonMappingException异常---");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("---发生IOException异常---");
			e.printStackTrace();
		}
		return json;
	}
	
	public static void main(String[] args){
//		Dept dept = new Dept();
//		dept.setDeptno(0);
//		dept.setDname("Neo");
//		dept.setLoc("深圳");
//		dept.setSalary(25000);
//		String json = JSONConverter.convertToJSONString(dept);
//		System.out.println(json);
	}
}

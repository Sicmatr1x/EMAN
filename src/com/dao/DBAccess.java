package com.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAccess {
	
	private static SqlSession sqlSession = null;
	
	public static SqlSession getSqlSession() {
		
		Reader reader;
		if(sqlSession != null){
			return sqlSession;
		}
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			sqlSession = sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlSession;
	}
	
//	@Test
	public void testgetSqlSession(){
		SqlSession sqlSession = DBAccess.getSqlSession();
		System.out.println(sqlSession);
	}
	
}

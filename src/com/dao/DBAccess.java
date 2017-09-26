package com.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAccess {
	
	public static SqlSession getSqlSession() {
		// ͨ�������ļ���ȡ��ݿ�������Ϣ
		Reader reader;
		SqlSession sqlSession = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			// ͨ��������Ϣ����һ��SqlSessionFactory
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			// ͨ��SqlSessionFactory��һ����ݿ�Ự
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

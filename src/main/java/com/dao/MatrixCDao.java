package com.dao;

import java.util.List;

import com.entity.MatrixC;

/*
 * 提供同现矩阵C的访问
 */
public interface MatrixCDao {
	/**
	 * 获取同现矩阵C的全部数据
	 * @return
	 */
	public List<MatrixC> selectAllMatrixC();
	/**
	 * 根据eid获取与其共现的书籍们
	 * @return
	 */
	public List<MatrixC> selectMatrixCByEidA(String eida);
	/**
	 * 保存计算的余弦相似度
	 */
	public int updateMatrixCWithCos_similarity(String eida, String eidb, Double cos_similarity);
	/**
	 * 修改count根据eida和eidb
	 */
	public int updateMatrixCWithCountByEidAAndEidB(String eida, String eidb, Integer counter);
	/**
	 * 查询eida,eidb记录是否存在
	 * @return
	 */
	public MatrixC selectMatrixCByEidAAndEidB(String eida, String eidb);
	
	public void insertMatrixC(MatrixC c);
}

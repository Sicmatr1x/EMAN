package com.statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import spider.EBookListSpider;

import com.dao.DBAccess;
import com.dao.RatingListDao;
import com.dao.impl.RatingListDaoImpl;
import com.entity.RatingList;

/*
 * 统计主分类的各个分数的评分人数、平均评分、评分的方差
 */
public class StatisticsClassifyMain {
	
	private static SqlSession sqlSession = DBAccess.getSqlSession();
	
	private static RatingListDao ratingListDao = new RatingListDaoImpl();
	
	/**
	 * mysql数据库连接
	 */
	public static Connection conn = null;
	
	public static void initConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		String url ="jdbc:mysql://localhost/ebookdb?user=root&password=root&useUnicode=true&characterEncoding=utf-8";
		//myDB为数据库名
		conn= DriverManager.getConnection(url);
		if(conn == null){
			System.out.println("创建数据库连接失败");
		}else{
			System.out.println("创建数据库连接成功" + conn);
		}
	}
	public static void closeConnection() throws SQLException{
		conn.close();
	}
	
	/**
	 * 统计各个类型图书的各个分数的平均打分人数
	 * @throws SQLException 
	 */
	public static void statisticsAverage() throws SQLException{
		DecimalFormat df = new DecimalFormat();  
        df.setMinimumFractionDigits(5);  
		
		for(int i = 0; i < EBookListSpider.areaNameList.length; i++){
			
			List<RatingList> list = sqlSession.selectList("selectRatingListByClassifyMain", EBookListSpider.areaNameList[i]);
			int reviewCount5 = 0;
			int reviewCount4 = 0;
			int reviewCount3 = 0;
			int reviewCount2 = 0;
			int reviewCount1 = 0;
			double varianceRatingValue;
			double avgRatingValue;
			for(RatingList r:list){
				double value = r.getRatingValue();
				if(value > 4.0){
					reviewCount5++;
				}else if(value > 3.0){
					reviewCount4++;
				}else if(value > 2.0){
					reviewCount3++;
				}else if(value > 1.0){
					reviewCount2++;
				}else{
					reviewCount1++;
				}
			}
			// 计算平均分
			avgRatingValue = 1.0*(5*reviewCount5 + 4*reviewCount4 + 3*reviewCount3 + 2*reviewCount2 + reviewCount1) / list.size();
			double sum = 0.0;
			for(int j = 0; j < list.size(); j++){
				sum += Math.pow(list.get(j).getRatingValue() - avgRatingValue, 2);
			}
			varianceRatingValue = sum/ (list.size()-1);
			
			System.out.println(EBookListSpider.areaNameList[i] + "的" + list.size() + "条评分记录,平均数=" + avgRatingValue + ",方差=" + varianceRatingValue);
			Statement statemenet = conn.createStatement();
			String sqlString = "UPDATE classifymainstatistics SET reviewCount5=" + reviewCount5 + 
					",reviewCount4=" + reviewCount4 +
					",reviewCount3=" + reviewCount3 +
					",reviewCount2=" + reviewCount2 +
					",reviewCount1=" + reviewCount1 +
					",avgRatingValue=" + df.format(avgRatingValue) +
					",varianceRatingValue=" + df.format(varianceRatingValue) +
					" where classifyMain='" + EBookListSpider.areaNameList[i] + "';";
			System.out.println(sqlString);
			statemenet.execute(sqlString);
			statemenet.close();
		}
		
		
	}
	
	
	public static void main(String[] args){
		try {
			StatisticsClassifyMain.initConnection();
			StatisticsClassifyMain.statisticsAverage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

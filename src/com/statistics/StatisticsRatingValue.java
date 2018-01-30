package com.statistics;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.EBookDao;
import com.dao.RatingListDao;
import com.dao.impl.EBookDaoImpl;
import com.dao.impl.RatingListDaoImpl;
import com.entity.EBook;
import com.entity.RatingList;
import com.service.EBookService;
import com.service.RatingListService;

@Repository("statisticsRatingValue")
public class StatisticsRatingValue {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	private EBookDao eBookDao = new EBookDaoImpl();

	public EBookDao getEBookDao() {
		return eBookDao;
	}

	public void seteBookDao(EBookDao eBookDao) {
		this.eBookDao = eBookDao;
	}
	
	private RatingListDao ratingListDao = new RatingListDaoImpl();

	public RatingListDao getRatingListDao() {
		return ratingListDao;
	}

	public void setRatingListDao(RatingListDao ratingListDao) {
		this.ratingListDao = ratingListDao;
	}
	
	/**
	 * 获得用户评分的平均数(0~5)
	 * @param eid
	 * @return 精确到小数点后一位
	 */
	public double statisticsRatingValue(String eid){
		List<RatingList> list = sqlSession.selectList("selectRatingListByEid", eid);
		double sum = 0;
		for(RatingList r : list){
			sum += r.getRatingValue();
		}
		return (double)(Math.round((sum / list.size())*10)/10.0);
	}
	
	/**
	 * 批量修改图书评分与评分人数
	 * @return 成功修改的数量
	 */
	private int updateRatingValueAndReviewCount(){
		int num = 0;
		// 从数据库获取所有图书
		List<EBook> eBookList = sqlSession.selectList("selectAllEBook");
		System.out.println("共读取了" + eBookList.size() + "本书");
		for(int i = 0, j = 0; i < eBookList.size(); i++, j++){
			EBook book = eBookList.get(i);
			List<RatingList> list = sqlSession.selectList("selectRatingListByEid", book.getEid());
			double sum = 0;
			double avg = 0;
			for(RatingList r : list){
				sum += r.getRatingValue();
			}
			if(list.size() >= 0){
				avg = 2.0*(double)(Math.round((sum / list.size())*10)/10.0);
				int result = eBookDao.updateRatingValueAndReviewCount(book.getEid(), avg, list.size());
				if(result > 0)
					num++;
			}
			// 进度指示器
			if(j == 20){
				j = 0;
				System.out.println("统计完成了" + (i+1) + "/" + eBookList.size() + ",完成百分比=" + ((i+1)*1.0/eBookList.size()) );
			}
		}
		return num;
	}
	
	public static void main(String[] args){
		StatisticsRatingValue statisticsRatingValue = new StatisticsRatingValue();
		
//		System.out.println(statisticsRatingValue.statisticsRatingValue("930946"));
		
		int sucessNum = statisticsRatingValue.updateRatingValueAndReviewCount();
		System.out.println("成功修改：" + sucessNum + "条数据");
	}
}

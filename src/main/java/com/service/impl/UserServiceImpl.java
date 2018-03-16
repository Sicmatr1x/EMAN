package com.service.impl;


import com.dao.EBookDao;
import com.dao.MatrixCDao;
import com.dao.RatingListDao;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.service.UserService;

import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao = null;

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	private EBookDao eBookDao = null;

	public EBookDao geteBookDao() {
		return eBookDao;
	}

	public void seteBookDao(EBookDao eBookDao) {
		this.eBookDao = eBookDao;
	}

	@Autowired
	private MatrixCDao matrixCDao = null;

	public MatrixCDao getMatrixCDao() {
		return matrixCDao;
	}

	public void setMatrixCDao(MatrixCDao matrixCDao) {
		this.matrixCDao = matrixCDao;
	}

	@Autowired
	private RatingListDao ratingListDao = null;

	public RatingListDao getRatingListDao() {
		return ratingListDao;
	}

	public void setRatingListDao(RatingListDao ratingListDao) {
		this.ratingListDao = ratingListDao;
	}

	@Override
	public User queryUserByUid(String uid) {
		User user = this.userDao.queryUserByUid(uid);
		return user;
	}

	@Override
	public User queryUserByUname(String uname) {
		User user = this.userDao.queryUserByUname(uname);
		return user;
	}

	@Override
	public void insertUser(User user) {
		this.userDao.insertUser(user);
	}

	@Override
	public List<EBook> userRecommendedList(String uid) {
		// 获取用户喜爱图书列表
		List<RatingList> likeList = this.ratingListDao.selectRatingListByUidAndRatingValue(uid, 4);

		// 定义计算用矩阵
		List<Item> matrix = new ArrayList<>();
		// 将用户喜爱的图书作为矩阵的列
		// 将与用户喜爱的图书同现的图书作为矩阵的行

		// 建立工作矩阵
		for(int i = 0; i < likeList.size(); i++){ // 遍历用户喜爱的图书
			RatingList temp = likeList.get(i);
			// 获取同现图书
			List<MatrixC> itemList = this.matrixCDao.selectMatrixCByEidAOrEidB(temp.getEid(), temp.getEid());
			for(int j = 0; j < itemList.size(); j++){
				MatrixC c = itemList.get(j);
				// 从matrixC的key中选出同现图书的eid
				String sEid = null;
				if(c.getEida().equals(temp.getEid())){
					sEid = c.getEidb();
				}else{
					sEid = c.getEida();
				}
				// 在行中查询同现图书是否存在
				if(matrix.indexOf(sEid) == -1){ // 若列中不存在
					double[] col = new double[likeList.size()];
					// 将同现图书所在行对应喜爱图书的数组值设为对应的余弦相似度*用户喜爱程度(4分为1,5分为2)
					col[likeList.indexOf(temp)] = c.getCos_similarity()*(temp.getRatingValue()-3);
					matrix.add(new Item(sEid, col)); // 增加行

				}else{ // 若列中存在
					// 则将同现图书所在行对应喜爱图书的数组值设为对应的余弦相似度*用户喜爱程度(4分为1,5分为2)
					matrix.get(matrix.indexOf(sEid)).col[likeList.indexOf(temp)] = c.getCos_similarity()*(temp.getRatingValue()-3);
				}
			}
		}

		// 计算预测兴趣度
		for(int i = 0; i < matrix.size(); i++){
			Item item = matrix.get(i);
			double interestValue = 0;
			for(int j =0; j < item.col.length; j++){
				interestValue += item.col[j];
			}
			matrix.get(i).interestValue = interestValue;
		}

		// 根据预测兴趣度进行排序
		Collections.sort(matrix);
		for(Item item : matrix){
			System.out.println(item.eid+",interestValue="+item.interestValue);
		}

		// 返回推荐图书列表
		List<EBook> resultList = new ArrayList<>();
		for(int i = 0; i < matrix.size(); i++){
			EBook eBook = this.eBookDao.queryEBookByEid(matrix.get(i).eid);
			resultList.add(eBook);
			// debug
			System.out.println(matrix.get(i).eid+","+eBook.getEname()+",interestValue="+matrix.get(i).interestValue);
		}

		return resultList;
	}


}

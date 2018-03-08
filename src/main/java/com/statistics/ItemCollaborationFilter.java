package com.statistics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.MatrixCDao;
import com.dao.RatingListDao;
import com.dao.UserDao;
import com.dao.impl.MatrixCDaoImpl;
import com.dao.impl.RatingListDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.MatrixC;
import com.entity.RatingList;
import com.entity.User;

public class ItemCollaborationFilter {
	
	/**
	 * 设置日期格式
	 */
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private UserDao userDao = new UserDaoImpl();

	public UserDao getUserDao() {
		return userDao;
	}
	
	private RatingListDao ratingListDao = new RatingListDaoImpl();

	public RatingListDao getRatingListDao() {
		return ratingListDao;
	}
	
	private MatrixCDao matrixCDao = new MatrixCDaoImpl();
	
	public MatrixCDao getMatrixCDao() {
		return matrixCDao;
	}
	
	

	/**
	 * 共现矩阵C：表示两个物品同时出现的次数
	 * 例如：物品10000464和10000517同时出现1次
	 * key:10000464,10000517(小的编号在左)
	 * value:1
	 */
	private Map<String,Integer> matrixC = new HashMap<>();
	/**
	 * 共现矩阵C：表示两个物品同时出现的次数
	 * 例如：物品10000464和10000517同时出现1次，并且10000464出现2次，10000517出现3次
	 * key:10000464,10000517(小的编号在左)
	 * value:1/Math.sqrt(2*3)约等于0.41
	 * 这个0.41就是物品10000464和10000517的相似度
	 */
	private Map<String,Double> matrixW = new HashMap<>();
	
	/**
	 * 计算共现矩阵C
	 */
	private void computerMatrixC(){
		// 建立用户物品倒排表
		// 若用户对物品评分大于等于4则认为喜欢(出现)
		List<User> allUser = userDao.queryAllUser();
		for(int i = 0; i < allUser.size(); i++){ // 遍历全部用户
			// 获取一个用户的评分列表中>=4的评分记录
			List<RatingList> likeList = ratingListDao.selectRatingListByUidAndRatingValue(allUser.get(i).getUid(), 4);
			if(likeList.size() <= 1){ // 若用户只喜欢一本或不喜欢任何图书
				continue;
			}
			
//			System.out.print("{");
//			for(int j = 0; j < likeList.size(); j++){
//				System.out.print(likeList.get(j).getEid()+","); // debug
//			}
//			System.out.println("}");
			
			for(int j = 0; j < likeList.size(); j++){ // 计算likeList中两两出现的图书并写入同现矩阵C
				for(int k = j+1; k < likeList.size(); k++){
					int a = Integer.valueOf(likeList.get(j).getEid());
					int b = Integer.valueOf(likeList.get(k).getEid());
					// 生成key
					String key = null;
					if(a < b){
						key = a + "," + b;
					}else{
						key = b + "," + a;
					}
//					System.out.println("key:"+key); // debug
					
					// 检查key是否已经存在
					if(this.matrixC.get(key) != null){
						int value = this.matrixC.get(key);
						this.matrixC.put(key, value+1);
//						System.out.println("allUser["+i+"]="+allUser.get(i).getUid()+",likeList["+j+"]="+a+"[k="+k+"]="+b+";"+this.matrixC.containsKey(key)+" key:"+key+",value:"+value+1); // debug
					}else{
						this.matrixC.put(key, 1);
//						System.out.println("allUser["+i+"]="+allUser.get(i).getUid()+",likeList["+j+"]="+a+"[k="+k+"]="+b+";"+this.matrixC.containsKey(key)+" key:"+key+",value:"+1); // debug
					}
				}
			}
			System.out.println("["+df.format(new Date())+"]"+"[已完成"+i+",共"+allUser.size()+"]:用户uid="+allUser.get(i).getUid()+"的记录以计算完成,共"+likeList.size()+"本图书"); // debug
//			try {
//				Thread.sleep(1000); // debug
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
	}
	
	/**
	 * 将共现矩阵C写入MySQL数据库
	 * @throws SQLException
	 */
	private void writeMatrixCToDB() throws SQLException{
		Iterator it = this.matrixC.entrySet().iterator();  
		while (it.hasNext()) { // 遍历同现矩阵C
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String)entry.getKey();  
			Integer value = (Integer)entry.getValue();  
			// System.out.println("key=" + key + " value=" + value);
			String[] eidList = key.split(",");
			// 查询数据库该数据是否已经存在
			MatrixC c = this.matrixCDao.selectMatrixCByEidAAndEidB(eidList[0], eidList[1]);
			if(c != null){ // 若存在则不插入
				this.matrixCDao.updateMatrixCWithCountByEidAAndEidB(eidList[0], eidList[1], value);
				System.out.println("update:[" + eidList[0] + ",[" + eidList[1] + "]:count=" + c.getCount() + "->" + value);
				continue;
			}
			// 写入数据库
			c = new MatrixC();
			c.setCount(value);
			this.matrixCDao.insertMatrixC(c);
			System.out.println("insert:[" + eidList[0] + ",[" + eidList[1] + "]:count=" + c.getCount() + "->" + value);
			//System.out.println(query); // debug
		}
		
	}
	
	/**
	 * 计算余弦相似度矩阵W
	 * 计算方法：
	 * 使用矩阵C的每个value作为分子，key中的两个图书的喜欢人数的积开根号作为分母
	 */
	private double computerMatrixW(String eida, String eidb, int value){
		DecimalFormat df = new DecimalFormat("#.##");
		// 查询每个图书有多少人喜欢
		int likeANum = this.ratingListDao.countRatingListByEidAndUserLike(eida);
		int likeBNum = this.ratingListDao.countRatingListByEidAndUserLike(eidb);
		if(likeANum == 0)
			likeANum = 1;
		if(likeBNum == 0)
			likeBNum = 1;
		// 开始计算
		Double answer = value*1.0/Math.sqrt(likeANum*likeBNum);
		// 精确到小数点后两位
		Double result = Double.parseDouble(df.format(answer));
		// 返回计算结果
		return result;
	}
	
	/**
	 * 将计算出来的余弦相似度矩阵W保存到矩阵C的cos_similarity属性中
	 */
	private void computerAndWriteMatrixW(){
		List<MatrixC> matrixCList = matrixCDao.selectAllMatrixC();
		System.out.println("["+this.df.format(new Date())+"]"+"开始余弦相似度矩阵W的计算,需要计算"+matrixCList.size()+"条");
		for(int i = 0; i < matrixCList.size(); i++){
			MatrixC c = matrixCList.get(i);
			Double cos_similarity = this.computerMatrixW(c.getEida(), c.getEidb(), c.getCount());
			this.matrixCDao.updateMatrixCWithCos_similarity(c.getEida(), c.getEidb(), cos_similarity);
		}
		System.out.println("["+this.df.format(new Date())+"]"+"完成余弦相似度矩阵W的计算,共计算"+matrixCList.size()+"条,写入数据库完毕");
	}
	
	//--------------------------------------------------------------------------------------
	
	/**
	 * 计算同现矩阵C并写入数据库
	 * @throws SQLException
	 */
	public void computerAndWriteMtrixC() throws SQLException{
		System.out.println("["+this.df.format(new Date())+"]"+"开始计算同现矩阵C");
		this.computerMatrixC();
		System.out.println("["+this.df.format(new Date())+"]"+"开始将同现矩阵C写入数据库");
		this.writeMatrixCToDB();
		System.out.println("["+this.df.format(new Date())+"]"+"同现矩阵C写入数据库完毕");
	}
	
	
	public static void main(String[] args){
		PrintStream ps;
		try {
			// 重定向标准输出到文件方便查看
			ps = new PrintStream(new FileOutputStream("output-ItemCollaborationFilter"));
			System.setOut(ps);
			
			ItemCollaborationFilter icf = new ItemCollaborationFilter();
			
			// 计算同现矩阵C
			try {
				icf.computerAndWriteMtrixC();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 计算余弦相似度矩阵W
//			icf.computerAndWriteMatrixW();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	
	
}

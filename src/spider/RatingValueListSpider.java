package spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.DBAccess;
import com.entity.*;

/**
 * 爬取豆瓣图书评分列表与用户
 * @author Sicmatr1x
 *
 */
public class RatingValueListSpider implements Runnable{
	
	private SqlSession sqlSession = DBAccess.getSqlSession();
	
	/**
	 * 页面获取超时时间MS
	 */
	public static int _getHtmlOverTime = 30 * 1000;
	/**
	 * 获取下一个页面间隔时间
	 */
	public static int _getNextPageWaitTime = 5 * 1000;
	/**
	 * 设置文件
	 */
	private static File settingFile = new File("user_spider_setting.properties");
	
	private List<EBook> eBookList = null;
	
	/**
	 * 电子书列表结构化HTML文件
	 */
	private transient Document Doc = null;

	/**
	 * 获取网页并转为 org.jsoup.nodes.Document
	 * 
	 * @param webAddress
	 * @return
	 * @throws IOException
	 */
	private Document getHtmlDoc(String webAddress) throws IOException {
		// System.out.println("getHtmlDoc():" + address);
		return Jsoup
				.connect(webAddress)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
				.timeout(_getHtmlOverTime).get();
	}
	
	/**
	 * 将用户数据写入数据库
	 * @param rList
	 */
	private void writeUserDate(User user){
		User result = sqlSession.selectOne("selectUserByUid", user.getUid());
		if(result == null){
			System.out.println("用户记录写入数据库:" + user.getUid() + "," + user.getUname());
			this.sqlSession.insert("insertUser", user);
			this.sqlSession.commit();
			
		}else{ // 若已存在用户记录
			System.out.println("用户记录已存在" + user.getUid() + "," + user.getUname());
		}
	}

	
	/**
	 * 将评分数据写入数据库
	 * @param rList
	 */
	public void writeRatingListDate(RatingList rList){
		Map<String,Object> map=new HashMap<String, Object>();
        map.put("eid", rList.getEid());
        map.put("uid", rList.getUid());
		RatingList result = sqlSession.selectOne("selectRatingListByEidAndUid", map);
		if(result == null){
			System.out.println("评分记录写入数据库:" + rList.getEid() + "," + rList.getUid());
			this.sqlSession.insert("insertRatingList", rList);
			this.sqlSession.commit();
			
		}else{ // 若已存在评分记录
			System.out.println("评分记录已存在" + rList.getEid() + "," + rList.getUid());
		}
	}

	@Override
	public void run() {
		// 从数据库获取所有图书
		this.eBookList = sqlSession.selectList("selectAllEBook");
		System.out.println("共读取了" + this.eBookList.size() + "本书");
		
		// 读取配置文件
		System.out.println("读取配置文件");
		FileInputStream input;
		List<String> lines = new ArrayList<>();
		try {
			input = new FileInputStream(settingFile);
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			bufferedReader.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 从配置文件里读取参数并设置
		int i = 0;
		double page_k = 1.0;
		if (lines.size() > 0) {
			System.out.println("读取配置文件成功");
		} else {
			i = 0;
			System.out.println("读取配置文件失败");
		}
		for (int k = 0; k < lines.size(); k++) {
			String[] workStrings = lines.get(k).split("=");
			if (workStrings[0].equals("begIndex")) {
				i = Integer.valueOf(workStrings[1]);
			}
			if (workStrings[0].equals("page_k")) {
				page_k = Double.valueOf(workStrings[1]);
			}
			
			System.out.println(workStrings[0] + "=" + workStrings[1]);
		}
		System.out.println("======================================================");
		
		// 开始爬取
		for(; i < this.eBookList.size(); i++){
			String eid = this.eBookList.get(i).getEid();
			// 根据eid生成图书评分列表页地址
			String webAddress = "https://read.douban.com/ebook/" + eid + "/ratings";
			System.out.println("开始爬取" + webAddress);
			try {
				this.Doc = this.getHtmlDoc(webAddress);
				// 获取最大页数
				Elements pageE = this.Doc.select(".pagination").select("li");
				int max = 1;
				if(pageE.isEmpty()){ // 若页数为1(即评分数<=25)
					
				}else{ // 页数大于1
					max = Integer.valueOf(pageE.get(pageE.size() - 2).text());
				}
				
				// 开始遍历评分列表
				// 使用page_k参数来设置获取评分列表的页数
				int getPageMaxSize = 1;
				if(max > 1){
					getPageMaxSize =(int) (max*page_k);
					if(getPageMaxSize <= 0)
						getPageMaxSize = 1;
				}else{
					getPageMaxSize = max;
				}
				System.out.println("评分列表页共" + max + "页,获取倍率为" + page_k + "获取" + getPageMaxSize + "页");
				
				for(int page = 1; page <= getPageMaxSize; page++){
					if(page == 1){ // 若是第一页则不用获取html
						
					}else{
						// 获取网页
						String curWebAddress = webAddress + "?start=" + (page-1)*25;
						this.Doc = this.getHtmlDoc(curWebAddress);
						System.out.println("爬取第" + page + "页/共" + max + "页,当前网址："+ curWebAddress);
					}
					// 解析用户评分与评论rating-comments-list
					Elements ratingCommentsList = this.Doc.select(".rating-comments-list").select("li");
					if(ratingCommentsList.isEmpty()){ // 若评分数为0
						break;
					}else{ // 若评分数大于0
						// 遍历本页评分列表
						for(int j = 0; j < ratingCommentsList.size(); j++){
							User user = new User();
							RatingList rList = new RatingList();
							rList.setEid(eid);
							
//							System.out.println(ratingCommentsList.get(j));
//							System.out.println("-----------");
							// 用户名
							user.setUname(ratingCommentsList.get(j).select(".user").select("a").text());
							// 用户id
							String userHomePage = ratingCommentsList.get(j).select(".user").select("a").attr("href");
							String[] work = userHomePage.split("[/]");
							if(work[2].matches("\\d+")){ // 若用户id为数字
								user.setUid(work[2]);
								rList.setUid(work[2]);
							}else{ // 若用户id为非数字
								continue;
							}
							// 评分
							rList.setRatingValue(Double.valueOf(ratingCommentsList.get(j).select(".impression").select(".rating-stars").attr("title")));
							// 评论
							rList.setRdescribe(ratingCommentsList.get(j).select(".desc").text());
							
//							System.out.println("评论=" + ratingCommentsList.get(j).select(".desc").text());				
//							System.out.println("user=" + ratingCommentsList.get(j).select(".user").select("a").text());
//							System.out.println("评分=" + ratingCommentsList.get(j).select(".impression").select(".rating-stars").attr("title"));
							System.out.println("-----------");
							
							// 写入数据库
							user.setPassword("123456");
							user.showField();
							rList.showField();
							this.writeUserDate(user);
							this.writeRatingListDate(rList);
							
						}
						
						
					}
					
					System.out.println("sleep " + _getNextPageWaitTime / 1000 + "s...");
					Thread.sleep(_getNextPageWaitTime);
				}
				
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 保存当前遍历图书序号
			// 写配置文件
			FileOutputStream output;
			try {
				output = new FileOutputStream(settingFile);
				OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				String arg1 = "begIndex=" + (i+1) + "\n";
				String arg2 = "page_k=" + page_k;
				bufferedWriter.write(arg1);
				bufferedWriter.write(arg2);
				bufferedWriter.close();

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		RatingValueListSpider us = new RatingValueListSpider();
//		RatingList rList = new RatingList(null,"10000464","63703924",3.0,"不如《历代经济变革得失》");
//		us.writeRatingListDate(rList);
		us.run();
		
	}
}

package spider;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.Statement;

import com.dao.DBAccess;
import com.entity.EBook;

/**
 * 爬取电子书详情页并存入数据库
 * @author admin
 *
 */
public class BookInfoSpider implements Runnable{
	/**
	 * 页面获取超时时间MS
	 */
	public static int _getHtmlOverTime = 20 * 1000;
	/**
	 * 获取下一个页面间隔时间
	 */
	public static int _getNextPageWaitTime = 2 * 1000;
	/**
	 * 若获取页面超时则重复获取几次
	 */
	public static int _retryHtmlTimes = 3;
	/**
	 * 若获取页面超时则重复获取几次
	 */
	private int curRetryTimes = 1;
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
	private SqlSession sqlSession = DBAccess.getSqlSession();
	/**
	 * 电子书详情页结构化HTML文件
	 */
	private transient Document Doc = null;
	//----------需要调用者设置的变量------------------------------------------
	/**
	 * 图书详情页
	 */
	private String webAddress;
	/**
	 * 主分类
	 */
	private String classifyMain;
	/**
	 * 副分类
	 */
	private String classifySub;
	/**
	 * 10分好评数
	 */
	private Integer bestRating;
	/**
	 * 0分差评数
	 */
	private Integer worstRating;
	/**
	 * 评分
	 */
	private Double ratingValue;
	/**
	 * 参与评分人数
	 */
	private Integer reviewCount;
	//----------------------------------------------------------------------
	/**
	 * 创建爬虫
	 */
	public BookInfoSpider() {
		super();
	}

	/**
	 * 获取网页并转为 org.jsoup.nodes.Document
	 * @param webAddress
	 * @return
	 * @throws IOException
	 */
	private Document getHtmlDoc(String webAddress) throws IOException {
        //System.out.println("getHtmlDoc():" + address);
        return Jsoup.connect(webAddress).userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0").timeout(_getHtmlOverTime).get();
    }
	
	/**
	 * 获取网页并转为 org.jsoup.nodes.Document
	 * @throws IOException
	 */
	private void initDoc() throws IOException{
		this.Doc = this.getHtmlDoc(this.webAddress);
		this.curRetryTimes = 1;
	}
	
	private String proventSQLInject(String str){
		if(str != null){
			String resultString = str.replace("'", "\\'").replace("`", "\\`").replace("\\", "\\\\").replace(";", "\\;");
			if(resultString.length()>=4096){
				resultString = resultString.substring(0, 4095);
			}
			return resultString;
		}
		return null;
	}

	/**
	 * 开始解析页面
	 */
	public void run() {
		while(this.curRetryTimes <= _retryHtmlTimes){
			EBook book = new EBook();
			try {
				book.setInfoAddress(this.webAddress);
				book.setClassifyMain(this.classifyMain);
				book.setBestRating(this.bestRating);
				book.setWorstRating(this.worstRating);
				book.setRatingValue(this.ratingValue);
				book.setReviewCount(this.reviewCount);
				
				String[] workStrings = this.webAddress.split("[/]");
				book.setEid(workStrings[4]);
				
				// 查询是否已经存在
				Statement statemenet = conn.createStatement();
				ResultSet rs = statemenet.executeQuery("select eid,name from ebook where eid='" + book.getEid() + "';");
				if(rs.next()){ // 若存在则不爬取
					System.out.print("[" + rs.getString("eid")+ rs.getString("name") + "]已经存在:");
					
					// 更新评分与评分人数功能
					String updateRatInfoString = "update ebook set " + 
					"bestRating=" + book.getBestRating() + "," +
					"worstRating=" + book.getWorstRating() + "," +
					"ratingValue=" + book.getRatingValue() + "," +
					"reviewCount=" + book.getReviewCount() +
					" where eid='" + book.getEid() + "';";
					System.out.println(updateRatInfoString);
					statemenet.executeUpdate(updateRatInfoString);
					
					// 更新电子书副分类信息
					ResultSet rsSub = statemenet.executeQuery("select classifySub from subclassify where eid='" + book.getEid() + "';");
					boolean addSubClassify = true;
					while(rsSub.next()){ // 查看副分类是否已经存在
						if(rsSub.getString("classifySub").equals(this.classifySub)){
							addSubClassify = false;
							System.out.println("且其副分类[" + this.classifySub +"]已经存在");
							break;
						}
					}
					if(addSubClassify){
						// 增加副分类记录
						String insertClassifySub = "insert into subclassify (eid,classifySub) values ('" + book.getEid() + "','" +  this.classifySub + "');";
						System.out.println("追加:'" + book.getEid() + "','" +  this.classifySub + "'");
						statemenet.execute(insertClassifySub);
					}
					statemenet.close();
					break;
				}
				
				// 增加副分类记录
				ResultSet rsSub = statemenet.executeQuery("select classifySub from subclassify where eid='" + book.getEid() + "';");
				boolean addSubClassify = true;
				while(rsSub.next()){ // 查看副分类是否已经存在
					if(rsSub.getString("classifySub").equals(this.classifySub)){
						addSubClassify = false;
						System.out.println("且其副分类[" + this.classifySub +"]已经存在");
						break;
					}
				}
				if(addSubClassify){
					// 增加副分类记录
					String insertClassifySub = "insert into subclassify (eid,classifySub) values ('" + book.getEid() + "','" +  this.classifySub + "');";
					System.out.println("新增:'" + book.getEid() + "','" +  this.classifySub + "'");
					statemenet.execute(insertClassifySub);
				}
				
				this.initDoc();
				
//				System.out.println(this.Doc.html());
				Elements articles = this.Doc.select(".article-profile-section");
				if(articles.size() <= 0){
					break;
				}
				Element head = articles.get(0);
				
				

				String imgAddress = head.select("img").attr("src");
				book.setImgAddress(imgAddress);

				String name = head.select("h1").text();
				book.setName(name);
				System.out.println("爬取[" + name + "]" + this.webAddress);
				
//				System.out.println(head.select(".article-meta"));
				Element article_meta = head.select(".article-meta").get(0);
				
				String author = article_meta.select(".author").select(".author-item").text();
				book.setAuthor(author);
				
				String category = article_meta.select(".category").select(".labeled-text").text();
				book.setCategory(category);

				Elements labels = article_meta.select("p");
				for(int i = 0 ;i < labels.size(); i++){
					if(labels.get(i).select(".label").text().equals("出版社")){
						String[] work = labels.get(i).select(".labeled-text").select("span").text().split("[ ]");
//						for(int j = 0; j < work.length; j++){
//							System.out.println(j + "[" + work[j] + "]");
//						}
						String publishingHouse;
						if(work.length >= 2){
							publishingHouse = work[1] + "/" + work[2];
						}else{
							publishingHouse = work[0];
						}
						book.setPublishingHouse(publishingHouse);
//						System.out.println("出版社:" + publishingHouse);
					}else if(labels.get(i).select(".label").text().equals("提供方")){
						String provider = labels.get(i).select(".labeled-text").select("a").text();
						book.setProvider(provider);
//						System.out.println("提供方:" + provider);
					}else if(labels.get(i).select(".label").text().equals("字数")){
						String[] work = labels.get(i).select(".labeled-text").select("span").text().split("[ ]");
						String words = work[1].replace(",", "");
						book.setWords(Integer.valueOf(words));
//						System.out.println("字数:" + words);
					}
					
//					System.out.println(i + ":" + labels.get(i).text());
				}
				
				/*String score = head.select(".score").text();
				if(score != null && !score.isEmpty()){
					book.setScore(Double.valueOf(score));
				}*/
				
				
				Elements body = this.Doc.select("[itemprop=description]");
				Elements descriptions = body.select("p");
				StringBuffer  description = new StringBuffer();
				for(int i = 0; i < descriptions.size(); i++){
					description.append(this.proventSQLInject(descriptions.get(i).text()));
				}
				book.setDescription(description.toString());
				
				Elements isbn = this.Doc.select("[itemprop=isbn]");
				if(isbn.size() > 0){
					String ISBN = isbn.get(0).text();
					book.setISBN(ISBN);
				}
				
				Elements scoreAddElements = head.select(".amount");
				if(scoreAddElements.size() > 0){
					String scoreAddress = scoreAddElements.get(0).select("a").attr("href");
					book.setScoreAddress(scoreAddress);
				}
				
				
//				System.out.println(head.select(".amount").get(0).select("a").attr("href"));
				// 写入数据库
//				book.showField();
				this.sqlSession.insert("insertEBook", book);
				this.sqlSession.commit();
				/*String query = "insert into ebook values (" + 
						"'" + book.getEid() + "'," +
						"'" + book.getISBN() + "'," +
						"'" + this.proventSQLInject(book.getName()) + "'," +
						"'" + this.proventSQLInject(book.getAuthor()) + "'," +
						"'" + this.proventSQLInject(book.getTranslator()) + "'," +
						"'" + this.proventSQLInject(book.getCategory()) + "'," +
						"'" + this.proventSQLInject(book.getPublishingHouse()) + "'," +
						"'" + this.proventSQLInject(book.getProvider()) + "'," +
						book.getWords() + "," +
						"'" + book.getImgAddress() + "'," +
						"'" + book.getInfoAddress() + "'," +
						"'" + book.getScoreAddress() + "'," +
						"'" + this.proventSQLInject(book.getDescription()) + "'," +
						book.getBestRating() + "," +
						book.getWorstRating() + "," +
						book.getRatingValue() + "," +
						book.getReviewCount() + "," +
						"'" + book.getClassifyMain() + 
						"');";
				System.out.println("[" + book.getReviewCount() + "]");
				System.out.println(query);
				statemenet.execute(query);

				statemenet.close();*/
				
				Thread.sleep(_getNextPageWaitTime);
				
			} catch (IOException e) {
				System.out.println("爬取" + this.webAddress + "  ERROR:" + e.getMessage());
				e.printStackTrace();
				System.out.println("尝试重新爬取" + this.webAddress);
			}catch(SQLException e){
				System.out.println("爬取" + this.webAddress + "  ERROR:" + e.getMessage());
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch(java.lang.IndexOutOfBoundsException e){
				System.out.println("爬取" + this.webAddress + "  ERROR:" + e.getMessage());
				e.printStackTrace();
			} catch (org.apache.ibatis.exceptions.PersistenceException e){
				e.printStackTrace();
				// 写入数据库
				System.out.println("尝试去掉description再次插入数据库" + book.getName());
				book.setDescription(null);
				this.sqlSession.insert("insertEBook", book);
				this.sqlSession.commit();
			}
			break;
		}
	}
	
	public static void main(String[] args){
		BookInfoSpider bis = new BookInfoSpider();
		try {
			BookInfoSpider.initConnection();
			bis.setWebAddress("https://read.douban.com/ebook/2015692/");
			bis.setClassifyMain("小说");
			bis.setClassifySub("科幻");
			bis.run();
			BookInfoSpider.closeConnection();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (org.apache.ibatis.exceptions.PersistenceException e){
			e.printStackTrace();
		}
		
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getClassifyMain() {
		return classifyMain;
	}

	public void setClassifyMain(String classifyMain) {
		this.classifyMain = classifyMain;
	}

	public String getClassifySub() {
		return classifySub;
	}

	public void setClassifySub(String classifySub) {
		this.classifySub = classifySub;
	}
	public Integer getBestRating() {
		return bestRating;
	}
	public void setBestRating(Integer bestRating) {
		this.bestRating = bestRating;
	}
	public Integer getWorstRating() {
		return worstRating;
	}
	public void setWorstRating(Integer worstRating) {
		this.worstRating = worstRating;
	}
	public Double getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(Double ratingValue) {
		this.ratingValue = ratingValue;
	}
	public Integer getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}


}

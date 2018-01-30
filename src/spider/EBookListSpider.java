package spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 爬取全部电子书
 * 
 * @author admin
 * 
 */
public class EBookListSpider implements Runnable {
	static final String baseWebAddress = "https://read.douban.com/kind/";
	static final String[][] xiaoshuo = { { "113", "世界名著" }, { "114", "影视原著" },
			{ "115", "推理悬疑" }, { "116", "科幻" }, { "117", "言情" },
			{ "118", "青春" }, { "119", "都市" }, { "120", "幻想" }, { "121", "武侠" },
			{ "122", "历史" }, { "123", "官场" }, { "124", "军事战争" },
			{ "125", "中国古典" }, { "126", "中国现代" }, { "127", "中国当代" },
			{ "128", "外国现当代" }, { "129", "中短篇集" }, { "130", "其他" } };
	static final String[][] wenxue = { { "131", "文学经典" }, { "132", "散文随笔" },
			{ "133", "日记书信" }, { "134", "演讲访谈" }, { "135", "纪实文学" },
			{ "136", "传记回忆" }, { "137", "诗歌及赏析" }, { "138", "戏剧曲艺" },
			{ "139", "寓言童话" }, { "140", "文学史" }, { "141", "文学理论与批评" },
			{ "142", "其他" } };
	static final String[][] renwensheke = { { "143", "经典著作" },
			{ "144", "心理学" }, { "145", "社会学" }, { "146", "人类学" },
			{ "147", "历史" }, { "148", "哲学" }, { "149", "文化" }, { "150", "宗教" },
			{ "151", "政治" }, { "152", "法律" }, { "153", "教育学" },
			{ "154", "新闻传播" }, { "155", "编辑出版" }, { "156", "考古" },
			{ "157", "人文地理" }, { "158", "语言文字" }, { "159", "军事" },
			{ "160", "其他" } };
	static final String[][] jingjiguanli = { { "161", "经典畅销" },
			{ "162", "创新创业" }, { "163", "市场营销" }, { "164", "企业经营" },
			{ "165", "投资理财" }, { "166", "领导力" }, { "167", "财务会计" },
			{ "168", "经济" }, { "169", "金融" }, { "170", "管理" }, { "171", "其他" } };
	static final String[][] kejikepu = { { "172", "科普百科" }, { "173", "数学" },
			{ "174", "物理" }, { "175", "化学" }, { "176", "天文" }, { "177", "生物" },
			{ "178", "医学" }, { "179", "自然地理" }, { "180", "城市建设" },
			{ "181", "工业技术" }, { "182", "农业技术" }, { "183", "其他" } };
	static final String[][] jisuanji = { { "184", "行业趋势" },
			{ "185", "云计算与大数据" }, { "186", "移动互联网" }, { "187", "互联网营销" },
			{ "188", "办公软件指南" }, { "189", "编程语言" }, { "190", "软件开发与应用" },
			{ "191", "硬件开发" }, { "192", "网络安全" }, { "193", "人工智能" },
			{ "194", "其他" } };
	static final String[][] chenggong = { { "195", "成功学" }, { "196", "励志" },
			{ "197", "情商与自我提升" }, { "198", "思维智力" }, { "199", "演讲口才" },
			{ "200", "职场" }, { "201", "人脉与人际关系" }, { "202", "其他" } };
	static final String[][] shenghuo = { { "203", "两性情感" }, { "204", "旅行" },
			{ "205", "美食与厨艺" }, { "206", "时尚美妆" }, { "207", "孕产育儿" },
			{ "208", "养生保健" }, { "209", "医学常识" }, { "210", "家庭医学" },
			{ "211", "体育健身" }, { "212", "星座占卜" }, { "213", "游戏娱乐" },
			{ "214", "宠物园艺" }, { "215", "其他" } };
	static final String[][] shaoer = { { "216", "家庭教育" }, { "217", "亲子阅读" },
			{ "218", "儿童文学" }, { "219", "启蒙读本" }, { "220", "少儿科普" },
			{ "221", "其他" } };
	static final String[][] yishusheji = { { "222", "设计" }, { "223", "摄影" },
			{ "224", "电影" }, { "225", "音乐" }, { "226", "美术" }, { "227", "建筑" },
			{ "228", "其他" } };
	static final String[][] manhua = { { "229", "漫画" }, { "230", "绘本" },
			{ "231", "其他" } };
	static final String[][] kaoshijiaoyu = { { "232", "外语学习" },
			{ "233", "教材教辅" }, { "234", "国外教材" }, { "235", "其他" } };
	static final String[][] zazhi = { { "18", "小说视界" }, { "19", "新闻人物" },
			{ "20", "文艺小赏" }, { "21", "生活休闲" }, { "22", "百科万象" } };
	public static final String[] areaNameList = { "小说", "文学", "人文社科", "经济管理", "科技科普",
			"计算机与互联网", "成功励志", "生活", "少儿", "艺术设计", "漫画绘本", "教育考试", "杂志" };

	/**
	 * 页面获取超时时间MS
	 */
	public static int _getHtmlOverTime = 30 * 1000;
	/**
	 * 获取下一个页面间隔时间
	 */
	public static int _getNextPageWaitTime = 3 * 1000;
	/**
	 * 设置文件
	 */
	private static File settingFile = new File("setting.properties");
	/**
	 * 当前主分类
	 */
	public static String classifyMain;
	/**
	 * 当前副分类
	 */
	public static String classifySub;
	/**
	 * 当前列表页
	 */
	public static int page;
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

	public void run() {
		List<String[][]> list = new ArrayList<>();
		list.add(xiaoshuo);
		list.add(wenxue);// 1
		list.add(renwensheke);
		list.add(jingjiguanli);
		list.add(kejikepu);
		list.add(jisuanji);
		list.add(chenggong);
		list.add(shenghuo);
		list.add(shaoer);
		list.add(yishusheji);
		list.add(manhua);
		list.add(kaoshijiaoyu);
		list.add(zazhi);// 12

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
		int endi = list.size();
		File imgRootFloder = null;
		if (lines.size() > 0) {
			System.out.println("读取配置文件成功");
		} else {
			i = 0;
			System.out.println("读取配置文件失败");
		}
		for (int k = 0; k < lines.size(); k++) {
			String[] workStrings = lines.get(k).split("=");
			if (workStrings[0].equals("classifyMainBegIndex")) {
				i = Integer.valueOf(workStrings[1]);
			}
			if (workStrings[0].equals("classifyMainEndIndex")) {
				endi = Integer.valueOf(workStrings[1]);
			}
			if (workStrings[0].equals("imgRootFloder")) {
				imgRootFloder = new File(workStrings[1]);
			}
			System.out.println(workStrings[0] + "=" + workStrings[1]);
		}
		System.out
				.println("======================================================");

		for (; i <= endi; i++) { // 遍历主分类
			for (int j = 0; j < list.get(i).length; j++) { // 遍历副分类
				String webAddress = baseWebAddress + list.get(i)[j][0];
				classifyMain = areaNameList[i];
				classifySub = list.get(i)[j][1];
				System.out.println("开始爬取:[" + classifyMain + "][" + classifySub
						+ "]");
				BookInfoSpider bookInfoSpider = new BookInfoSpider();

				try {
					this.Doc = this.getHtmlDoc(webAddress);
					System.out.println("开始爬取:" + webAddress);
					// 获取最大页数
					// System.out.println(this.Doc.html());
					Elements pageE = this.Doc.select(".pagination")
							.select("li");
					int max = Integer.valueOf(pageE.get(pageE.size() - 2)
							.text());
					// 开始逐页爬取
					for (page = 1; page <= max; page++) {
						System.out.println("开始爬取:[" + classifyMain + "]["
								+ classifySub + "]:第" + page + "页");
						String curWebAdd = webAddress + "?start=" + (page - 1)
								* 20;
						this.Doc = this.getHtmlDoc(curWebAdd);
						// System.out.println(this.Doc.select(".ebook-list").get(0).select("li"));
						Elements ebookListTable = this.Doc
								.select(".ebook-list");
						if (ebookListTable.size() <= 0) {
							ebookListTable = this.Doc.select(".list-lined");
							if (ebookListTable.size() <= 0) {
								ebookListTable = this.Doc
										.select(".column-list");
							}
						}
						Elements ebookList = ebookListTable.get(0).select("li");

						// 开始对列表中电子书详情进行爬取
						for (int k = 0; k < ebookList.size(); k++) {
							// System.out.println(k+":" +
							// ebookList.get(k).select(".pic").attr("href"));
							// 获取电子书详情页地址
							String bookinfo = "https://read.douban.com"
									+ ebookList.get(k).select(".pic")
											.attr("href");

							// 获取电子书评分相关信息
							String bestRating = null;
							String worstRating = null;
							String ratingValue = null;
							String reviewCount = null;
							try {
								bestRating = ebookList.get(k).select(".rating")
										.select("[itemprop=bestRating]")
										.attr("content");
								worstRating = ebookList.get(k)
										.select(".rating")
										.select("[itemprop=worstRating]")
										.attr("content");
								ratingValue = ebookList.get(k)
										.select(".rating")
										.select("[itemprop=ratingValue]")
										.attr("content");
								reviewCount = ebookList.get(k)
										.select(".rating")
										.select("[itemprop=reviewCount]")
										.attr("content");
							} catch (java.lang.IndexOutOfBoundsException e) {
								System.out.println("ERROR爬取:[" + classifyMain
										+ "][" + classifySub + "]:第" + page
										+ "页" + bookinfo + "的图书评分相关信息时出错");
								e.printStackTrace();
							}
							// System.out.println("bestRating=" + bestRating);
							// System.out.println("worstRating=" + worstRating);
							// System.out.println("ratingValue=" + ratingValue);
							// System.out.println("reviewCount=" + reviewCount);

							// 获取电子书图片
							// TODO:
							// System.out.println(ebookList.get(k));
							String imgUrl = ebookList.get(k).select(".pic").select("img").attr("src");
//							System.out.println(imgUrl);
							// 从图片url中截取图片文件名
							int begIndex = imgUrl.indexOf("public/");
							int endIndex = imgUrl.indexOf("?v=", begIndex);
							String fileName = imgUrl.substring(begIndex + "public/".length(), endIndex);
//							System.out.println(imgRootFloder + "\\" + fileName);
							// 判断文件是否存在不存在则创建文件开始下载
							File imgFile = new File(imgRootFloder + "\\" + fileName);
							if (!imgFile.exists()) {
								try {
									imgFile.createNewFile();
									InputStream inImg = HttpURLConnectionUtil.getInputStreamByGet(imgUrl);
									HttpURLConnectionUtil.saveData(inImg, imgFile);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println(imgFile + "下载完成" + (imgFile.length()/1024) + "kb");
							}else{
								System.out.println(imgFile + "已经存在" + (imgFile.length()/1024) + "kb");
							}
							

							// 使用参数初始化电子书详情页爬虫
							bookInfoSpider.setWebAddress(bookinfo);
							bookInfoSpider.setClassifyMain(classifyMain);
							bookInfoSpider.setClassifySub(classifySub);
							if (bestRating.length() > 0
									&& bestRating.matches("\\d+")) {
								bookInfoSpider.setBestRating(Integer
										.valueOf(bestRating));
							} else {
								System.out.print("bestRating=" + bestRating
										+ "图书：" + bookinfo);
							}
							if (worstRating.length() > 0
									&& worstRating.matches("\\d+")) {
								bookInfoSpider.setWorstRating(Integer
										.valueOf(worstRating));
							} else {
								System.out.print("worstRating=" + worstRating
										+ "图书：" + bookinfo);
							}
							if (ratingValue.length() > 0
									&& ratingValue.matches("\\d[.]\\d*")) {
								bookInfoSpider.setRatingValue(Double
										.valueOf(ratingValue));
							} else {
								System.out.print("ratingValue=" + ratingValue
										+ "图书：" + bookinfo);
							}
							if (reviewCount.length() > 0
									&& reviewCount.matches("^[0-9]+")) {
								if (reviewCount.contains("少于")) { // 少于 10 人评价
									bookInfoSpider.setReviewCount(10);
								} else if (reviewCount.contains("评价")) { // 暂无评价
									bookInfoSpider.setReviewCount(0);
								} else {
									bookInfoSpider.setReviewCount(Integer
											.valueOf(reviewCount));
								}

							} else {
								System.out.println("reviewCount=" + reviewCount
										+ "图书：" + bookinfo);
							}
							bookInfoSpider.run();
							// System.out.println("---------------------------------------");
						}

						System.out
								.println("=========================================================================");
						System.out.println("==========>" + i + "["
								+ classifyMain + "]已完成" + (j + 1) + "/"
								+ list.get(i).length + "[" + classifySub
								+ "]已完成" + page + "/" + max
								+ "页<====================");
						System.out
								.println("=========================================================================");
						System.out.println("sleep " + _getNextPageWaitTime
								/ 1000 + "s...");
						Thread.sleep(_getNextPageWaitTime);
					}
					System.out.println();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (java.lang.IndexOutOfBoundsException e) {
					System.out.println("ERROR爬取:[" + classifyMain + "]["
							+ classifySub + "]:第" + page + "页");
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			BookInfoSpider.initConnection();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EBookListSpider els = new EBookListSpider();
		els.run();
		System.out.println("爬取结束");
	}
}

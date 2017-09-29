/**
 * 你超哥哥哦
 */
var moviesCount = 0 ; //电影数目
var pageSize = 12; //每页显示的记录条数
var curPage = 0; //当前页
var lastPage; //最后页
var direct = 0; //方向
var len=0; //总行数
var page; //总页数
var begin;
var countSize = 1;
$(document).ready(
		function intipage(){
			init();
		}
);
	function init(){
		var keyWords = getQueryString("keyWords");
		$("#searchResult").text("\""+keyWords+"\""+"的搜索结果：");
//		alert(encodeURIComponent(encodeURIComponent(keyWords)) );
		len=0;
		page = 0;
		$.ajax({      
			url:"http://localhost:8080/MovieSystem/movies/queryByContians.htm?keyWords="+keyWords,
			type: "post",
//			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			dataType: "json",
			success:function(data){
				for(var js in data){  
					moviesCount++; 				    
				}
				len=moviesCount % 3 == 0 ? moviesCount / 3 : Math.floor(moviesCount / 3) + 1;
				$(data).each(function(i, movieslist){
					var actorList = data[i].actors.toString().split(" ");
					var writerList = data[i].writer.toString().split(" ");
				var $jsonString = $(
					"<div class=\"listDiv\">"+
						"<div class=\"moviesDiv\" >"+
							"<a href='http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+data[i].mid+"'>"
							+"<img class=\"mainImg\" src=\"http://localhost:8080/MovieSystem/pics/" + data[i].mid + "/cover.jpg\">"
							+"</a>"
						+"</div>"+
						"<div class=\"moviesInfo\">"+
							"<b>名称：</b><span>"+data[i].title+"</span></br>"+
							"<b>导演：</b><span>"+data[i].director+"</span></br>"+
							"<b>演员：</b><span>"+actorList[0]+" "+actorList[1]+"</span></br>"+
							"<b>编剧：</b><span>"+writerList[0]+"</span></br>"+
							"<b>类型：</b><span>"+data[i].mtype+"</span></br>"+
							"<b>片长：</b><span>"+data[i].runtime+"分钟</span></br>"+
							"<b>上映时间：</b><span>"+data[i].releaseDate+"</span>"+
						"</div>"+
					"</div>"
						);
					$("#filmsDiv").append($jsonString);
				
				});
				display();  //换页初始化函数
			},
			error:function(a,b,c){
				console.log(a+"\n"+b);
				alert("服务器内部异常");
			}
		
		});
	}
	
	function display(){
		direct = 0;
		page = moviesCount % pageSize == 0 ? moviesCount / pageSize : Math.floor(moviesCount / pageSize) + 1; //根据记录条数，计算页数
		curPage = 1; // 设置当前为第一页					
		displayPage(); //显示第一页
		$("#btn1").click(function firstPage() { // 首页
			curPage = 1;
			direct = 0;
			displayPage();
		});
		$("#btn2").click(function frontPage() { // 上一页
			direct = -1;
			displayPage();
		});
		$("#btn3").click(function nextPage() { // 下一页
			direct = 1;
			displayPage();
		});
		$("#btn4").click(function lastPage() { // 尾页
			curPage = page;
			direct = 0;
			displayPage();
		});		
	}
	
	function displayPage() {
		if (curPage <= 1 && direct == -1) {
			direct = 0;
			/*alert("已经是第一页了");*/
			return;
		}
		else if (curPage >= page && direct == 1) {
			direct = 0;
			/*alert("已经是最后一页了");*/
			return;
		}

		lastPage = curPage;

		// 修复当len=1时，curPage计算得0的bug
		if (moviesCount > pageSize) {
			curPage = ((curPage + direct + moviesCount) % moviesCount);
		}
		else {
			curPage = 1;
		}

		document.getElementById("btn0").innerHTML = "当前 " + curPage + "/"
			+ page + " 页 "; // 显示当前多少页 

		begin = (curPage - 1) * pageSize; // 起始记录号
		$(".listDiv").hide(); // 首先，设置所有行为隐藏
		countSize = 1;
		$(".listDiv").each(function(i) {
				if ((i >= begin && countSize <= pageSize)) {
					countSize++;
					$(this).show();
				}
		});
	}
	//查询电影
	function SearchMovies(){
		
		var moviesName = $("#searchMovie").val();
//		alert(moviesName);
		window.location.href='/MovieSystem/movies/searchList.htm?keyWords='+moviesName;
	}
	function getQueryString(name) {
		// (^|&)  : 要求以name开头或者以&开头
		// ([^&]*): 匹配除了&外的任意字符，不限个数；括号表示在获取结果时，括号中的表达式匹配到的内容可以被单独得到
		// (&|$)  : 要求以&或者([^&]*)结尾
		var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return decodeURI(r[2]);
		}
		return null;
	}
	
		
var mid = null;
$(document).ready(
	function init() {
		mid = getQueryString("mid");
		//获取电影字段信息
				$.ajax({
					url:"http://localhost:8080/MovieSystem/movies/queryOne.htm?mid="+mid,
					dataType : "json",
					success : function(result) {
						//主图片
						$("#mainImg").attr("src","http://localhost:8080/MovieSystem/pics/" + result.mid + "/cover.jpg");
						//剧照"
						$("#stagePhotos").append($("<img class=\"photos\" src=\"http://localhost:8080/MovieSystem/pics/" + result.mid + "/pic1.jpg\" >"));
						$("#stagePhotos").append($("<img class=\"photos\" src=\"http://localhost:8080/MovieSystem/pics/" + result.mid + "/pic2.jpg\" >"));
						$("#stagePhotos").append($("<img class=\"photos\" src=\"http://localhost:8080/MovieSystem/pics/" + result.mid + "/pic3.jpg\" >"));
						$("#stagePhotos").append($("<img class=\"photos\" src=\"http://localhost:8080/MovieSystem/pics/" + result.mid + "/pic4.jpg\" >"));
						$("#stagePhotos").append($("<img class=\"photos\" src=\"http://localhost:8080/MovieSystem/pics/" + result.mid + "/pic5.jpg\" >"));
						$("#stagePhotos").append($("<img class=\"photos\" src=\"http://localhost:8080/MovieSystem/pics/" + result.mid + "/pic6.jpg\" >"));
						
						$("#movieTitle").text(result.title);
						$("#director").text(result.director);
						$("#actors").text(result.actors);
						$("#scriptwriter").text(result.writer);
						$("#movietype").text(result.mtype);
						$("#filmlenght").text(result.runtime);
						$("#filmcountry").text(result.country);
						$("#releasedate").text(result.releaseDate);
						$("#movierate").text(result.rate);
						//电影简介
						$("#intro").text(result.intro);
					}
				});
		//获取猜你喜欢电影列表
		$.ajax({
			url : "http://localhost:8080/MovieSystem/recommendUser/queryOne.htm",
			type : "Get",
			dataType : "JSON",
			success : function(results) {
				$.each(results, function(i, movieslist) {
					var $jsonString = $(
						"<div class=\"guestDivs\">" 
						//+ "<a href='http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+results[i].mid+"'>"
						+"<img onclick=\"window.location.href='http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+results[i].mid+"'\" src=\"http://localhost:8080/MovieSystem/pics/" + results[i].mid + "/cover.jpg\" "
						//+ "</a>"
						+ "style=\"width: 90%;height:90%;\">"
						+ "<a href=\"http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+results[i].mid+"\">" + results[i].title + "</a>"
						+ "</div>"
					);
					$("#GuestULikeDiv").append($jsonString);
				});
			},
			error : function(a, b, c) {
				alert(a + "," + b + "," + c);
			}
		});
		//。。。。。。。
				$.ajax({
					url : "http://localhost:8080/MovieSystem/recommendMovie/queryOne.htm",
					type : "Get",
					dataType : "JSON",
					success : function(results) {
						$.each(results, function(i, movieslist) {
							var $jsonString = $(
								"<div class=\"guestDivs\">" 
								//+ "<a href='http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+results[i].mid+"'>"
								+"<img onclick=\"window.location.href='http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+results[i].mid+"'\" src=\"http://localhost:8080/MovieSystem/pics/" + results[i].mid + "/cover.jpg\" "
								//+ "</a>"
								+ "style=\"width: 90%;height:90%;\">"
								+ "<a href=\"http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+results[i].mid+"\">" + results[i].title + "</a>"
								+ "</div>"
							);
							$("#GuestULikeDivBasedUser").append($jsonString);
						});
					},
					error : function(a, b, c) {
						alert(a + "," + b + "," + c);
					}
				});
		//获取电影的评论列表
		$.ajax({
			url : "http://localhost:8080/MovieSystem/comments/queryByMid.htm?mid="+mid,
			dataType : "json",
			success : function(results) {
				$.each(results, function(i, movieslist) {
					var $jsonString = $(
							"<p><a href=\"\">"+results[i].uname+"</a>："+results[i].comment+"</p>"
						);
					$("#commentsDiv").append($jsonString);
				});
			},
			error : function(a, b, c) {
				alert(a + "," + b + "," + c);
			}
		});
		
		
		$.ajax({
			url : "http://localhost:8080/MovieSystem/ratings/queryCountByMid.htm?mid="+mid,
			dataType : "json",
			success : function(results) {
				$('#rateCount').text(results.count);
			},
			error : function(a, b, c) {
				alert(a + "," + b + "," + c);
			}
		});
		
		$.ajax({
			url : "http://localhost:8080/MovieSystem/ratings/queryRateDistribute.htm?mid="+mid,
			dataType : "json",
			success : function(results) {
				//alert(results);
				setChartData(results);
				showChart();
			},
			error : function(a, b, c) {
				alert(a + "," + b + "," + c);
			}
		})
		
	}
	
	
	
);
function sendComment(){
	var comments = $("#CommentArea").val();
//	alert(comments);
	$.ajax({
		/*url : "http://192.168.1.127:8080/MovieSystem/comments/insert.htm?mid="+mid+"&comment="+comments,*/
		url : "http://localhost:8080/MovieSystem/comments/insert.htm?mid="+mid+"&uid=1&comment="+comments,
		dataType : "json",
		success : function(results) {
			if(results.result == 1){
				alert("评论成功！");
				window.location.href="http://localhost:8080/MovieSystem/movies/movieDetail.htm?mid="+mid;
			
			}else{
				alert("已经评论过了！");
			}
		},
		error : function(a, b, c) {
			alert(a + "," + b + "," + c);
		}
	});
}
function getQueryString(name) {
	// (^|&)  : 要求以name开头或者以&开头
	// ([^&]*): 匹配除了&外的任意字符，不限个数；括号表示在获取结果时，括号中的表达式匹配到的内容可以被单独得到
	// (&|$)  : 要求以&或者([^&]*)结尾
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}

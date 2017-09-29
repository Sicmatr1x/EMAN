

//recent film
Vue.component('recent-film-pic', {
  template: '\
  <div class="recentFilm item col-md-3" >\
				<a v-bind:href="url"><img v-bind:src="pic" alt="RecPic"></a>\
			 <a v-bind:href="url"><span class="film-title">{{ title }}</span></a>\
	    <p><span class="direactor">导演: {{director}}</span></p>\
		</div>\
  ',
  props:['title','director','pic','url']
})

var recentFilm=new Vue({
	el: '#film',
	data: {
        FilmObject:{
            mid:'',
            title:'',
            rate :'',
            title:'',
            director:'',
						writer:'',
						actors:'',
						country:'',
						mtype:'',
						runtime:'',
						releaseDate:'',
						intro:'',
						pic:'',
						url:''
        },
        FilmObjectList:[]
        
	}
})



axios.get('./movies/queryLast.htm').then(function (res){
    recentFilm.FilmObjectList=res.data;
		console.log( recentFilm.FilmObjectList);
		
		for(var i=0;i<recentFilm.FilmObjectList.length;i++){
			recentFilm.FilmObjectList[i].pic="./pics/"+recentFilm.FilmObjectList[i].mid+"/cover.jpg";
			recentFilm.FilmObjectList[i].url="./movies/movieDetail.htm?mid="+recentFilm.FilmObjectList[i].mid;
			console.log( recentFilm.FilmObjectList[i].pic);
			console.log( recentFilm.FilmObjectList[i].url);
			console.log("---------");
		}
		
		
})
var topFilm=new Vue({
	el:'#film-top-8',
	data: {
        FilmObject:{
            mid:'',
            title:'',
            rate :'',
            title:'',
            director:'',
						writer:'',
						actors:'',
						country:'',
						mtype:'',
						runtime:'',
						releaseDate:'',
						intro:'',
						pic:'',
						url:''
        },
        FilmObjectList2:[]
        
	}
})
axios.get('./movies/queryTop8.htm').then(function  (res){
	topFilm.FilmObjectList2=res.data;
		console.log( topFilm.FilmObjectList2);
		
		for(var i=0;i<topFilm.FilmObjectList2.length;i++){
			topFilm.FilmObjectList2[i].pic="./pics/"+topFilm.FilmObjectList2[i].mid+"/cover.jpg";
			topFilm.FilmObjectList2[i].url="./movies/movieDetail.htm?mid="+topFilm.FilmObjectList2[i].mid;
			console.log( topFilm.FilmObjectList2[i].pic);
			console.log( topFilm.FilmObjectList2[i].url);
			console.log("---------");
		}
		
		
})

//search-bar
var postSeartchText=new Vue({
    el: '#search-bar',
    data:{
        msg:''
    },
    methods:{
        submit:function(event){
        	
//        	alert(this.msg);
        	if(this.msg !== ''){
        		window.location.href="./movies/searchList.htm?keyWords="+this.msg;
        		
        	}
        	
//            axios.post('',{
//                SearchContent:this.msg
//            }).then(function(response){
//                console.log(response);
//            })
//            .catch(function(error){
//                console.log(error);
//            });
//            console.log(this.msg);
        }
    }
    
})

//login-table
var postLoinTbale=new Vue({
    el:'#loginModal',
    data:{
        userName:'',
        password:''
    },
    methods:{
        login:function(event){
        	$.ajax({
				url:"http://localhost:8080/MovieSystem/login.htm",
				type: "post",
				dataType:"json",
				data:{
						'uname':this.userName,
						'password':this.password,
					},
				success:function(result){
					var loginFlag = result.result;
					if(loginFlag == 1){
						$('#login-success').show()
						setTimeout(function() {
							window.location.href='http://localhost:8080/MovieSystem/index.jsp';
						}, 700)
					}else{
						$('#login-fail').show();
						setTimeout(function() {
							$('#login-fail').hide();
						}, 700)
					}
					
				},
				error:function(a,b,c){
					console.log(a+"\n"+b);
					alert("服务器内部异常！");
				}
				
			});
        	
        	
//            axios.post('login.htm',{
//                       uname:this.userName,
//                       password:this.password
//                    }).then(function(response){
//                    	console.log(response);
////                    	if(response.data.result== 1 ){
////    						alert("登录成功！");
////    						window.location.href="/index.jsp";
////    					}else{
////    						alert("登录失败！");
////    					}
////                        console.log(response);
////                        
//                    })
//                    .catch(function(error){
//                        console.log(error);
//                    });
                    console.log(this.userName);
                    console.log(this.password);
                
            }
        }
})
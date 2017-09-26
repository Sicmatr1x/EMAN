//login-table
var postLoinTbale=new Vue({
    el:'#loginModal1',
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
                    console.log(this.userName);
                    console.log(this.password);
                
            }
        }
})
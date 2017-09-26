new Vue({
    el:'#register-table',
    data:{
        userName:'',
        password:'',
        cpassword:''
    },
    methods:{
        register:function(event){
            if(this.password===this.cpassword){
            	
            	$.ajax({
    				url:"./user/insert.htm",
    				type: "post",
    				dataType:"json",
    				data:{
    						'uname':this.userName,
    						'password':this.password,
    					},
    				success:function(result){
    					var flag = result.result;
    					console.log(flag);
    					if(flag == 1){
//    						alert('success!');
    						$('#login-success').show()
    						setInterval("$('#login-success').hide()",2000);
    					}else{
//    						alert('failed!');
    						$('#login-fail').show()
    						setInterval("$('#login-fail').hide()",2000);
    						
    					}
    					
    				},
    				error:function(a,b,c){
    					console.log(a+"\n"+b);
    					alert("服务器内部异常！");
    				}
    				
    			});
//                        axios.post('',{
//                       uname:this.userName,
//                       password:this.password
//                    }).then(function(response){
//                    	console.log(response.data)
//                        console.log(response);
//                    })
//                    .catch(function(error){
//                        console.log(error);
//                    });
                    console.log(this.userName);
                    console.log(this.password);
                }else{
                    console.log("the password isn't commited")
                }            
        }
    }
})
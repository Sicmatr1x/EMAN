package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.EBook;
import com.entity.MatrixC;
import com.util.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 用户相关接口
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService = null;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转选择用户喜爱分类页面
	 * http://localhost:8080/EMAN/user/home.htm
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/home.htm")
	public String home(HttpServletRequest request, HttpSession session){

		return "home";
	}

	/**
	 * 跳转登录页面
	 * http://localhost:8080/EMAN/user/login.htm
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.htm")
	public String login(HttpServletRequest request, HttpSession session){

		return "login";
	}
	
	/**
	 * 登出接口
	 * @param request
	 * @param session
	 */
	@RequestMapping("/logout.htm")
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		session.setAttribute("uid", null);
		session.setAttribute("uname", null);
		session.setAttribute("password", null);
		session.setAttribute("state", "logout");
		try {
			response.sendRedirect("http://localhost:8080/EMAN/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录表单提交接口
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping("/loginByUname.htm")
	public void loginByUname(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String uname = (String)request.getParameter("uname");
		String password = (String)request.getParameter("password");
		session.setAttribute("uname", uname);
		session.setAttribute("password", password);
		
		// 从数据库查询用户并校验密码
		User user = this.userService.queryUserByUname(uname);
		if(user.getPassword().equals(password)){ // 密码正确
			session.setAttribute("uid", user.getUid());
			session.setAttribute("state", "login");
			try {
				response.sendRedirect("http://localhost:8080/EMAN/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{ // 密码错误
			try {
				response.sendRedirect("http://localhost:8080/EMAN/user/login.htm");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("用户登录uid=" + user.getUid() +  ",uname=" + uname + ",password=" + password);
		
	}
	
	/**
	 * 跳转注册页面
	 * http://localhost:8080/EMAN/user/register.htm
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/register.htm")
	public String register(HttpServletRequest request, HttpSession session){

		return "register";
	}
	
	/**
	 * 用户注册接口
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping("/registerByUname.htm")
	public void registerByUname(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//
		User user = new User();
		String uname = (String)request.getAttribute("uname");
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		String sex = (String)request.getAttribute("sex");
		Integer age = Integer.valueOf((String)request.getAttribute("age"));
		// 生成随机UID
		int uid;
		User t = null;
		do{
			uid = this.getRandomUid();
			t = null;
			t = this.userService.queryUserByUid(uid + "");
		}while(t != null);
        
		
		// 检查字段数据合法性
		//TODO
		user.setUid("" + uid);
		user.setUname(uname);
		user.setEmail(email);
		user.setPassword(password);
		user.setSex(sex);
		user.setAge(age);
		this.userService.insertUser(user);
		
		try {
			response.sendRedirect("http://localhost:8080/EMAN/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据物品的相似度和用户的历史行为给用户生成推荐列表接口
	 * http://localhost:8080/EMAN/user/userRecommendedList.htm?uid=131952373
	 * @param uid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/userRecommendedList.htm")
	@ResponseBody
	public void userRecommendedList(@RequestParam(value="uid")String uid, PrintWriter out, HttpServletRequest request){
		List<EBook> resultList = this.userService.userRecommendedList(uid);

		String json = "{}";
		json = JSONConverter.convertToJSONString(resultList);
		out.print(json);
		out.flush();
	}
	
	/**
	 * 生成随机UID[1000001,9999998]
	 * @return
	 */
	private int getRandomUid(){
		int min=1000001;
		int max=9999998;
		Random random = new Random(System.currentTimeMillis());
        int uid = random.nextInt(max)%(max-min+1) + min;
        return uid;
	}
}

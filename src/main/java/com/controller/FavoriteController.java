package com.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Favorite;
import com.service.FavoriteService;
import com.util.JSONConverter;

/*
 * 用户喜爱分类接口
 */
@Controller
@RequestMapping("/favorite")
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService = null;

	public FavoriteService getFavoriteService() {
		return favoriteService;
	}

	public void setFavoriteService(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}
	
	private final String[] areaNameList = { "小说", "文学", "人文社科", "经济管理", "科技科普",
			"计算机与互联网", "成功励志", "生活", "少儿", "艺术设计", "漫画绘本", "教育考试", "杂志" };
	
	@RequestMapping("/test.htm")
	public String test(
			@RequestParam(value="uid", required=false)String uid,
			@RequestParam(value="classifyMain", required=false)String classifyMain[],
			@RequestParam(value="classifySub", required=false)String classifySub[],
			PrintWriter out,
			HttpServletRequest request){
		return "success";
	}

	
	/**
	 * 新增用户喜爱分类数据
	 * http://localhost:8080/EMAN/favorite/add.htm?uid=131952373&classifyMain=小说
	 * http://localhost:8080/EMAN/favorite/add.htm?uid=131952373&classifySub=世界名著
	 * @param uid
	 * @param classifyMain
	 * @param classifySub
	 * @param request
	 */
	@RequestMapping("/add.htm")
	public String addFavorite(
			@RequestParam(value="uid")String uid,
			@RequestParam(value="classifyMain", required=false)String classifyMain[],
			@RequestParam(value="classifySub", required=false)String classifySub[],
			HttpServletRequest request, HttpServletResponse response){
		
		if(uid == null)
			return "login";
		
		System.out.println("uid="+uid);
		if(classifyMain != null){
			for(int i = 0; i < classifyMain.length; i++){
				int index = Integer.valueOf(classifyMain[i]);
				System.out.println("classifyMain["+i+"]="+index+"->"+this.areaNameList[index]);
				Favorite favorite = new Favorite();
				favorite.setUid(uid);
				favorite.setClassifyMain(this.areaNameList[index]);
				favoriteService.insertFavorite(favorite);
			}
		}
		if(classifySub != null){
			for(int i = 0; i < classifySub.length; i++){
				System.out.println("classifySub["+i+"]="+classifySub[i]);
				Favorite favorite = new Favorite();
				favorite.setUid(uid);
				favorite.setClassifySub(classifySub[i]);
				favoriteService.insertFavorite(favorite);
			}
		}
		
		response.reset();
		return "success";
	}

	/**
	 * 新增用户喜爱分类数据
	 * http://localhost:8080/EMAN/favorite/edit.htm?uid=131952373&classifyMain=小说
	 * http://localhost:8080/EMAN/favorite/edit.htm?uid=131952373&classifySub=世界名著
	 * @param uid
	 * @param classifyMain
	 * @param classifySub
	 * @param request
	 */
	@RequestMapping("/edit.htm")
	public String editFavorite(
			@RequestParam(value="uid")String uid,
			@RequestParam(value="classifyMain", required=false)String classifyMain[],
			@RequestParam(value="classifySub", required=false)String classifySub[],
			HttpServletRequest request, HttpServletResponse response){

		if(uid == null)
			return "login";

		System.out.println("uid="+uid);

		// 删除全部记录
		this.favoriteService.deleteAllFavoriteByUid(uid);

		// 添加新纪录
		if(classifyMain != null){
			for(int i = 0; i < classifyMain.length; i++){
				int index = Integer.valueOf(classifyMain[i]);
				System.out.println("classifyMain["+i+"]="+index+"->"+this.areaNameList[index]);
				Favorite favorite = new Favorite();
				favorite.setUid(uid);
				favorite.setClassifyMain(this.areaNameList[index]);
				favoriteService.insertFavorite(favorite);
			}
		}
		if(classifySub != null){
			for(int i = 0; i < classifySub.length; i++){
				System.out.println("classifySub["+i+"]="+classifySub[i]);
				Favorite favorite = new Favorite();
				favorite.setUid(uid);
				favorite.setClassifySub(classifySub[i]);
				favoriteService.insertFavorite(favorite);
			}
		}

		return "home";

//		response.reset();
//		return "success";
	}
	
	/**
	 * 根据用户uid查询用户喜爱分类
	 * http://localhost:8080/EMAN/favorite/query.htm?uid=131952373
	 * @param uid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/query.htm")
	@ResponseBody
	public void queryFavoriteByUid(
			@RequestParam(value="uid")String uid,
			PrintWriter out,
			HttpServletRequest request){
		List<Favorite> favoriteList= favoriteService.selectFavoriteByUid(uid);
		for(Favorite f : favoriteList){ // 将ClassifyMain名称转换为对应编号
			for(int i = 0; i < areaNameList.length; i++){
				if(areaNameList[i].equals(f.getClassifyMain())){
					f.setClassifyMain(i+"");
					continue;
				}
			}

		}
		String json;
		json = JSONConverter.convertToJSONString(favoriteList);
		out.print(json);
		out.flush();
	}
	
	/**
	 * 根据用户uid查询用户喜爱分类
	 * http://localhost:8080/EMAN/favorite/delete.htm?uid=131952373&classifyMain=小说
	 * http://localhost:8080/EMAN/favorite/delete.htm?uid=131952373&classifySub=世界名著
	 * @param uid
	 * @param out
	 * @param request
	 */
	@RequestMapping("/delete.htm")
	@ResponseBody
	public void delete(
			@RequestParam(value="uid")String uid,
			@RequestParam(value="classifyMain", required=false)String classifyMain,
			@RequestParam(value="classifySub", required=false)String classifySub,
			PrintWriter out,
			HttpServletRequest request){
		Favorite favorite = new Favorite();
		favorite.setUid(uid);
		favorite.setClassifyMain(classifyMain);
		favorite.setClassifySub(classifySub);
		int result = favoriteService.deleteFavoriteByUidAndclassifyMainAndclassifySub(favorite);
		String json;
		json = JSONConverter.convertToJSONString(result);
		out.print(json);
		out.flush();
	}
	
	
	
}

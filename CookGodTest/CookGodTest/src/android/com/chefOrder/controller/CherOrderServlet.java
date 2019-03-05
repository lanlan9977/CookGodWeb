package android.com.chefOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favFdSup.model.FavFdSupService;
import com.favFdSup.model.FavFdSupVO;
import com.foodMall.model.FoodMallService;
import com.foodMall.model.FoodMallVO;
import com.foodSup.model.FoodSupService;
import com.foodSup.model.FoodSupVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class CherOrderServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> stringList;
	List<FavFdSupVO> favSuoList;
	List<FoodMallVO> foodMallList;
	List<FoodSupVO> foodSupList;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String chef_ID = jsonObject.get("selectFoodMall").getAsString();
		favSuoList=new ArrayList<>();
		stringList=new ArrayList<>();
		foodMallList=new ArrayList<>();
		foodSupList=new ArrayList<>();

		//最愛的食材供應商
		FavFdSupService favFdSupService=new FavFdSupService();
		favSuoList=favFdSupService.getAllByChefID(chef_ID);
		String favSuoListJsonIn=gson.toJson(favSuoList);
		stringList.add(favSuoListJsonIn);
		
		//食材商城
		FoodMallService foodMallService=new FoodMallService();
		foodMallList=foodMallService.getAll();
		String foodMallJsonIn=gson.toJson(foodMallList);
		stringList.add(foodMallJsonIn);
		
		//食材供應商
		FoodSupService foodSupService=new FoodSupService();
		for(int i=0;i<foodMallList.size();i++) {
			FoodSupVO foodSupVO=foodSupService.getOneFoodSup(foodMallList.get(i).getFood_sup_ID());
			foodSupList.add(foodSupVO);
		}
		String foodSupJsonIn=gson.toJson(foodSupList);
		stringList.add(foodSupJsonIn);
		
		
		
		String outStr="";
		outStr=gson.toJson(stringList);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		System.out.println();
	}

}

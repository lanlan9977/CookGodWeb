package android.com.chefOdDetail.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chefOdDetail.model.ChefOdDetailService;
import com.chefOdDetail.model.ChefOdDetailVO;
import com.chefOrder.model.ChefOrderDAO;
import com.chefOrder.model.ChefOrderDAO_interface;
import com.chefOrder.model.ChefOrderService;
import com.chefOrder.model.ChefOrderVO;
import com.cust.model.CustService;
import com.foodSup.model.FoodSupVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import android.com.chefOrder.controller.CherOrderServlet;

public class ChefOdDetailByChefServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> stringList;
	List<ChefOrderVO> chefOrderList;
	List<ChefOdDetailVO> chefOdDetailList;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		chefOdDetailList = new ArrayList<>();
		stringList = new ArrayList<>();
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
		String chef_ID = jsonObject.get("chef_ID").getAsString();

		ChefOrderService chefOrderService = new ChefOrderService();
		chefOrderList = chefOrderService.getOneChefOrder_ChefID(chef_ID);
		if (!chefOrderList.isEmpty()) {
			System.out.println(""+chefOrderList.size());
			String chefOrderJsonIn = gson.toJson(chefOrderList);
			stringList.add(chefOrderJsonIn);
		}

		ChefOdDetailService chefOdDetailService = new ChefOdDetailService();
		for (int i = 0; i < chefOrderList.size(); i++) {
			chefOdDetailList.add(chefOdDetailService.getOneChefOdDetail(chefOrderList.get(i).getChef_or_ID()));
		}
		String chefOdDetailJsonIn = gson.toJson(chefOdDetailList);
		stringList.add(chefOdDetailJsonIn);

		String outStr = "";
		outStr = gson.toJson(stringList);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		System.out.println();

	}

}

package android.com.chefOdDetail.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.cust.model.CustVO;
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
	Map<String, List<ChefOdDetailVO>> chefOdDetailMap;
//	Map<ChefOdDetailVO,List<>>

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		chefOdDetailList = new ArrayList<>();
		stringList = new ArrayList<>();
		chefOdDetailMap = new LinkedHashMap<>();
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
			System.out.println("" + chefOrderList.size());
			String chefOrderJsonIn = gson.toJson(chefOrderList);
			stringList.add(chefOrderJsonIn);
		}

		ChefOdDetailService chefOdDetailService = new ChefOdDetailService();
		chefOdDetailList = chefOdDetailService.gelAllChefOdDetail();
		int count = chefOdDetailService.gelAllChefOdDetail().size();
		System.out.println("count: " + count);
		int count1=0;
		for (int i = 0; i < chefOrderList.size(); i++) {
			for (int j = 0; j < count; j++) {
				List<ChefOdDetailVO> list = null;
				list = new ArrayList<>();
				if (chefOdDetailList.get(j).getChef_or_ID().equals(chefOrderList.get(i).getChef_or_ID())) {
					list.add(chefOdDetailList.get(j));

					
				}
				if (!list.isEmpty()) {
					chefOdDetailMap.put(chefOrderList.get(i).getChef_or_ID(), list);
				}
			}

		}

		System.out.println("chefOdDetailMap.size(): " + chefOdDetailMap.size());

		String chefOdDetailJsonIn = gson.toJson(chefOdDetailMap);
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

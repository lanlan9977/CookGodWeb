package android.com.menuOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.festOrder.model.FestOrderService;
import com.festOrder.model.FestOrderVO;
import com.foodOrder.model.FoodOrderDAO;
import com.foodOrder.model.FoodOrderService;
import com.foodOrder.model.FoodOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;

public class MenuOrderServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<MenuOrderVO> menuOrderList;
	private List<FestOrderVO> festOrderList;
	private List<FoodOrderVO> foodOrderList;
	
	private List<String> Orderlist = new ArrayList<>();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		String cust_ID = jsonObject.get("selectOrder").getAsString();

		MenuOrderService menuOrderService = new MenuOrderService();
		menuOrderList = menuOrderService.getCustMenuOrder(cust_ID);
		String menuOrderJsonIn = gson.toJson(menuOrderList);
		if (!menuOrderList.isEmpty()) {
			Orderlist.add(menuOrderJsonIn);
		}else {
			Orderlist.add("");
		}
		
		FestOrderService festOrderService = new FestOrderService();
		festOrderList = festOrderService.getCustFestOrder(cust_ID);
		String festOrderJsonIn = gson.toJson(festOrderList);
		if (!festOrderList.isEmpty()) {
			Orderlist.add(festOrderJsonIn);
		}else {
			Orderlist.add("XXXXXXXXXXXXXXX");
		}

		FoodOrderService foodOrderService = new FoodOrderService();
		foodOrderList = foodOrderService.getCustFoodOrder(cust_ID);
		String foodOrderJsonIn = gson.toJson(foodOrderList);
		if (!foodOrderList.isEmpty()) {
			Orderlist.add(foodOrderJsonIn);
		}else {
			Orderlist.add("");
		}
	

		String outStr = "";

		outStr = gson.toJson(Orderlist);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		System.out.println("output: " + outStr);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}
}

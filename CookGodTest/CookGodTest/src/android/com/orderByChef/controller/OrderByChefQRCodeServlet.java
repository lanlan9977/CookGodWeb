package android.com.orderByChef.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.festOrder.model.FestOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;

public class OrderByChefQRCodeServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

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
		String outStr = "";
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);

		String identity = jsonObject.get("action").getAsString();
		String menu_od_ID = jsonObject.get("menu_od_ID").getAsString();
		String identity_ID = jsonObject.get("identity_ID").getAsString();
		MenuOrderService menuOrderService = new MenuOrderService();
		if (identity.equals("isChef")) {
			if (menuOrderService.getOneMenuOrder(menu_od_ID).getChef_ID().equals(identity_ID)) {
				if (menuOrderService.getOneMenuOrder(menu_od_ID).equals("g1")) {
					menuOrderService.updateMenuOrderStatus(menu_od_ID, "g3");
					outStr = "主廚到府確認成功!";
				} else {
					outStr = "訂單狀態有誤，請確認!";
				}

			} else {
				outStr = "非該訂單之主廚，請重新確認。";
			}

		} else if (identity.equals("isCust")) {

			if (menuOrderService.getOneMenuOrder(menu_od_ID).getCust_ID().equals(identity_ID)) {
				if (menuOrderService.getOneMenuOrder(menu_od_ID).equals("g3")) {
					menuOrderService.updateMenuOrderStatus(menu_od_ID, "g4");
					outStr = "訂單已完成!";
				} else {
					outStr = "訂單狀態有誤，請確認!";
				}
			} else {
				outStr = "非該訂單之顧客，請重新確認。";
			}
		}

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

}
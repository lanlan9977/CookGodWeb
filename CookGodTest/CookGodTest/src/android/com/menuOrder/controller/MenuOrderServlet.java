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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;

public class MenuOrderServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<MenuOrderVO> list;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();

		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}

		System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String cust_ID = jsonObject.get("selectMenuOrder").getAsString();

		MenuOrderService menuOrderService = new MenuOrderService();
		list = menuOrderService.getCustMenuOrder(cust_ID);

		String outStr = "";

		if (!list.isEmpty()) {
			outStr = gson.toJson(list);

		}
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

package android.com.menuOrder.controller;

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

import com.broadcast.model.BroadcastService;
import com.festOrder.model.FestOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;

public class MenuOrderServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<MenuOrderVO> menuOrderList;
	private List<FestOrderVO> festOrderList;

	private List<String> Orderlist;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Orderlist = new ArrayList<>();
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
		String menu_od_ID = jsonObject.get("menu_od_ID").getAsString();
		String menu_od_status = jsonObject.get("menu_od_status").getAsString();

		MenuOrderService menuOrderService = new MenuOrderService();
		menuOrderService.updateMenuOrderStatus(menu_od_ID, menu_od_status);

		BroadcastService broadcastService = new BroadcastService();
		StringBuilder broadcast_con_sb = new StringBuilder();
		broadcast_con_sb.append("訂單推播通知；您在")
				.append((menuOrderService.getOneMenuOrder(menu_od_ID).getMenu_od_start()).toString())
				.append("所訂購的嚴選套餐訂單");

		if ("g1".equals(menu_od_status)) {
			broadcast_con_sb.append("已通過審核");

		} else if ("g2".equals(menu_od_status)) {
			broadcast_con_sb.append("未通過審核");
		}

		broadcastService.addBroadcast(broadcast_con_sb.toString(),
				menuOrderService.getOneMenuOrder(menu_od_ID).getCust_ID());

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

}

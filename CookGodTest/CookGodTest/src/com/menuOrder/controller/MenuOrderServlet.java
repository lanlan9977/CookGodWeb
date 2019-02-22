package com.menuOrder.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menuOrder.model.*;
import com.broadcast.model.*;

public class MenuOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		StringBuilder broadcast_con_sb = new StringBuilder();

		String status = req.getParameter("MenuOrderStatus");

		String menuOrderID = req.getParameter("MenuOrderID");
		MenuOrderService menuOrderService = new MenuOrderService();
		MenuOrderVO menuOrderVO_DB = menuOrderService.getOneMenuOrder(menuOrderID);

		Timestamp menu_or_start1 = (menuOrderVO_DB.getMenu_od_start());
		String menu_or_start=menu_or_start1.toString();
		broadcast_con_sb.append("訂單推播通知；您在").append(menu_or_start).append("所訂購的嚴選套餐訂單");

		if ("g1".equals(status)) {
			broadcast_con_sb.append("已通過審核");
			menuOrderService.updateMenuOrder(menuOrderVO_DB.getMenu_ID(), "g1", menuOrderVO_DB.getMenu_od_book(),
					menuOrderVO_DB.getMenu_od_end(), menuOrderVO_DB.getMenu_od_rate(), menuOrderVO_DB.getMenu_od_msg(),
					menuOrderVO_DB.getChef_ID(), menuOrderVO_DB.getMenu_ID());

		} else if ("g2".equals(status)) {
			broadcast_con_sb.append("未通過審核");
			menuOrderService.updateMenuOrder(menuOrderVO_DB.getMenu_ID(), "g2", menuOrderVO_DB.getMenu_od_book(),
					menuOrderVO_DB.getMenu_od_end(), menuOrderVO_DB.getMenu_od_rate(), menuOrderVO_DB.getMenu_od_msg(),
					menuOrderVO_DB.getChef_ID(), menuOrderVO_DB.getMenu_ID());
		}
		BroadcastService broadcastService = new BroadcastService();
		broadcastService.addBroadcast(broadcast_con_sb.toString(), menuOrderVO_DB.getCust_ID());
		String url = "/back-end/menuOrder/updateMenuOrder.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);

	}

}

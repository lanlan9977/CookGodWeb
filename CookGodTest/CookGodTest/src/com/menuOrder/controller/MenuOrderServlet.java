package com.menuOrder.controller;

import java.io.IOException;

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
		HttpSession session = req.getSession();

		String action = req.getParameter("action");

		StringBuilder broadcast_con_sb = new StringBuilder();

		String status = req.getParameter("MenuOrderStatus");

		String menuOrderID = req.getParameter("MenuOrder");
		MenuOrderService menuOrderService = new MenuOrderService();
		MenuOrderVO menuOrderVO_DB = menuOrderService.getOneMenuOrder(menuOrderID);

		String menu_or_start = (menuOrderVO_DB.getMenu_od_start()).toString();
		broadcast_con_sb.append("訂單推播通知；您在").append(menu_or_start).append("所訂購的嚴選套餐訂單");

		if ("M1".equals(status)) {
			broadcast_con_sb.append("已通過審核");
//			menuOrderVO_DB.setMenu_od_status("M1");
//			menuOrderService.updateMenuOrder(menu_od_ID, "M1", menu_od_book, menu_od_end, menu_od_rate, menu_od_msg, chef_ID, menu_ID)

		} else {
			broadcast_con_sb.append("未通過審核");
//			menuOrderVO_DB.setMenu_od_status("M2");
//			menuOrderService.updateMenuOrder(menu_od_ID, "M0", menu_od_book, menu_od_end, menu_od_rate, menu_od_msg, chef_ID, menu_ID)

		}

		BroadcastService broadcastService = new BroadcastService();
//		broadcastService.addBroadcast(broadcast_start, broadcast_con_sb, broadcast_status, menuOrderVO_DB.getCust_ID());

		String url = "/updateMenuOrder.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);

	}

}

package com.broadcast.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.broadcast.model.BroadcastService;
import com.broadcast.model.BroadcastVO;

public class BroadcastServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		List<BroadcastVO> broadcastConList = (ArrayList<BroadcastVO>) session.getAttribute("broadcastConList");
		int unReadCount = (int) session.getAttribute("unReadCount");// JSP未讀推播訊息上的次數

		
		
		if (!broadcastConList.isEmpty()) {// 當從資料庫推播訊息list不為空時
			String readAction = req.getParameter("readAction");// 讀取jsp上的已讀推播訊息位置
			int d = Integer.parseInt(readAction);
			if (broadcastConList.size() > d && unReadCount > 0) {// 若推播訊息list長度大於以讀訊息的位置且JSP推播訊息上的次數大於0
				BroadcastVO readBroadcast = broadcastConList.get(d);
				BroadcastService broadcastService = new BroadcastService();
				broadcastService.updateBroadcast(readBroadcast.getBroadcast_ID(), "B1");// 將此訊息設為已讀(將該訊息修改進資料庫)
				broadcastConList.remove(d);
				unReadCount--;
			}
		}
		session.setAttribute("unReadCount", unReadCount);
		RequestDispatcher rd = req.getRequestDispatcher("/back-end/mainPage.jsp");
		rd.forward(req, res);

	}

}

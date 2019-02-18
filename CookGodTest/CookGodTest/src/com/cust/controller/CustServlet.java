package com.cust.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.broadcast.model.BroadcastService;
import com.broadcast.model.BroadcastVO;
import com.cust.model.CustService;
import com.cust.model.CustVO;

public class CustServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		String action = req.getParameter("action");
		List<CustVO> custList = (List<CustVO>) session.getAttribute("custList");

		// 當顧客登入
		if ("selectCust".equals(action)) {
			String cust_acc = req.getParameter("cust_acc");
			String cust_pwd = req.getParameter("cust_pwd");
			CustService custService = new CustService();
			CustVO db_custVO = custService.getOneCustAcc(cust_acc);

			String cust_accDB = db_custVO.getCust_acc();

			// 辨識資料庫是否有該帳號
			if (cust_acc.equals(cust_accDB)) {
				session.setAttribute("cust", db_custVO);// 將此顧客資料存入session

				BroadcastService broadcastService = new BroadcastService();// 取得資料庫內該會員的推播訊息
				List<BroadcastVO> broadcastList = broadcastService.getOneBroadcastByCustID(db_custVO.getCust_ID());

				session.setAttribute("broadcast", broadcastList);// 將該顧客的推播訊息存入session

				String url = "/back-end/mainPage.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);// 帶回至主頁面
				rd.forward(req, res);

			} else {

			}

		}
		String url = "/back-end/cust/addCust.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}

}

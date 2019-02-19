package com.cust.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
		
		List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		try {
			// 登入之後
			if (action.equals("selectCust")) {
				String cust_acc = req.getParameter("cust_acc");
				String cust_pwd = req.getParameter("cust_pwd");
				CustService custService = new CustService();

				CustVO db_custVO = custService.getOneCustAcc(cust_acc);

				String cust_accDB = db_custVO.getCust_acc();

				

				// 判斷資料庫內是否有此顧客帳號
				if (cust_accDB.equals(cust_acc)) {

					session.setAttribute("cust", db_custVO);// 顧客資料設定在session

					BroadcastService broadcastService = new BroadcastService();//
					List<BroadcastVO> broadcastList = broadcastService.getOneBroadcastByCustID(db_custVO.getCust_ID());// 從資料庫查詢此顧客的推播

					session.setAttribute("broadcast", broadcastList);// 此顧客的推播設定在session

					String url = "/back-end/mainPage.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);//
					rd.forward(req, res);
				}

			}

		} catch (NullPointerException e) {
			errorMsgs.add("帳號或密碼錯誤");
		}
		String url = "/back-end/cust/addCust.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);

	}

}

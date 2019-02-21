package com.cust.controller;

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

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		// 登入之後
		if (action.equals("selectCust")) {
			String cust_acc = req.getParameter("cust_acc");// 顧客輸入的帳號
			CustService custService = new CustService();
			CustVO db_custVO = custService.getOneCustAcc(cust_acc);

			try {
				String cust_accDB = db_custVO.getCust_acc();
				if (cust_accDB.equals(cust_acc)) {// 判斷資料庫內是否有此顧客帳號

					session.setAttribute("cust", db_custVO);// 顧客資料設定在session

					BroadcastService broadcastService = new BroadcastService();//
					List<BroadcastVO> broadcastConList = new ArrayList<BroadcastVO>();
					broadcastConList = (ArrayList<BroadcastVO>) broadcastService
							.getOneBroadcastByCustID(db_custVO.getCust_ID());// 從資料庫查詢此顧客的推播

					int unReadCount = 0;
					if (!broadcastConList.isEmpty()) {

						for (int index = 0; index < broadcastConList.size(); index++) {
							if ("B0".equals(broadcastConList.get(index).getBroadcast_status())) {// 假如推播狀態為未讀(B0)
								unReadCount++;
							} else {
								broadcastConList.remove(index);
								index--;
								// 若非為未讀，則不顯示該訊息
							}
						}
					}

					session.setAttribute("broadcastConList", broadcastConList);// 此顧客的推播訊息集合設定在session
					session.setAttribute("unReadCount", unReadCount);// 此顧客未讀的推播訊息次數設定在session
					RequestDispatcher rd = req.getRequestDispatcher("/back-end/mainPage.jsp");//
					rd.forward(req, res);
					return;

				}
			} catch (NullPointerException e) {
				errorMsgs.add("帳號密碼錯誤，請重新輸入");
			}
			RequestDispatcher rd = req.getRequestDispatcher("/back-end/cust/selectCust.jsp");
			rd.forward(req, res);
		} else {
			List<BroadcastVO> broadcastConList = (ArrayList<BroadcastVO>) session.getAttribute("broadcastConList");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			int unReadCount = (int) session.getAttribute("unReadCount");
			session.setAttribute("broadcastConList", broadcastConList);
			session.setAttribute("unReadCount", unReadCount);
			RequestDispatcher rd = req.getRequestDispatcher("/back-end/mainPage.jsp");//
			rd.forward(req, res);
		}

	}

}

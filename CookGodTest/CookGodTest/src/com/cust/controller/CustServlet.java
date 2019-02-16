package com.cust.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		// �n�J(�d��CUST�����)
		if ("selectCust".equals(action)) {
			String cust_acc = req.getParameter("cust_acc");
			String cust_pwd = req.getParameter("cust_pwd");
			CustService custService = new CustService();
			CustVO db_custVO = custService.getOneCustAcc(cust_acc);

			
			String cust_accDB = db_custVO.getCust_acc();

			//����U�ȿ�J��CUST�b���O�_�P��Ʈw���@��
			if(cust_acc.equals(cust_accDB)) {
				
			}else {
				
			}

		}

	}

}

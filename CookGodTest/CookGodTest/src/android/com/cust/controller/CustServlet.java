package android.com.cust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cust.model.CustService;
import com.cust.model.CustVO;
import com.broadcast.model.BroadcastVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class CustServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

//	List<String , Integer> list = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();// 將請求透過BufferedReader輸出在StringBuilder
		StringBuilder jsonIn = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);

		String cust_account_json = jsonObject.get("selectCust").getAsString();// Android送出的KEY
		CustVO cust_account = gson.fromJson(cust_account_json, CustVO.class);// 利用gson.fromJson轉換CustVO類型

		String outStr = "";
		CustService custService = new CustService();
		CustVO cust_db = custService.getOneCustAcc(cust_account.getCust_acc());// 利用cust_account查詢資料庫該筆顧客資料

		if (cust_db != null && cust_account.getCust_pwd().equals(cust_db.getCust_pwd())) {// 若有該顧客資料且密碼輸入正確
			outStr = gson.toJson(cust_db);
		}

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		System.out.println("output: " + outStr);

	}

}

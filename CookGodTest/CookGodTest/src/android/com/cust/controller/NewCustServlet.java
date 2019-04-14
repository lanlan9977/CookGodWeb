package android.com.cust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.cust.model.CustService;
import android.com.cust.model.CustVO;


public class NewCustServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		System.out.println("jijijijijiji");
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String outStr="";
		
		String name = jsonObject.get("name").getAsString();
		String email = jsonObject.get("email").getAsString();
		String id = jsonObject.get("id").getAsString();
		System.out.println(name+email+id);
		CustService custService=new CustService();
		custService.addCust(name, name, name, "M", "0912345678", "Ã“à@ –","A123456789", email, null, new Date(System.currentTimeMillis()), null, "a0", name);
		CustVO custVO=custService.getOneCustAcc(name);
		outStr=gson.toJson(custVO);

	
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		System.out.println();
		System.out.println();

	}

}

package android.com.menuOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
public class MenuOrederServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<String> areas;
	private List<Team> alList;
	private List<Team> nlList;

	@Override
	public void init() throws ServletException {
		super.init();
		areas = new ArrayList<String>();
		areas.add("all");
		areas.add("American League");
		areas.add("National League");

		alList = new ArrayList<Team>();
		alList.add(new Team(1, "New York Yankees", 0.562, areas.get(1)));
		alList.add(new Team(2, "Boston Red Sox", 0.574, areas.get(1)));
		nlList = new ArrayList<Team>();
		nlList.add(new Team(3, "Chicago Cubs", 0.568, areas.get(2)));
		nlList.add(new Team(4, "Los Angels Dodgers", 0.642, areas.get(2)));
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String param = jsonObject.get("param").getAsString();
		String outStr = "";
		switch (param) {
		case "area":
			outStr = gson.toJson(areas);
			break;
		case "all":
			List<Team> teamList = new ArrayList<Team>();
			teamList.addAll(alList);
			teamList.addAll(nlList);
			outStr = gson.toJson(teamList);
			break;
		case "American League":
			outStr = gson.toJson(alList);
			break;
		case "National League":
			outStr = gson.toJson(nlList);
			break;
		default:
			doGet(req, res);
			break;
		}

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		System.out.println("output: " + outStr);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Gson gson = new Gson();
		String areas_json = gson.toJson(areas);
		String alList_json = gson.toJson(alList);
		String nlList_json = gson.toJson(nlList);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println("<h3>Areas</h3>");
		out.println(areas_json);
		out.println("<h3>American League</h3>");
		out.println(alList_json);
		out.println("<h3>National League</h3>");
		out.println(nlList_json);
	}
}

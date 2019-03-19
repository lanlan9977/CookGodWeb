package android.com.ad.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ad.model.AdService;
import com.dish.model.DishVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import piciotest.ImageUtil;

public class AdServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<DishVO> dishList;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();

		String line = null;
		while ((line = br.readLine()) != null) {

			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		AdService adService = new AdService();
		if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			int position = jsonObject.get("position").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = adService.getAllAdPic().get(position);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);	
			}
			os.write(image);
			os.close();

		}else if("getSize".equals(action)){
			
			int AdSize=adService.getAll().size();
			String stringSize=String.valueOf(AdSize);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.println(stringSize);
			out.close();
		}
		res.setContentType(CONTENT_TYPE);
	}
}

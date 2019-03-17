package android.com.ad.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ad.model.AdService;
import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import piciotest.ImageUtil;
import android.com.ad.controller.Base64Decoder;

public class Adservlet extends HttpServlet {
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

		AdService  adService = new AdService (); 
		if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			int imageSize = jsonObject.get("imageSize").getAsInt();
//			byte[] image = dishService.getDish_Pic(dish_ID);
			String StringImage=adService.getAllAdCon().get(0);
//			System.out.println("CLOB: " + StringImage);
//			byte [] image=StringImage.getBytes();
//			
			Base64Decoder decoder = null;
		    try {
		      decoder = new Base64Decoder(
		                new BufferedInputStream(
		                new FileInputStream(StringImage)));
		      byte[] buf = new byte[4 * 1024];  // 4K buffer
		      int bytesRead;
		      while ((bytesRead = decoder.read(buf)) != -1) {
		        System.out.write(buf, 0, bytesRead);
		        os.write(buf);
				os.close();
		      }
		    }
		    finally {
		      if (decoder != null) decoder.close();
		    }
			
//			if (image != null) {
//				image = ImageUtil.shrink(image, imageSize);
//				res.setContentType("image/jpeg");
//				res.setContentLength(image.length);	
//			}
			
		}
		res.setContentType(CONTENT_TYPE);
	}
}

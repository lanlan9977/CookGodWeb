package android.com.cust.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cust.model.CustService;
import com.cust.model.CustVO;

public class CustServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "type/html;charset=UTF-8";
	private CustVO custVO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		CustService custService=new CustService();
		custVO=custService.getOneCustAcc(cust_acc);
	
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

}

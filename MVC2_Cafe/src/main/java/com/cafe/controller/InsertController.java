package com.cafe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.CafeDAO;
import com.cafe.dto.CafeDTO;


@WebServlet("/insert")
public class InsertController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String product_name = request.getParameter("product_name");
		int product_price = Integer.parseInt(request.getParameter("product_price"));
		
		CafeDAO dao = new CafeDAO();
		
		try {
			int rs = dao.insert(new CafeDTO(0,product_name,product_price));
			if(rs > 0) {
				System.out.println("insert 성공");
				response.sendRedirect("/index.jsp");
			}else {
				System.out.println("insert 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

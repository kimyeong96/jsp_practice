package com.cafe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.CafeDAO;
import com.cafe.dto.CafeDTO;

@WebServlet("/modify")
public class ModifyController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		CafeDAO dao = new CafeDAO();
		try {
			CafeDTO dto = dao.selectBySeq(seq);
			// 받아온 값 처리 
			if(dto != null) { // 조회한 값이 있다면 modify.jsp로 넘기기
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/modify.jsp").forward(request, response);
			}else { // 조회한 값이 없다면 출력 보여주기 
				response.sendRedirect("/toOutput");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

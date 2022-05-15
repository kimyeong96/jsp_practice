package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.dao.StudentDAO;
import com.student.dto.StudentDTO;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		StudentDAO dao = new StudentDAO();
		
		// dao selectByName일 경우 name 값을 넘겨 -> 데이터 조회(list로 받아야한다 -> 이름이 같을수도있기 때문에)
		// dao selectById일 경우 -> StudentDTO로 받을 것(고유값)
		
		try {
			ArrayList<StudentDTO> list = dao.selectByName(name);
			
			
			// 헤더 영역
						out.write("<html><head></head><body>");
						out.write("<table border=1 align=center>");
						out.write("<tr><th colspan=6>Student</th></tr>");
						out.write("<tr><th>ID</th><th>Name</th><th>KOR</th><th>ENG</th><th>MATH</th><th>SUM</th></tr>");
						
						// 실제 데이터 영역
						for(StudentDTO dto : list) {
							out.write("<tr>"
									+ "<td>" + dto.getId() + "</td>"
									+ "<td>" + dto.getName() + "</td>"
									+ "<td>" + dto.getKor() + "</td>"
									+ "<td>" + dto.getEng() + "</td>"
									+ "<td>" + dto.getMath() + "</td>"
									+ "<td>" + (dto.getKor() + dto.getEng() + dto.getMath()) + "</td>"
									+ "</tr>");
						}
						
						// 삭제 영역
						out.write("<tr><form action='/delete' method='post'>"
								+ "<td colspan=6>"
								+ "<input type='text' name='id' placeholder='삭제할 학생의 번호를 입력하세요'>"
								+ "<button type='submit'>Delete</button>"
								+ "</td>"
								+ "</form>"
								+ "</tr>");
						
						// 수정 영역 
						out.write("<tr>"
								+ "<form action='/update' method='post'>"
								+ "<td colspan=6>"
								+ "<input type='text' name ='id' placeholder='수정할 학생의 번호'>"
								+ "<button type='submit'>Update</button><br>"
								+ "<input type='text' name ='name' placeholder='수정할 학생의 이름'><br>"
								+ "<input type='text' name ='kor' placeholder='수정할 학생의 국어점수'><br>"
								+ "<input type='text' name ='eng' placeholder='수정할 학생의 영어점수'><br>"
								+ "<input type='text' name ='math' placeholder='수정할 학생의 수학점수'>"
								+ "</td>"
								+ "</form>"
								+ "</tr>");
						
						// 검색 영역
						out.write("<tr>"
								+ "<form action='/search' method='post'>"
								+ "<td colspan=6>"
								+ "<input type='text' name ='name' placeholder='검색할 학생의 이름 입력'>"
								+ "<button type='submit'>Search</button>"
								+ "<button type='button' id='showAll'>ShowAll</button>"
								+ "</td>"
								+ "</form>"
								+ "</tr>");
						
						// ShowAll
						out.write("<script>"
								+ "<document.getElementById('showAll').onclick=function(){location.href='/toOutput'}>"
								+ "</script>");
						
						// back 버튼
						out.write("<tr>"
								+ "<td colspan=6><button type='button' id='back'>Back</button>"
								+ "</td>"
								+ "</tr>");
						
						// ShowAll , back 클릭시 이동
						out.write("<script>");
						out.write("<document.getElementById('showAll').onclick=function(){location.href='/toOutput'}>");
						out.write("<document.getElementById('back').onclick=function(){location.href='/index.html'}>");						
						out.write("</script>");
						
						out.write("</body></html>");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

package com.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.student.dto.StudentDTO;

public class StudentDAO {
	private BasicDataSource bds;
	
	public StudentDAO()  {
		// 서버가 실행될 때 이미 생성된 Connection Pool 찾는 작업
		try {
			Context iCtx = new InitialContext(); // Connection Pool을 검색하기 위한 인스턴스 생성
			Context envCtx = (Context)iCtx.lookup("java:comp/env");
			bds = (BasicDataSource)envCtx.lookup("jdbc/bds");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public Connection getConnection() throws Exception {
		return bds.getConnection();
	}
	
	
	// 학생 데이터 수정 
	public int update(StudentDTO dto) throws Exception {
		
		String sql = "update tbl_student2 set name = ?, kor = ?, eng = ?, math = ? where id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMath());
			pstmt.setInt(5, dto.getId());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	
	// 학생 데이터 삽입
	public int insert(StudentDTO dto) throws Exception{
		String sql = "insert into tbl_student2 values(seq_student2.nextval, ? , ? , ? , ?)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMath());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
		
	}
	
	
	// 학생 데이터 삭제
	public int delete (int id) throws Exception {
		String sql = "delete from tbl_student2 where id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			 
			pstmt.setInt(1, id);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	// 학생 개별 조회
	public ArrayList<StudentDTO> selectByName(String name) throws Exception {
		
		// 쿼리문 
		String sql = "select * from tbl_student2 where name = ?";
	
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1); // 테이블로부터 id 가져오기  
				String name_std = rs.getString(2); // 테이블로부터 name 가져오기 
				int kor = rs.getInt(3); // 테이블로부터 kor 가져오기 
				int eng = rs.getInt(4); // 테이블로부터 eng 가져오기 
				int math = rs.getInt(5); // 테이블로부터 math 가져오기 
				list.add(new StudentDTO(id,name_std,kor,eng,math));
			}
			
			return list;
		}
		
	}
	
	
	// 모든 학생 조회 
	public ArrayList<StudentDTO> selectAll() throws Exception {
		
		// 쿼리문 
		String sql = "select * from tbl_student2";
	
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1); // 테이블로부터 id 가져오기  
				String name = rs.getString(2); // 테이블로부터 name 가져오기 
				int kor = rs.getInt(3); // 테이블로부터 kor 가져오기 
				int eng = rs.getInt(4); // 테이블로부터 eng 가져오기 
				int math = rs.getInt(5); // 테이블로부터 math 가져오기 
				list.add(new StudentDTO(id,name,kor,eng,math));
			}
			
			return list;
		}
		
	}
	

}

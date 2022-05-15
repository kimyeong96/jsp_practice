package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.cafe.dto.CafeDTO;

public class CafeDAO {
	
	private BasicDataSource bds;
	
	public CafeDAO() {
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
	
	// 데이터 삭제 
	public int delete(int seq) throws Exception {
		String sql = "delete from tbl_cafe where product_id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setInt(1, seq);
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	

	// 데이터 수정
	public int modifyBySeq(CafeDTO dto) throws Exception {
		
		String sql = "update tbl_cafe set product_name = ?, product_price = ? where product_id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				pstmt.setString(1, dto.getProduct_name());
				pstmt.setInt(2, dto.getProduct_price());
				pstmt.setInt(3, dto.getProduct_id());
				
				int rs = pstmt.executeUpdate();
				return rs;

			}
	}
	
	// 데이터 보여주기 
	// seq에 의해 선택된 데이터 보여주기
	public CafeDTO selectBySeq(int seq) throws Exception {
		String sql = "select * from tbl_cafe where product_id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, seq);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String product_name = rs.getString("product_name");
				int product_price = rs.getInt("product_price");
				return new CafeDTO(seq, product_name, product_price);
			}
		}
		return null;
	}
	
	// 데이터 삽입
	public int insert(CafeDTO dto) throws Exception {
		String sql = "insert into tbl_cafe values(seq_cafe.nextval, ?, ?)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, dto.getProduct_name());
			pstmt.setInt(2, dto.getProduct_price());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
		
	}
	
	// 데이터 출력 
	public ArrayList<CafeDTO> selectAll() throws Exception {
		String sql = "select * from tbl_cafe";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ArrayList<CafeDTO> list = new ArrayList<CafeDTO>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int product_price = rs.getInt(3);
				
				list.add(new CafeDTO(product_id,product_name,product_price));
			}
			return list;

		}

	}
}

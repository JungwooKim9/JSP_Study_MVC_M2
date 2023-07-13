package com.mysite.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysite.common.JDBCUtil;
import com.mysite.users.UsersDTO;

public class ProductsDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String PRODUCTS_INSERT =
			"insert into products(p_code, p_name, p_kind, p_price, p_content, p_quantity) values(?,?,?,?,?,?)";
	
	public void insertProductsDTO(ProductsDTO dto) {
		System.out.println("=====insertProducts 기능 처리=====");
		
		try {
			conn = JDBCUtil.getConnection();	// conn 객체가 활성화(Oracle / XE / HR2 / 1234)
			
//			System.out.println("커넥션 이후 호출 잘됨 ");
			// PreparedStatement 객체를 활성화
			pstmt = conn.prepareStatement(PRODUCTS_INSERT);	// pstmt 객체 활성화
			// ?에 들어갈 값을 dto에 getter를 사용해서 값을 할당
			pstmt.setInt(1, dto.getP_code());
			pstmt.setString(2, dto.getP_name());
			pstmt.setString(3, dto.getP_kind());
			pstmt.setString(4, dto.getP_price());
			pstmt.setString(5, dto.getP_content());
			pstmt.setString(6, dto.getP_quantity());
			pstmt.setDate(7, dto.getIndate());
			
			
//			System.out.println("변수값 할당 잘됨 - 이후 호출 잘됨 ");
//			System.out.println(dto.getTitle());
//			System.out.println(dto.getWriter());
//			System.out.println(dto.getContent());
			
			
			// PreparedStatement 객체를 실행: DB에 값이 Insert 됨
			pstmt.executeUpdate(); 	// insert / update / delete
			
			System.out.println("PRODUCTS 테이블에 값이 Insert 되었습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("PRODUCTS 테이블에 값 Insert 실패했습니다.");			
		}finally {
			// 사용한 객체를 제거함
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	
}

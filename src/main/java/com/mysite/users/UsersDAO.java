package com.mysite.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysite.users.UsersDTO;
import com.mysite.common.JDBCUtil;

public class UsersDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// SQL 쿼리를 상수로 정의 후에 각각 필요한 메소드에서 사용
	private final String USER_INSERT =
			"insert into users(id, password, name, role) values(?,?,?,?)";
	private final String USER_UPDATE = "";
	private final String USER_DELETE = "";
	private final String USER_GET = "";
	private final String USER_LIST = "select * from users order by id asc";
	
	
	// 1. users 테이블의 값을 넣는 메소드
	public void insertUsers(UsersDTO dto) {
		System.out.println("=====insertUser 기능 처리=====");
		
		try {
			conn = JDBCUtil.getConnection();	// conn 객체가 활성화(Oracle / XE / HR2 / 1234)
			
//			System.out.println("커넥션 이후 호출 잘됨 ");
			// PreparedStatement 객체를 활성화
			pstmt = conn.prepareStatement(USER_INSERT);	// pstmt 객체 활성화
			// ?에 들어갈 값을 dto에 getter를 사용해서 값을 할당
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getRole());
			
			
//			System.out.println("변수값 할당 잘됨 - 이후 호출 잘됨 ");
//			System.out.println(dto.getTitle());
//			System.out.println(dto.getWriter());
//			System.out.println(dto.getContent());
			
			
			// PreparedStatement 객체를 실행: DB에 값이 Insert 됨
			pstmt.executeUpdate(); 	// insert / update / delete
			
			System.out.println("USER 테이블에 값이 Insert 되었습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("USER 테이블에 값 Insert 실패했습니다.");			
		}finally {
			// 사용한 객체를 제거함
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// 2. users 테이블의 모든 레코드를 출력하는 메소드
	public List<UsersDTO> getUsersList(UsersDTO dto) {
		System.out.println("getUsersList 메소드 호출 - 유저 리스트 페이지");
		
		List<UsersDTO> usersList = new ArrayList<UsersDTO>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_LIST);
			rs = pstmt.executeQuery();		// rs에는 select한 결과 레코드셋을 담고 있다.
			
			// rs의 값을 끄집어 내서 DTO에 저장
			while (rs.next()) {
				
				// 주의: While 블락 내에서 DTO 객체를 생성해야 board의 힙주소가 새로 생성됨
				UsersDTO users = new UsersDTO();	// DTO 객체를 while 루프 내에서 생성
				
				users.setId(rs.getString("ID"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setName(rs.getString("NAME"));
				users.setRole(rs.getString("ROLE"));
				
				// boardList에 DTO를 추가
				usersList.add(users);
			}
			
			System.out.println("usersList에 모든 레코드 추가 성공");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("usersList에 모든 레코드 추가 실패");
			
		}finally {
			// 사용한 객체 close
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return usersList;
	}
	
	
	
	
}

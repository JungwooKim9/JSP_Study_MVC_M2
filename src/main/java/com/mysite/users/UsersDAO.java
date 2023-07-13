package com.mysite.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysite.common.JDBCUtil;

public class UsersDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// SQL 쿼리를 상수로 정의 후에 각각 필요한 메소드에서 사용
	private final String USERS_INSERT =
			"insert into users(id, password, name, role) values(?,?,?,?)";
	private final String USERS_UPDATE = "update users set password=?, name=?, role=? where id=?";
	private final String USERS_DELETE = "";
	private final String USERS_GET = "select * from users where id = ?";
	private final String USERS_LIST = "select * from users order by id asc";
	private final String USERS_LOGIN = "select * from users where id = ? and password = ?";
	
	// 1. users 테이블의 값을 넣는 메소드
	public void insertUsers(UsersDTO dto) {
		System.out.println("=====insertUser 기능 처리=====");
		
		try {
			conn = JDBCUtil.getConnection();	// conn 객체가 활성화(Oracle / XE / HR2 / 1234)
			
//			System.out.println("커넥션 이후 호출 잘됨 ");
			// PreparedStatement 객체를 활성화
			pstmt = conn.prepareStatement(USERS_INSERT);	// pstmt 객체 활성화
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
			pstmt = conn.prepareStatement(USERS_LIST);
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

	//USERS_UPDATE = "update users set password=?, name=?, role=? where id=?";
	public void updateUsers(UsersDTO dto) {
		System.out.println("updateUsers 메소드 호출");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USERS_UPDATE);
			
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getRole());
			pstmt.setString(4, dto.getId());
			
			pstmt.executeUpdate();
			
			System.out.println("업데이트 성공");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("업데이트 실패");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public UsersDTO getUsers(UsersDTO dto) {
		UsersDTO users = new UsersDTO();
		
		//System.out.println("메소드 출력 : " + dto.getSeq());
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USERS_GET);
			pstmt.setString(1, dto.getId() );
			
			rs = pstmt.executeQuery();
			
			System.out.println("메소드 출력 부분 ");
			
			while (rs.next()) {
				
				users.setId(rs.getString("ID"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setName(rs.getString("NAME"));
				users.setRole(rs.getString("ROLE"));
				
			}
			
			
			System.out.println("Users 테이블에서 상세 레코드가 잘 처리되었습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Users 테이블에서 상세 레코드 처리가 실패되었습니다.");
			
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return users;
	}
	
	// 로그인 처리 메소드
	// "select * from users where id = ? and password = ?";
	public UsersDTO login(UsersDTO dto) {
		UsersDTO users = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt= conn.prepareStatement(USERS_LOGIN);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			
			rs = pstmt.executeQuery();
			
			// 레코드가 존재할 때 작동
			while (rs.next()) {
				users = new UsersDTO();
				users.setId(rs.getString("ID"));
				users.setName(rs.getString("NAME"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setRole(rs.getString("ROLE"));
				
				System.out.println("인증 성공: DB에 해당 ID와 Password가 존재함");
				
			}
			
			// rs의 값이 존재하면: 인증 성공
			// rs의 값이 존재하지 않으면: 인증 실패
			System.out.println("로그인 메소드 잘 작동");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 처리시 오류 발생");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		return users;
	}
	
	
	
	
}

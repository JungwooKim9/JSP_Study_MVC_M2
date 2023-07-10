package com.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

/**
 * Servlet implementation class MyController
 */
//@WebServlet("/MyController")	<== 어노테이션으로 요청을 처리(spring)
	/*
	 	Controller: Client의 요청을 처리함(get, post)
	 		- doGet() {}: client에서 보내는 get 요청을 처리하는 메소드	<form method="get" action="a.jsp">, <a href="b.jsp">
	 		- doHost() {}: client에서 보내는 post 요청을 처리하는 메소드, <form method="post" action="a.jsp">
	 		
	 	controller 요청을 처리하는 방법:
	 		web.xml: 클라이언트의 요청에 대한 controller를 지정함
	 		@WebServlet: 어노테이션을 사용해서 처리
	 		
	 	client가 보내는 요청 정보의 URI를 캐취해서 분기처리
	 */
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at (MyController) : ").append(request.getContextPath());
		
		// client에서 get 방식으로 넘어오는 요청 처리를 doPost에서 한꺼번에 처리하도록 던져줌
//		doPost(request, response);
		
		// client에서 Get / Post 모두를 한꺼번에 처리함
		
		// URL: http://localhost:8181/JSP_Study_MVC_M2/my.do
		// URI: /JSP_Study_MVC_M2/my.do
		
		
		// 1. Client의 요청 정보를 Path 변수에 등록함
		String url = request.getRealPath(getServletInfo());
			System.out.println("클라이언트가 보내는 전체 URL(실제 시스템의 물리적 경로): " + url);
		
		String uri = request.getRequestURI();
			System.out.println("클라이언트가 보내는 요청 uri: " + uri);
			
		String path = uri.substring(uri.lastIndexOf("/"));
//			System.out.println("파일명을 읽어옴: " + path);
			
		// 2. 클라이언트의 요청 정보에 따라서 적절히 분기 처리 한다.
		if(path.equals("/login.do")) {
//			System.out.println("login.do를 요청했습니다.");
			// 로그인을 처리하는 코드 블락
			
		} else if (path.equals("/logout.do")) {
//			System.out.println("logout.do를 요청헀습니다.");
			// 로그아웃 요청을 처리하는 코드 블락
			
		} else if (path.equals("/insertBoard.do")) {
			System.out.println("insertBoard.do를 요청헀습니다.");
			// 게시판에서 값을 DB에 저장함.
			
			// 1. client 폼에서 넘어오는 변수의 값을 받아서 새로운 변수에 재할당
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			// 2. DTO 객체를 생성해서 Setter 주입
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setWriter(writer);
			dto.setContent(content);
			
			// 3. DAO 객체 생성 후 insertBoard(dto)
			BoardDAO dao = new BoardDAO();
			dao.insertBoard(dto);	// insert 완료
			
			// 4. 비즈니스 로직을 처리 후 view 페이지로 이동
			response.sendRedirect("getBoardList.jsp");
			
		} else if (path.equals("/getBoard.do")) {
//			System.out.println("getBoard.do를 요청했습니다.");
			// 게시판의 값을 읽어올 때
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

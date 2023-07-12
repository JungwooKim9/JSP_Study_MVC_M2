package com.mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;
import com.mysite.users.UsersDAO;
import com.mysite.users.UsersDTO;

/**
 * Servlet implementation class MyController
 */
//@WebServlet("/MyController")      <== 어노테이션으로 요청을 처리 ( spring ) 
	/*
	    Controller : Client 의 요청을 처리함. ( get, post )
	     		- doGet() {} : client 에서 보내는 get 요청을 처리하는 메소드 ,   <form method = "get" action= "a.jsp">, <a href = "b.jsp"> 
	     		- doPost() {} : client 에서 보내는 Post 요청을 처리하는 메소드 , <form method = "post" action="a.jsp"> 
	     		
	      controller 요청을 처리 하는 방법 : 
	      		web.xml : 클라이언트의 요청에 대한 controller를 지정함. 
	      		@WebServlet : 어노테이션을 사용해서 처리 
	      		
	      		
	      client 가 보내는 요청정보의 URI를 캐취해서 분기처리 
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
		//response.getWriter().append("Served at (MyController) : ").append(request.getContextPath());
		
		// client 에서 get 방식으로 넘어오는 요청 처리를 doPost에서 한꺼번에 처리하도록 던져줌 	
		request.setCharacterEncoding("UTF-8"); 
		doPost ( request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//client 에서 Get / Post 모두를 한꺼번에 처리함. 
		
		// URL : http://localhost:8181/JSP_Study_MVC_M2/my.do
		// URI : /JSP_Study_MVC_M2/my.do
		
		request.setCharacterEncoding("UTF-8"); 
		
		
		//1. Client의 요청 정보를 path 변수에 등록 함. 
		String url = request.getRealPath(getServletInfo()); 
			//System.out.println("클라이언트가 보내는 전체 URL(실제 시스템의 물리적 경로)  : " + url );
		
		StringBuffer urll = request.getRequestURL(); 
		System.out.println("System URL : " + urll);
		
		String uri = request.getRequestURI(); 
			//System.out.println("클라이언트가 보내는 요청 uri : " + uri);
		
		String path = uri.substring(uri.lastIndexOf("/")); 
			//System.out.println("파일 명을 읽어옴 : " + path );
			
		//2. 클라이언트의 요청 정보에 따라서 적절히 분기 처리 한다. 
		if ( path.equals("/login.do")) {       
			System.out.println("login.do 요청을 했습니다. ");
			// 로그인을 처리하는 코드 블락 
			
		} else if (path.equals("/logout.do")) {
			System.out.println("logout.do 요청을 했습니다. ");
			//로그아웃 요청을 처리하는 코드 블락 
			
		} else if (path.equals("/insertBoard.do")) {
			System.out.println("insertBoard.do 요청을 했습니다. ");
			// 게시판에서 값을 DB에 저장함.
			
			// 1. client 폼에서 넘어오는 변수의 값을 받아서 새로운 변수에 제 할당. 
			String title = request.getParameter("title"); 
			String writer = request.getParameter("writer"); 
			String content = request.getParameter("content");
			
			//2. DTO 객체를 생성해서 Setter 주입 
			BoardDTO dto = new BoardDTO (); 
			dto.setTitle(title); 
			dto.setWriter(writer); 
			dto.setContent(content); 
			
			//3. DAO 객체 생성후 insertBoard(dto)  
			BoardDAO dao = new BoardDAO (); 
			dao.insertBoard(dto);        //Insert 완료 
			
			//4. 비즈니스 로직을 처리후 view 페이지로 이동 
			response.sendRedirect("getBoardList.do");
		
		} else if (path.equals("/getBoard.do")) {
			System.out.println("getBoard.do 를 요청 했습니다. ");
			// 게시판의 값을 읽어 올 때(글 상세)
			
			// request.getParameter으로 넘어오는 모든 변수의 값은 String
			// Spring에서는 자동으로 Framework에서 자동으로 처리됨
				// Integer.parseInt(seq)
			
			String seq = request.getParameter("seq");
			
			//System.out.println(seq);
			
			// 1. DTO에 seq의 값을 setter 주입
			BoardDTO dto = new BoardDTO();
			dto.setSeq(Integer.parseInt(seq));
			
			// 3. DAO 객체에 getBoard(dto) 넣어서 호출 ===> 리턴으로 dto를 받아온다.
			BoardDAO dao = new BoardDAO();
			
			// 리턴으로 돌아오는 DTO 값을 받을 변수 선언
			BoardDTO board = new BoardDTO();
			board = dao.getBoard(dto);
			
			 System.out.println(board);
			
			 // 4. session에 값을 저장 후 뷰 페이지로 전달: Model
			 	// 현재 클라이언트가 서버에 접속한 세션을 가지고 옴
			 
			 // 세션 변수 선언
			 HttpSession session = request.getSession();
			 
			 // 세션의 변수에 DB에서 select한 DTO를 저장 후 뷰 페이지로 전송
			 session.setAttribute("board", board);
			 
			response.sendRedirect("getBoard.jsp");
			
			
		} else if (path.equals("/getBoardList.do")) {
			System.out.println("getBoardList.do 를 요청 했습니다. ");
			
			// 1. DTO 객체 생성
			BoardDTO dto = new BoardDTO();
			
			// 2. DAO의 getBoardList(dto)
			BoardDAO dao = new BoardDAO();
			
			List<BoardDTO> boardList = new ArrayList<BoardDTO>();
			
			// boardLlist에는 board 테이블의 각 레코드를 dto에 저장 후 boardList에 추가된 객체를 리턴
			boardList = dao.getBoardList(dto);
			
			// 리턴 받은 boardList를 Client View 페이지로 전송 (Session에 리스트를 저장 후 클라이언트로 전송)
			// Session: 접속한 client에 고유하게 부여된 식별자가 서버 메모리에 할당
			
			// 세션 변수 선언
			HttpSession session = request.getSession();
			
			// 세션에 boardList를 추가
			session.setAttribute("boardList", boardList);
			
			// 클라이언트 뷰 페이지로 이동
			response.sendRedirect("getBoardList.jsp");
			
			
		}else if (path.equals("/getUsers.do")) {
			System.out.println("getUsers.do 를 요청 했습니다. ");
			
			String id = request.getParameter("id");
			
			//System.out.println(seq);
			
			// 1. DTO에 seq의 값을 setter 주입
			UsersDTO dto = new UsersDTO();
			dto.setId(id);
			
			// 3. DAO 객체에 getBoard(dto) 넣어서 호출 ===> 리턴으로 dto를 받아온다.
			UsersDAO dao = new UsersDAO();
			
			// 리턴으로 돌아오는 DTO 값을 받을 변수 선언
			UsersDTO users = new UsersDTO();
			users = dao.getUsers(dto);
			
			 System.out.println(users);
			
			 // 4. session에 값을 저장 후 뷰 페이지로 전달: Model
			 	// 현재 클라이언트가 서버에 접속한 세션을 가지고 옴
			 
			 // 세션 변수 선언
			 HttpSession session = request.getSession();
			 
			 // 세션의 변수에 DB에서 select한 DTO를 저장 후 뷰 페이지로 전송
			 session.setAttribute("users", users);
			 
			response.sendRedirect("getUsers.jsp");
			
		} else if (path.equals("/insertUsers.do")) {
			// Users 테이블에 값을 Insert 코드 블락
			
			System.out.println("insertUsers.do 요청을 했습니다. ");
			// 게시판에서 값을 DB에 저장함.
			
			// 1. client 폼에서 넘어오는 변수의 값을 받아서 새로운 변수에 제 할당. 
			String id = request.getParameter("id"); 
			String password = request.getParameter("password"); 
			String name = request.getParameter("name");
			String role = request.getParameter("role");
			
			//2. DTO 객체를 생성해서 Setter 주입 
			UsersDTO dto = new UsersDTO (); 
			dto.setId(id); 
			dto.setPassword(password); 
			dto.setName(name); 
			dto.setRole(role); 
			
			//3. DAO 객체 생성후 insertBoard(dto)  
			UsersDAO dao = new UsersDAO (); 
			dao.insertUsers(dto);        //Insert 완료 
			
			//4. 비즈니스 로직을 처리후 view 페이지로 이동 
			response.sendRedirect("getUsersList.do");
			
		}else if (path.equals("/getUsersList.do")) {
			// Users 테이블의 값을 Select 해서 출력
			System.out.println("getBoardList.do 를 요청 했습니다. ");
			
			// 1. DTO 객체 생성
			UsersDTO dto = new UsersDTO();
			
			// 2. DAO의 getBoardList(dto)
			UsersDAO dao = new UsersDAO();
			
			List<UsersDTO> usersList = new ArrayList<UsersDTO>();
			
			// boardLlist에는 board 테이블의 각 레코드를 dto에 저장 후 boardList에 추가된 객체를 리턴
			usersList = dao.getUsersList(dto);
			
			// 리턴 받은 boardList를 Client View 페이지로 전송 (Session에 리스트를 저장 후 클라이언트로 전송)
			// Session: 접속한 client에 고유하게 부여된 식별자가 서버 메모리에 할당
			
			// 세션 변수 선언
			HttpSession session = request.getSession();
			
			// 세션에 boardList를 추가
			session.setAttribute("usersList", usersList);
			
			// 4. 비즈니스로직 모두 처리 후 view 페이지 이동
			response.sendRedirect("getUsersList.jsp");
			
			// 글 수정
		}else if (path.equals("/updateBoard.do")) {
			//
			System.out.println("/updateBoard.do - 요청 성공");
			
			// 1. 클라이언트에서 넘어오는 변수 값을 받음
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			
			// 2. DTO에 Setter 주입
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setSeq(Integer.parseInt(seq));
			
			System.out.println("넘버 변수 변환: " + seq);
			
			// 3. DAO의 메소드 호출: updateBoard(dto)
			BoardDAO dao = new BoardDAO();
			dao.updateBoard(dto);
			
			// 4. View 페이지로 이동
			response.sendRedirect("getBoardList.do");
			
		}else if (path.equals("/updateUsers.do")) {
			//
			System.out.println("/updateUsers.do - 요청 성공");
			
			// 1. 클라이언트에서 넘어오는 변수 값을 받음
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String role = request.getParameter("role");
			
			// 2. DTO에 Setter 주입
			UsersDTO dto = new UsersDTO();
			dto.setId(id);
			dto.setPassword(password);
			dto.setName(name);
			dto.setRole(role);
			
			// 3. DAO의 메소드 호출: updateBoard(dto)
			UsersDAO dao = new UsersDAO();
			dao.updateUsers(dto);
			
			// 4. View 페이지로 이동
			response.sendRedirect("getUsersList.do");
			
		}
			
		
		
	}

}
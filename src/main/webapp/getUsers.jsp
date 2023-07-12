<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.mysite.users.UsersDTO" %>    
    
<%
	// 세션에 저장된 변수의 값을 불러옴
	UsersDTO users = (UsersDTO) session.getAttribute("users");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 상세</title>
<style>
	div {
		width: 700px;
		margin: 0 auto;
	}
	td {
		padding: 10px;
	}
</style>
</head>
<body>
	<div>
		<h1> 유저 상세 페이지 </h1>
		<hr>
		<br> <br>
		
		<form action="updateUsers.do" method="post">
		
			<!-- 글 수정시 seq 변수 값을 서버로 전송 -->
			<input type="hidden" name="id" value="<%= users.getId() %>">
			
			<table border="1px" cellspaing="0" cellpadding="0">
				<tr> <td bgcolor="orange"> 아이디 </td>
					 <td> <%= users.getId() %> </td>
				</tr>
				<tr> <td bgcolor="orange"> 패스워드 </td>
					 <td> <input type="password" name="password" value="<%= users.getPassword() %>"> </td>
				</tr>
				<tr> <td bgcolor="orange"> 이름 </td>
					 <td> <input type="text" name="name" value="<%= users.getName() %>"> </td>
				</tr>
				<tr> <td bgcolor="orange"> 권한 </td>
					 <td> <input type="text" name="role" value="<%= users.getRole() %>"> </td>
				</tr>
				<tr> <td colspan="2" align="center"> <input type="submit" value="정보 수정 하기"> </td>
				</tr>
			</table>
		</form>
		
		<br> <br>
		<a href="getUsersList.do"> 유저 목록 보기 </a>
		<br> <br>
		<a href="insertUsers.jsp"> 유저 추가 </a>
		<br> <br>
		<a href="deleteUsers.do?id=<%= users.getId() %>"> 유저 삭제 </a>
		
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%@ page import="com.mysite.users.UsersDTO" %>
    
    <%
    	// 세션에 저장된 값을 끄집어 낸다.
    	List<UsersDTO> usersList = new ArrayList<UsersDTO>();
    
    	usersList = (List) session.getAttribute("usersList");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 목록</title>
<style>
	div {
		width: 750px;
		margin: 0 auto;
	}
</style>
</head>
<body>
	<div>
	<h1> 유저 목록 </h1>

	<table border="1px" cellpadding="0" cellspacing="0" width="700px">
		<tr>
			<th bgcolor="orange" width="100px"> 아이디 </th>
			<th bgcolor="orange" width="200px"> 패스워드 </th>
			<th bgcolor="orange" width="150px"> 이름 </th>
			<th bgcolor="orange" width="150px"> Role </th>
		</tr>
		
		<!-- DB의 값을 가져와서 루프 시작 -->
		<%
			for (UsersDTO k : usersList) {
		
		%>
		<tr>
			<td> <%= k.getId() %> </td>
			<td> <%= k.getPassword() %> </td>
			<td> <%= k.getName() %> </td>
			<td> <%= k.getRole() %> </td>
		</tr>
		<%
			}
			// session 변수를 사용 후 변수에 담긴 값을 제거
			session.removeAttribute("usersList");
		%>
		<!-- DB의 값을 가져와서 루프 시작 -->
	
	</table>
	
	<br> <br>
	<a href="insertUsers.jsp"> 새 유저 등록 </a>

	</div>
</body>
</html>
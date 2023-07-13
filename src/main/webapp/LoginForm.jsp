<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// session에 저장된 값을 읽어옴
	String sessionId = (String) session.getAttribute("id");
	String sessionRole = (String) session.getAttribute("role");
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td {
		padding: 10px;
	}

</style>

<script>
	function validateForm(form){
		if (!form.id.value){
			alert("아이디를 입력해주세요");
			form.id.focus();
			return false;
		}
		if (!form.password.value){
			alert("패스워드를 입력해주세요");
			form.password.focus();
			return false;
		}
	}
</script>

</head>
<body>
	<h1> 로그인 폼 </h1>
	<hr>
	<br> <br>
	
	<%
		if (sessionId == null) {	// 로그인이 안되었을 때 출력 부분
			
	%>
	
	<!-- 유효성 검사 후 넘김 -->
	<form action="login.do" method="post" name="LoginFrm" onsubmit="return validateForm(this);">
		<table>
			<tr>
				<td> ID: </td> <td> <input type="text" name="id"> </td>
			</tr>
			<tr>
				<td> Password: </td> <td> <input type="password" name="password"> </td>
			</tr>
			<tr>
				<td> <input type="submit" value="로그인 하기"> </td>
			</tr>
		</table>
		
	</form>
	<%
		}else {	// 로그인 성공시 출력 부분
	%>
	
		<%= sessionId %> 로그인이 성공 되었습니다. <br> <br>
		당신의 권한은 <%= sessionRole %> 입니다. <br> <br>
		
		<a href="logout.do"> 로그아웃 </a>
	<%
		}	
	%>	
			
	<a href="getBoardList.do"> 게시판 리스트 페이지 </a> <p />
	<a href="insertBoard.jsp"> 게시판 글쓰기 </a> <p />
	
	<hr>
	
	<br> <p /> <p />
	<a href="getUsersList.do"> 사용자 리스트 페이지 </a> <p />
	<a href="insertUsers.jsp"> 사용자 등록 </a> <p />
	
</body>
</html>
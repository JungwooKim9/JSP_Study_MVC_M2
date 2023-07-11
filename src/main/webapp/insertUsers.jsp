<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>유저 등록</title>
</head>
<body>
	<center>
		<h1>유저 등록</h1>
		<a href="logout.do">Log-out</a>
		<hr>
		<form action="insertUsers.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">아이디</td>
					<td align="left"><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">비밀번호</td>
					<td align="left"><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">이름</td>
					<td align="left"><input name="name" size="10"></input></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">Role</td>
					<td align="left"><input name="role" size="10"></input></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value=" 유저 등록 " /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="getUsersList.do">유저 목록 가기</a>
	</center>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="update.do" method="get">
		<table border="1">
			<tr>
				<td><label for="id">아이디</label></td>
				<td><input type="text" id="id" required="" name="userid">
				</td>
			</tr>

			<tr>
				<td><label for="username">이름</label></td>
				<td><input type="text" required="" id="username"
					name="username" /></td>
			</tr>

			<tr>
				<td><label for="password">비밀번호</label></td>
				<td><input type="password" required="" id="password" name="password" /></td>
			</tr>


		</table>
		<div>
			<input type="submit" value="수정완료"><input type="reset"
				value="다시작성">
		</div>
	</form>



</body>
</html>
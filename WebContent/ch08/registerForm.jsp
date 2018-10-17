<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%response.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="registerPro.jsp">
<table border="1">
		<tr>
		<td><label for="idt">아이디</label></td>
		<td><input type="text" id="idt" name="id" size="10"></td>
		</tr>
		
		<tr>
		<td><lable for="pwt">비밀번호</lable></td>
		<td><input type="password" id="pwt" name="passwd" size="10"></td>
		</tr>
		
		<tr>
		<td><label for="namet">이름</label></td>
		<td><input type="text" id="namet" name="name" size="10"></td>
		</tr>
		
		<tr><td><input type="submit" value="회원가입"><input type="reset" value="다시작성"></td></tr>
</table>
</form>

</body>
</html>
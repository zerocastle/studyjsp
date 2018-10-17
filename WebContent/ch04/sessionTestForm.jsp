<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("id") == null){ %>
<form method = "post" action="sessionTest.jsp">
<div>아이디 : <input type="text" name="id"></div>
<div>비밀번호 : <input type="text" name="pw"></div>
<div><input type="submit" value="전송"></div>
</form>
<%}else {%>
<%String id = (String)session.getAttribute("id"); %>
<div><%=id%>님 오셨군요</div>
<form method = "post" action="logout.jsp">
<input type="submit" value="logout">
</form>
<%} %>

</body>
</html>
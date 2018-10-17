<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!String id; String pw; %>

<%
	String dbId = "kys";
	String dbPw = "1234";
	this.id = request.getParameter("id");
	this.pw = request.getParameter("pw");
	
	if(this.id.equals(dbId) && this.pw.equals(dbPw)){
		session.setAttribute("id", id);
		response.sendRedirect("sessionTestForm.jsp");
	}else
		response.sendRedirect("sessionTestForm.jsp");
%>

</body>
</html>
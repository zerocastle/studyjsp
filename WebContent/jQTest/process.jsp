<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<% request.setCharacterEncoding("utf-8"); %>
<title>Insert title here</title>
</head>
<body>
<%
	String resultStr = "ó����� <br>";
	String name = request.getParameter("name");
	String stus = request.getParameter("stus");
	
	resultStr = resultStr + "�̸���" + name + "�̰�,<br/>";
	resultStr = resultStr + "������´�" + stus;
	out.println(resultStr);
%>

</body>
</html>
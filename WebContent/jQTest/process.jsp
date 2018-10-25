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
	String resultStr = "처리결과 <br>";
	String name = request.getParameter("name");
	String stus = request.getParameter("stus");
	
	resultStr = resultStr + "이름은" + name + "이고,<br/>";
	resultStr = resultStr + "현재상태는" + stus;
	out.println(resultStr);
%>

</body>
</html>
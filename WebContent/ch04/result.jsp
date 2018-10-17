<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String age = (String) request.getAttribute("result");
		String[] test = (String[])request.getAttribute("test");
	%>
	<div>
		여기는 result 페이지 입니다.<%=age%>
		<div><%out.println(Arrays.toString(test)); %></div>
		</div>

</body>
</html>
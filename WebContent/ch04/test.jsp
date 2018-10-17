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

<%!int age ; String gender; String hobby; String[] like;%>

<%
	this.age = Integer.parseInt(request.getParameter("age"));
	this.gender = request.getParameter("gender");
	this.hobby = request.getParameter("hobby");
	this.like = request.getParameterValues("season");
	
%>
<div>나이 : <%=this.age %></div>
<div>성별 : <%=this.gender %></div>
<div>나이 : <%=this.hobby %></div>

<div>내가 좋아하는 계절은 = 
<%
	if(this.like == null){
		out.println("선택하신 계절이 없습니다.");
	}else
		out.println(Arrays.toString(this.like));
%>
</div>

</body>
</html>
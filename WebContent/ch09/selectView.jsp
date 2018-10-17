<%@page import="mainAction.SelectDo"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<%
	SelectDo obj = new SelectDo();
	Iterator iter = obj.member.iterator();
	
	while(iter.hasNext()){
		out.println(iter.next());
	}
	%>
	</div>

	
	<a href="updateForm.jsp" style="text-decoration : none">업데이트 시키세요</a>



</body>
</html>
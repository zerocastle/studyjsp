<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="registerBean" class="ch08.bean.RegisterBean">
	<jsp:setProperty name="registerBean" property="*"/>
</jsp:useBean>

<%registerBean.setReg_date(new Timestamp(System.currentTimeMillis()));

%>
<table border="1">
	<tr>
		<td>아이디:</td>
		<td><jsp:getProperty property="id" name="registerBean"/></td>
	</tr>
	<tr>
		<td>비밀번호:</td>
		<td><jsp:getProperty property="passwd" name="registerBean"/></td>
	</tr>
	
	<tr>
		<td>이름:</td>
		<td><jsp:getProperty property="name" name="registerBean"/></td>	
	</tr>
	
	<tr>
		<td>가입일:</td>
		<td><jsp:getProperty property="reg_date" name="registerBean"/></td>	
	</tr>
	
	
	
</table>

</body>
</html>
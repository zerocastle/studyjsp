<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="testBean" class="ch08.bean.TestBean">
	<jsp:setProperty name="testBean" property="*" />
</jsp:useBean>

<div>입려괸 아이디 : <jsp:getProperty property="id" name="testBean"/></div>
<div>입력된 이름 : <jsp:getProperty property="name" name="testBean"/></div>

</body>
</html>
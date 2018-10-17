<%@page import="todo.TodoBean.TodoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form actoin="TodoServlet" method="post">
	<input type="text" name="detail" required=""/>
	<input type="submit" value="ADD"/>
</form>
<c:if test="${todoList == null}">
등록된 상품이 없습니다.
</c:if>

<c:if test="${todoList != null }">
<ul>
<c:forEach var="p" items="${todoList }">
	<li>${p.detail }  </li>
</c:forEach>
</ul>
</c:if>

</body>
</html>
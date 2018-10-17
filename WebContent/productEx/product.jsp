<%@page import="ys.shop.Product"%>
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
<%--for(Prodcut p : list) --%>

<%

	ArrayList<Product> list = (ArrayList<Product>)session.getAttribute("productList");

	if(list != null) out.println("총" + list.size() + "개 상품");
	else
		out.println("등록된 상품이 없습니다.");
%>
<ul>
<%
	if(list != null)
		for(int i = 0; i < list.size(); i++){
			%>
			<li><%=list.get(i)%></li>
		<%
		}else{
			out.println("상품 목록이 없어요");
		}
			%>
</ul>

<form name="form" action="">
	<div>1번<input type="checkbox" name="selected" value="1"/></div>
	<div>2번<input type="checkbox" name="selected" value="2"/></div>
	<div>3번<input type="checkbox" name="selected" value="3"/></div>
	<div>4번<input type="checkbox" name="selected" value="4"/></div>
</form>
<br><br>
<div>여기 아래부터 교수 코드(JSTL 적용 전)</div>
<%if (list != null){
	out.println("<form>");
		for(Product p : list){
%>
<input type="checkbox" name="selected" value="<%=p.getId()%>">
<%=p.getName()%>
<%=p.getInfo()%>
<%=p.getPrice()%><br>
<%
	}
	out.println("</form>");
}
%>

<div>=====JSTL 적용 공간 =========</div>
<c:if test="${productList == null }">
등록된 상품이 없습니다.
</c:if>

<c:if test="${productList != null }"><!-- <c:if test="${not empty productList }"></c:if> -->

<form>
	<c:forEach var="p" items="${productList }">
	<input type="checkbox" name="selected" value="${p.id }"/>
	${p.name },${p.info },${p.price }
	</c:forEach>
</form>
</c:if>



</body>
</html>
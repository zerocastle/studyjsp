<%@page import="ch11.logon.LogonDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
    
<jsp:useBean id="member" class="ch11.logon.LogonDataBean">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>

<%
	LogonDBBean manager = LogonDBBean.getInstance();
int check = manager.updateMember(member);

out.println(check);

%>

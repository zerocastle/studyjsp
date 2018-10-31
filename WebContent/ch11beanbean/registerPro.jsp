<%@page import="java.sql.Date"%>
<%@page import="ch11.logon.LogonDBBean"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

<%request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="member" class="ch11.logon.LogonDataBean">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>
<%
	member.setReg_date(new Timestamp(System.currentTimeMillis()));
	LogonDBBean manager = LogonDBBean.getInstance();
	manager.insertMember(member);
%>


<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	try{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/TestDB");
		conn = ds.getConnection();
		
		String sql = "select * from member";
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		
		while(rs.next()){
			String id = rs.getString("userid");
			String password = rs.getString("password");
			String name = rs.getString("username");
			Timestamp time = rs.getTimestamp("reg_date");
			out.println(id + " ," + password + " ," + name + " ," + time);
		}
	
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
			if(rs != null)
				try{rs.close();}catch(SQLException sqle){}
			if(pstm != null)
				try{pstm.close();}catch(SQLException sqle){}
			if(conn != null)
				try{conn.close();}catch(SQLException sqle){}
	}
%>
</body>
</html>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
    <%
		String result = "----------------<br/>"; //결과 문자열
		String realFolder = ""; // 웹 어플리케이션 상에 절대 경로저장
		String saveFolder = "/fileSave";
		String encType = "utf-8";
		int maxSize = 5*1024 * 1024; // 업로드될 파일 크기 최대 5MB
		ServletContext context = getServletContext();
		//out.println(context);
		realFolder = context.getRealPath(saveFolder);
		//out.println(realFolder);
		try{
			MultipartRequest upload = null;
			// 파일 업로드를 수행하는  MultipartRequest 객체 생성
			upload = new MultipartRequest(request,realFolder,maxSize ,encType ,new DefaultFileRenamePolicy());
			Enumeration<?> params = upload.getParameterNames();
			
			while(params.hasMoreElements()){
				String name =(String) params.nextElement(); // 파라미터명
				String value = upload.getParameter(name); // 파라미터 값
				result += name + " : " +  value + "<br/>"; //결과 문자열 누적
			}
			// <input type="file"> 인 모든 파라미터를 얻어냄
					Enumeration<?> files = upload.getFileNames();
			
			while(files.hasMoreElements()){
				String name =(String) files.nextElement(); // 파라미터명
				String filename = upload.getFilesystemName(name); // 파라미터 값
				String original = upload.getOriginalFileName(name);
				
				String type = upload.getContentType(name);
				
				result += "파라미터 이름 " + name + "<br/>";
				result += "실제 파일 이름 " + original + "<br/>";
				result += "저장된 파일 이름" + filename + "<br/>";
				result += "파일 타입 " + type + "<br/>";
				
				File file = upload.getFile(name);
				if(file!= null)
					result += "크기 : " + file.length() + "bytes <br/>"; //파일 크기
			}
			result += "---------------------<br>";
			out.println(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
    %>

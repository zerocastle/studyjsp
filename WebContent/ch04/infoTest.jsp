<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%!int age;%>
	<%
		String cloneAge = request.getParameter("age");
		this.age = Integer.parseInt(cloneAge);
		String result;
		String[] test = {"a","b","c"};
		if (this.age >= 20) {
			result = "성인";

		} else {
			result = "미성년";

		}
		request.setAttribute("result", result);
		request.setAttribute("test",test);
		RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
		dispatcher.forward(request, response);
	%>

</body>
</html>
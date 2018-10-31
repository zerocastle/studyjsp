<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	#result{
		width : 200px;
		height : 200px;
		border : 5px double blue;
	}
</style>
<script>
	$(document).ready(function(){
		$("#b2").click(function(){
			$("#display").load("ex07-01.jsp");
		});
	});
</script>
</head>
<body>
<button id="b2">로드</button>
<div id="display"></div>
</body>
</html>
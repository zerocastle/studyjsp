<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
		$("#b1").click(function(){
			$.post("process.jsp",{name:"kingdora" , stus : "homebody"},
			function(data,status){
				if(status = "success")
					$("#result").html(data);
			});
		});
	});

</script>
</head>
<body>

<button id = "b1">결과</button>
<div id = "result"></div>

</body>
</html>
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
    $("#b1").click(function(){ //[결과] 버튼을 클릭하면 자동실행
       //jQTest5.txt를 get 방식으로 요청
       var query={userid:$("#userid").val(),password:$("#password").val()};
       $.ajax({
          type:"POST",
          url: "/studyjsp/MemberServlet",
          data: query,
          success: function(data){
             if(data == 1){$("#result").text("로그인성공")}
             else if(data == 0){$("#result").text("비번틀림")}
             else if(data == -1){$("#result").text("비회원")}
             
          }
       });
    });
 });
</script>

</head>
<body>

<div>아이디 : <input type="text" name="userid" id="userid"></div>
<div>번호 : <input type="password" name="password" id="password"></div>
<div>결과</div>
<button id = "b1">버튼</button>
<div id = "result"></div>


</body>
</html>
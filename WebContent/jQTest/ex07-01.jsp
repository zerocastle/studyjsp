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
    $("#b1").click(function(){ //[���] ��ư�� Ŭ���ϸ� �ڵ�����
       //jQTest5.txt�� get ������� ��û
       var query={userid:$("#userid").val(),password:$("#password").val()};
       $.ajax({
          type:"POST",
          url: "/studyjsp/MemberServlet",
          data: query,
          success: function(data){
             if(data == 1){$("#result").text("�α��μ���")}
             else if(data == 0){$("#result").text("���Ʋ��")}
             else if(data == -1){$("#result").text("��ȸ��")}
             
          }
       });
    });
 });
</script>

</head>
<body>

<div>���̵� : <input type="text" name="userid" id="userid"></div>
<div>��ȣ : <input type="password" name="password" id="password"></div>
<div>���</div>
<button id = "b1">��ư</button>
<div id = "result"></div>


</body>
</html>
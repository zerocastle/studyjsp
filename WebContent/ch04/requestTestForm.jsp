<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

request 예제 --<br>
<form method="get" action="test.jsp">
		
		<dl>
			<dd><label for="name">이름 : </label><input type="text" name="name" id="name"></dd>
			<dd><label for="age">나이 : </label><input type="text" name="age" id="age"></dd>
		
		<fieldset>
			<legend>성별</legend>
			<label for="man">남</label>
			<input type="radio" name="gender" id="man" value="man">
			<label for="girl">여</label>
			<input type="radio" name="gender" id="girl" value="girl">
		</fieldset>
		<div>취미 <select name="hobby">
			<option value="잠자기">잠자기</option>
			<option value="자기">자기</option>
			<option value="자고싶다">자고싶다</option>
		</select></div>
		<div><label for="season">좋아하는 계절</label><input type="checkbox" name="season" id="season" value="봄">봄 <input type="checkbox" name="season" value="여름">여름
		<input type="checkbox" name="season" value="가을">가을<input type="checkbox" name="season" value="겨울">겨울
		</div>

		<div><input type="submit" name="submit" value="전송"></div>
		</dl>
	</form>


</body>
</html>
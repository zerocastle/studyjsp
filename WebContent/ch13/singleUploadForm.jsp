<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js" integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="singleupload.js"></script>
</head>
<body>

	<form id="upForm1" action="singleuploadPro.jsp" method="post"
		enctype="multipart/form-data">
		<div id="form">
			<ul>
				<li><label for="title">제목</label> <input type="text" id="title"
					name="title" size="20" maxlength="50" placeholder="제목입력" autofocus />
				</li>
				<li><label for="file1">파일선택</label> <input type="file"
					id="file1" name="file1" /></li>
				<li>
				<input type="submit" id="upPro1" value="단일 파일 업로드"/></li>
			</ul>
		</div>

	</form>
	
	<div id="upResult"></div>

</body>
</html>
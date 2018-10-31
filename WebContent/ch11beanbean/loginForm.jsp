<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="login.js"></script>


<%String id = ""; 
	try{
		id=(String)session.getAttribute("id");
%>
<%if(id == null || id.equals("")){ %>
<div id="status">
	<ul>
		<li><label for="id">아이디</label>
		<input id="id" name="id" type="email" size="20" maxlength="50" placeholder="example@kings.com"></li>
		<li><label for="passwd">비밀번호</label>
		<input id="passwd" name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자" maxlength="16"></li>
		<li class="label2">
			<button id="login">로그인</button>
			<button id="register">회원 가입 </button>
		</li>
	</ul>
	</div>
			<%}else{//인증된 사용자 영역%>
			
	<div id="status">
		<ul>
			<li><b><%=id %></b> 님이 로그인 하셨습니다.
			<li><button id="logout">로그아웃</button>
			<button id="update">회원 정보 변경</button>
			</li>
		</ul>
	</div>
	<%}}catch(Exception e){e.printStackTrace();} %>



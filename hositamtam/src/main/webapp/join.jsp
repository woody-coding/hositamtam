<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지입니다.</title>
<link rel="stylesheet" href="/finalProject/css/join.css" />
<script src="/finalProject/js/join.js" charset="UTF-8"></script>
</head>
<body>

	<h3>[회원가입]</h3>
	
	<form method="POST" id="insertMember">
	<fieldset>
		<legend>회원 정보를 입력하세요.</legend>
		
		<label for="id">아이디</label>
		<input type="email" name="id" id="id" placeholder="ex) example@gmail.com" required /><br />
		
		<label for="passwd">비밀번호</label>
		<input type="password" name="passwd" id="passwd" placeholder="4글자 이상" required /><br />
		
		<label for="repasswd">비밀번호 확인</label>
		<input type="password" id="repasswd" placeholder="위와 같은 비밀번호 입력" required /><br />
		
		<label for="nicakname">닉네임</label>
		<input type="text" name="nickname" id="nickname" placeholder="두글자 이상의 닉네임을 입력해주세요" required />
		
		<label for="gender">성별</label>
		<input type="checkbox" name="gender" value="남자"/>
		<input type="checkbox" name="gender" value="여자"/> :::
		
		<input type="submit" value="가입" />
	</fieldset>
	</form>
	
	<div id="msg">${msg}</div>
	
</body>
</html>
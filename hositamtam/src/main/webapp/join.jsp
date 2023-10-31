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
		<input type="text" name="id" id="id" placeholder="4글자 이상 12글자 이하" required />
		<button class="idcheck">중복확인</button><br />
		
		<label for="nicakname">닉네임</label>
		<input type="text" name="nickname" id="nickname" placeholder="2글자 이상 8글자 이하" required />
		<button class="nicknamecheck">중복확인</button><br />
		
		<label for="passwd">비밀번호</label>
		<input type="password" name="passwd" id="passwd" placeholder="8글자 이상 16글자 이하" required /><br />
		
		<label for="repasswd">비밀번호 확인</label>
		<input type="password" id="repasswd" placeholder="비밀번호를 한번 더 입력해주세요" required /><br />
		
		<label for="gender">성별</label>
		<input type="radio" name="gender" id="male" value="male" checked/>남자
		<input type="radio" name="gender" id="female" value="female"/>여자 <br /><br />
		
		<input type="submit" value="회원가입" />
		
	</fieldset>
	</form>
	
	<div id="msg">${msg}</div>
	
</body>
</html>
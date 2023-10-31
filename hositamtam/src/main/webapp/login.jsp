<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지입니다.</title>
</head>
<body>

	<h2>호시탐탐의 로그인 페이지입니다</h2>
	
	<form method="POST" action="/finalProject/views/main">
	
	<label for="id">아이디</label>
	<input type="text" name="id" id="id"/>

	<label for="passwd">비밀번호</label> 
	<input type="password" name="passwd" id="passwd" />

	<input type="submit" value="로그인"/> 
	</form>
</body>
</html>
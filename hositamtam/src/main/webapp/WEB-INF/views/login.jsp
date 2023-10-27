<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>호시탐탐의 로그인 페이지입니다</h2>
	
	<form method="GET" action="/finalProject/views/main">
	
	<label for="id">아이디</label>
	<input type="text" name="id" id="id"/>

	<label for="double">중복선택</label> 
	<input type="checkbox" name="double" value="선택1"/>
	<input type="checkbox" name="double" value="선택2"/>
	<input type="checkbox" name="double" value="선택3"/>

	<input type="submit" value="등록"/> 
	</form>
</body>
</html>
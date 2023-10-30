<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<a href="/finalProject/views/main">메인</a>
	<a href="/finalProject/views/login">로그인</a>
	<a href="/finalProject/views/join">회원가입</a>
	<a href="/finalProject/views/post">시끌시끌</a>
	
	
	<hr/>
	<h2>호시탐탐의 메인 페이지입니다</h2> 
	<hr/>
	<h4>${testModel.controllerMsg}</h4>
	
	<form method="GET" action="/finalProject/views/market">
	<button>시장보기</button>
	</form>
</body>
</html>
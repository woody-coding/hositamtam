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

	<h2>호시탐탐의 시장리스트 페이지입니다</h2>
	<hr/>
	<h4>${testModel.controllerMsg}</h4>
	
	<h3>[시장조회]</h3>
	
	<form method="GET" action="/finalProject/views/store">
		<table>
			<tr>
				<th>시장번호:</th>
				<td>${testModel.no}</td>
			</tr>
			<tr>
				<th>시장명:</th>
				<td>${testModel.name}</td>
			</tr>
			<tr>
				<th>구분:</th>
				<td>${testModel.type}</td>
			</tr>
			<tr>
				<td>
				<button>점포페이지로</button>
				<input type="hidden" id="name" name="name" value="${testModel.name}">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
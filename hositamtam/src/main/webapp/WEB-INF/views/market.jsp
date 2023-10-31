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
	
	<h3>[시장조회]</h3>
	
	<table id="listTable">
		<tr>
			<th>시장번호:</th>
			<th>시장명:</th>
			<th>구분:</th>
			<th>주소:</th>
			<th>위도:</th>
			<th>경도:</th>
			<th>화장실 유무:</th>
			<th>주차 가능 유무:</th>
			<th>전화번호:</th>
			<th>업데이트 일자:</th>
		</tr>
	<c:forEach items="${marketList}" var="market" varStatus="status">

		<tr>
			<td>${status.count}</td>
			<td>${market.mname}</td>
			<td>${market.mtype}</td>
			<td>${market.maddr}</td>
			<td>${market.mlat}</td>
			<td>${market.mlng}</td>
			<td>${market.mtoilet}</td>
			<td>${market.mparking}</td>
			<td>${market.mtel}</td>
			<td>${market.mupdateday}</td>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>
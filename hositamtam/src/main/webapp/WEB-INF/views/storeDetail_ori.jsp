<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="navi.jsp" %>

	<h2>호시탐탐의 점포 상세 페이지입니다</h2>

<!-- 	<form method="GET" action="/finalProject/views/storeDetail"> -->

		<h3>[시장조회]</h3>

		<table id="listTable">
			<tr>
				<th>등록자 닉네임</th>
				<th>점포명</th>
				<th>점포형태</th>
				<th>결제방식</th>
				<th>취급품목</th>
				<th>경도:</th>
				<th>화장실 유무:</th>
				<th>주차 가능 유무:</th>
				<th>전화번호:</th>
				<th>업데이트 일자:</th>
<!-- 				닉네임 평점, 가게형태, 결제방식, 취금품목, 사진, 총 리뷰수 -->
			</tr>
			<c:forEach items="${storeDetail}" var="market" varStatus="status">

				<tr>
					<td>${store.id}</td>
					<td>${store.sname}</td>
					<td>${store.stype}</td>
					<td>${store.payno}</td>
					<td>${store.mlng}</td>
					<td>${store.mtoilet}</td>
					<td>${store.mparking}</td>
					<td>${store.mtel}</td>
					<td>${store.mupdateday}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>
					<button>상세페이지로</button> <input type="hidden" id="name" name="name"
					value="${testModel.storeName}">
				</td>
			</tr>
		</table>
		
		
		<%@ include file="footer.jsp" %>
</body>
</html>
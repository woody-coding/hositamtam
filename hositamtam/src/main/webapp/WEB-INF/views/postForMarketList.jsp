<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>postForMarketList.jsp</title>
</head>
<body>
	<h3>호심탐탐의 시끌시끌 페이지 입니다(시장 선택 안되었을 때)</h3>
	<button id="whatMarket">시장 선택하기</button>
	<hr/>
	
	<form method="GET" action="/finalProject/views/postMain">
	<c:forEach items="${marketList}" var="market">
	<label for="mno"></label>
	<button id="mno" name="mno" value="${market.mno}">"${market.mname}"</button>
	</c:forEach>
	</form>
	<hr>
	<h4>시장을 선택해주세요!</h4>
	
</body>
</html>
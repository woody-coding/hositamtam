<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>post.jsp</title>
</head>
<body>
	<h3>호심탐탐의 시끌시끌 페이지 입니다</h3>
	<button id="whatMarket">시장 선택하기</button>
	<hr/>
	
	<form method="GET" action="/finalProject/views/postMain">
	<c:forEach items="${marketList}" var="market">
	<label for="mno"></label>
	<button id="mno" name="mno" value="${market.mno}">"${market.mname}"</button>
	</c:forEach>
	</form>
	<br/>
	<br/>

	<form method="GET" action="/finalProject/views/postHot">
	<label for="pCategory"></label>
	<button id="pCategory" name="pCategory" value="">인기글</button>
	</form>
	
	<form  method="GET" action="/finalProject/views/postCategory">
	<label for="pCategory"></label>
	<button id="pCategory" name="pCategory" value="궁금해요">궁금해요</button>
	<button id="pCategory" name="pCategory" value="도와주세요">도와주세요</button>
	<button id="pCategory" name="pCategory" value="소통해요">소통해요</button>
	<button id="pCategory" name="pCategory" value="시장소식">시장소식</button>
	</form>
	<hr/>
	
</body>
</html>
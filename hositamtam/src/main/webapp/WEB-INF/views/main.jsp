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
	<a href="/finalProject/views/store">가게(임시)</a>
	<a href="/finalProject/views/storeDetail">가게상세(임시)</a>
	<form method="GET" action="/finalProject/views/marketSearch">
	<label for=keyword></label>
	<input type="text" id="keyword" name="keyword" value="">
	<input type="submit" value="시장이름으로 검색">
	</form>
	
	<hr/>
	<h2>호시탐탐의 메인 페이지입니다</h2> 
	<hr/>
	
	<form method="GET" action="/finalProject/views/market">
	<button>부산 전체 시장</button>
	</form>
	<form method="POST" action="/finalProject/views/marketCategory">
	<label for="cateno"></label>
	<button id="cateno" name="cateno" value="1">농산물</button>
	<button id="cateno" name="cateno" value="2">음식점</button>
	<button id="cateno" name="cateno" value="3">가공식품</button>
	<button id="cateno" name="cateno" value="4">수산물</button>
	<button id="cateno" name="cateno" value="5">축산물</button>
	<button id="cateno" name="cateno" value="6">가정용품</button>
	<button id="cateno" name="cateno" value="7">의류</button>
	<button id="cateno" name="cateno" value="8">신발</button>
	<button id="cateno" name="cateno" value="9">기타</button>
	</form>
</body>
</html>
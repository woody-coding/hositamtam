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
	<h3>호심탐탐의 시끌시끌 메인 페이지 입니다</h3>
	<h3>시장번호 ${post.ptitle}의 페이지 입니다</h3>
	<button id="whatMarket">시장 선택하기</button>
	<hr/>
	<div id="markets"></div>
	<hr/>
	<form  method="GET" action="/finalProject/views/postCatrgoryList">
	<label for="pCategory"></label>
	<button id="pCategory" name="pCategory" value="hot">인기글</button>
	<button id="pCategory" name="pCategory" value="que">시장질문</button>
	<button id="pCategory" name="pCategory" value="acc">사건사고</button>
	<button id="pCategory" name="pCategory" value="day">일상</button>
	<button id="pCategory" name="pCategory" value="lost">실종/분실</button>
	</form>
	<hr/>
	
	<table id="postlistTable">
		<tr>
			<th>번호:</th>
			<th>제목:</th>
			<th>내용:</th>
			<th>작성일자:</th>
			<th>사진:</th>
			<th>좋아요 수:</th>
			<th>작성자:</th>
			<th>댓글 수:</th>
			<th>카테고리:</th>
		</tr>
	<c:forEach items="${postList}" var="post" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${post.ptitle}</td>
			<td>${post.pcontent}</td>
			<td>${post.pregdate}</td>
			<td>${post.pphoto}</td>
			<td>${post.plikecount}</td>
			<td>${post.nickname}</td>
			<td>${post.countcomments}</td>
			<td>${post.pcategory}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
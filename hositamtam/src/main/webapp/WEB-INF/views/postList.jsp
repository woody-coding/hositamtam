<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>postList.jsp</title>
</head>
<body>
	<h3>호심탐탐의 시끌시끌 ${market.mname} 페이지 입니다</h3>
	<button id="whatMarket">시장 선택하기</button>
	<br/>
	<form method="GET" action="/finalProject/views/toPostUpdate">
	<label for="mno"></label>
	<button>글 등록</button>
	<input type="hidden" id="mno" name="mno" value="${market.mno}">
	</form>
	<hr/>
	<form method="GET" action="/finalProject/views/postMain">
	<label for="mno"></label>
	<button>전체글</button>
	<input type="hidden" id="mno" name="mno" value="${market.mno}">
	</form>
	<br/>
	<form method="GET" action="/finalProject/views/postHot">
	<label for="mno"></label>
	<button>인기글</button>
	<input type="hidden" id="mno" name="mno" value="${market.mno}">
	</form>
	<br/>
	<form  method="GET" action="/finalProject/views/postCategory">
	<label for="pCategory"></label>
	<button id="pCategory" name="pCategory" value="궁금해요">궁금해요</button>
	<button id="pCategory" name="pCategory" value="도와주세요">도와주세요</button>
	<button id="pCategory" name="pCategory" value="소통해요">소통해요</button>
	<button id="pCategory" name="pCategory" value="시장소식">시장소식</button>
	<input type="hidden" id="mno" name="mno" value="${market.mno}">
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
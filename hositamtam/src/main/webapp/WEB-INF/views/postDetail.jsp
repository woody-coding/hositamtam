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

	<%@ include file="navi.jsp" %>
	
	<c:forEach items="${post}" var="post">	
	<div>${post.pno}</div>
	<div>${post.id}</div>
	<h2>호심탐탐 시끌시끌의 게시글 상세 페이지입니다.</h2>
	<hr/><br/>
	<div>커뮤니티 > ${post.pcategory}</div>
	<br/>
	<br/>
	<h4>${post.ptitle}</h4>
	<div>${market.mname}</div>
	<br/>
	<br/>
	<div><b>${post.nickname}</b></div>
	<div>${post.pregdate}</div>
	<br/>
	<br/>
	<div>
		${post.pcontent}
	</div>
	<br/>
	<div>${post.pphoto}</div>
	<br/>
	<hr/>
	<div>좋아요 수 : ${post.plikecount}</div>
	<div>댓글 수 : ${post.countcomments}</div>
	<form method="POST" action="/finalProject/views/InsertComment">
	<label for="pno"></label>
	<input type="hidden" name="pno" value="${post.pno}">
	<label for="id"></label>
	<input type="hidden" name="id" value="${post.id}">
	<label for="ccontent">댓글</label>
	<input type="text" name="ccontent" placeholder="댓글을 남겨보세요.">
	<input type="submit" value="등록">
	</form>
	<c:forEach items="${commentList}" var="comment">	
	<br/>
	<br/>
	<div><b>${comment.cnickname}</b></div>
	<div>${comment.ccontent}</div>
	<div>${comment.cregdate}</div>
	<hr/>
	</c:forEach>
	</c:forEach>
	
	<%@ include file="footer.jsp" %>
</body>
</html>
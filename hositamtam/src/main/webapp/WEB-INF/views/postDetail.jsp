<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Favicon -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

<!-- G-Market Fonts -->
<link href="https://webfontworld.github.io/gmarket/GmarketSans.css"
	rel="stylesheet" />

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/89490613c7.js"
	crossorigin="anonymous"></script>
<!-- CSS -->
<link rel="stylesheet" href="../css/loginHeader.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/postMain.css" />
<!-- JavaScript -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>

​
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src=".../js/plikecountTest.js"></script>
</head>
<body>	

	<%@ include file="navi.jsp" %>
	<div  class="section" id="section1">
	<div class="container mt-5">
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
	<div id="plikecountUpdate">좋아요 수 : ${post.plikecount}</div>
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
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
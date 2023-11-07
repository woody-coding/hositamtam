<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>postList.jsp</title>
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
	<script src="../js/postMain.js"></script>
	<script src="../js/postList.js"></script>
</head>
<body>
	<%@ include file="navi.jsp" %>
	<div  class="section" id="section1">
	<div class="container mt-5">
	<div id="mkNameBox">
	<span id="mkName">호심탐탐의 시끌시끌 ${market.mname} 페이지 입니다</span>
	<button id="whatMarket">시장 선택하기</button>
	<div class="mkNameList">
	<form method="GET" action="/finalProject/views/postMain">
	<c:forEach items="${marketList}" var="market">
	<label for="mno"></label>
	<button id="mno" name="mno" value="${market.mno}">${market.mname}</button>
	</c:forEach>
	</form>
	</div>
	</div>
	<br/>
	<form method="GET" action="/finalProject/views/toPostUpdate" id="insertPost">
	<label for="mno"></label>
	<button id="writePost"><i class="bi bi-pencil-square"></i>글 등록</button>
	<input type="hidden">
	</form>
	<hr/>
	<form method="GET" action="/finalProject/views/postMain">
	<label for="mno"></label>
	<button>전체글</button>
	<input type="hidden" name="mno" value="${market.mno}">
	</form>
	<br/>
	<form method="GET" action="/finalProject/views/postHot">
	<label for="mno"></label>
	<button>인기글</button>
	<input type="hidden" name="mno" value="${market.mno}">
	</form>
	<br/>
	<div class="list row">
	<form  method="GET" action="/finalProject/views/postCategory">
	<label for="pCategory"></label>
	<button id="pCategory" name="pCategory" value="궁금해요">궁금해요</button>
	<button id="pCategory" name="pCategory" value="도와주세요">도와주세요</button>
	<button id="pCategory" name="pCategory" value="소통해요">소통해요</button>
	<button id="pCategory" name="pCategory" value="시장소식">시장소식</button>
	<input type="hidden" name="mno" value="${market.mno}">
	</form>
	</div>
	
	<table id="postlistTable">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th class="pcontent">내용</th>
			<th>작성일자</th>
			<th>좋아요</th>
			<th>작성자</th>
			<th>댓글</th>
			<th>카테고리</th>
			
		</tr>
	<c:forEach items="${postList}" var="post" varStatus="status">
		<tr >
			<td id="${post.pno}">${status.count}</td>
			<td id="${post.pno}">${post.ptitle}</td>
			<td id="${post.pno}">${post.pcontent}</td>
			<td id="${post.pno}">${post.pregdate}</td>
			<td id="${post.pno}">${post.plikecount}</td>
			<td id="${post.pno}">${post.nickname}</td>
			<td id="${post.pno}">${post.countcomments}</td>
			<td id="${post.pno}">${post.pcategory}</td>
			<th id="postOrHide">⁝</th>

		</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>
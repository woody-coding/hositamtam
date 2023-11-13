<%@page import="model.PostDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String userId = (String)session.getAttribute("userId"); 
	String url = "/finalProject/views/toPostUpdate";
%>

<!DOCTYPE html>
<html>
<head>
<title>호시탐탐</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Favicon -->
<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />

<!-- G-Market Fonts -->
<link href="https://webfontworld.github.io/gmarket/GmarketSans.css"
	rel="stylesheet" />

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/89490613c7.js"
	crossorigin="anonymous"></script>
<!-- CSS -->
<link rel="stylesheet" href="../css/whiteBgHeader.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/postMain.css" />
<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="../js/postMain.js"></script>
<script src="../js/postList.js"></script>
</head>

	<body>
		<%@ include file="navi.jsp"%>
	<div class="section" id="section1">
		<div class="container mt-5">
			<div id="mkNameBox">
				<span id="mkName">${market.mname} 시끌시끌</span>
				
			</div>
			<br />
			<form method="GET" action="/finalProject/views/toPostInsert" id="insert">
				<label for="mno"></label>
				<button id="writePost">
					글쓰기
				</button>
				<input type="hidden" name="mno" value="${market.mno}">
			</form>
			<br>

			<div class="list">
				<form method="GET" action="/finalProject/views/postMain">
					<label for="mno"></label>
					<button class="tab-button " >전체글</button>
					<input type="hidden" name="mno" value="${market.mno}">
				</form>

				<form method="GET" action="/finalProject/views/postHot">
					<label for="mno"></label>
					<button class="tab-button" >인기글</button>
					<input type="hidden" name="mno" value="${market.mno}">
				</form>

				<form method="GET" action="/finalProject/views/postCategory">
					<label for="pCategory"></label>
					<button  class="tab-button" name="pCategory" value="궁금해요" >궁금해요</button>
					<button  class="tab-button" name="pCategory" value="도와주세요" >도와주세요</button>
					<button  class="tab-button" name="pCategory" value="소통해요">소통해요</button>
					<button class="tab-button" name="pCategory" value="시장소식"> 시장소식</button>
					<input type="hidden" name="mno" value="${market.mno}">
				</form>
			</div>

			<table id="postlistTable">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성일자</th>
					<th>좋아요</th>
					<th>작성자</th>
					<th>댓글 수</th>
					<th>카테고리</th>
					<th></th>

				</tr>
				<c:forEach items="${postList}" var="post" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td class="pTitle"><a href="/finalProject/views/toPostDetail?pno=${post.pno}">${post.ptitle}</a></td>
						<td class="pContent"><a href="/finalProject/views/toPostDetail?pno=${post.pno}">${post.pcontent}</a></td>
						<td>${post.pregdate}</td>
						<td>${post.plikecount}</td>
						<td>${post.nickname}</td>
						<td>${post.countcomments}</td>
						<td>${post.pcategory}</td>
						<c:if test="${post.id != userId}">
						<td></td>
						</c:if>
						<c:if test="${post.id == userId}">
						<th><button class="modify" type="button" onclick="location.href='/finalProject/views/toPostUpdate?mno=${market.mno}&pno=${post.pno}'">글수정</button></th>
						</c:if>
					</tr>
				</c:forEach>
				<%-- <tfoot> 페이지네이션 되면 쓸것
                    <tr>
                        <td colspan="4">
                        	<c:if test="${searchList.currentPage > 1}">
                        		<a href="main.jsp?command=search&query=${searchList.query}&page=${searchList.currentPage - 1}"><i class="bi bi-caret-left-fill"></i></a>
                        	</c:if>
                        	
		                    <c:if test="${searchList.currentPage < searchList.maxPageNum}">
                        		<a href="main.jsp?command=search&query=${searchList.query}&page=${searchList.currentPage + 1}"><i class="bi bi-caret-right-fill"></i></a>
                        	</c:if>
                        	
                        </td>
                    </tr>
                </tfoot> --%>
			</table>
			
		</div>

	</div>
	<%-- <%@ include file="footer.jsp"%> --%>
</body>
</html>
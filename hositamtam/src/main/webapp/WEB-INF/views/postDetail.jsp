<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String userId = (String)session.getAttribute("userId"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>호시탐탐</title>
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
<link rel="stylesheet" href="../css/postDetail.css" />
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<script>
		function init() {
			
			let pno = '<%= request.getAttribute("pno") %>';
			
			pno = (pno === 'null') ? null : pno;
		    
		    console.log('pno : ' + pno + '    /    typeof : ' + typeof(pno));
		    
		    // 로컬 스토리지에 저장되어 있는 데이터를 가져옴

	        window.localStorage.removeItem('postNumber');
  	
            const postNum = { pno: pno };
            const postNumber = JSON.stringify(postNum);
            window.localStorage.setItem('postNumber', postNumber);
	     
		}
		window.addEventListener('load', init);
	</script>
<script src="../js/plikecountUpdate.js"></script>
</head>
<body>	

	<%@ include file="navi.jsp" %>
	<div  class="section" id="section1">
	<div class="container mt-5">	
	<!-- <div class="col-1"><button onclick="javascript:history.back()">목록으로</button></div> 위치 재설정 및 css로 꾸며야 함
	<br/> -->
	<span>커뮤니티 > ${post.pcategory}</span>
	<hr/>
	
	
	<div class="postHeader">
	<div>
	<h4>${post.ptitle}</h4>
	${market.mname} | ${post.nickname} | ${post.pregdate}
	
	<button id="plikecountUpdate"><i class="fa-solid fa-heart"></i></button>
	<span class="likeComment"><span id="plikecountView"></span> | 댓글  ${post.countcomments}</span>
	
	</div>
	
	</div>
	<hr/>
	<div>
		${post.pcontent}
	</div>
	<br/>
	<c:if test="${post.pphoto != null}">
	<img src="${post.pphoto}" style="width: 400px; height: 400px;"></img>
	</c:if>
	<br/>
	<p id="msg"></p>
	<hr/>
	<div class="commentInsert row">
	<form method="POST" action="/finalProject/views/InsertComment">
	<label for="pno"></label>
	<input type="hidden" name="pno" value="${post.pno}">
	<label for="id"></label>
	<input  type="hidden" name="id" value="${userId}">
	<label class="col-2"  for="ccontent">댓글</label>
	<input class="col-8" type="text" name="ccontent" id="commentContent" placeholder="댓글을 남겨보세요.">
	<input class="col-1"  type="submit" id="commentInsert" value="등록">
	</form>
	</div>
	<c:forEach items="${commentList}" var="comment" varStatus="status">		
	
	<div class="commentBox row">
	<div class="col-1">${status.count}</div>
	<div class="col-1">${comment.cnickname}</div>
	<div class="col-6">${comment.ccontent}</div>
	<div class="col-2">${comment.cregdate}</div>
	<c:if test="${post.id == userId}">
	<div class="col-1"><button class="modify" onclick="location.href='/finalProject/views/deleteComment?cno=${comment.cno}&pno=${post.pno}'">삭제</button></div>
	</c:if>
	</div>
	</c:forEach>
	</div>
	</div><hr/>
	<%@ include file="footer.jsp" %>
</body>
</html>
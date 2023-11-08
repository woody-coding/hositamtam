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
<link rel="stylesheet" href="../css/postDetail.css" />
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
	<c:forEach items="${post}" var="post">	
	<h2>${market.mname}의 게시글 상세 페이지입니다.</h2>
	<hr/>
	<div>커뮤니티 > ${post.pcategory}</div>
	<hr/>
	
	
	<div class="postHeader">
	<div>
	<h4>${post.ptitle}</h4>
	${market.mname} | ${post.nickname} | ${post.pregdate}
	
	<button id="plikecountUpdate"><i class="fa-solid fa-heart"></i></button>
	<span class="likeComment"><span id="plikecountView">좋아요 ${post.plikecount}개</span> | 댓글  ${post.countcomments}</span>
	
	</div>
	
	</div>
	<hr/>
	<div>
		${post.pcontent}
	</div>
	<br/>
	<img src="${post.pphoto}"></img>
	<br/>
	<hr/>

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
	<div class="commentBox">
	<div>${comment.cnickname}</div>
	<div>${comment.ccontent}</div>
	<div>${comment.cregdate}</div>
	
	</div>
	<hr/>
	</c:forEach>
	</c:forEach>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
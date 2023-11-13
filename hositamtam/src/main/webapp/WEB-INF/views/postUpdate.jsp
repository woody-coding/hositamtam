<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" href="../css/postHeader.css" />
<link rel="stylesheet" href="../css/footer.css" />

<link rel="stylesheet" href="../css/postUpdate.css" />
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
<script  src ="/finalProject/js/postMain.js"></script>	

</head>
<body>
	<%@ include file="navi.jsp" %>
	<div  class="section" id="section1">
	<div class="container mt-5">
	<p id="msg"></p>
<!-- 	<div class="col-1"><button type="button" onclick="javascript:history.back()">목록으로</button></div> 위치 재설정 및 css로 꾸며야 함
	<br/> -->
	<form method="POST" action="/finalProject/views/postUpdate" enctype="multipart/form-data">
	<div class="postCategory-radio">
	            <div class="radioBox">
                <label for="pcategory">카테고리</label><br/>
				<select  id="pcategory" name="pcategory">
                  <option value="궁금해요"  ${post.pcategory eq '궁금해요' ? 'selected' : ''}>궁금해요</option>
                  <option value="도와주세요" ${post.pcategory eq '도와주세요' ? 'selected' : ''}>도와주세요</option>
                  <option value="소통해요" ${post.pcategory eq '소통해요' ? 'selected' : ''}>소통해요</option>
                  <option value="시장소식" ${post.pcategory eq '시장소식' ? 'selected' : ''}>시장소식</option>
                </select>
      
                  </div> 
	</div>
	 <div class="postHead row">
	<label for="ptitle">제목</label>
	<input type="text" name="ptitle" value="${post.ptitle}" id="ptitle" placeholder="제목을 입력하세요" />
	</div> 
	
     <div class="postContent row">
	<label for="pcontent">내용</label>
	<textarea name="pcontent" id="myTextarea" placeholder="내용을 입력하세요" >${post.pcontent}</textarea>
	</div>  
	
	<label for="pphoto">사진</label>
		<input type="file" name="pphoto" value="${post.pphoto}" />
	<label for="mno"></label>
		<input type="hidden" name="mno" value="${market.mno}"/>
	<label for="id"></label>
		<input type="hidden" name="id" value="${userId}"/>
	<label for="pno"></label>
		<input type="hidden" name="pno" value="${post.pno}"/>
	<div class=btnBox>
	<input class="modify" type="submit" value="수정하기">
		<input class="submit" type="button" onclick="location.href='/finalProject/views/deletePost?pno=${post.pno}&mno=${market.mno}'" value="삭제하기">
	
	</div>
	</form>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
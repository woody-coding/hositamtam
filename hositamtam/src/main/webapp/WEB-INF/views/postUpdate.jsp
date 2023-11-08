<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>postUpdate.jsp</title>
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
<!-- <link rel="stylesheet" href="../css/postMain.css" /> -->
<link rel="stylesheet" href="../css/postUpdate.css" />
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
<script  src ="../js/postMain.js"></script>	

</head>
<body>
	<%@ include file="navi.jsp" %>
	<div  class="section" id="section1">
	<div class="container mt-5">
	
	<h2>${market.mname}의 게시글 등록 페이지입니다.</h2>

	<hr/>
	<form method="POST" action="/finalProject/views/postUpdate" enctype="multipart/form-data">
	<div class="postCategory-radio">

	<!-- <label for="pcategory">카테고리</label> --> 	
	<!-- <input class="radioBox" type="radio" name="pcategory" value="궁금해요" />궁금해요
	<input class="radioBox" type="radio" name="pcategory" value="도와주세요" />도와주세요
	<input class="radioBox" type="radio" name="pcategory" value="소통해요" />소통해요
	<input class="radioBox" type="radio" name="pcategory" value="시장소식" />시장소식
	 -->
	              <div class="radioBox">
                  <label for="pcategory"><input type="radio" name="pcategory" value="시장질문" />시장질문</label>


                  </div>
                   <div class="radioBox">
                  <label><input type="radio" name="pcategory" value="사건사고">사건사고</label>

                  </div>
                  <div class="radioBox">
                  <label><input type="radio" name="pcategory" value="일상">일상</label>

                  </div>
                  <div class="radioBox">
                    <label><input type="radio" name="pcategory" value="실종/분실">실종/분실</label>

                  </div> 
	</div>
	 <div class="postHead row">
	<label for="ptitle">제목</label>
	<input type="text" name="ptitle" placeholder="제목을 입력하세요" />
	</div>
	 <div class="postContent row">
	<label for="pcontent">내용</label>
	<textarea  name="pcontent" placeholder="내용을 입력하세요" ></textarea>
	</div>
	<label for="pphoto">사진</label>
	<input type="file" name="pphoto" placeholder="내용을 입력하세요" />
	<br>
	<label for="mno"></label>
	<input type="hidden" name="mno" value="${market.mno}"/>
	<label for="id"></label>
	<input type="hidden" name="id" value="${userId}"/>
	<input class="btn btn-primary" type="submit" value="등록하기">
	</form>
	</div>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>
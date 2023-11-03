<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>postUpdate.jsp</title>
</head>
<body>
	<h2>${market.mname}의 게시글 등록 페이지입니다.</h2>
	<form method="POST" action="/finalProject/views/postUpdate">
	<label for="pcategory">카테고리</label> 	
	<input type="radio" name="pcategory" value="궁금해요" />궁금해요
	<input type="radio" name="pcategory" value="도와주세요" />도와주세요
	<input type="radio" name="pcategory" value="소통해요" />소통해요
	<input type="radio" name="pcategory" value="시장소식" />시장소식
	<br>
	<label for="ptitle">제목</label>
	<input type="text" name="ptitle" placeholder="제목을 입력하세요" />
	<br>
	<label for="pcontent">내용</label>
	<input type="text" name="pcontent" placeholder="내용을 입력하세요" />
	<br>
	<label for="pphoto">사진</label>
	<input type="file" name="pphoto" placeholder="내용을 입력하세요" />
	<br>
	<label for="mno"></label>
	<input type="hidden" name="mno" value="${market.mno}"/>
	<label for="id"></label>
	<input type="hidden" name="id" value="${userId}"/>
	<input type="submit" value="등록하기">
	</form>
</body>
</html>
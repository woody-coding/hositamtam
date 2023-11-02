<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>postUpdate.jsp</title>
</head>
<body>
	<h2>${mname}의 게시글 등록 및 수정 페이지입니다.</h2>
	<form method="POST" action="/finalProject/views/postUpdate">
	<label for="pCategory">카테고리</label> 	
	<input type="radio" name="pCategory" value="궁금해요" />궁금해요
	<input type="radio" name="pCategory" value="도와주세요" />도와주세요
	<input type="radio" name="pCategory" value="소통해요" />소통해요
	<input type="radio" name="pCategory" value="시장소식" />시장소식
	<br>
	<label for="pTitle">제목</label>
	<input type="text" name="pTitle" value="" placeholder="제목을 입력하세요" />
	<br>
	<label for="pContent">내용</label>
	<input type="text" name="pContent" value="" placeholder="내용을 입력하세요" />
	<br>
	<label for="pPhoto">사진</label>
	<input type="file" name="pPhoto" value="" placeholder="내용을 입력하세요" />
	<br>
	<input type="submit" value="등록하기">
	
	
	</form>
</body>
</html>
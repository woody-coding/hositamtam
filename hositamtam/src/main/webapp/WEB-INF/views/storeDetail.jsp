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

	<h2>${testModel.storeName}의 점포 상세 페이지입니다</h2>

	<table>
			<tr>
				<th>점포번호:</th>
				<td>${testModel.no}</td>
			</tr>
			<tr>
				<th>점포명:</th>
				<td>${testModel.name}</td>
			</tr>
		</table>
</body>
</html>
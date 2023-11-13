<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="stylesheet" href="../css/postForMarketList.css" />
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

	<div class="section" id="section1">
		<div class="container mt-5">
			<h3>시끌시끌</h3>
			<button id="whatMarket">시장 선택하기</button>
		
			<div class="mkNameList">
				<table>
<!-- 					<tr> -->
<!-- 						<th>ㄱ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
							
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㄴ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㄷ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㄹ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅁ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅂ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅅ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅇ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅈ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅊ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅋ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅌ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅍ - </th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>ㅎ - </th> -->
<!-- 					</tr> -->
					<tr>
						<td>
							<form class="market_form" method="GET" action="/finalProject/views/postMain">
								<c:forEach items="${marketList}" var="market">
									<label for="mno"></label>
									<button id="mno" name="mno" value="${market.mno}" class="flex-container">${market.mname}</button>
								</c:forEach>
							</form>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="firstShow"></div>
		</div>
	</div>

	<%-- <%@ include file="footer.jsp"%> --%>
</body>
</html>
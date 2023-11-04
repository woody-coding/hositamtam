<%@ page contentType="text/html; charset=UTF-8"%>

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 --%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>호시탐탐</title>

<!-- Favicon -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

<!-- G-Market Fonts -->
<link href="https://webfontworld.github.io/gmarket/GmarketSans.css"
	rel="stylesheet" />

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/89490613c7.js"
	crossorigin="anonymous"></script>


<!-- JavaScript -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
	<script type="text/javascript"
	src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
	

<!-- CSS -->
<link rel="stylesheet" href="../css/loginHeader.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/marketList.css" />


</head>

<body>
	 <%@ include file="navi.jsp"%>
 
	<!--  -->
	<!-- <div id="map" style=></div> -->
	
	<div id="map" style="width: 70%; height: 90%;"></div>
	<div id="howGetMarket"></div><br/>
	<div id="marketErrorMsg"></div>
	<div id="marketContent"></div>
	
	<!-- <div class="section">
		<div class="listCategory">
			<div class="close"></div>
			<div class="listCategoryContent">

				<form method="POST" action="/finalProject/views/marketCategory">

					<p>어떤 시장이 궁금한가요?</p>
					<div class="goCategory">
						<a href="market.jsp?cateno=1">농산물</a>
						marketTest.jsp? 경로 바꿔야됨
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=2">음식점</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=3">가공식품</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=4">수산물</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=5">축산물</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=6">가정용품</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=7">의류</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=8">신발</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=9">기타</a>
					</div>

				</form>
			</div>
		</div>

		
		<div id="mkSearchName"></div>
		<div id="marketErrorMsg"></div>
		<div id="marketContent"></div>

		<div id="mkListResult" class="row"></div>
			
			
			
	</div> -->
<%-- <div class="mkcontainer" class="row">


				<div class="col-9">
					<p id="mkName">${market.mname}</p>
					<p id="mkaddr">${market.maddr}</p>
					<span>화장실 <span id="isToilet">${market.mtoilet}, </span>주차장
						<span id="isParking">${market.mparking} </span></span>
				</div>
			</div>
			
		</c:forEach></div>
		<!--  <div id="mkSearchName">부평으로 검색된 결과입니다.</div> -->

		<!-- <table id="listTable">
		<tr>
			<th>시장번호:</th>
			<th>시장명:</th>
			<th>구분:</th>
			<th>주소:</th>
			<th>위도:</th>
			<th>경도:</th>
			<th>화장실 유무:</th>
			<th>주차 가능 유무:</th>
			<th>전화번호:</th>
			<th>업데이트 일자:</th>
		</tr>
		</table> -->
		
<%-- <tr>
			<td>${status.count}</td>
			<td>${market.mname}</td>
			<td>${market.mtype}</td>
			<td>${market.maddr}</td>
			<td>${market.mlat}</td>
			<td>${market.mlng}</td>
			<td>${market.mtoilet}</td>
			<td>${market.mparking}</td>
			<td>${market.mtel}</td>
			<td>${market.mupdateday}</td>
		</tr> --%>

		<!-- <div class="mkcontainer" class="row">

            <div id="markerImg" class="col-3">
                <img src="../images/마커.png" width="80px">
            </div>
            <div class="col-9">
                <p id="mkName">${market.mname}</p>
                <p id="mkaddr">${market.maddr}</p>
                <span>화장실 <span id="isToilet">${market.mtoilet}, </span>주차장 <span id="isParking"></td>
			<td> </span></span>
            </div>
        </div>
        <div class="mkcontainer" class="row">

            <div id="markerImg" class="col-3">
                <img src="../images/마커.png" width="80px">
            </div>
            <div class="col-9">
                <p id="mkName">자갈치시장</p>
                <p id="mkaddr">자갈치해안로 52</p>
                <span>화장실 <span id="isToilet">O, </span>주차장 <span id="isParking">O </span></span>
            </div>
        </div> -->
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- CSS -->
<link rel="stylesheet" href="../css/loginHeader.css" />
<!-- <link rel="stylesheet" href="../css/login.css" /> -->
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/marketList.css" />


<!-- JavaScript -->
<!-- 경인키 51l0xj0874 -->
<!-- 동영키 e9fw481dyk 5502 동작-->
<script type="text/javascript"
	src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script defer src="../js/marketList.js"></script>
</head>

<body>
	<%@ include file="navi.jsp"%>

	<!--  -->
	<div class="section">


		<div id="map"></div>
		<div id="mkListResult" class="row">
		<c:forEach items="${marketList}" var="market" varStatus="status">
			<div class="mkcontainer" class="row">

				<div id="markerImg" class="col-3">
					<img src="../images/마커.png" width="80px">
				</div>
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

	<!--  <footer id="information" class="section">
        <div class="information__located">
          <div class="max-container">
            <h2 class="information__title">&copy; TMI - All rights reserved</h2>
            <div class="information__contents">
              <p class="information__title">
                Creator <br />팀장: 안효철 &nbsp;&nbsp; 팀원: 김동영 <br />
                팀원: 김진성 &nbsp;&nbsp; 팀원: 남경인 <br />
                팀원: 석신성 &nbsp;&nbsp; 팀원: 주영진
              </p>
              <p class="information__title">
                <br />
                <i class="fa-brands fa-github"></i>
                https://github.com/wlstjd3398/TMI.git
              </p>
            </div>
          </div>
        </div>
      </footer> -->
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>
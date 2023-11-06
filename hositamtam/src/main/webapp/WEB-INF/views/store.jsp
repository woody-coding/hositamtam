<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>점포리스트</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
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
<link rel="stylesheet" href="../css/storeList.css" />
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
     <script defer src="../js/storeList.js"></script>
</head>
<body>

    <%@ include file="navi.jsp" %>
    
<div class="section">
    <div class="app">
        <div id="map" >
          <button id="insertStore" class="plusStore"><i class="bi bi-plus-circle-fill"></i></button>

        </div>
        
        <div class="right">
            <div class="searchResultName">
                <h4>부평깡통시장</h4>
                <button id="toPost">시끌시끌</button>
            </div>
            <div class="btnSort">
                <button>리뷰 많은순</button><button>별점 높은 순</button><button>찜 많은 순</button>
            </div>
            <div id="mkListResult" class="row">
                <div class="mkcontainer" class="row">
                    <div id="mkImg" class="col-3">
                        <img src="../images/거인통닭.jpg" >
                    </div>
                    <div class="col-9">
                        <p id="mkName">거인통닭</p>
                        <p id="mkaddr">부평1길 39</p>
                        
                        <img src="../images/review.png">리뷰개수
                        <img src="../images/2b50.png" >별점
                        
                        <i class="fa-solid fa-heart"></i>찜개수
                    </div>
                    
                </div>
                <div class="mkcontainer" class="row">
                  <div id="mkImg" class="col-3">
                      <img src="../images/거인통닭.jpg" >
                  </div>
                  <div class="col-9">
                      <p id="mkName">거인통닭</p>
                      <p id="mkaddr">부평1길 39</p>
                      
                      <img src="../images/review.png" alt="">리뷰개수
                      <img src="../images/2b50.png" alt="">별점
                      
                      <i class="fa-solid fa-heart"></i>찜개수
                  </div>
                  
                </div>
                <!-- mk -> store 로 바꾸기 -->
                <div class="mkcontainer" class="row">
        
                    <div id="mkImg" class="col-3">
                        <img src="../images/돼지갈비후라이드.jpg">
                    </div>
                    <div class="col-9">
                        <p id="mkName">국제시장</p>
                        <p id="mkaddr">중구로 43</p>
                        <img src="../images/review.png" alt="">리뷰개수
                        <img src="../images/2b50.png" alt="">별점
                        <i class="fa-solid fa-heart"></i>찜개수
                       
                    </div>
                </div>
                <div class="mkcontainer" class="row">
        
                    <div id="mkImg" class="col-3">
                        <img src="../images/이가네떡볶이.jpg" >
                    </div>
                    <div class="col-9">
                        <p id="mkName">자갈치시장</p>
                        <p id="mkaddr">자갈치해안로 52</p>
                        <img src="../images/review.png" alt="">리뷰개수
                        <img src="../images/2b50.png" alt="">별점
                        <i class="fa-solid fa-heart"></i>찜개수
                       
                    </div>
                </div>
            </div>
            
        </div>
        
    </div>
    
      
        
       </div>
       
       
       <%@ include file="footer.jsp" %>
         

	<%-- <form method="GET" action="/finalProject/views/storeDetail">
		<table>
			<tr>
				<th>점포번호:</th>
				<td>${store.sno}</td>
			</tr>
			<tr>
				<th>점포명:</th>
				<td>${store.sname}</td>
			</tr>
			<tr>
				<td>
				<button>상세페이지로</button>
				<input type="hidden" id="name" name="name" value="${testModel.storeName}">
				</td>
			</tr>
		</table>
	</form> --%>

</body>
</html>
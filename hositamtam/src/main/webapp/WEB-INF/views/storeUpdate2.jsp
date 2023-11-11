<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>호시탐탐</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />

    <!-- G-Market Fonts -->
    <link
      href="https://webfontworld.github.io/gmarket/GmarketSans.css"
      rel="stylesheet"
    />

    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/89490613c7.js"
      crossorigin="anonymous"
    ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="../css/loginHeader.css" />
    <link rel="stylesheet" href="../css/footer.css" />
    <link rel="stylesheet" href="../css/storeDetail.css" />

    <!-- JavaScript -->
	<script>
		function init() {
			
			let sno = '<%= request.getAttribute("sno") %>';
			
			sno = (sno === 'null') ? null : sno;
		    
		    console.log('sno : ' + sno + '    /    typeof : ' + typeof(sno));
		    
		    // 로컬 스토리지에 저장되어 있는 데이터를 가져옴

	        window.localStorage.removeItem('storeNumber');
  	
            const storeNum = { sno: sno };
            const storeNumber = JSON.stringify(storeNum);
            window.localStorage.setItem('storeNumber', storeNumber);
	     
		}
	
		window.addEventListener('load', init);
	</script>
    <script src="../js/anchor.js"></script>
    <script src="../js/storeDetailReview.js"></script>
</head>
<body>

<%-- 	<%@ include file="navi.jsp" %> --%>

	<h2>호시탐탐의 점포 수정 페이지입니다</h2>

    <!-- Main -->
    <!-- Store Detail -->
    <section id="storeDetail" class="max-container">
      <div class="storeDetail__info">
        <button class="storeDetail__like">
          <i class="fa-solid fa-heart"></i>
        </button>
        <p id="storeLikeCount"></p>
        
        ${store.nickname} 님이 등록한 점포입니다.
        <button class="storeDetail__modify" onclick="location.href='/finalProject/views/storeUpdate?sno=${store.sno}'">점포 수정</button>
      </div>
      <div class="storeDetail__star__name">
        <span>
          <h4 class="storeDetail__h4 storeDetail__star">
            <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp; ${storeReviewAvg.rating}
            
          </h4>
        </span>
        <span>
          <h4 class="storeDetail__h4 storeDetail__name">
            ${store.sname}
          </h4>
        </span>
      </div>
	<div class="storeDetail__storeType">
	    <span class="storeDetail__storeType__title">
	        <h4 class="storeDetail__h4">점포 형태</h4>
	    </span>
	    <input type="checkbox" name="stype" value="좌판" <c:if test="${store.stype eq '좌판'}">checked="checked"</c:if> disabled><span>좌판</span>
	    <input type="checkbox" name="stype" value="매장" <c:if test="${store.stype eq '매장'}">checked="checked"</c:if> disabled><span>매장</span>
	</div>

      <div class="storeDetail__payment">
        <span class="storeDetail__payment__title">
        <h4 class="storeDetail__h4">결제 방식</h4></span>
        
	<c:forEach var="storePayment" items="${storePaymentList}">
	    <input type="checkbox" name="paytype" value="${storePayment.paytype}" checked="checked" disabled><span>${storePayment.paytype}</span>
	</c:forEach>

        
      </div>
      <div class="storeDetail__category">
        <span class="storeDetail__category__title">
        <h4 class="storeDetail__h4">취급 품목</h4></span>
        <span>${store.scategory}</span>
      </div>
      <div class="storeDetail__photo">
        <!-- 등록된 점포 사진 -->
        <img src="../images/${store.sphoto}" style="width:300px; height: 150px;"/>
      </div>
    </section>

    <!-- Review -->
    <section id="review" class="max-container">
      <!-- <span class="review__level">
          <img src="images/icons8-병아리-60.png" class="review__level__photo" />
        </span> -->
      <div class="review__info">
        
      </div>

    </section>

 
    <!-- Anchor -->
    <aside>
      <a class="storeDetail__arrow-up" href="#storeDetail" title="back to top">
        <div class="arrow-up__icon">
          <i class="fa-solid fa-arrow-up arrowUp__menu"></i>
        </div>
      </a>
    </aside>
		
		
		<%@ include file="footer.jsp" %>
</body>
</html>
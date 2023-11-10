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

	<h2>호시탐탐의 점포 상세 페이지입니다</h2>

    <!-- Main -->
    <!-- Store Detail -->
    <section id="storeDetail" class="max-container">
      <div class="storeDetail__info">
        <button class="storeDetail__like">
          <i class="fa-solid fa-heart"></i><div class="storeDetail__like__count" id="storeLikeCount"></div>
        </button>
        
        ${store.nickname} 님이 등록한 점포입니다.
        <button class="storeDetail__modify">점포 수정</button>
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
        <img src="../images/icons8-병아리-60.png" class="review__level__photo" />
        <span class="review__ask">
          ${memberInfo.nickname}님 해당 점포에 리뷰를 남겨주세요.
        </span>
        <span class="review__star">
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star"></i>
        </span>
      </div>

      <div>
        <form id="review__form" style="display: none">
          <div class="review__form">
            <label class="review__label">
              <input
                class="review__input"
                type="text"
                id=""
                placeholder="리뷰를 남겨주세요."
              />
            </label>
          </div>

          <button class="review__button" type="submit">작성하기</button>
        </form>
      </div>
      <hr class="storeDetail__hr" />
    </section>

    <!-- Review Contents -->
    <section id="review__contents" class="max-container">
      <div class="review__contents__total">
        <span class="review__contents__star">
          <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp;${storeReviewAvg.rating}
        </span>
        <span>
          <i class="fa-regular fa-comment-dots storeDetail__review"></i>&nbsp;${storeReviewAvg.review}
        </span>
      </div>
      
      
      <hr class="storeDetail__hr" />
      
      
      <c:forEach var="storeReview" items="${storeReviewList}">
      	<div class="review__contents__list">
	        <section class="review__contents__photo" style="color: #0188cc">
	          <img
	            src="../images/icons8-caveman-64.png"
	            class="review__contents__img"
	          />
	        </section>
	        <section class="review__contents__main">
	          <div class="review__contents__name">${storeReview.id}</div>
	          <div class="review__contents__ownerInfo">
	            리뷰 ${storeReview.review } &nbsp; 별점평균 ${storeReview.rating} &nbsp; | &nbsp; ${storeReview.rregdate}
	          </div>
	        </section>
	        <section class="review__contents__value">
	          ${storeReview.content}
	        </section>
	      </div>
        	<hr class="storeDetail__hr" />
      </c:forEach>
        
      <!-- 
      <div class="review__contents__list">
        <section class="review__contents__photo" style="color: #0188cc">
          <img
            src="../images/icons8-caveman-64.png"
            class="review__contents__img"
          />
        </section>
        <section class="review__contents__main">
          <div class="review__contents__name">호탐시탐짱입니당</div>
          <div class="review__contents__ownerInfo">
            리뷰 15 &nbsp; 별점평균 4.5 &nbsp; | &nbsp; 23.10.15
          </div>
        </section>
        <section class="review__contents__value">
          샤슬릭이 좋습니다. 샤슬릭이 좋습니다. 샤슬릭이 좋습니다. 샤슬릭이
          좋습니다. 샤슬릭이 좋습니다. 샤슬릭이 좋습니다. 샤슬릭이 좋습니다.
          샤슬릭이 좋습니다. 샤슬릭이 좋습니다. 샤슬릭이 좋습니다. 샤슬릭이
          좋습니다.
        </section>
      </div>
      
      
      <hr class="storeDetail__hr" />
      
      
      <div class="review__contents__list">
        <section class="review__contents__photo" style="color: #662583">
          <img
            src="../images/icons8-explorer-60.png"
            class="review__contents__img"
          />
        </section>
        <section class="review__contents__main">
          <div class="review__contents__name">쩝쩝박사</div>
          <div class="review__contents__ownerInfo">
            리뷰 20 &nbsp; 별점평균 4.0 &nbsp; | &nbsp; 23.10.10
          </div>
        </section>
        <section class="review__contents__value">SIUUUUUU</section>
      </div>
      <hr class="storeDetail__hr" />
      <div class="review__contents__list">
        <section class="review__contents__photo" style="color: #0e4194">
          <img
            src="../images/icons8-spaceman-66.png"
            class="review__contents__img"
          />
        </section>
        <section class="review__contents__main">
          <div class="review__contents__name">시장지박령유망주</div>
          <div class="review__contents__ownerInfo">
            리뷰 245 &nbsp; 별점평균 4.5 &nbsp; | &nbsp; 23.10.04
          </div>
        </section>
        <section class="review__contents__value">
          샤오룽바오가 육즙이 넘치구 속에 고기도 많이 들어서 너무 맛있었어요 ~
        </section>
      </div>
      <hr class="storeDetail__hr" />
 -->
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
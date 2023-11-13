<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String userId = (String)session.getAttribute("userId"); %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>호시탐탐</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="/finalProject/images/favicon.ico" type="image/x-icon" />

    <!-- G-Market Fonts -->
    <link
      href="https://webfontworld.github.io/gmarket/GmarketSans.css"
      rel="stylesheet"
    />
    
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>

    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/89490613c7.js"
      crossorigin="anonymous"
    ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="/finalProject/css/whiteBgHeader.css" />
    <link rel="stylesheet" href="/finalProject/css/footer2.css" />
    <link rel="stylesheet" href="/finalProject/css/storeDetail.css" />

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
    <script defer src="/finalProject/js/storeDetailReview.js"></script>
    <script defer src="/finalProject/js/anchor.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	<%@ include file="navi.jsp" %>
    <!-- Main -->
    <!-- Store Detail -->
    <section id="storeDetail" class="max-container storeDetail">
      <div class="storeDetail__info">
        <span class="storeDetail__title">${store.sname}</span>
        
        <button class="storeDetail__modify">점포 수정</button>
        
        <%-- <h4 class="storeDetail__h4 storeDetail__star">
           <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp; ${storeReviewAvg.rating}
        </h4> --%>
        <!-- <button class="storeDetail__like">
          <i class="fa-solid fa-heart">  </i>
          <span class="storeDetail__like__count" id="storeLikeCount"></span>
        </button> -->
      </div>
	    <div class="storeDetail__star__like">
	        <span class="storeDetail__category__title">
	        	<h4 class="storeDetail__h4 storeDetail__star">
	           		<i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp; <fmt:formatNumber value="${storeReviewAvg.rating}" pattern="0.#"/>
	
	        	</h4>
	        </span>
	        <span>
	        	<button class="storeDetail__like">
		          <i class="fa-solid fa-heart">  </i>
		          <span class="storeDetail__like__count" id="storeLikeCount"></span>
	        	</button>
	        </span>
	      </div>
	      
		<div class="storeDetail__storeType">
		    <span class="storeDetail__storeType__title">
		        <h4 class="storeDetail__h4">점포 형태</h4>
		    </span>
		    <input type="checkbox" name="stype" value="좌판" <c:if test="${store.stype eq '좌판'}">checked="checked"</c:if> disabled><span>좌판</span> &nbsp;&nbsp;&nbsp;
		    <input type="checkbox" name="stype" value="매장" <c:if test="${store.stype eq '매장'}">checked="checked"</c:if> disabled><span>매장</span>
		</div>
	
	      <div class="storeDetail__payment">
	        <span class="storeDetail__payment__title">
	        	<h4 class="storeDetail__h4">결제 방식</h4>
	        </span>
	        
		<c:forEach var="storePayment" items="${storePaymentList}">
		    <input type="checkbox" name="paytype" value="${storePayment.paytype}" checked="checked" disabled><span>${storePayment.paytype}</span> &nbsp;&nbsp;&nbsp;
		    <%-- <input type="checkbox" name="paytype" value="현금" <c:if test="${storePayment.paytype eq '현금'}">checked="checked"</c:if> disabled><span>현금</span>
		    <input type="checkbox" name="paytype" value="카드" <c:if test="${storePayment.paytype eq '카드'}">checked="checked"</c:if> disabled><span>카드</span>
		    <input type="checkbox" name="paytype" value="계좌이체" <c:if test="${storePayment.paytype eq '계좌이체'}">checked="checked"</c:if> disabled><span>계좌이체</span> --%>
		</c:forEach>
	      </div>
	      
	      <div class="storeDetail__category">
	        <span class="storeDetail__category__title">
	        <h4 class="storeDetail__h4">취급 품목</h4></span>
	        <span>${store.scategory}</span>
	      </div>
	      
	      <div class="storeDetail__storeType">
	      	<span class="storeDetail__storeInsert__name">
	          <h4 class="storeDetail__h4">점포 등록자</h4>
	        </span>
	        <span>${store.nickname}</span>
	        <img src="../images/grade${store.grade}.png" class="review__level__photo" />
	      </div>
	      
	      <div>
	        <!-- 등록된 점포 사진 -->
	        <img src="${store.sphoto}" class="storeDetail__photo" />
	      </div>
    </section>

    <!-- Review -->
    <section id="review" class="max-container">
      <!-- <span class="review__level">
          <img src="images/icons8-병아리-60.png" class="review__level__photo" />
        </span> -->
      <div class="review__info">
     	<c:choose>
			<c:when test="${not empty sessionScope.memberInfo}">
				<span class="review__ask">
		          '${memberInfo.nickname}'&nbsp;님의 솔직한 리뷰를 남겨주세요!
		        </span>
			</c:when>
			<c:otherwise>
				<span class="review__ask">
		          로그인 후 해당 점포에 리뷰를 남겨주세요!
		        </span>
			</c:otherwise>
		</c:choose>
        

      </div>
      <div>

        <form id="review__form" method="GET" action="/finalProject/views/reviewInsert">
	    	<div id="ratingStar">
				<input type="radio" name="rrating" value="5" id="rate1" ><label for="rate1">★</label> 
				<input type="radio" name="rrating" value="4" id="rate2" ><label for="rate2">★</label> 
				<input type="radio" name="rrating" value="3" id="rate3" ><label for="rate3">★</label> 
				<input type="radio" name="rrating" value="2" id="rate4" ><label for="rate4">★</label> 
				<input type="radio" name="rrating" value="1" id="rate5" ><label for="rate5">★</label>
			</div><br/>

          <div class="review__form">
           <label class="review__label">
              <textarea
                class="review__input"
                name="rcontent"
                maxlength="100"
                placeholder="리뷰를 남겨주세요.(최대 100자)"
              ></textarea>
            </label>
          </div>
          <input type="hidden" name="sno" value="${store.sno}"/>
          <input type="hidden" name="id" value="${userId}"/>
          <div class="review__error" id="msg"></div>
          <button class="review__button" type="submit">작성하기</button>
        </form>
      </div>
    </section>

    <!-- Review Contents -->
    <section id="review__contents" class="max-container">
      <div class="review__contents__total">
        <span class="review__contents__star">
          <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp;<fmt:formatNumber value="${storeReviewAvg.rating}" pattern="0.#"/>

        </span>
        <span>
          <i class="fa-regular fa-comment-dots storeDetail__review"></i>&nbsp;${storeReviewAvg.review}
        </span>
      </div>
      
      <hr class="storeDetail__hr" />
      
      <c:forEach var="storeReview" items="${storeReviewList}">
      	<div class="review__contents__list">
	        <section class="review__contents__photo">
	          <img
	            src="../images/grade${storeReview.grade}.png"
	            class="review__contents__img"
	          />
	        </section>
	        <section class="review__contents__main">
                 <div class="review__contents__name">${storeReview.nickname}</div>
	          <!--<div class="review__contents__name">
            	${storeReview.id}<br /><br />
            	<i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp;사용자가 점포리뷰 때 남긴 별점
             </div>
              -->
	          <div class="review__contents__ownerInfo">
	            리뷰 ${storeReview.review } &nbsp; 별점평균 ${storeReview.rating} &nbsp; | &nbsp; ${storeReview.rregdate}
	            <c:if test="${storeReview.id == userId}">
	            <button class="reviewDelete__button" type="button" onclick="location.href='/finalProject/views/deleteReview?sno=${store.sno}&rno=${storeReview.rno}'">삭제</button>
	            </c:if>
	          </div>
	        </section>
	        <section class="review__contents__value">
	          ${storeReview.content}
	        </section>
	      </div>
        	<hr class="storeDetail__hr" />
      </c:forEach>
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
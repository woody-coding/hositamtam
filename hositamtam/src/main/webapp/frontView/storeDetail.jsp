<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
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

    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/89490613c7.js"
      crossorigin="anonymous"
    ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="/finalProject/css/loginHeader.css" />
    <link rel="stylesheet" href="/finalProject/css/footer.css" />
    <link rel="stylesheet" href="/finalProject/css/storeDetail.css" />

    <!-- JavaScript -->
    <script defer src="/finalProject/js/storeDetailReview.js"></script>
    <script defer src="/finalProject/js/anchor.js"></script>
    <script>
    
	    // 검색어 입력 필드에서 Enter 키를 눌렀을 때 검색 실행
	    document.getElementById("searchInput").addEventListener("keyup", function (event) {
	        if (event.key === "Enter") { // Enter 키가 눌렸을 때
	        
	            const searchInput = document.querySelector("#searchInput").value;
	            const encodedSearchInput = encodeURIComponent(searchInput);
	            const newURL = "market.jsp?command=search&query=" + encodedSearchInput;
	            window.location.href = newURL;
	        }
	  	  });
		}

		window.addEventListener('load', init);
    </script>
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
  </head>
  <body>
  
      <%@ include file="navi.jsp" %>
  
  


    <!-- Main -->
    <!-- Store Detail -->
    <section id="storeDetail" class="max-container">
      <div class="storeDetail__info">
      	<h4 class="storeDetail__h4 storeDetail__star">
           <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp; ${storeReviewAvg.rating}
        </h4>
        <button class="storeDetail__like">
          <i class="fa-solid fa-heart"></i>
          <div class="storeDetail__like__count">124</div>
        </button>
        <!-- <p id="storeLikeCount"></p> -->
        
        <span class="storeDetail__title">${store.sname}</span>
        
        <button class="storeDetail__modify">점포 수정</button>
      </div>
      
      <div class="storeDetail__storeType">
        <span class="storeDetail__storeType__title">
          <h4 class="storeDetail__h4">점포 형태</h4>
        </span>
        <%-- <span>${store.stype}</span> --%>
        <input type="checkbox" name="stype" value="좌판" <c:if test="${store.stype eq '좌판'}">checked="checked"</c:if>><span>좌판</span>
        <input type="checkbox" name="stype" value="매장" <c:if test="${store.stype eq '매장'}">checked="checked"</c:if>><span>매장</span>
      </div>
      <div class="storeDetail__payment">
        <span class="storeDetail__payment__title">
          <h4 class="storeDetail__h4">결제 방식</h4>
        </span>
        <%-- <span>${storePayment.paytype}</span> --%>
        <c:forEach var="storePayment" items="${storePaymentList}">
        	<input type="checkbox" name="paytype" value="${storePayment.paytype}" checked="checked"><span>${storePayment.paytype}</span>
        </c:forEach>
      </div>
      <div class="storeDetail__category">
        <span class="storeDetail__category__title">
          <h4 class="storeDetail__h4">취급 품목</h4>
        </span>
        <span>${store.scategory}</span>
      </div>
      <div class="storeDetail__storeType">
        <span class="storeDetail__storeInsert__name">
          <h4 class="storeDetail__h4">점포 등록자</h4>
        </span>
        <span>'${store.nickname}' 님</span>
      </div>
      <div>
        <img src="../images/${store.sphoto}" class="storeDetail__photo"/>
      </div>
    </section>

    <!-- Review -->
    <section id="review" class="max-container">
      <!-- <span class="review__level">
          <img src="images/icons8-병아리-60.png" class="review__level__photo" />
        </span> -->
      <div class="review__info">
        <img src="/finalProject/images/icons8-병아리-60.png" class="review__level__photo" />
        <span class="review__ask">
          ${store.nickname} 님 해당 점포에 리뷰를 남겨주세요.
        </span>
        <span class="review__star">
          <i class="fa-solid fa-star review__star1"></i>
          <i class="fa-solid fa-star review__star2"></i>
          <i class="fa-solid fa-star review__star3"></i>
          <i class="fa-solid fa-star review__star4"></i>
          <i class="fa-solid fa-star review__star5"></i>
        </span>
      </div>

      <div>
        <form id="review__form" style="display: none">
          <div class="review__form">
            <label class="review__label">
              <textarea
                class="review__input"
                id=""
                name="review"
                maxlength="100"
                placeholder="리뷰를 남겨주세요.(최대 100자)"
              ></textarea>
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
      <%-- <div class="review__contents__list">
        <section class="review__contents__photo">
          <img
            src="/finalProject/images/icons8-caveman-64.png"
            class="review__contents__img"
          />
        </section>
        <section class="review__contents__main">
          <div class="review__contents__name">
          	${memberDO.nickname}<br /><br />
            <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp;5.0
          </div>
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
        <section class="review__contents__photo">
          <img
            src="/finalProject/images/icons8-explorer-60.png"
            class="review__contents__img"
          />
        </section>
        <section class="review__contents__main">
          <div class="review__contents__name">
          	${memberDO.nickname}<br /><br />
            <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp;4.0
          </div>
          <div class="review__contents__ownerInfo">
            리뷰 20 &nbsp; 별점평균 4.0 &nbsp; | &nbsp; 23.10.10
          </div>
        </section>
        <section class="review__contents__value">SIUUUUUU</section>
      </div>
      <hr class="storeDetail__hr" />
      <div class="review__contents__list">
        <section class="review__contents__photo">
          <img
            src="/finalProject/images/icons8-spaceman-66.png"
            class="review__contents__img"
          />
        </section>
        <section class="review__contents__main">
          <div class="review__contents__name">
          ${memberDO.nickname}<br /><br />
            <i class="fa-solid fa-star storeDetail__starIcon"></i>&nbsp;5.0
          </div>
          <div class="review__contents__ownerInfo">
            리뷰 245 &nbsp; 별점평균 4.5 &nbsp; | &nbsp; 23.10.04
          </div>
        </section>
        <section class="review__contents__value">
          샤오룽바오가 육즙이 넘치구 속에 고기도 많이 들어서 너무 맛있었어요 ~
        </section>
      </div>
      <hr class="storeDetail__hr" /> --%>
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

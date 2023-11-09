<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    
    <!-- <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
	
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script> -->

    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/89490613c7.js"
      crossorigin="anonymous"
    ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="/finalProject/css/loginHeader.css" />
    <link rel="stylesheet" href="/finalProject/css/footer.css" />
    <link rel="stylesheet" href="/finalProject/css/myPage.css" />

    <!-- JavaScript -->
    <script src="/finalProject/js/myPage.js" charset="UTF-8"></script>
    <script defer src="/finalProject/js/myPageUpdate.js"></script>
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
  </head>
  <body>
  <%@ include file="navi.jsp" %>
  
  
<!-- 기존의 동영님 코드  네비바? 
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <img class="mainHeader__logo__img" src="/finalProject/images/logo.ico" alt="logo" />
      </div>

      <form id="searchForm" method="get" action="SearchController">
        <div class="market__search">
          <input
            type="text"
            class="market__searchInput"
            name="market"
            id="searchInput"
            placeholder="   궁금한 시장 이름을 입력하세요. Ex.부평깡통시장"
          />
          <button
            class="market__searchButton"
            type="submit"
            name="action"
            value="search"
          >
            <i class="fa-solid fa-magnifying-glass"></i>
          </button>
        </div>
      </form>

      <nav class="mainHeader__nav">
        <ul class="mainHeader__menu">
          <li><a class="mainHeader__menu__item" href="#">서비스안내</a></li>
          <li><a class="mainHeader__menu__item" href="#">시끌시끌</a></li>
          <li><a class="mainHeader__menu__item" href="#">카테고리</a></li>
        </ul>
      </nav>
    </header>
 -->

    <!-- Main -->
    <!-- My Information -->
    <section id="myinfo" class="max-container">
      <!-- Level -->
      <div class="mylevel">
        <section class="mylevel__info">
          <div class="mylevel__picture">
            <img src="/finalProject/images/icons8-병아리-60.png" />
          </div>
          <div class="mylevel__name">
            <div class="mylevel__grade">${memberInfo.nickname}님의 등급</div>
            <div>${gradeName}</div>
          </div>
        </section>
        <div id="mylevel__gage">
          <section class="mylevel__gage">
            <div
              class="mylevel__gage__value"
              style="width: ${memberInfo.exp}%; background-color: #e6007e"
            >
              ${memberInfo.exp}%
            </div>
          </section>
        </div>

        <div class="mylevel__gage__data">
          <span>시장 <br />왕초보</span>
          <span>시장 <br />햇병아리</span>
          <span>시장 <br />탐험가</span>
          <span>시장 <br />지킴이</span>
          <span>시장 <br />지박령</span>
        </div>
      </div>

      <!-- Profile -->
      <div class="myprofile">
        <div class="myprofile__name">${memberInfo.nickname} 님 (${userId})</div>
        <div class="modify__delete__button">
        
          <form id="updateForm" class="myprofile__modify__form" action="/finalProject/views/myPageUpdate" method="GET">
          	<input type="hidden" name="id" value="${userId}" />
          	<button class="myprofile__modify__button" type="submit" id="mypageedit_button" >정보수정</button>
          </form>
          
          <form id="deleteForm" class="myprofile__delete__form" action="/finalProject/views/myPage/deleteMember" method="POST">
            <input type="hidden" name="id" value="${userId}" />
            <button class="myprofile__delete__button" type="submit" id="delete_button" >회원탈퇴</button>
          </form>
        </div>

        <section class="myprofile__activity">
          <div class="myprofile__activity__contents">
            <a href="#myStore">
              <i class="fa-solid fa-store myStore__icon"></i> 16 <br />
              내가 등록한 점포
            </a>
          </div>
          <div class="myprofile__activity__contents">
            <a href="#myPost">
              <i class="fa-regular fa-pen-to-square myPost__icon"></i> 1 <br />
              내가 작성한 글
            </a>
          </div>
        </section>
        <section class="myprofile__activity2">
          <div class="myprofile__activity__contents">
            <a href="#myStoreLike">
              <i class="fa-solid fa-heart myStoreLike__icon"></i> 5 <br />
              내가 찜한 점포
            </a>
          </div>
          <div class="myprofile__activity__contents">
            <a href="#myReview">
              <i class="fa-regular fa-comment-dots myReview__icon"></i> 1 <br />
              내가 작성한 리뷰
            </a>
          </div>
        </section>
      </div>
    </section>

    <!-- My Activity -->
    <section id="myActivity" class="max-container">
      <div class="myActivity">
        <div class="myStore">
          <h2 id="myStore" class="myPage__h2">
            <i class="fa-solid fa-store myStore__icon"></i>
            &nbsp;내가 등록한 점포
          </h2>
          <button class="myStore__more">더보기</button>
          <button class="myStore__less">접기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myStore__th">점포명</th>
            <th class="myStore__th">점포 형태</th>
            <th class="myStore__th">결제 방식</th>
            <th class="myStore__th">취급 품목</th>
          </tr>
          <%-- <c:forEach >
	          <tr class="myStore__tr">
	            <td class="myStore__td">${storeDO.sname}</td>
	            <td class="myStore__td">${storeDO.stype}</td>
	            <td class="myStore__td">${storeDO.payno}</td>
	            <td class="myStore__td">${storeDO.scategory}</td>
	          </tr>
          </c:forEach> --%>
          <tr class="myStore__tr">
            <td class="myStore__td">은하갈비</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 카드</td>
            <td class="myStore__td">음식점</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">기장농산</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 계좌이체</td>
            <td class="myStore__td">농산물</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">망미축산도매센타</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStore__td">축산물</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">안동상회 앞 잡화점</td>
            <td class="myStore__td">좌판</td>
            <td class="myStore__td">현금 &nbsp; 계좌이체</td>
            <td class="myStore__td">기타</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">명가 장충동 족발</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStore__td">음식점</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">명가 장충동 족발</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStore__td">음식점</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">명가 장충동 족발</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStore__td">음식점</td>
          </tr>
          <tr class="myStore__tr">
            <td class="myStore__td">명가 장충동 족발</td>
            <td class="myStore__td">매장</td>
            <td class="myStore__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStore__td">음식점</td>
          </tr>
        </table>
      </div>

      <div class="myActivity">
        <div class="myPost">
          <h2 id="myPost" class="myPage__h2">
            <i class="fa-regular fa-pen-to-square myPost__icon"></i>
            &nbsp;내가 작성한 글
          </h2>
          <button class="myPost__more">더보기</button>
          <button class="myPost__less">접기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myPost__th__title">제목</th>
            <th class="myPost__th">카테고리</th>
            <th class="myPost__th">좋아요</th>
            <th class="myPost__th">댓글</th>
            <th class="myPost__th">작성일</th>
          </tr>
          <%-- <c:forEach >
	          <tr class="myPost__tr">
	            <td class="myPost__td__title">${postDO.ptitle}</td>
	            <td class="myPost__td">${postDO.pcategory}</td>
	            <td class="myPost__td">
	              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;${postDO.plikecount}
	            </td>
	            <td class="myPost__td">${postDO.countcomments}</td>
	            <td class="myPost__td">${postDO.pregdate}</td>
	          </tr>
          </c:forEach> --%>
          <tr class="myPost__tr">
            <td class="myPost__td__title">거인통닭의 실체</td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
          <tr class="myPost__tr">
            <td class="myPost__td__title">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
          <tr class="myPost__tr">
            <td class="myPost__td__title">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
          <tr class="myPost__tr">
            <td class="myPost__td__title">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
          <tr class="myPost__tr">
            <td class="myPost__td__title">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
          <tr class="myPost__tr">
            <td class="myPost__td__title">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
          <tr class="myPost__tr">
            <td class="myPost__td__title">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myPost__td">일상</td>
            <td class="myPost__td">
              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;3
            </td>
            <td class="myPost__td">0</td>
            <td class="myPost__td">2023-10-04</td>
          </tr>
        </table>
      </div>

      <div class="myActivity">
        <div class="myReview">
          <h2 id="myReview" class="myPage__h2">
            <i class="fa-regular fa-comment-dots myReview__icon"></i>
            &nbsp;내가 작성한 리뷰
          </h2>
          <button class="myReview__more">더보기</button>
          <button class="myReview__less">접기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myReview__th__name">점포명</th>
            <th class="myReview__th__star">평점</th>
            <th class="myReview__th__contents">내용</th>
            <th class="myReview__th">작성일</th>
          </tr>
          <%-- <c:forEach >
	          <tr class="myReview__tr">
	            <td class="myReview__td__name">${storeDO.sname}</td>
	            <td class="myReview__td__star">
	              <i class="fa-solid fa-star myReview__star"></i> 5.0
	            </td>
	            <td class="myReview__td__contents">
	              이렇게 맛있는 치킨은 제 인생 처음이에요 !
	            </td>
	            <td class="myReview__td">2023-10-04</td>
	          </tr>
          </c:forEach> --%>
          <tr class="myReview__tr">
            <td class="myReview__td__name">괘법르네시떼 거인통닭</td>
            <td class="myReview__td__star">
              <i class="fa-solid fa-star myReview__star"></i> 5.0
            </td>
            <td class="myReview__td__contents">
              이렇게 맛있는 치킨은 제 인생 처음이에요 !
            </td>
            <td class="myReview__td">2023-10-04</td>
          </tr>
          <tr class="myReview__tr">
            <td class="myReview__td__name">핫둘셋넷다여일여아열핫둘셋넷다</td>
            <td class="myReview__td__star">
              <i class="fa-solid fa-star myReview__star"></i> 5.0
            </td>
            <td class="myReview__td__contents">
              여기보다 맛있는 집이 있을 수가 없어요 ! 여기보다 맛있는 데가
              있으면 저한테 한번만 말해주세요 나 진짜 거기 가볼래요
            </td>
            <td class="myReview__td">2023-10-04</td>
          </tr>
          <tr class="myReview__tr">
            <td class="myReview__td__name">핫둘셋넷다여일여아열핫둘셋넷다</td>
            <td class="myReview__td__star">
              <i class="fa-solid fa-star myReview__star"></i> 5.0
            </td>
            <td class="myReview__td__contents">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myReview__td">2023-10-04</td>
          </tr>
          <tr class="myReview__tr">
            <td class="myReview__td__name">핫둘셋넷다여일여아열핫둘셋넷다</td>
            <td class="myReview__td__star">
              <i class="fa-solid fa-star myReview__star"></i> 5.0
            </td>
            <td class="myReview__td__contents">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myReview__td">2023-10-04</td>
          </tr>
          <tr class="myReview__tr">
            <td class="myReview__td__name">핫둘셋넷다여일여아열핫둘셋넷다</td>
            <td class="myReview__td__star">
              <i class="fa-solid fa-star myReview__star"></i> 5.0
            </td>
            <td class="myReview__td__contents">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myReview__td">2023-10-04</td>
          </tr>
          <tr class="myReview__tr">
            <td class="myReview__td__name">핫둘셋넷다여일여아열핫둘셋넷다</td>
            <td class="myReview__td__star">
              <i class="fa-solid fa-star myReview__star"></i> 5.0
            </td>
            <td class="myReview__td__contents">
              핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열핫둘셋넷다여일여아열
            </td>
            <td class="myReview__td">2023-10-04</td>
          </tr>
        </table>
      </div>

      <div class="myActivity">
        <div class="myStoreLike">
          <h2 id="myStoreLike" class="myPage__h2">
            <i class="fa-solid fa-heart myStoreLike__icon"></i>
            &nbsp;내가 찜한 점포
          </h2>
          <button class="myStoreLike__more">더보기</button>
          <button class="myStoreLike__less">접기</button>
        </div>
        <table class="myPage__table">
          <tr class="myStoreLike__tr">
            <th class="myStoreLike__th">점포명</th>
            <th class="myStoreLike__th">점포 형태</th>
            <th class="myStoreLike__th">결제 방식</th>
            <th class="myStoreLike__th">취급 품목</th>
          </tr>
          <%-- <c:forEach >
	          <tr>
	            <td class="myStoreLike__td">${storeDO.sname}</td>
	            <td class="myStoreLike__td">${storeDO.stype}</td>
	            <td class="myStoreLike__td">${storeDO.payno}</td>
	            <td class="myStoreLike__td">${storeDO.scategory}</td>
	          </tr>
          </c:forEach> --%>
          <tr class="myStoreLike__tr">
            <td class="myStoreLike__td">괘법르네시떼 거인통닭</td>
            <td class="myStoreLike__td">매장</td>
            <td class="myStoreLike__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStoreLike__td">음식점</td>
          </tr>
          <tr class="myStoreLike__tr">
            <td class="myStoreLike__td">순희닭강정</td>
            <td class="myStoreLike__td">매장</td>
            <td class="myStoreLike__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStoreLike__td">음식점</td>
          </tr>
          <tr class="myStoreLike__tr">
            <td class="myStoreLike__td">은하갈비</td>
            <td class="myStoreLike__td">매장</td>
            <td class="myStoreLike__td">현금 &nbsp; 카드</td>
            <td class="myStoreLike__td">음식점</td>
          </tr>
          <tr class="myStoreLike__tr">
            <td class="myStoreLike__td">망미청과</td>
            <td class="myStoreLike__td">매장</td>
            <td class="myStoreLike__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStoreLike__td">농산물</td>
          </tr>
          <tr class="myStoreLike__tr">
            <td class="myStoreLike__td">초읍만두</td>
            <td class="myStoreLike__td">매장</td>
            <td class="myStoreLike__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStoreLike__td">음식점</td>
          </tr>
          <tr class="myStoreLike__tr">
            <td class="myStoreLike__td">초읍만두</td>
            <td class="myStoreLike__td">매장</td>
            <td class="myStoreLike__td">현금 &nbsp; 카드 &nbsp; 계좌이체</td>
            <td class="myStoreLike__td">음식점</td>
          </tr>
        </table>
      </div>
    </section>

    <!-- Anchor -->
    <aside>
      <section>
        <a class="myPage__store" href="#myStore" title="back to store">
          <div class="store__icon">
            <i class="fa-solid fa-store myStore__menu"></i>
          </div>
        </a>
        <div class="myPage__store__info">내가 등록한 점포 보기</div>
      </section>
      <section>
        <a class="myPage__post" href="#myPost" title="back to post">
          <div class="store__icon">
            <i class="fa-regular fa-pen-to-square myPost__menu"></i>
          </div>
        </a>
        <div class="myPage__post__info">내가 작성한 글 보기</div>
      </section>
      <section>
        <a class="myPage__review" href="#myReview" title="back to review">
          <div class="store__icon">
            <i class="fa-regular fa-comment-dots myReview__menu"></i>
          </div>
        </a>
        <div class="myPage__review__info">내가 작성한 리뷰 보기</div>
      </section>
      <section>
        <a class="myPage__storeLike" href="#myStoreLike" title="back to like">
          <div class="store__icon">
            <i class="fa-solid fa-heart myStoreLike__menu"></i>
          </div>
        </a>
        <div class="myPage__storeLike__info">내가 찜한 점포 보기</div>
      </section>
      <section>
        <a class="myPage__arrow-up" href="#myinfo" title="back to top">
          <div class="store__icon">
            <i class="fa-solid fa-arrow-up arrowUp__menu"></i>
          </div>
        </a>
        <div class="myPage__arrow-up__info">맨 위로 가기</div>
      </section>
    </aside>

    
	<%@ include file="footer.jsp" %>
  </body>
</html>

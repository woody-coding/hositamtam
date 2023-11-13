<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>
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
    <link rel="stylesheet" href="/finalProject/css/myPage.css" />

    <!-- JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="/finalProject/js/myPage.js" charset="UTF-8"></script>
    <script src="/finalProject/js/myPageHeader.js"></script>
    <script src="/finalProject/js/anchor.js"></script>
  </head>
  <body>
  
    <%@ include file="navi.jsp" %>

    <!-- Main -->
    <!-- My Information -->
    <section id="myinfo" class="max-container">
      <!-- Level -->
      <div class="mylevel">
        <section class="mylevel__info">
          <div class="mylevel__picture">
            <img src="/finalProject/images/grade${memberList.grade}.png" />
          </div>
          <div class="mylevel__name">
            <div class="mylevel__grade">${memberList.nickname}님의 등급</div>
            <div>${gradeName}</div>
          </div>
        </section>
        <div id="mylevel__gage">
          <section class="mylevel__gage">
            <div
              class="mylevel__gage__value"
              style="width: ${memberList.exp}%; background-color: #e6007e"
            >
              ${memberList.exp}%
            </div>
          </section>
        </div>

        <div class="mylevel__gage__data">
          <span>삐약이</span>
          <span>왕초보</span>
          <span>탐험가</span>
          <span>지킴이</span>
          <span>개척자</span>
        </div>
      </div>

      <!-- Profile -->
      <div class="myprofile">
        <div class="myprofile__name">${memberList.nickname} 님 (${userId})</div>
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
              <i class="fa-solid fa-store myStore__icon"></i> ${storeCount} <br />
              내가 등록한 점포
            </a>
          </div>
          <div class="myprofile__activity__contents">
            <a href="#myPost">
              <i class="fa-regular fa-pen-to-square myPost__icon"></i> ${postCount} <br />
              내가 작성한 글
            </a>
          </div>
        </section>
        <section class="myprofile__activity2">
          <div class="myprofile__activity__contents">
            <a href="#myStoreLike">
              <i class="fa-solid fa-heart myStoreLike__icon"></i> ${storeLikeCount} <br />
              내가 찜한 점포
            </a>
          </div>
          <div class="myprofile__activity__contents">
            <a href="#myReview">
              <i class="fa-regular fa-comment-dots myReview__icon"></i> ${reviewCount} <br />
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
          
            <c:forEach var="storeDO" items="${storeDOInfoList}">
	          <tr class="myStore__tr" onclick="location.href='/finalProject/views/storeDetail?sno=' + ${storeDO.sno};">
	            <td class="myStore__td">${storeDO.sname}</td>
	            <td class="myStore__td">${storeDO.stype}</td>
	            <td class="myStore__td">${storeDO.paytype}</td>
	            <td class="myStore__td">${storeDO.scategory}</td>
	          </tr>
			</c:forEach>

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
            <th class="myPost__th">작성일</th>
          </tr>
          
          
          <c:forEach var="postDO" items="${postDOList}">
		        <tr class="myPost__tr" onclick="location.href='/finalProject/views/toPostDetail?pno=' + ${postDO.pno};">
		            <td class="myPost__td__title">${postDO.ptitle}</td>
		            <td class="myPost__td">${postDO.pcategory}</td>
		            <td class="myPost__td">
		              <i class="fa-regular fa-thumbs-up myPost__thumbsUp"></i> &nbsp;${postDO.plikecount}
		            </td>
		            <td class="myPost__td">${postDO.pregdate}</td>
      			</tr>
			</c:forEach>
          
          
          

          
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
			<c:forEach var="reviewDO" items="${reviewDOList}">
			          <tr class="myReview__tr" onclick="location.href='/finalProject/views/storeDetail?sno=' + ${reviewDO.sno};">
			            <td class="myReview__td__name">${reviewDO.sname}</td>
			            <td class="myReview__td__star">
			              <i class="fa-solid fa-star myReview__star"></i> ${reviewDO.rrating}
			            </td>
			            <td class="myReview__td__contents">
			              ${reviewDO.rcontent}
			            </td>
			            <td class="myReview__td">${reviewDO.rregdate}</td>
	    			 </tr>
			</c:forEach>
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
	        <c:forEach var="storeDO" items="${storeDOLikeList}">
	          <tr class="myStoreLike__tr" onclick="location.href='/finalProject/views/storeDetail?sno=' + ${storeDO.sno};">
	            <td class="myStoreLike__td">${storeDO.sname}</td>
	            <td class="myStoreLike__td">${storeDO.stype}</td>
	            <td class="myStoreLike__td">${storeDO.paytype}</td>
	            <td class="myStoreLike__td">${storeDO.scategory}</td>
	          </tr>
			</c:forEach>

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

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
  </body>
</html>

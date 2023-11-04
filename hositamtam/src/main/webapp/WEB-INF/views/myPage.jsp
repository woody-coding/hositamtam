<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>호시탐탐</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

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
    <link rel="stylesheet" href="/finalProject/css/myPage.css" />

    <!-- JavaScript -->
    <script src="/finalProject/js/myPage.js" charset="UTF-8"></script>
  </head>
  <body>
  
    <%@ include file="navi.jsp" %>

    <!-- Main -->
    <!-- My Information -->
    <section id="myinfo" class="max-container">
      <!-- Level -->
      <div class="mylevel">
        <section class="mylevel__info">
          <div class="mylevel__picture" style="color: #e6007e">
            <i class="fa-solid fa-user"></i>
          </div>
          <div class="mylevel__name">
            회원님의 등급 <br />
            시장 햇병아리🐤
          </div>
        </section>
        <div id="mylevel__gage">
          <section class="mylevel__gage">
            <div
              class="mylevel__gage__value"
              style="width: 35%; background-color: #e6007e"
            >
              35%
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
        <div class="myprofile__name">탐탐 님 (tmi****)</div>
        <div class="modify__delete__button">
          <button class="myprofile__modify__button">정보수정</button>
          <button class="myprofile__delete__button">회원탈퇴</button>
        </div>

        <section class="myprofile__activity">
          <div class="myprofile__activity__contents">
            <a href="#myStore">✔️ 16 <br />내가 등록한 점포</a>
          </div>
          <div class="myprofile__activity__contents">
            <a href="#myPost">✍️ 1 <br />내가 작성한 글</a>
          </div>
        </section>
        <section class="myprofile__activity2">
          <div class="myprofile__activity__contents">
            <a href="#myStoreLike">❤️ 5 <br />내가 찜한 점포</a>
          </div>
          <div class="myprofile__activity__contents">
            <a href="#myReview">📝 1 <br />내가 작성한 리뷰</a>
          </div>
        </section>
      </div>
    </section>

    <!-- My Activity -->
    <section id="myActivity" class="max-container">
      <div class="myActivity">
        <div class="myStore">
          <h2 id="myStore" class="myPage__h2">✔️ 내가 등록한 점포</h2>
          <button class="myStore__more">더보기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myPage__th">점포명</th>
            <th class="myPage__th">점포 형태</th>
            <th class="myPage__th">결제 방식</th>
            <th class="myPage__th">취급 품목</th>
          </tr>
          <tr>
            <td class="myPage__td">괘법르네시떼 거인통닭</td>
            <td class="myPage__td">매장</td>
            <td class="myPage__td">현금 카드 계좌이체</td>
            <td class="myPage__td">음식점</td>
          </tr>
        </table>
      </div>

      <div class="myActivity">
        <div class="myPost">
          <h2 id="myPost" class="myPage__h2">✍️ 내가 작성한 글</h2>
          <button class="myPost__more">더보기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myPage__th">제목</th>
            <th class="myPage__th">카테고리</th>
            <th class="myPage__th">좋아요</th>
            <th class="myPage__th">작성일</th>
          </tr>
          <tr>
            <td class="myPage__td">거인통닭의 실체</td>
            <td class="myPage__td">일상</td>
            <td class="myPage__td">👍 3</td>
            <td class="myPage__td">2023-10-04</td>
          </tr>
        </table>
      </div>

      <div class="myActivity">
        <div class="myReview">
          <h2 id="myReview" class="myPage__h2">📝 내가 작성한 리뷰</h2>
          <button class="myReview__more">더보기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myPage__th">점포명</th>
            <th class="myPage__th">평점</th>
            <th class="myPage__th">리뷰내용</th>
            <th class="myPage__th">작성일</th>
          </tr>
          <tr>
            <td class="myPage__td">거인통닭</td>
            <td class="myPage__td">⭐ 4.5</td>
            <td class="myPage__td">
              이렇게 맛있는 치킨은 제 인생 처음이에요 !
            </td>
            <td class="myPage__td">2023-10-04</td>
          </tr>
        </table>
      </div>

      <div class="myActivity">
        <div class="myStoreLike">
          <h2 id="myStoreLike" class="myPage__h2">❤️ 내가 찜한 점포</h2>
          <button class="myStoreLike__more">더보기</button>
        </div>
        <table class="myPage__table">
          <tr>
            <th class="myPage__th">점포명</th>
            <th class="myPage__th">점포 형태</th>
            <th class="myPage__th">결제 방식</th>
            <th class="myPage__th">취급 품목</th>
          </tr>
          <tr>
            <td class="myPage__td">괘법르네시떼 거인통닭</td>
            <td class="myPage__td">매장</td>
            <td class="myPage__td">현금 카드 계좌이체</td>
            <td class="myPage__td">음식점</td>
          </tr>
          <tr>
            <td class="myPage__td">순희닭강정</td>
            <td class="myPage__td">매장</td>
            <td class="myPage__td">현금 카드 계좌이체</td>
            <td class="myPage__td">음식점</td>
          </tr>
        </table>
      </div>
    </section>

    <!-- <section id="myActivity" class="max-container">
      <h1 class="myActivity__title">나의 활동 내역</h1>
      <div class="myActivity">
        <h2 id="myStore">✔️ 내가 등록한 점포</h2>
        <div class="myStore">
          <span class="myStore__contents"> 괘법르네시떼 거인통닭 </span>
          <button class="myStore__more">더보기</button>
        </div>
        <hr class="myPage__hr" />
      </div>
      <div class="myActivity">
        <h2 id="myPost">✍️ 내가 작성한 글</h2>
        <div class="myPost">
          <span class="myPost__contents"> 거인통닭 사실 포장만 됨 </span>
          <button class="myPost__more">더보기</button>
        </div>
        <hr class="myPage__hr" />
      </div>
      <div class="myActivity">
        <h2 id="myReview">📝 내가 작성한 리뷰</h2>
        <div class="myReview">
          <span class="myReview__contents"> 백종원 나와서 이제 못 먹음 </span>
          <button class="myReview__more">더보기</button>
        </div>
        <hr class="myPage__hr" />
      </div>
      <div class="myActivity">
        <h2 id="myStoreLike">❤️ 내가 찜한 점포</h2>
        <div class="myStoreLike">
          <span class="myStoreLike__contents"> 거인통닭 / 순희닭강정 </span>
          <button class="myStoreLike__more">더보기</button>
        </div>
        <hr class="myPage__hr" />
      </div>
    </section> -->

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
  </body>
</html>

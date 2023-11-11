<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
System.out.println("JOKE storeInsertAndUpdate.jsp");
String slat = request.getParameter("slat");
String slng = request.getParameter("slng");
String mno = request.getParameter("mno");
%>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" href="../css/storeInsertAndUpdate.css" />

    <!-- JavaScript -->
    <script defer src="../js/storeInsertAndUpdate.js"></script>
  </head>
  <body>
    <!-- Header -->
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <img class="mainHeader__logo__img" src="../images/logo.ico" alt="logo" />
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

    <!-- Main -->
    <!-- Store Insert And Update -->
    <section id="store__update" class="section">
      <div class="max-container">
        <div class="store__update">
          <form id="store__update__form">
            <label for="store__name" class="store__label">
              <input
                class="store__input"
                type="text"
                id="store__name"
                placeholder="점포의 이름을 지어주세요 !"
              />
              <button class="change__location">위치 수정</button>
            </label>
            <div class="store__update__form">
              <div class="store__type">
                <h2 class="store__type__title siau__h2">점포 형태</h2>
                <button class="store__type__button">좌판</button>
                <button class="store__type__button">매장</button>
              </div>
              <div class="store__payment">
                <h2 class="store__payment__title siau__h2">결제 방식</h2>
                <button class="store__payment__button">현금</button>
                <button class="store__payment__button">카드</button>
                <button class="store__payment__button">계좌이체</button>
              </div>
              <div class="store__category">
                <h2 class="store__category__title siau__h2">취급 품목</h2>
                <label for="store__category__contents" class="category__label">
                  <input
                    class="category__input"
                    type="text"
                    id="store__category__contents"
                    placeholder="점포의 취급품목을 알려주세요 !"
                  />
                </label>
              </div>
              <div class="store__photo">
                <h2 class="store__photo__title siau__h2">점포 사진</h2>
                <label class="store__photo__label">
                  <input type="file" name="photo" class="store__photo__input" />
                </label>
              </div>
            </div>

            <button class="store__update__button" type="submit">
              등록하기
            </button>
          </form>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <footer id="information" class="section">
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
    </footer>
  </body>
</html>

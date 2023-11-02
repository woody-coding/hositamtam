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
    <link rel="stylesheet" href="/finalProject/css/login.css" />

    <!-- JavaScript -->
    <script src="/finalProject/js/login.js" charset="UTF-8"></script>
  </head>
  <body>
    <!-- Header -->
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <img class="mainHeader__logo__img" src="images/logo.ico" alt="logo" />
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
          <li><a class="mainHeader__menu__item">서비스안내</a></li>
          <li><a class="mainHeader__menu__item">시끌시끌</a></li>
          <li><a class="mainHeader__menu__item" id="category">카테고리</a></li>
        </ul>
      </nav>
    </header>

    <!-- Main -->
    <!-- Category -->
    <!-- <section class="category">
      <div class="category__menu">
        <ul class="category__list">
          <li><a class="category__contents">농산물</a></li>
          <li><a class="category__contents">가공식품</a></li>
          <li><a class="category__contents">축산물</a></li>
          <li><a class="category__contents">의류</a></li>
          <li><a class="category__contents">신발</a></li>
        </ul>
        <ul class="category__list">
          <li><a class="category__contents">음식점</a></li>
          <li><a class="category__contents">수산물</a></li>
          <li><a class="category__contents">가정용품</a></li>
          <li><a class="category__contents">기타</a></li>
        </ul>
      </div>
    </section> -->

    <!-- Login -->
    <section id="login" class="section">
      <div class="max-container">
        <div class="login">
          <form id="login__form">
            <div class="login__form">
              <label for="id" class="login__label">
                <input
                  class="login__input"
                  type="text"
                  id="id"
                  name="id"
                  placeholder="아이디"
                />
                <br />
              </label>

              <label for="password" class="login__label">
                <input
                  class="login__input"
                  type="password"
                  id="passwd"
                  placeholder="비밀번호"
                />
                <br />
              </label>

              <div class="login__error">아이디 혹은 패스워드가 틀렸습니다.</div>

              <div class="login__signup__button">
                <button class="login__button" type="submit">로그인</button>
                <button class="signup__button" id="signup-button">
                  회원가입
                </button>
              </div>
            </div>
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
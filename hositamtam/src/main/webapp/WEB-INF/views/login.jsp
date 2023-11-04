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
  
    <%@ include file="navi.jsp" %>

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
          <form method="POST" id="login__form" action="/finalProject/views/loginMember">
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

              <label for="passwd" class="login__label">
                <input
                  class="login__input"
                  type="password"
                  id="passwd"
                  name="passwd"
                  placeholder="비밀번호"
                />
                <br />
              </label>

              <div class="login__error" id="msg">${error }</div>

              <div class="login__signup__button">
                <button class="login__button" type="submit" id="login_button">로그인</button>
                <button class="signup__button" id="signup-button">회원가입</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
  </body>
</html>
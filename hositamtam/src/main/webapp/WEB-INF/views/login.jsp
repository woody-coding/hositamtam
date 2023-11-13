<%@ page contentType="text/html; charset=UTF-8"%>
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

    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/89490613c7.js"
      crossorigin="anonymous"
    ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="/finalProject/css/whiteBgHeader.css" />
    <link rel="stylesheet" href="/finalProject/css/footer2.css" />
    <link rel="stylesheet" href="/finalProject/css/login.css" />

    <!-- JavaScript -->
    <script defer src="/finalProject/js/anchor.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
      
    <script src="/finalProject/js/login.js" charset="UTF-8"></script>
    <script>
    	function init() {
    		window.sessionStorage.removeItem('memberId');
    	}
    
    	window.addEventListener('load', init);
    </script>
  </head>
  <body>
  
    <%@ include file="navi.jsp" %>

    <!-- Main -->
    <!-- Login -->
    <section id="login" class="login__section">
      <div class="max-container">
        <div class="login">
        <img src="/finalProject/images/fontLogo.ico" alt="로고" class="login__logo" />
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

              <div class="login__error" id="msg">${msg}</div>

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
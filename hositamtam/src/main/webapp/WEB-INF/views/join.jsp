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
    <link rel="stylesheet" href="/finalProject/css/join.css" />

    <!-- JavaScript -->
    <script defer src="/finalProject/js/anchor.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
      
    <script src="/finalProject/js/join.js" charset="UTF-8"></script>
  </head>
  <body>
  
    <%@ include file="navi.jsp" %>

    <!-- Main -->
    <!-- Sign Up -->
    <section id="join" class="join__section">
      <div class="max-container">
        <div class="join">
          <img src="/finalProject/images/fontLogo.ico" alt="로고" class="join__logo" />
          <form method="POST" id="join__form" action="/finalProject/views/joinMember">
            <div class="join__form">
              <label for="id" class="join__label">
                <input
                  class="join__input"
                  type="text"
                  id="id"
                  name="id"
                  placeholder="아이디" required
                />
                <button type="button" class="duplication__check" id="id_check"">중복확인</button>
              </label>
              
              <label for="nickname" class="join__label">
                <input
                  class="join__input"
                  type="text"
                  id="nickname"
                  name="nickname"
                  maxlength="8"
                  placeholder="닉네임" required
                />
                <button type="button" class="duplication__check" id="nickname_check"">중복확인</button>
              </label>

              <label for="passwd" class="join__label">
                <input
                  class="join__input"
                  type="password"
                  id="passwd"
                  name="passwd"
                  placeholder="비밀번호" required
                />
              </label>

              <label for="repasswd" class="join__label">
                <input
                  class="join__input"
                  type="password"
                  id="repasswd"
                  name="repasswd"
                  placeholder="비밀번호 확인" required
                />
              </label>

              <label for="birthdate" class="join__label">
                <input
                  class="join__input"
                  type="date"
                  id="birthdate"
                  name="birthdate"
                  placeholder="생년월일" required
                />
              </label>

              <label for="gender" class="join__label">
                <input type="radio"  id="gender" name="gender" value="남" checked/>남
                <input type="radio" id="gender" name="gender" value="여" />여
              </label>
            </div>

            <div class="join__error" id="msg"></div>
            
            <button class="join__button" type="submit" id="join_button">회원가입</button>
          </form>
        </div>
      </div>
    </section>

    <%@ include file="footer.jsp" %>
    
  </body>
</html>

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
    <link rel="stylesheet" href="/finalProject/css/loginHeader.css" />
    <link rel="stylesheet" href="/finalProject/css/footer.css" />
    <link rel="stylesheet" href="/finalProject/css/myPageUpdate.css" />

    <!-- JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="/finalProject/js/myPageUpdate.js" charset="UTF-8"></script>
  </head>
  <body>
  
    <%@ include file="navi.jsp" %>

    <!-- Main -->
    <!-- My Page Update -->
    <section id="mypage__update" class="section">
      <div class="max-container">
        <div class="mypage__update">
          <!--  <form id="mypage__update__form" action="/finalProject/AjaxController.jsp" method="POST">-->
            <div class="mypage__update__form">
              <label for="new-username" class="mypage__update__id">
                ${userId}
              </label>
              
              <label for="change__nickname" class="mypage__update__label">
                <input
                  class="mypage__update__input"
                  type="text"
                  id="change__nickname"
                  name="nickname"
                  maxlength="8"
                  placeholder="닉네임"
                />
                <button class="duplication__check" id="nickname_check" type="button">중복확인</button>
              </label>

              <label for="change-password" class="mypage__update__label">
                <input
                  class="mypage__update__input"
                  type="password"
                  id="change__password"
                  name="password"
                  placeholder="변경할 비밀번호"
                />
              </label>

              <label for="confirm-password" class="mypage__update__label">
                <input
                  class="mypage__update__input"
                  type="password"
                  id="confirm__password"
                  placeholder="변경할 비밀번호 확인"
                />
              </label>
            </div>

            <div class="mypage__update__error" id="error_msg">
              
            </div>

	            <button class="mypage__update__button" type="submit" id="update_button">
	              수정하기
	            </button>
         	<!-- </form> -->
        </div>
      </div>
    </section>

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
  </body>
</html>

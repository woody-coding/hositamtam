<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>
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
    <link rel="stylesheet" href="/finalProject/css/myPageUpdate.css" />

    <!-- JavaScript -->
    <script src="/finalProject/js/myPageUpdate.js" charset="UTF-8"></script>
  </head>
  <body>
  
    <%@ include file="navi.jsp" %>

    <!-- Main -->
    <!-- My Page Update -->
    <section id="mypage__update" class="section">
      <div class="max-container">
        <div class="mypage__update">
          
            <div class="mypage__update__form">
              <label for="new-username" class="mypage__update__id">
                ${userId}
              </label>
              
			<form id="mypage__update__nickname" action="/finalProject/views/myPageUpdate" method="POST">
              <label for="change-nickname" class="mypage__update__label">
                <input
                  class="mypage__update__input"
                  type="text"
                  id="change-nickname"
                  placeholder="닉네임"
                />
                <button class="duplication__check" id="nickname_check">중복확인</button>
              </label>
            </form>

			<form id="mypage__update__passwd" action="/finalProject/views/myPageUpdate" method="POST">
              <label for="change-password" class="mypage__update__label">
                <input
                  class="mypage__update__input"
                  type="password"
                  id="change-password"
                  placeholder="변경할 비밀번호"
                />
                <button class="duplication__check" id="passwd_check">중복확인</button>
              </label>
            </form>

              <label for="confirm-password" class="mypage__update__label">
                <input
                  class="mypage__update__input"
                  type="password"
                  id="confirm__password"
                  placeholder="변경할 비밀번호 확인"
                />
              </label>
            </div>

            <div class="mypage__update__error">
              비밀번호가 일치하지 않습니다.
            </div>

			<form id="mypage__update__form" >
	            <button class="mypage__update__button" type="submit" id="update_button">
	              수정하기
	            </button>
         	</form>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
  </body>
</html>

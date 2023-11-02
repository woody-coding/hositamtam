<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="../css/loginHeader.css" />
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
</head>
<body>
	
	<!-- Header -->
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <a href="/finalProject/views/main"><img class="mainHeader__logo__img" src="../images/logo.ico" alt="logo" /></a>
      </div>

      <form id="searchForm" method="GET" action="/finalProject/views/marketSearch">
        <div class="market__search">
          <input
            type="text"
            class="market__searchInput"
            name="keyword"
            id="keyword"
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
          <li><a href="/finalProject/views/post" class="mainHeader__menu__item">시끌시끌</a></li>
          <li><a class="mainHeader__menu__item" id="category">카테고리</a></li>
          <li><a href="/finalProject/views/login" class="mainHeader__menu__item" id="category">로그인</a></li> 
          <li><a href="/finalProject/views/join" class="mainHeader__menu__item" id="category">회원가입</a></li>
        </ul>
      </nav>
    </header>
</body>
</html>


<%--  로그인 되있으면 바꾸기 
<c:choose>
<c:when test="${sessionScope.nickname != null}">
	<a href="main.jsp?command=logout">${sessionScope.nickname}님/로그아웃</a>&nbsp;&nbsp;
	</c:when>
	<c:otherwise>
		
		<li><a href="/finalProject/views/login" class="mainHeader__menu__item" id="category">로그인</a></li> 
       <li><a href="/finalProject/views/join" class="mainHeader__menu__item" id="category">회원가입</a></li>
	</c:otherwise>
</c:choose> --%>









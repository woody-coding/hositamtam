<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
       <script src="../js/navi.js"></script>
</head>
<body>
	<%-- <form id="searchBox" action="market.jsp">
	    <input type="hidden" name="command" value="search" />
	    <input type="text" id="searchInput" name="query" placeholder="시장 이름을 입력해주세요. ex) 부평깡통시장" />
	</form>



    <div class="loginform">
        <h1>로그인</h1><br>
        
        <form action="login.jsp?command=login" method="POST" id="loginForm">
            <li>
                <label for="id">아이디</label><br>
                <input type="text" id="id" name="id" minlength="5" maxlength="20" required/>
            </li>
            <li>
                <label for="passwd">패스워드</label><br>
                <input type="password" id="passwd" name="passwd" minlength="5" maxlength="20" required/>
            </li>
            <button type="submit" id="submit" name="command" value="login">로그인</button>
            <p class="fail">${errorMsg}</p>
            <c:choose>
				<c:when test="${requestScope.loginAgainMsg != null}"><p class="fail">${requestScope.loginAgainMsg}</p></c:when>
			</c:choose>
        </form>
    </div> --%>
    
    
    
    
    <%-- <a href="login.jsp?command=logout">${sessionScope.nickname}님/로그아웃</a><br/> --%>
  
	<!-- Header -->
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <a href="/finalProject/views/main"><img class="mainHeader__logo__img" src="../images/logo.ico" alt="logo" /></a>
      </div>

		<form id="searchBox" action="market.jsp">
		    <input type="hidden" name="command" value="search" />
		    <input type="text" id="searchInput" name="query" placeholder="시장 이름을 입력해주세요. ex) 부평깡통시장" />
		</form>


	    <a href="market.jsp?cateno=1">농산물</a><br/>
	    <a href="market.jsp?cateno=2">음식점</a><br/>
	    <a href="market.jsp?cateno=3">가공식품</a><br/>
	    <a href="market.jsp?cateno=4">수산물</a><br/>
	    <a href="market.jsp?cateno=5">축산물</a><br/>
	    <a href="market.jsp?cateno=6">가정용품</a><br/>
	    <a href="market.jsp?cateno=7">의류</a><br/>
	    <a href="market.jsp?cateno=8">신발</a><br/>
	    <a href="market.jsp?cateno=9">기타</a>


<!-- 기존의 동영님 검색바 코드 
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
            name="action"ㅍ
            value="search"
          >
            <i class="fa-solid fa-magnifying-glass"></i>
          </button>
        </div>
      </form>
-->


      <nav class="mainHeader__nav">
        <ul class="mainHeader__menu">
          <li><a class="mainHeader__menu__item">서비스안내</a></li>
          <li><a href="/finalProject/views/post" class="mainHeader__menu__item">시끌시끌</a></li>
          <li><a href="#" class="mainHeader__menu__item" id="categoryOpen">카테고리</a></li>
          <li><a href="/finalProject/views/login" class="mainHeader__menu__item" id="category">로그인</a></li> 
          <li><a href="/finalProject/views/join" class="mainHeader__menu__item" id="category">회원가입</a></li>
        </ul>
      </nav>
    </header>
    <div class="listCategory">
			<div class="close"></div>
			<div class="listCategoryContent">

				<form method="POST" action="/finalProject/views/marketCategory">

					<p>어떤 시장이 궁금한가요?</p>
					<div class="goCategory">
						<a href="market.jsp?cateno=1">농산물</a>
						<!-- marketTest.jsp? 경로 바꿔야됨 -->
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=2">음식점</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=3">가공식품</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=4">수산물</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=5">축산물</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=6">가정용품</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=7">의류</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=8">신발</a>
					</div>
					<div class="goCategory">
						<a href="market.jsp?cateno=9">기타</a>
					</div>

				</form>
			</div>
		</div>
</body>
</html>


 <!-- 로그인 되있으면 바꾸기  -->
			<c:choose>
				<c:when test="${not empty sessionScope.memberInfo}">
					<a href="/finalProject/views/logout">${memberInfo.nickname}님 / 로그아웃</a>&nbsp;&nbsp;
			        <li><a href="/finalProject/views/myPage" class="mainHeader__menu__item" id="category">마이페이지</a></li>&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<li><a href="/finalProject/views/login" class="mainHeader__menu__item" id="category">로그인</a></li> 
			      	<li><a href="/finalProject/views/join" class="mainHeader__menu__item" id="category">회원가입</a></li>
				</c:otherwise>
			</c:choose>
        </ul>
      </nav>
    </header>

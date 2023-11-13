<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<!-- Header -->
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <a href="/finalProject/views/main"><img class="mainHeader__logo__img" src="../images/logo.ico" alt="logo" /></a>
      </div>

		<form id="searchBox" action="/finalProject/views/marketList">
		<div class="market__search">
			<button
	            class="market__searchButton"
	            type="submit"
	            name="action"
	            value="search"
	          >
	            <i class="fa-solid fa-magnifying-glass"></i>
	          </button>
		    <input type="text" id="searchInput" name="keyword" class="market__searchInput" placeholder="시장명 또는 주소를 입력하세요.  ex)부평" />

          </div>
		</form>


	 



      <nav class="mainHeader__nav">
        <ul class="mainHeader__menu">
          <li><a href="#section2" class="mainHeader__menu__item">서비스안내</a></li>
          <li><a href="#" class="mainHeader__menu__item dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            탐험하기
          </a>
           <ul class="dropdown-menu">
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=1" class="mainHeader__menu__category">농산물</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=2" class="mainHeader__menu__category">음식점</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=3" class="mainHeader__menu__category">가공식품</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=4" class="mainHeader__menu__category">수산물</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=5" class="mainHeader__menu__category">축산물</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=6" class="mainHeader__menu__category">가정용품</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=7" class="mainHeader__menu__category">의류</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=8" class="mainHeader__menu__category">신발</a></li>
             <li class="mainHeader__menu__li"><a href="/finalProject/views/marketList?cateno=9" class="mainHeader__menu__category">기타</a></li>    

           </ul>
           </li>
          <li><a href="/finalProject/views/post" class="mainHeader__menu__item">시끌시끌</a></li>

          <!-- 로그인 되있으면 바꾸기  -->
			<c:choose>
				<c:when test="${not empty sessionScope.memberInfo}">
			        <li><a href="/finalProject/views/myPage" class="mainHeader__menu__item" id="category">마이페이지</a></li>&nbsp;&nbsp;
					<li><a class="mainHeader__menu__item" href="/finalProject/views/logout">${memberInfo.nickname}님 / 로그아웃</a></li>&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<li><a href="/finalProject/views/login" class="mainHeader__menu__item" id="category">로그인</a></li> 
			      	<li><a href="/finalProject/views/join" class="mainHeader__menu__item" id="category">회원가입</a></li>
				</c:otherwise>
			</c:choose>
        </ul>
      </nav>
    </header>
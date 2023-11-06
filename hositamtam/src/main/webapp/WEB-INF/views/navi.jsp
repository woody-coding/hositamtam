<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<!-- Header -->
    <header class="mainHeader">
      <div class="mainHeader__logo">
        <a href="/finalProject/views/main"><img class="mainHeader__logo__img" src="../images/logo.ico" alt="logo" /></a>
      </div>

		<form id="searchBox" action="/finalProject/views/marketList">
		    <input type="hidden" name="command" value="search" />
		    <input type="text" id="searchInput" name="keyword" placeholder="시장 이름을 입력해주세요. ex) 부평깡통시장" />
		</form>


	    <a href="/finalProject/views/marketList?cateno=1">농산물</a><br/>
	    <a href="/finalProject/views/marketList?cateno=2">음식점</a><br/>
	    <a href="/finalProject/views/marketList?cateno=3">가공식품</a><br/>
	    <a href="/finalProject/views/marketList?cateno=4">수산물</a><br/>
	    <a href="/finalProject/views/marketList?cateno=5">축산물</a><br/>
	    <a href="/finalProject/views/marketList?cateno=6">가정용품</a><br/>
	    <a href="/finalProject/views/marketList?cateno=7">의류</a><br/>
	    <a href="/finalProject/views/marketList?cateno=8">신발</a><br/>
	    <a href="/finalProject/views/marketList?cateno=9">기타</a>


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
          <li><a class="mainHeader__menu__item" id="category">카테고리</a></li>
          
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

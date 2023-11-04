<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


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



<!--  기존의 검색바 동영님 코드
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
-->


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
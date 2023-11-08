<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <link rel="stylesheet" href="../css/loginHeader.css" />
    <link rel="stylesheet" href="../css/main.css" />

    <!-- JavaScript -->
   
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    ​<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
  />
  
    <script defer src="../js/main.js"></script>
    <script>
    
    
    	// 로그인에 성공하면 컨트롤러에서 무조건 main.jsp로 보내야 밑에 코드가 잘 실행됨! - 경인
	    let id = '<%= session.getAttribute("id") %>';
	    let nickname = '<%= session.getAttribute("nickname") %>';
	
	    
	    id = (id === 'null') ? null : id;
	    nickname = (nickname === 'null') ? null : nickname;
	    
	    console.log('id : ' + id + '    /    typeof : ' + typeof(id));
	    
	    // 로컬 스토리지에 저장되어 있는 데이터를 가져옴
	    const localStorageData = window.localStorage.getItem("memberIdNickname");
	    const localStorageMember = JSON.parse(localStorageData);
	
	    
	    if(id !== null) {
	    	if (localStorageMember !== null && localStorageMember.id !== id) {
	    		window.localStorage.removeItem('memberIdNickname');
	    	}
	    	
	        const member = { id: id, nickname: nickname };
	        const memberIdNickname = JSON.stringify(member);
	        window.localStorage.setItem('memberIdNickname', memberIdNickname);
	    }
	    else if(id === null && localStorageMember !== null) {
	    	window.localStorage.removeItem('memberIdNickname');
	    }
    
    
    
    
	    // 검색어 입력 필드에서 Enter 키를 눌렀을 때 검색 실행
	    document.getElementById("searchInput").addEventListener("keyup", function (event) {
	        if (event.key === "Enter") { // Enter 키가 눌렸을 때
	        
	            const searchInput = document.querySelector("#searchInput").value;
	            const encodedSearchInput = encodeURIComponent(searchInput);
	            const newURL = "market.jsp?command=search&query=" + encodedSearchInput;
	            window.location.href = newURL;
	        }
	  	  });
		}

		window.addEventListener('load', init);
    
    </script>
</head>
<body>
  <%@ include file="navi.jsp" %>
    <div  class="section" id="section1">
     <video src="../video/main영상.mp4" muted autoplay loop id="myVideo"> </video>

      
      <!-- header 끝 -->

      <div class="listCategory" >
        <div class="close"></div>
        <div class="listCategoryContent">

          <form method="POST" action="/finalProject/views/marketCategory">
            
            <p>어떤 시장이 궁금한가요?</p>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="1">농산물</button>
	
            </div>	
            <div class="goCategory">
             <button id="cateno" name="cateno" value="2">음식점</button>
            </div>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="3">가공식품</button>
            </div>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="4">수산물</button>
            </div>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="5">축산물</button>
            </div>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="6">가정용품</button>
            </div>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="7">의류</button>
            </div>
            <div class="goCategory">
              <button id="cateno" name="cateno" value="8">신발</button>
            </div>
            <div class="goCategory">
             <button id="cateno" name="cateno" value="9">기타</button>
            </div>
      
          </form>
        </div>  
      </div> 
     
      
      <div id="btnGroup">
        
          <button id="mkList" class="category"><i class="bi bi-basket-fill"></i><br> 시장 리스트 </button>
          <!-- <button id="category" class="category"><i class="bi bi-megaphone-fill"></i><br> 시끌시끌</button> -->
          <a id="category" class="category" href="/finalProject/views/post" class="mainHeader__menu__item"><i class="bi bi-megaphone-fill"></i><br> 시끌시끌</a>
          <button id="category" class="category"><i class="bi bi-info-circle-fill"></i><br><a href="#section2">서비스 안내</a></button>
         <!-- 서비스안내 조정 -->
      </div>
      <div id="busanExplain" >
        <p>부산 전통 시장</p>
         <p>부산의 맛과 문화를 만나다!</p>
     </div>
    </div>

  <!---------------------- section1 끝 ------------------------>

    <div class="section" id="section2">
      <!-- <h4>호시탐탐 안내</h4> -->
      <div class="hosiInfo">
        <p>부산 전통시장에 대한 정보를 공유하고 등록되지 않은 점포를 등록해보세요!</p>
        <!-- carosell 3초마다 이미지 변경 시장리스트사진 커뮤니티  모든 페이지 완성하면 캡쳐해서 슬라이드이미지로 넣기-->
        <div class="swiper">
          <!-- Additional required wrapper -->
          <div class="swiper-wrapper">
            <!-- Slides -->
            <div class="swiper-slide">시끌시끌사이트이미지</div>
            <div class="swiper-slide">시장사이트이미지</div>
            <div class="swiper-slide">시장내부사이트이미지</div>
            
          </div>
          <!-- If we need pagination -->
          <div class="swiper-pagination"></div>
        
          <!-- If we need navigation buttons -->
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        
         
        </div>
      </div>
      <h4>등급 안내</h4>
      <div class="gradeInfo">
        <div class="gradeIcon">
          <div class="gradeImage">
            <img src="../images/icons8-병아리-60.png" alt="">
  
            </div>
          <div class="gradeImage">
          <img src="../images/icons8-caveman-64.png" alt="">

          </div>
          <div class="gradeImage">
            <img src="../images/icons8-explorer-60.png" alt="">

          </div>
          <div class="gradeImage">
            <img src="../images/icons8-기사-64.png" alt="">

          </div>
          <div class="gradeImage">
            <img src="../images/icons8-spaceman-66.png" alt="">

          </div>

        </div>
        <div class="gradeLine">
          <div class="grade1"></div>
          <div class="grade2"></div>
          <div class="grade3"></div>
          <div class="grade4"></div>
          <div class="grade5"></div>

        </div>
        <div class="gradeNameBox">
          <div class="gradeName">시장 왕초보</div>
          <div class="gradeName">시장 햇병아리</div>
          <div class="gradeName">시장 탐험가</div>
          <div class="gradeName">시장 지킴이</div>
          <div class="gradeName">시장 지박령</div>

        </div>  
          
      </div>
      
    </div>
    <footer id="information" class="section">
      <div class="information__located">
        <div class="max-container">
          <img class="footer__logo__img" src="../images/logo.ico" alt="logo" />

		  <img class="footer__logo__img" src="../images/2030black-removebg-preview.png"  />



          <h2 class="information__title">&copy; TMI - All rights reserved</h2>
          <div class="information__contents">
            <p class="information__title">
              Creator <br />팀장: 안효철 &nbsp;&nbsp; 팀원: 김동영 <br />
              팀원: 김진성 &nbsp;&nbsp; 팀원: 남경인 <br />
              팀원: 석신성 &nbsp;&nbsp; 팀원: 주영진
            </p>
            <p class="information__title">
              <p>저희 호시탐탐은 2030 부산세계박람회 개최를 응원합니다!</p>
              <a href="https://github.com/wlstjd3398/TMI.git" target="blank"><i class="fa-brands fa-github"></i></a>
            </p>
            <div class="dropdown">
              <div class="btnDrop">관련사이트<i class="bi bi-chevron-down"></i>  </div>
              <ul class="dropDownMenu">
                <li><a class="dropdown-item" target="_blank" href="https://www.busan.go.kr/index">부산광역시</a></li>
                <li><a class="dropdown-item" target="_blank"  href="https://www.expo2030busan.kr/kor/index.do">2030 부산세계박람회</a></li>
                <li><a class="dropdown-item" target="_blank"  href="https://www.multicampus.com/main">멀티캠퍼스</a></li>
              </ul>
            </div>
            <a href="#top"><i class="bi bi-arrow-up-circle-fill"></i></a>
        
          </div>
        </div>
      </div>
    </footer> 
    
</body>
</html>
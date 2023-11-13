<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>호시탐탐</title>
<!-- Favicon -->
<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />

<!-- G-Market Fonts -->
<link href="https://webfontworld.github.io/gmarket/GmarketSans.css"
	rel="stylesheet" />

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/89490613c7.js"
	crossorigin="anonymous"></script>
<!-- CSS -->
<link rel="stylesheet" href="../css/mainHeader.css" />
<link rel="stylesheet" href="../css/main.css" />
<link rel="stylesheet" href="../css/wave.css" />
<!-- JavaScript -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
/>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	
<script>
	function init() {
		window.sessionStorage.removeItem('memberId');
		
		
	    // 현재 접속한 사용자가 회원인지 비회원인지 판단하기
	    let id = '<%= session.getAttribute("userId") %>';
	    
	    id = (id === 'null') ? null : id;
	    
	    // 세션 스토리지에 저장되어 있는 데이터를 가져옴
	    const sessionStorageData = window.sessionStorage.getItem("memberId");
	    const sessionStorageMember = JSON.parse(sessionStorageData);
	
	    if(id !== null) {
           	if (sessionStorageMember !== null && sessionStorageMember.id !== id) {
           		window.sessionStorage.removeItem('memberId');
           	}
           	
            const member = { id: id };
            const memberId = JSON.stringify(member);
            window.sessionStorage.setItem('memberId', memberId);
        }
        else if(id === null && sessionStorageMember !== null) {
            window.sessionStorage.removeItem('memberId');
        }
	}
	
	window.addEventListener('load', init);
</script>

<script src="../js/main.js"></script>

</head>
<body>

	<%@ include file="mainNavi.jsp" %>

    <div  class="section" id="section1">
      <video src="../video/main영상.mp4" muted autoplay loop id="myVideo"> </video> 

      

      <div class="listCategory" >
        <div class="close"></div>
        
        <div class="listCategoryContent">

          
            <p>어떤 시장이 궁금한가요?</p>
            <div class="goCategory">
         
           
            <ul>
	            <li><a href="/finalProject/views/marketList?cateno=1">농산물</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=2">음식점</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=3">가공식품</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=4">수산물</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=5">축산물</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=6">가정용품</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=7">의류</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=8">신발</a></li>
	            <li><a href="/finalProject/views/marketList?cateno=9">기타</a></li>
           </ul>
            </div>	
        </div>  
           <div class='wave -one'></div>
  <div class='wave -two'></div>
  <div class='wave -three'></div>
      </div> 
     
      
      <div id="btnGroup">
          <button id="mkList" class="category">
          
          <i class="bi bi-basket-fill"></i>
          <br>
<!--            시장 리스트  -->
     
           </button>
          <button id="goPost" class="category" onclick="location.href='/finalProject/views/post'"><i class="bi bi-megaphone-fill"></i>
<!--           <br> 시끌시끌 -->
          </button>
     
			<button id="service" class="category" onclick="location.href='#section2'">
				<i class="bi bi-info-circle-fill"></i>
<!-- 				<br> 서비스 안내 -->
			</button>
			<!-- 서비스안내 조정 -->
		</div>
		
		<div id="busanExplain">
			<p>부산 전통시장<br/>
			문화와 맛을 즐기다!</p>
		</div>
	</div>

	<!---------------------- section1 끝 ------------------------>

	<div class="section" id="section2">
		<!-- <h4>호시탐탐 안내</h4> -->
		<div id="goService" class="hosiInfo">
			<p>부산 전통시장에 대한 정보를 공유하고 등록되지 않은 점포를 등록해보세요!</p>
			<!-- carosell 3초마다 이미지 변경 시장리스트사진 커뮤니티  모든 페이지 완성하면 캡쳐해서 슬라이드이미지로 넣기-->

			<div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel" data-bs-pause="false">
  
          <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="1500">
              <img src="../images/시장리스트.PNG" >
              <div class="carousel-caption">
		        
    		  </div>
    		  <p>부산전통시장을 탐험해보세요!</p>
            </div>
            <div class="carousel-item" data-bs-interval="1500">
              <img src="../images/점포리스트.PNG" >
              <div class="carousel-caption ">
		       
    		  </div>
    		   <p>점포를 검색하고 등록되지 않은 점포를 등록해보세요!</p>
   	        </div>
            <div class="carousel-item" data-bs-interval="1500">
   			  <img src="../images/시끌시끌.PNG" >
              <div class="carousel-caption d-none d-md-block">
      		  	<!-- <p>전통시장과 관련된 정보를 다른 사용자들과 나누세요!</p> -->
  		    </div>
  		    	<p>전통시장과 관련된 정보를 다른 사용자들과 나누세요!</p>
            </div>
          </div>
          
        </div>
		</div>
		<br/>
		<br/>
		<h4>열심히 활동해 등급을 올려보세요!</h4>
		
		<div class="gradeInfo">
			<div class="gradeIcon">
				<div class="gradeImage">
					<img src="../images/icons8-병아리-60.png">
				</div>
				
				<div class="gradeImage">
					<img src="../images/icons8-caveman-64.png" >
				</div>
				<div class="gradeImage">
					<img src="../images/icons8-explorer-60.png" >
				</div>
				
				<div class="gradeImage">
					<img src="../images/icons8-기사-64.png" >
				</div>
				
				<div class="gradeImage">
					<img src="../images/icons8-spaceman-66.png" >
				</div>
			</div>
			
			<div class="gradeLine">
				<div class="grade1">시장 삐약이</div>
				<div class="grade2">시장 왕초보</div>
				<div class="grade3">시장 탐험가</div>
				<div class="grade4">시장 지킴이</div>
				<div class="grade5">시장 개척자</div>
			</div>
			<div class="expInfo">
			<span>경험치 : 리뷰, 댓글 개당 1점 | 게시글 개당 3점 | 점포 등록 개당 5점</span>
			</div>

		</div>

	</div>
	<footer id="information" class="section">
		<div class="information__located">
			<div class="max-container">
				<img class="footer__logo__img" src="../images/logo.ico" alt="logo" />

				<img class="footer__logo__img"
					src="../images/2030black-removebg-preview.png" />



				<h2 class="information__title">&copy; TMI - All rights reserved</h2>
				<div class="information__contents">
					<p class="information__title">
						Creator <br />팀장: 안효철 &nbsp;&nbsp; 팀원: 김동영 <br /> 팀원: 김진성
						&nbsp;&nbsp; 팀원: 남경인 <br /> 팀원: 석신성 &nbsp;&nbsp; 팀원: 주영진
					</p>
					<p class="information__title">
					<p>저희 호시탐탐은 2030 부산세계박람회 개최를 응원합니다!</p>
					<a href="https://github.com/wlstjd3398/hositamtam" target="blank">
					<i class="fa-brands fa-github"></i></a>
				
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">관련사이트</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" target="_blank"
								href="https://www.busan.go.kr/index">부산광역시</a></li>
							<li><a class="dropdown-item" target="_blank"
								href="https://www.expo2030busan.kr/kor/index.do">2030
									부산세계박람회</a></li>
							<li><a class="dropdown-item" target="_blank"
								href="https://www.multicampus.com/main">멀티캠퍼스</a></li>
						</ul>
					</div>
					<a href="#top"><i class="bi bi-arrow-up-circle-fill"></i></a>

				</div>
			</div>
		</div>
	</footer>
</body>
</html>
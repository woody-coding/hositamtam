<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
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
	<link rel="stylesheet" href="../css/loginHeader.css" />
	
	<link rel="stylesheet" href="../css/footer.css" />
	<link rel="stylesheet" href="../css/storeList.css" />
	
	
	<!-- JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>

		function init() {
			
			// 1. 파라미터 mno 값 읽어오기
		    let mno = '<%= request.getAttribute("mno") %>';
		    let msg = '<%= request.getAttribute("msg") %>';
		
		    mno = (mno === 'null') ? null : mno;
		    msg = (msg === 'null') ? null : msg;
		    
		    //console.log('mno : ' + mno + '    /    typeof : ' + typeof(mno));
		
		    // 기존의 로컬은 무조건 삭제 후, 다시 등록
			window.localStorage.removeItem('mnoToStore');
		    	
		    const whatMno = { mno: mno, msg: msg };
		    const mnoToStore = JSON.stringify(whatMno);
		    window.localStorage.setItem('mnoToStore', mnoToStore);
	
		}
		
		window.addEventListener('load', init);

	</script>
    <script src="/finalProject/js/storeList.js"></script>
</head>
<body>
	<%@ include file="navi.jsp" %>
	
	<div class="section">
		<div class="mkSideBox store">  
			<div id="marketName"></div>
			 <div class="btnSort">

				<button id="recentInsert">최신순</button>
				<button id="manyReview">리뷰순</button>
				<button id="manyRating">별점순</button>
				<button id="manyStoreLike">찜 순</button>
			</div>
			<div id="storeContent"></div>
			<div id="storeErrorMsg"></div>
			<div id="errMsg"></div>
		</div>
		
		<div class ="map store" id="map">
			
			<button id="insertStore" class="plusStore">
			<i class="bi bi-plus-circle-fill"></i>
			<span>점포등록!</span>
			</button>
		</div>
	
	</div>

<%-- 	 <%@ include file="footer.jsp" %>  --%>
</body>
</html>
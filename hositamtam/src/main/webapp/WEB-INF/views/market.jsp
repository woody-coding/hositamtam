<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<link rel="stylesheet" href="../css/loginHeader.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/marketList.css" />


<!-- JavaScript -->
<!-- 경인키 51l0xj0874 -->
<!-- 동영키 e9fw481dyk 5502 동작-->
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

<script>

	function init() {
		
		// 로컬 스토리지를 활용해서 파라미터 keyword 혹은 cateno값 읽어오기
	    let keyword = '<%= request.getAttribute("keyword") %>';
	    let cateno = '<%= request.getAttribute("cateno") %>';
	    let msg = '<%= request.getAttribute("msg") %>';
	    
	    //alert(cateno);
	
	    
	    keyword = (keyword === 'null') ? null : keyword;
	    cateno = (cateno === 'null') ? null : cateno;
	    msg = (msg === 'null') ? null : msg;
	    
	    //console.log('keyword : ' + keyword + '    /    typeof : ' + typeof(keyword));
	    //console.log('cateno : ' + cateno + '    /    typeof : ' + typeof(cateno));
	
	    // 기존의 로컬은 무조건 삭제 후, 다시 등록
		window.localStorage.removeItem('KeywordAndCateno');
	    	
	    const keyCateno = { keyword: keyword, cateno: cateno, msg: msg };
	    const KeywordAndCateno = JSON.stringify(keyCateno);
	    window.localStorage.setItem('KeywordAndCateno', KeywordAndCateno);
	
		//alert(localStorage.getItem('KeywordAndCateno'));
	}
	
	window.addEventListener('load', init);

</script>
<script src="/finalProject/js/marketList.js"></script>
</head>

<body>
	<%@ include file="navi.jsp" %>

	<div class="section">

<!-- 아래의 howGetMarket에서 출력하는 문구는 -->
<!-- 'oo으로 조회한 결과입니다.'임. marketList.js 파일을 참고. -->
<!-- 향후, 조회 수를 쿼리로 불러올 경우를 대비하여 -->
<!-- 동일한 id의 class로 div를 만들어두었음. 예시문구) '총 15건 조회' -->
 
				<div class="howGetMarket">
				<div id="howGetMarket"></div>
				</div>
				
				<div class="mkSideBox">
<!-- 				<div id="howGetMarket"></div> -->
				<div id="marketErrorMsg"></div>

				<div id="mkListResult" class="row">
				<div id="marketContent"></div>
				</div> 
				</div>
				
		 	<div id="map" ></div>
		 
		
	</div>
	
	
	
<%-- <%@ include file="footer.jsp"%> --%>
	
</body>
</html>
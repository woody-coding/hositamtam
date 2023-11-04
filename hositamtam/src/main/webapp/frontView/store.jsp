<%@ page contentType="text/html; charset=UTF-8"%>




<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>시장 내 점포 리스트 지도로 뿌리기</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <script>
    
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
  
  

<div id="map" style="width:1100px; height:700px;"></div>

<div id="marketName"></div>

<button id="manyReview">리뷰 많은 순</button>
<button id="manyRating">별점 높은 순</button>
<button id="manyStoreLike">찜 많은 순</button>

<div id="storeContent"></div>

<button id="insertStore">새 점포 등록</button><br/>

<script charset="UTF-8" src="/js/storeList.js"></script>

	<%@ include file="footer.jsp" %>
</body>
</html>
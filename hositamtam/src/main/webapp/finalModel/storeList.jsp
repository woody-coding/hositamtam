<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>시장 내 점포 리스트 지도로 뿌리기</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
    <script>
    
		// JavaScript를 사용하여 세션에서 정보를 가져와 로컬 스토리지에 저장
	    
		function init() {
			
			const id = "${id}";
		    const nickname = "${nickname}";
			
		    
		    // 세션 값 o, 로컬 x		=> 로컬에 저장하기		||		세션 값 x, 로컬 o		=> 로컬에서 삭제하기
			if(id !== null && window.localStorage.getItem("memberInfo") === null) {
	
			    const member = { id: id, nickname: nickname };
			    const memberInfo = JSON.stringify(member);
			    window.localStorage.setItem('memberInfo', memberInfo);
			}
			else if(id === null && window.localStorage.getItem("memberInfo") !== null) {
				window.localStorage.removeItem('memberInfo');
			}
	
		    
		}
		
	    window.addEventListener('load', init);
		
	</script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
</head>
<body>


<div id="map" style="width:1100px; height:700px;"></div>
<button id="insertStore">새 점포 등록</button><br/>

<button id="manyReview">리뷰 많은 순</button>
<button id="manyRating">별점 높은 순</button>
<button id="manyStoreLike">찜 많은 순</button>

<div id="storeContent"></div>

<script charset="UTF-8" src="storeList.js"></script>

</body>
</html>
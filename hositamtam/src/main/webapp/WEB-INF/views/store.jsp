<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>시장 내 점포 리스트 지도로 뿌리기</title>
    <!-- Favicon -->
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
	
	<!-- G-Market Fonts -->
	<link href="https://webfontworld.github.io/gmarket/GmarketSans.css"
		rel="stylesheet" />
	
	<!-- Font Awesome -->
	<script src="https://kit.fontawesome.com/89490613c7.js"
		crossorigin="anonymous"></script>
	
	<!-- CSS -->
	<link rel="stylesheet" href="../css/loginHeader.css" />
	<!-- <link rel="stylesheet" href="../css/login.css" /> -->
	<link rel="stylesheet" href="../css/footer.css" />
	<link rel="stylesheet" href="../css/storeList.css" />
	
	
	<!-- JavaScript -->
	<!-- 경인키 51l0xj0874 -->
	<!-- 동영키 e9fw481dyk 5502 동작-->
	<script type="text/javascript"
		src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
		crossorigin="anonymous">
    <script>

		function init() {
			
			// 1. 파라미터 mno 값 읽어오기
		    let mno = '<%= request.getAttribute("mno") %>';
		
		    mno = (mno === 'null') ? null : mno;
		    
		    console.log('mno : ' + mno + '    /    typeof : ' + typeof(mno));
		
		    // 기존의 로컬은 무조건 삭제 후, 다시 등록
			window.localStorage.removeItem('mnoToStore');
		    	
		    const whatMno = { mno: mno };
		    const mnoToStore = JSON.stringify(whatMno);
		    window.localStorage.setItem('mnoToStore', mnoToStore);
		
		    
		    
		    
		    // 2. 현재 접속한 사용자가 회원인지 비회원인지 판단하기
		    let id = '<%= session.getAttribute("userId") %>';
		
		    
		    id = (id === 'null') ? null : id;
		    
		    console.log('id : ' + id + '    /    typeof : ' + typeof(id));
		    
		    // 로컬 스토리지에 저장되어 있는 데이터를 가져옴
		    const localStorageData = window.localStorage.getItem("memberId");
		    const localStorageMember = JSON.parse(localStorageData);
		
            
		    if(id !== null) {
            	if (localStorageMember !== null && localStorageMember.id !== id) {
            		window.localStorage.removeItem('memberId');
            	}
            	
                const member = { id: id };
                const memberId = JSON.stringify(member);
                window.localStorage.setItem('memberId', memberId);
            }
            else if(id === null && localStorageMember !== null) {
            	window.localStorage.removeItem('memberId');
            }
		    
		    
		
		}
		
		window.addEventListener('load', init);

	</script>
    <script src="/finalProject/js/storeList.js"></script>
</head>
<body>
	
	
	<div id="map" style="width:1100px; height:700px;"></div>
	
	<div id="marketName"></div>
	
	<button id="manyReview">리뷰 많은 순</button>
	<button id="manyRating">별점 높은 순</button>
	<button id="manyStoreLike">찜 많은 순</button>
	
	<div id="storeContent"></div>
	
	<button id="insertStore">새 점포 등록</button><br/>
	
	
	
</body>
</html>
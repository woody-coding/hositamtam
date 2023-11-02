<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=51l0xj0874"></script>
   	<script>
			// JavaScript를 사용하여 정보를 가져와 로컬 스토리지에 저장
		    //const cateno = 1;
		  //	const marketCateno = { cateno: cateno };
		   // const marketCategory = JSON.stringify(marketCateno);
		   // window.localStorage.setItem('marketCategory', marketCategory);
		
			//const storedData = window.localStorage.getItem('marketCategory');
			//console.log(storedData);
			
			
			//window.localStorage.removeItem('marketSearchKeyword');
			
 			//const keyword = '용호';
		   // const marketKeyword = { keyword: keyword };
		   // const marketSearchKeyword = JSON.stringify(marketKeyword);
		  //  window.localStorage.setItem('marketSearchKeyword', marketSearchKeyword);
		   // const marketSearchData = window.localStorage.getItem('marketSearchKeyword');
			//console.log(marketSearchData);
			
		// JavaScript를 사용하여 세션에서 정보를 가져와 로컬 스토리지에 저장
	    
		function init() {
			
			const id = "${id}";
		    const nickname = "${nickname}";
			
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

	<div id="map" style="width: 1100px; height: 700px;"></div>
	<script charset="UTF-8" src="marketList.js"></script>

</body>
</html>
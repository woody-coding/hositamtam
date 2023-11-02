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
		/*
			const mno = 2;
		    const marketmno = { mno: mno };
		    const marketMno = JSON.stringify(marketmno);
		    window.localStorage.setItem('marketMno', marketMno);
		    
		  const marketMnoData = window.localStorage.getItem('marketMno');
			console.log(marketMnoData);
		*/
		
		
/*		


function init() {
	
		const memberInfo = window.localStorage.getItem('memberInfo');
   
		if (memberInfo) {
		    const member = JSON.parse(memberInfo);
		    //console.log('id:', member.id);
		    //console.log('nickname:', member.nickname);
		    
		    currentId = member.id;
		    currentNickname = member.nickname;
		} else {
		    console.log('로컬 스토리지에 멤버 정보가 없습니다.');
		}
	




		document.querySelector('#부모 html 태그의 id 값').addEventListener.('click', function(event) {
       		if (event.target.className === '좋아요 버튼 클래스 값') {
			
	            let currentPno = event.target.getAttribute('id');
				xhr.onreadystatechange = postLikeAjaxHandler;
				
		        let param = '?command=updateLike&pno=' + currentPno + '&id=' + currentId;
		        xhr.open('GET', 'toAjaxController.jsp' + param, true);
		        xhr.send();
	        }
		});

}

	    document.addEventListener('click', function (event) {
	        if (event.target.className === '좋아요 버튼 클래스 값') {
	            const pno = event.target.getAttribute('id');
	            const marketmno = { pno: pno };
	            const marketMno = JSON.stringify(marketmno);
	            window.localStorage.setItem('marketMno', marketMno);
	            
	            const marketMnoData = window.localStorage.getItem('marketMno');
	            console.log(marketMnoData);
	        }
	    });
*/	    
	</script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
</head>
<body>


<div id="map" style="width:1100px; height:700px;"></div>
<button id="insertStore">새 점포 등록</button>
<script charset="UTF-8" src="storeList.js"></script>

</body>
</html>
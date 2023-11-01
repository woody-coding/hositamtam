<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
	
	    // JavaScript를 사용하여 세션에서 정보를 가져와 로컬 스토리지에 저장
	    const id = "${id}";
	    const nickname = "${nickname}";
	    const member = { id: id, nickname: nickname };
	    const memberInfo = JSON.stringify(member);
	    window.localStorage.setItem('memberInfo', memberInfo);
	    
	</script>
	<script src="ajaxTest3.js" charset="UTF-8"></script>
	<link rel="stylesheet" href="ajaxTest.css" />
</head>

<body>

	<h3>AJAX 비동기 통신으로 데이터 받아서 화면 구성하기~!</h3>
	<button id="whatMarket">시장 선택하기</button>
	<hr/>
	<div id="markets"></div>
	<hr/>
	
	<button id="hot">인기글</button>
	<button id="que">시장질문</button>
	<button id="acc">사건사고</button>
	<button id="day">일상</button>
	<button id="lost">실종/분실</button>
	<hr/>
	
	<div id="pcontent"></div>
</body>
</html>
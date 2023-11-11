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
<script src="localStorageTest.js"></script>
</head>
<body>

	<p>${sessionScope.nickname}님 로그인이 완료되었습니다!</p>

</body>
</html>
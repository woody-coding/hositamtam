<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
    <title>로그인과 검색</title>
    <meta charset="UTF-8" />
    <script>
    
		function init() {
			
			
		    let id = '<%= session.getAttribute("id") %>';
		    let nickname = '<%= session.getAttribute("nickname") %>';
		
		    
		    id = (id === 'null') ? null : id;
		    nickname = (nickname === 'null') ? null : nickname;
		    
		    console.log('id : ' + id + '    /    typeof : ' + typeof(id));
		    
		    // 로컬 스토리지에 저장되어 있는 데이터를 가져옴
		    const localStorageData = window.localStorage.getItem("memberIdNickname");
		    const localStorageMember = JSON.parse(localStorageData);
		
            
		    if(id !== null) {
            	if (localStorageMember !== null && localStorageMember.id !== id) {
            		window.localStorage.removeItem('memberIdNickname');
            	}
            	
                const member = { id: id, nickname: nickname };
                const memberIdNickname = JSON.stringify(member);
                window.localStorage.setItem('memberIdNickname', memberIdNickname);
            }
            else if(id === null && localStorageMember !== null) {
            	window.localStorage.removeItem('memberIdNickname');
            }

            
			
			
			
			
	        // 검색어 입력 필드에서 Enter 키를 눌렀을 때 검색 실행
	        document.getElementById("searchInput").addEventListener("keyup", function (event) {
	            if (event.key === "Enter") { // Enter 키가 눌렸을 때
	            
	                const searchInput = document.querySelector("#searchInput").value;
	                const encodedSearchInput = encodeURIComponent(searchInput);
	                const newURL = "marketTest.jsp?command=search&query=" + encodedSearchInput;
	                window.location.href = newURL;
	            }
	        });
		}
	
		window.addEventListener('load', init);
		
    </script>
</head>

<body>

	<form id="searchBox" action="marketTest.jsp">
	    <input type="hidden" name="command" value="search" />
	    <input type="text" id="searchInput" name="query" placeholder="시장 이름을 입력해주세요. ex) 부평깡통시장" />
	</form>
	<br/><br/>

    
    
	<c:choose>
		<c:when test="${sessionScope.nickname != null}">
			<a href="login.jsp?command=logout">${sessionScope.nickname}님/로그아웃</a>&nbsp;&nbsp;
		</c:when>
		<c:otherwise>
			<a href="realLoginView.jsp" target="_self">로그인/회원가입</a>
		</c:otherwise>
	</c:choose>
    
    <br/><br/>
    <a href="marketTest.jsp?cateno=1">농산물</a><br/>
    <a href="marketTest.jsp?cateno=2">음식점</a><br/>
    <a href="marketTest.jsp?cateno=3">가공식품</a><br/>
    <a href="marketTest.jsp?cateno=4">수산물</a><br/>
    <a href="marketTest.jsp?cateno=5">축산물</a><br/>
    <a href="marketTest.jsp?cateno=6">가정용품</a><br/>
    <a href="marketTest.jsp?cateno=7">의류</a><br/>
    <a href="marketTest.jsp?cateno=8">신발</a><br/>
    <a href="marketTest.jsp?cateno=9">기타</a>
    
</body>
</html>
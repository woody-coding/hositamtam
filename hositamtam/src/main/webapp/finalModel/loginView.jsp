<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
    <title>로그인과 검색</title>
    <meta charset="UTF-8" />
    <script>
    
		function init() {
	        // 검색어 입력 필드에서 Enter 키를 눌렀을 때 검색 실행
	        document.getElementById("searchInput").addEventListener("keyup", function (event) {
	            if (event.key === "Enter") { // Enter 키가 눌렸을 때
	            
	                const searchInput = document.getElementById("searchInput").value;
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



    <div class="loginform">
        <h1>로그인</h1><br>
        
        <form action="login.jsp?command=login" method="POST" id="loginForm">
            <li>
                <label for="id">아이디</label><br>
                <input type="text" id="id" name="id" minlength="5" maxlength="20" required/>
            </li>
            <li>
                <label for="passwd">패스워드</label><br>
                <input type="password" id="passwd" name="passwd" minlength="5" maxlength="20" required/>
            </li>
            <button type="submit" id="submit" name="command" value="login">로그인</button>
            <p class="fail">${errorMsg}</p>
            <c:choose>
				<c:when test="${requestScope.loginAgainMsg != null}"><p class="fail">${requestScope.loginAgainMsg}</p></c:when>
			</c:choose>
        </form>
    </div>
    
    
    
    
    <a href="login.jsp?command=logout">${sessionScope.nickname}님/로그아웃</a>
    
</body>
</html>
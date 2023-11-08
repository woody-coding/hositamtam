<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
    <title>로그인과 검색</title>
    <meta charset="UTF-8" />
</head>

<body>

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

    
</body>
</html>
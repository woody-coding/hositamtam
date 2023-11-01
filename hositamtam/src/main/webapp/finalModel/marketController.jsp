<%@ page contentType="text/html; charset=UTF-8"%>

<jsp:useBean id="memberDAO" class="finalModel.MemberDAO" scope="session" />
<jsp:useBean id="memberDO" class="finalModel.MemberDO" scope="session" />
<jsp:setProperty name="memberDO" property="*" /> 

<%
	String command = request.getParameter("command");

	// 로그인 작업
	if(request.getMethod().equals("POST") && command != null && command.equals("login"))
	
		// 로그인 성공할 때
		if(memberDAO.loginCheck(memberDO)) {
			session.setAttribute("id", memberDO.getId());
			session.setAttribute("nickname", memberDO.getNickname());
		    response.sendRedirect("ajaxTest.jsp");
		}
		
		// 로그인 실패할 때 -> 로그인 페이지를 재로드 시키기
		else {
			request.setAttribute("loginAgainMsg", "로그인에 실패하였습니다. 다시 시도해주시기 바랍니다.");
			pageContext.forward("loginView.jsp");
		}
	
%>
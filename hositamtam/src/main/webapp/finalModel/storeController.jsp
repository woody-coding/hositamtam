<%@ page contentType="text/html; charset=UTF-8"%>

<jsp:useBean id="memberDAO" class="finalModel.MemberDAO" scope="session" />
<jsp:useBean id="memberDO" class="finalModel.MemberDO" scope="session" />
<jsp:useBean id="storeDO" class="finalModel.StoreDO" scope="session" />
<jsp:setProperty name="memberDO" property="*" /> 
<jsp:setProperty name="storeDO" property="*" />

<%
	String command = request.getParameter("command");
	String mlat = request.getParameter("mlat");
	String mlng = request.getParameter("mlng");

	// 로그인 작업
	//if(request.getMethod().equals("GET") && command != null && command.equals("insertStore")) {
	//	
	//}
	if(command != null && command.equals("getStoreInMarket")) {
		pageContext.forward("storeList.jsp");
	}
	else if (command != null && command.equals("insertStore")) {
    	
        String slat = request.getParameter("slat");
        String slng = request.getParameter("slng");
        String param = "?command=insertStorePage&slat=" + slat + "&slng=" +slng";
        
		pageContext.forward("marketList.html" + param);
        // 추출된 값 출력 또는 필요한 작업 수행
    }
	
		
	
%>
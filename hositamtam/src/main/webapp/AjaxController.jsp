<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>

<jsp:useBean id="memberDAO" class="model.MemberDAO" />
<jsp:useBean id="jsonMarketStore" class="model.JsonMarketStore" />
<jsp:useBean id="memberDO" class="model.MemberDO" />
<jsp:setProperty name="memberDO" property="*" />

<%
	//command 파라미터 값을 가져옵니다.
	jsonMarketStore.setMemberDAO(memberDAO);
	String command = request.getParameter("command");

	if (command != null && command.equals("checkNickname")) {
		
		// nickname 파라미터 값을 가져와서 newNickname에 담음
		String newNickname = request.getParameter("nickname");
		memberDO.setNickname(newNickname);
		// JSON 형태의 응답 생성
		out.println(jsonMarketStore.getCheckNickName(memberDO));
		out.flush();
	} 
	
	if(command != null && command.equals("updateProfile")){
		
		String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        
        
        
        out.println();
		out.flush();
	}
%>
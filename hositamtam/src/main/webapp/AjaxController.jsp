<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>

<jsp:useBean id="memberDAO" class="model.MemberDAO" />
<jsp:useBean id="jsonMarketStore" class="model.JsonMarketStore" />
<jsp:useBean id="memberDO" class="model.MemberDO" />
<jsp:setProperty name="memberDO" property="*" />

<%
	// 닉네임 중복확인에 대한 파라미터 가져오기
	//command 파라미터 값을 가져옵니다.
	jsonMarketStore.setMemberDAO(memberDAO);
	String command = request.getParameter("command");

	// nickname 파라미터 값을 가져와서 newNickname에 담음
	String newNickname = request.getParameter("nickname");
	
	String id = request.getParameter("id");
	
	// newPassword 파라미터 값을 가져와서 newNickname에 담음
	String newPassword = request.getParameter("password");
	
	if (command != null && command.equals("checkNickname")) {
		
		memberDO.setNickname(newNickname);
		// JSON 형태의 응답 생성
		out.println(jsonMarketStore.getCheckNickName(memberDO));
		out.flush();
	} 
	
	// 비밀번호 중복확인에 대한 파라미터 가져오기
	if(command != null && command.equals("checkPassword")){

    	 // JSON 형태의 응답 생성
        out.println(jsonMarketStore.getCheckPasswd(newPassword, id));
		out.flush();
	}
	
	// 닉네임과 비밀번호에 대한 파라미터 가져오기
	if(command != null && command.equals("changeProfile")){
		
		memberDO.setNickname(newNickname);
        memberDO.setPasswd(newPassword);
        
     // JSON 형태의 응답 생성
        out.println(jsonMarketStore.getChangeProfile(memberDO));
		out.flush();
	}
%>
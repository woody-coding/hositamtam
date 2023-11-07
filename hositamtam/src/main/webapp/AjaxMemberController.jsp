<%@ page contentType="text/html; charset=UTF-8" import="model.MemberDO"%>

<jsp:useBean id="memberDAO" class="model.MemberDAO" />
<jsp:useBean id="JsonNickName" class="model.JsonCheckNickName" />
<jsp:useBean id="memberDO" class="model.MemberDO" />
<jsp:setProperty name="memberDO" property="*" />

<%
	JsonNickName.setMemberDAO(memberDAO);
	String command = request.getParameter("command");

	if (command != null && command.equals("checkNickname")) {
		// 클라이언트에서 전달한 닉네임 값 가져오기
		String newNickname = request.getParameter("newNickname");

		// 닉네임 중복 확인
		boolean isDuplicate = memberDAO.isNicknameDuplicate(newNickname);

		// JSON 형태의 응답 생성
		String jsonResponse = JsonNickName.getCheckNickName(isDuplicate);
		out.println(jsonResponse);
		out.flush();
	} 
%>
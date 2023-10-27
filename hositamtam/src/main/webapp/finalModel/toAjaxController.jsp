<%@ page contentType="application/json; charset=UTF-8"%>

<jsp:useBean id="postDAO" class="finalModel.PostDAO" />

<%
	String command = request.getParameter("command");
	String mno = request.getParameter("mno");
	
	if(command != null && command.equals("getMarket")) {
		out.println(postDAO.getAllMarket());
	}
	else if(command != null && command.equals("getPost")) {
		out.println(postDAO.getAllPost((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("hot")) {
		out.println(postDAO.getPostHot((Integer.parseInt(mno))));
	}

/*	
	else if(command != null && command.equals("que")) {
		String mno = request.getParameter("mno");
		out.println(postDAO.getPostQue(Integer.parseInt(mno)));
	}
	else if(command != null && command.equals("acc")) {
		String mno = request.getParameter("mno");
		out.println(postDAO.getPostAcc(Integer.parseInt(mno)));
	}
	else if(command != null && command.equals("day")) {
		String mno = request.getParameter("mno");
		out.println(postDAO.getPostDay(Integer.parseInt(mno)));
	}
	else if(command != null && command.equals("lost")) {
		String mno = request.getParameter("mno");
		out.println(postDAO.getPostLost(Integer.parseInt(mno)));
	}
*/	
	

	out.flush();
%>
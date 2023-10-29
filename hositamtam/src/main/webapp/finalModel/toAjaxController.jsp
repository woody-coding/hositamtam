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
	else if(command != null && command.equals("que")) {
		out.println(postDAO.getPostQue((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("acc")) {
		out.println(postDAO.getPostAcc((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("day")) {
		out.println(postDAO.getPostDay((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("lost")) {
		out.println(postDAO.getPostLost((Integer.parseInt(mno))));
	}
	

	out.flush();
%>
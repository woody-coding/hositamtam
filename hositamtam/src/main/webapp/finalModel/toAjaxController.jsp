<%@ page contentType="application/json; charset=UTF-8"%>


<jsp:useBean id="postDAO" class="finalModel.PostDAO" />
<jsp:useBean id="marketDAO" class="finalModel.MarketDAO" />
<jsp:useBean id="storeDAO" class="finalModel.StoreDAO" />


<%
	String command = request.getParameter("command");
	String mno = request.getParameter("mno");
	String pno = request.getParameter("pno");
	String sno = request.getParameter("sno");
	String id = request.getParameter("id");
	String cno = request.getParameter("cno");
	String cateno = request.getParameter("cateno");
	String keyword = request.getParameter("keyword");
	
	
	if(command != null && command.equals("getMarket")) {
		out.println(postDAO.getAllMarket());
	}
	else if(command != null && command.equals("getMarketList")) {
		out.println(marketDAO.getMarketList());
	}
	else if(command != null && command.equals("getMarketListByItem")) {
		out.println(marketDAO.getMarketListByItem((Integer.parseInt(cateno))));
	}
	else if(command != null && command.equals("getMarketListBySearch")) {
		out.println(marketDAO.getMarketListBySearch(keyword));
	}
	else if(command != null && command.equals("getStoreInMarket")) {
		out.println(storeDAO.getStoreInMarket((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getMarketLatLng")) {
		out.println(marketDAO.getMarketLatLng((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getManyReview")) {
		out.println(storeDAO.getManyReview((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getManyRating")) {
		out.println(storeDAO.getManyRating((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getManyStoreLike")) {
		out.println(storeDAO.getManyStoreLike((Integer.parseInt(mno))));
	}
//	else if(command != null && command.equals("notStore")) {
//		out.println(storeDAO.notStore((Integer.parseInt(sno))));
//	}
//	else if(command != null && command.equals("insertStore")) {
//		out.println(storeDAO.getStoreInMarket((Integer.parseInt(mno))));
//	}
	else if(command != null && command.equals("getPost")) {
		out.println(postDAO.getAllPost((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getComments")) {
		out.println(postDAO.getComments((Integer.parseInt(pno))));
	}
	else if(command != null && command.equals("getPnoPost")) {
		out.println(postDAO.getPcontent((Integer.parseInt(pno))));
	}
	else if(command != null && command.equals("updateLike")) {
		out.println(postDAO.updateLike((Integer.parseInt(pno)), id));
	}

//	else if(command != null && command.equals("commentModify")) {
//		out.println(postDAO.commentModify((Integer.parseInt(cno)), id));
//	}
//	else if(command != null && command.equals("commentDelete")) {
//		out.println(postDAO.commentDelete((Integer.parseInt(cno)), id));
//	}

	
	out.flush();
%>
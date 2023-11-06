<%@ page contentType="application/json; charset=UTF-8"%>


<jsp:useBean id="postDAO" class="model.PostDAO" />
<jsp:useBean id="marketDAO" class="model.MarketDAO" />
<jsp:useBean id="storeDAO" class="model.StoreDAO" />


<%
	String command = request.getParameter("command");
	String mno = request.getParameter("mno");
	String pno = request.getParameter("pno");
	String sno = request.getParameter("sno");
	String id = request.getParameter("id");
	String cno = request.getParameter("cno");
	String cateno = request.getParameter("cateno");
	String keyword = request.getParameter("keyword");
	
	
	if(command != null && command.equals("getMarketList")) {
		out.println(marketDAO.getMarketList());
	}
	else if(command != null && command.equals("getMarketListByItem")) {
		if(!cateno.equals("1") && !cateno.equals("2") && !cateno.equals("3") && !cateno.equals("4") && !cateno.equals("5") && !cateno.equals("6") && !cateno.equals("7") && !cateno.equals("8") && !cateno.equals("9")) {
			cateno = "11";
		}
		
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
	//else if(command != null && command.equals("updateLike")) {
	//	out.println(postDAO.updateLike((Integer.parseInt(pno)), id));
	//}

	
	out.flush();
%>
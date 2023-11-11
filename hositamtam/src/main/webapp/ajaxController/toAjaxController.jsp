<%@ page contentType="application/json; charset=UTF-8"%>

<%
	System.out.println("joke");
	System.out.println("command=" + request.getParameter("command"));
	System.out.println("slat=" + request.getParameter("slat"));
	System.out.println("slng=" + request.getParameter("slng"));
%>
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
        try {
            int number = Integer.parseInt(mno);
            // 정수로 변환 가능한 경우
            out.println(marketDAO.getMarketLatLng(number));
        } catch (NumberFormatException e) {
            // 정수로 변환할 수 없는 경우
        	out.println(marketDAO.getMarketLatLng(0));
        }	
	}
	
//신성추가 : 점포 최신 등록순	
	else if(command != null && command.equals("getRecentInsert")) {
		out.println(storeDAO.getRecentInsert((Integer.parseInt(mno))));
	}
//
	else if(command != null && command.equals("getManyReview")) {
		out.println(storeDAO.getManyReview((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getManyRating")) {
		out.println(storeDAO.getManyRating((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("getManyStoreLike")) {
		out.println(storeDAO.getManyStoreLike((Integer.parseInt(mno))));
	}
	else if(command != null && command.equals("updateLike")) {
		out.println(postDAO.updateLike((Integer.parseInt(pno)), id));
	}
	else if(command != null && command.equals("updateLikeStore")) {
		out.println(storeDAO.updateLikeStore((Integer.parseInt(sno)), id));
	}
	// 글 좋아요 상태 확인
	else if(command != null && command.equals("updateLikeStatus")) {
		out.println(postDAO.updateLikeStatus((Integer.parseInt(pno))));
	}
	// 점포 찜 상태 확인
	else if(command != null && command.equals("updateLikeStoreStatus")) {
		out.println(storeDAO.updateLikeStoreStatus((Integer.parseInt(sno))));
	}
	
	// 점포 제보
	else if(command != null && command.equals("notStore")) {
		out.println(storeDAO.notStore((Integer.parseInt(sno)), id));
	}
	// 점포 제보 상태 확인
	else if(command != null && command.equals("notStoreStatus")) {
		out.println(storeDAO.notStoreStatus((Integer.parseInt(sno)), id));
	}
	
	// 점포 등록
	else if(command != null && command.equals("insertStore")) {
		RequestDispatcher dis = request.getRequestDispatcher("/views/storeInsert");
		dis.forward(request, response);

	}
	
	
	out.flush();
%>
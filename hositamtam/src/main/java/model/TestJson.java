package model;

public class TestJson {

	public static void main(String[] args) throws Exception {
		
		PostDAO postDAO = new PostDAO();
	//	System.out.println(postDAO.getAllPost(100));
	//	System.out.println(postDAO.getComments(1));
	//	System.out.println(postDAO.updateLike(1, "longlee"));
		
		MarketDAO marketDAO = new MarketDAO();
	//	System.out.println(marketDAO.getMarketListBySearch("ㄴㅇㄹㄴㅇ"));
	//	System.out.println(marketDAO.getMarketListByItem(1));
	//	System.out.println(marketDAO.getMarketLatLng(1));
		
		
		StoreDAO storeDAO = new StoreDAO();
	//	System.out.println(storeDAO.getStoreInMarket(2));
//		MemberDO memberDO = new MemberDO();
//		JsonMarketStore jsonMarketStore = new JsonMarketStore();
//		System.out.println(jsonMarketStore.getCheckNickName(memberDO));
//		System.out.println(storeDAO.notStore(2, "shortlee"));
		
		JsonMarketStore jsonMarketStore = new JsonMarketStore();
	//	System.out.println(jsonMarketStore.getCheckPasswd("123456789", "longlee"));
	//	System.out.println(jsonMarketStore.getChangeProfile("123456789", "shortlee", ""));
	
	//	String result = jsonMarketStore.getChangeProfile("1234567899", "shortlee", "숏다리걸");
	//	System.out.println("Result: " + result);


		MemberDAO memberDAO = new MemberDAO();
	//	System.out.println(memberDAO.deleteMember("shortlee"));
		System.out.println(memberDAO.loginMember("longlee", "1234567891"));

	}
}

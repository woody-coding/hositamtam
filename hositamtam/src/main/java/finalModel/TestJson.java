package finalModel;

public class TestJson {

	public static void main(String[] args) {
		
		PostDAO postDAO = new PostDAO();
	//	System.out.println(postDAO.getAllPost(100));
	//	System.out.println(postDAO.getComments(1));
	//	System.out.println(postDAO.updateLike(1, "longlee"));
		
		MarketDAO marketDAO = new MarketDAO();
	//	System.out.println(marketDAO.getMarketListBySearch("ㄴㅇㄹㄴㅇ"));
	//	System.out.println(marketDAO.getMarketListByItem(1));
		System.out.println(marketDAO.getMarketLatLng(1));
		
		
		StoreDAO storeDAO = new StoreDAO();
	//	System.out.println(storeDAO.getStoreInMarket(2));
	}

}

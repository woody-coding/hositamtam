package model;

import java.util.ArrayList;

public class TestJson {

	public static void main(String[] args) throws Exception {
		
		PostDAO postDAO = new PostDAO();
	//	System.out.println(postDAO.getAllPost(100));
	//	System.out.println(postDAO.getComments(1));
	//	System.out.println(postDAO.updateLike(1, "longlee"));
	//	System.out.println(postDAO.getPostCount("shortlee"));
		
//		ArrayList<PostDO> postDOArrayList = postDAO.getAllPostById("shortlee");
//		for(PostDO postDO : postDOArrayList) {
//			System.out.println(postDO.getPno());
//			System.out.println(postDO.getPtitle());
//			System.out.println(postDO.getPcategory());
//			System.out.println(postDO.getPlikecount());
//			System.out.println(postDO.getPregdate());
//		}
		
		StoreDO storeDO = new StoreDO();
		
		
		MarketDAO marketDAO = new MarketDAO();
	//	System.out.println(marketDAO.getMarketListBySearch("ㄴㅇㄹㄴㅇ"));
	//	System.out.println(marketDAO.getMarketListByItem(1));
	//	System.out.println(marketDAO.getMarketLatLng(1));
		
		
		StoreDAO storeDAO = new StoreDAO();
	//	System.out.println(storeDAO.getStoreInMarket(2));
//		MemberDO memberDO = new MemberDO();
//		JsonMarketStore jsonMarketStore = new JsonMarketStore();
//		System.out.println(jsonMarketStore.getCheckNickName(memberDO));
//		System.out.println(storeDAO.updateLikeStoreStatus(6));
//		System.out.println(storeDAO.notStoreStatus(1, "king123"));
//		System.out.println(storeDAO.notStore(1, "longlee"));
//		System.out.println(storeDAO.getStoreCount("shortlee"));
//		System.out.println(storeDAO.getStoreLikeCount("shortlee"));
		
		
		ReviewDAO reviewDAO = new ReviewDAO();
//		System.out.println(reviewDAO.getReviewCount("shortlee"));
		
		
		ArrayList<ReviewDO> reviewDOArrayList = reviewDAO.getAllReviewById("shortlee");
		for(ReviewDO reviewDO : reviewDOArrayList) {
			System.out.println(reviewDO.getSno());
			System.out.println(reviewDO.getSname());
			System.out.println(reviewDO.getRrating());
			System.out.println(reviewDO.getRcontent());
			System.out.println(reviewDO.getRregdate());
		}
		
		
		
		
		JsonMarketStore jsonMarketStore = new JsonMarketStore();
	//	System.out.println(jsonMarketStore.getCheckPasswd("123456789", "longlee"));
	//	System.out.println(jsonMarketStore.getChangeProfile("123456789", "shortlee", ""));
	
	//	String result = jsonMarketStore.getChangeProfile("1234567899", "shortlee", "숏다리걸");
	//	System.out.println("Result: " + result);


		MemberDAO memberDAO = new MemberDAO();
	//	System.out.println(memberDAO.deleteMember("shortlee"));
	//	System.out.println(memberDAO.loginMember("longlee", "1234567891"));
	//	System.out.println(memberDAO.joinMember("kingright", "테스트용", "123456789", "2000-10-10", "남"));
	//	System.out.println(memberDAO.isIdDuplicate("shortlee"));
	//	System.out.println(memberDAO.isNicknameDuplicate("테스트용"));
	}
}

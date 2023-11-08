package model;

import org.json.simple.JSONObject;

public class JsonMarketStore {
	
	private MemberDAO memberDAO;
	
	public JsonMarketStore() {
	}
	
	// MemberDAO를 인자로 받는 생성자
	public JsonMarketStore(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
		
	// MemberDAO를 설정하는 메서드
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	// 닉네임 중복 확인을 위한 JSON 응답 생성 메서드
	@SuppressWarnings("unchecked")
	public String getCheckNickName(MemberDO memberDO) {
		JSONObject jsonObject = new JSONObject();
		// MemberDAO를 사용하여 닉네임 중복 확인
		boolean isNicknameDuplicate = memberDAO.checkNickname(memberDO);
		// JSON 객체에 중복 여부를 추가
		jsonObject.put("isDuplicate", isNicknameDuplicate);
		
		// JSON 객체를 문자열로 변환하여 반환
		return jsonObject.toJSONString();
	}
	
	// checkNickName 중복확인 테스트(java application test)
//	public static void main(String [] args) {
//		JsonMarketStore jms = new JsonMarketStore();
//		MemberDAO memberDAO = new MemberDAO();
//		jms.setMemberDAO(memberDAO);
//		
//		MemberDO member = new MemberDO();
//		member.setNickname("test5555");
//		
//		System.out.println(jms.getCheckNickName(member));
//	}
	
	
	
	@SuppressWarnings("unchecked")
	public String getCheckPasswd(MemberDO memberDO) {
		JSONObject jsonObject = new JSONObject();
		// MemberDAO를 사용하여 닉네임 중복 확인
		boolean isPasswdDuplicate = false;
		
		try {
			isPasswdDuplicate = memberDAO.checkPasswd(memberDO);
		} catch (Exception e) {
			e.printStackTrace(); // 또는 다른 예외 처리 로직을 추가할 수 있음
	        // 예외 처리를 위한 오류 메시지를 JSON 객체에 추가할 수 있음
	        jsonObject.put("error", "비밀번호 중복 확인 중 오류가 발생했습니다.");
		}
		// JSON 객체에 중복 여부를 추가
		jsonObject.put("isDuplicate", isPasswdDuplicate);
		
		// JSON 객체를 문자열로 변환하여 반환
		return jsonObject.toJSONString();
	}
	
	
	
	
	
}











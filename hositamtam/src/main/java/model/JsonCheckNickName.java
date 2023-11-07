package model;

import org.json.simple.JSONObject;

public class JsonCheckNickName {
	
	private MemberDAO memberDAO;
	
	public JsonCheckNickName() {
	}
	
	public JsonCheckNickName(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@SuppressWarnings("unchecked")
	public String getCheckNickName(boolean isDuplicate) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("isDuplicate", isDuplicate);
		
		return jsonObject.toJSONString();
	}
	
	public static void main(String [] args) {
		System.out.println(new JsonCheckNickName().getCheckNickName(true));
	}
}

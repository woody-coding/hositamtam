package model;

import org.json.simple.JSONObject;

public class JsonCheckNickName {
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

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonMarketStore {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private MemberDAO memberDAO;
	
	
	public JsonMarketStore() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	public String getCheckPasswd(String newPassword, String id) {
		//ArrayList<MemberDO> memberDOList = new ArrayList<MemberDO>();

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "select passwd from member where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				MemberDO memberDO = new MemberDO();
				memberDO.setPasswd(rs.getString("passwd"));
				
				if(memberDO.getPasswd().equals(newPassword)) {
		            jsonObject = new JSONObject();
		            jsonObject.put("isDuplicate", true);
				} else {
		            jsonObject = new JSONObject();
		            jsonObject.put("isDuplicate", false);
				}
            }
			
			jsonArray.add(jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return jsonArray.toJSONString();
	}
		
		
		
	/*
	 * JSONObject jsonObject = new JSONObject(); // MemberDAO를 사용하여 닉네임 중복 확인
	 * boolean isPasswdDuplicate = false;
	 * 
	 * try { isPasswdDuplicate = memberDAO.checkPasswd(memberDO); } catch (Exception
	 * e) { e.printStackTrace(); // 또는 다른 예외 처리 로직을 추가할 수 있음 // 예외 처리를 위한 오류 메시지를
	 * JSON 객체에 추가할 수 있음 jsonObject.put("error", "비밀번호 중복 확인 중 오류가 발생했습니다."); } //
	 * JSON 객체에 중복 여부를 추가 jsonObject.put("isDuplicate", isPasswdDuplicate);
	 * 
	 * // JSON 객체를 문자열로 변환하여 반환 return jsonObject.toJSONString(); }
	 */
	
	
	@SuppressWarnings("unchecked")
	public String getChangeProfile(String newPassword, String id, String newNickname) {

	    JSONArray jsonArray = new JSONArray();
	    JSONObject jsonObject = new JSONObject();

	    try {
	        if (newPassword != null && newNickname == null) {
	            sql = "update member set passwd = ? where id = ?";

	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, newPassword);
	            pstmt.setString(2, id);
	            pstmt.executeUpdate(); // executeUpdate로 변경

                jsonObject.put("isUpdate", true);
	            
	        } else if (newPassword == null && newNickname != null) {
	            sql = "update member set nickname = ? where id = ?";

	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, newNickname);
	            pstmt.setString(2, id);
	            pstmt.executeUpdate(); // executeUpdate로 변경

                jsonObject.put("isUpdate", true);
	            
	        } else if (newPassword != null && newNickname != null) {
	            sql = "update member set nickname = ?, passwd = ? where id = ?";

	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, newNickname);
	            pstmt.setString(2, newPassword);
	            pstmt.setString(3, id);
	            pstmt.executeUpdate(); // executeUpdate로 변경
	            
            	jsonObject.put("isUpdate", true);
	            
	        } else {
	        	jsonObject.put("isUpdate", false);
	        }

	        jsonArray.add(jsonObject);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 예외 발생 여부와 관계없이 필요한 자원을 정리합니다.
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return jsonArray.toJSONString();
	}




}








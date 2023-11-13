package model;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.MemberDO;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public MemberDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";

		if (conn == null) {
			try {
				Class.forName(jdbc_driver);
				conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 1.회원 정보 조회
	public MemberDO getMember(String id) {
		MemberDO memberDO = null;
		this.sql = "SELECT id, nickname, passwd, to_char(birthdate, 'YYYY-MM-DD') as birthdate, gender, exp, grade, exist FROM member where id = ?";
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.rs = pstmt.executeQuery();
			while (rs.next()) {
				memberDO = new MemberDO();
				memberDO.setId(rs.getString("id"));
				memberDO.setNickname(rs.getString("nickname"));
				memberDO.setPasswd(rs.getString("passwd"));
				memberDO.setBirthdate(rs.getString("birthdate"));
				memberDO.setGender(rs.getString("gender"));
				memberDO.setExp(rs.getInt("exp"));
				memberDO.setGrade(rs.getInt("grade"));
				memberDO.setExist(rs.getInt("exist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return memberDO;
	}

	// 2. 회원 가입
	public String joinMember(String id, String nickname, String passwd, String birthdate, String gender) throws Exception {
		int rowCount = 0;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			this.sql = "insert into member values (?, ?, ?, ?, ?, 0, 0, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, nickname);
			pstmt.setString(3, passwd);
			pstmt.setString(4, birthdate);
			pstmt.setString(5, gender);
			rowCount = pstmt.executeUpdate();
			if (rowCount > 0) {
				jsonObject.put("isJoin", true);
			}
			jsonArray.add(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.setAutoCommit(true);

				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArray.toJSONString();
	}

	// 2-1. 아이디 중복확인
	public String isIdDuplicate(String id) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try {
			this.sql = "SELECT id FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			this.rs = pstmt.executeQuery();

			if (rs.next()) {
				jsonObject.put("isIdDuplicate", true);
			} else {
				jsonObject.put("isIdDuplicate", false);
			}
			jsonArray.add(jsonObject);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jsonArray.toJSONString();
	}

	// 2-2. 닉네임 중복확인
	public String isNicknameDuplicate(String nickname) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			this.sql = "SELECT nickname FROM member WHERE nickname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				jsonObject.put("isNicknameDuplicate", true);
			} else {
				jsonObject.put("isNicknameDuplicate", false);
			}
			jsonArray.add(jsonObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray.toJSONString();
	}

	// 3. 로그인
	public boolean loginMember(String id, String passwd) throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		MemberDO memberDO = memberDAO.getMember(id);
		boolean loginSuccess = false;
		System.out.println(memberDO.getExist());
		try {
			if (memberDO.getExist() == 0) {
				this.sql = "SELECT id FROM member WHERE id = ? AND passwd = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, passwd);
				this.rs = pstmt.executeQuery();

				if (rs.next()) {
					loginSuccess = true;
				} else {
					loginSuccess = false;
				}
			} else {
				loginSuccess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loginSuccess;
	}

	// 4-1.회원 정보 수정(닉네임 중복 검사)
	public boolean checkNickname(MemberDO memberDO) {
		boolean isNicknameDuplicate = false; 
		try {
			this.sql = "SELECT nickname FROM member WHERE nickname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDO.getNickname());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isNicknameDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isNicknameDuplicate;
	}

	// 4-2.회원 정보 수정(비밀번호 중복확인)
	public boolean checkPasswd(MemberDO memberDO) throws Exception {
		boolean isPasswdDuplicate = false; // 비밀번호 중복검사 불통
		try {
			this.sql = "SELECT passwd FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDO.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String oldPasswd = rs.getString("passwd");
				if (oldPasswd == null) {
					throw new Exception("사용자를 찾을 수 없습니다.");
				}
				if (!oldPasswd.equals(memberDO.getPasswd())) {
					isPasswdDuplicate = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (isPasswdDuplicate == false) {
			throw new Exception("이전의 패스워드가 지금의 패스워드와 중복됩니다. 다른 비밀번호를 선택해주세요.");
		}
		return isPasswdDuplicate;
	}

	// 4-3.회원 정보 수정(중복확인을 통과한 닉네임과 변경할 비밀번호로 덮어씌우기)
	public int changeProfile(MemberDO memberDO) throws Exception {
		int rowCount = 0;
		try {
			if (checkNickname(memberDO) || checkPasswd(memberDO)) {
				this.sql = "UPDATE member SET nickname = ?, passwd = ? WHERE id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberDO.getNickname());
				pstmt.setString(2, memberDO.getPasswd());
				rowCount = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	// 5.회원 정보 삭제(회원 탈퇴)
	public int deleteMember(String id) {
		int rowCount = 0;
		this.sql = "UPDATE member SET exist = 1 WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

}

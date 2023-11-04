package model;

import java.sql.*;
import java.util.*;

import model.MemberDO;

public class MemberDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ResultSet rsNick;
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
	// 2.회원 가입 -> db에 등록된 아이디와 닉네임끼리 중복 검사, 비밀번호는 비밀번호확인란과 같은지 검사
	// 3.회원 정보 수정(닉네임은 db에 등록된 닉네임과 중복 검사, 비밀번호는 db에 저장된 비밀번호와 달라야함)
	// 4.회원 정보 삭제(회원 탈퇴)
	
	
	// 1.회원 정보 조회
	public MemberDO getMember(String id) {
		MemberDO memberDO = null;

		this.sql = "SELECT id, nickname, passwd, to_char(birthdate, 'YYYY-MM-DD HH24:MI:SS') as birthdate, gender, exp, grade  FROM member where id = ?";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberDO = new MemberDO(); // MemberDO 객체 생성

				memberDO.setId(rs.getString("id"));
                memberDO.setNickname(rs.getString("nickname"));
                memberDO.setPasswd(rs.getString("passwd"));
                memberDO.setBirthdate(rs.getString("birthdate"));
                memberDO.setGender(rs.getString("gender"));
                memberDO.setExp(rs.getInt("exp"));
                memberDO.setGrade(rs.getInt("grade"));
                
	            }
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}		
		
		return memberDO;
	}
	
		
	// 2.회원 가입
	public int joinMember(MemberDO memberDO) throws Exception {
	    int rowCount = 0;
	    boolean isIdDuplicate = false;
	    boolean isNicknameDuplicate = false;

	    try {
			this.conn.setAutoCommit(false);
			
			this.sql = "select id from member where id = ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, memberDO.getId());			
			this.rs = pstmt.executeQuery();

			this.sql = "select nickname from member where nickname = ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, memberDO.getNickname());			
			this.rsNick = pstmt.executeQuery();

			if(!rs.next() && !rsNick.next()) {

				this.sql = "INSERT INTO member (id, nickname, passwd, birthdate, gender, exp, grade) VALUES (?, ?, ?, sysdate, ?, 0, 0) ";
				pstmt = conn.prepareStatement(sql);			
				pstmt.setString(1, memberDO.getId());
				pstmt.setString(2, memberDO.getNickname());
				pstmt.setString(3, memberDO.getPasswd());
				pstmt.setString(4, memberDO.getGender());

				
				rowCount = pstmt.executeUpdate();
				this.conn.commit();
			}
			else if(rs.next()) {
				isIdDuplicate = true;
				this.conn.rollback();
			} else if(rsNick.next()) {
				isNicknameDuplicate = true;
				this.conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {			
			try {
				this.conn.setAutoCommit(true);
				
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(isIdDuplicate) {
			throw new Exception("아이디가 중복되었습니다.");
		}
		if(isNicknameDuplicate) {
			throw new Exception("닉네임이 중복되었습니다.");
		}
		return rowCount;
	}
// 아이디 중복 확인
//	        this.sql = "SELECT id FROM member WHERE id = ?";
//	        pstmt = conn.prepareStatement(sql);
//	        pstmt.setString(1, memberDO.getId());
//	        this.rs = pstmt.executeQuery();
//
//	        if (rs.next()) {
//	            isIdDuplicate = true;
//	            throw new Exception("아이디가 중복되었습니다.");
//	        }
//
//	        // 닉네임 중복 확인
//	        this.sql = "SELECT nickname FROM member WHERE nickname = ?";
//	        pstmt = conn.prepareStatement(sql);
//	        pstmt.setString(1, memberDO.getNickname());
//	        this.rs = pstmt.executeQuery();
//
//	        if (rs.next()) {
//	            isNicknameDuplicate = true;
//	            throw new Exception("닉네임이 중복되었습니다.");
//	        }
//
//	        // 비밀번호란과 비밀번호확인란이 같은지 확인
//	        if (rs.next()) {
//            	isPasswordMatch = true;
//	        }
//
//	        if (!isIdDuplicate && !isNicknameDuplicate && isPasswordMatch) {
	            // 아이디, 닉네임 중복이 아니고, 비밀번호가 비밀번호 확인란과 일치하는 경우에만 회원 등록 수행
	    	

	// 아이디 중복 확인
	public String isIdDuplicate(MemberDO memberDO) throws Exception {
	    boolean isIdDuplicate = false;
	    String errorMessage = "";

	    try {
	        this.sql = "SELECT id FROM member WHERE id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memberDO.getId());
	        this.rs = pstmt.executeQuery();

	        if (rs.next()) {
	            isIdDuplicate = true;
	            errorMessage = "아이디가 중복되었습니다.";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        errorMessage = "아이디 중복 확인 중 오류가 발생했습니다.";
	    }

	    return errorMessage;
	}

	// 닉네임 중복 확인
	public String isNicknameDuplicate(MemberDO memberDO) throws Exception {
	    boolean isNicknameDuplicate = false;
	    String errorMessage = "";

	    try {
	        this.sql = "SELECT nickname FROM member WHERE nickname = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memberDO.getNickname());
	        this.rs = pstmt.executeQuery();

	        if (rs.next()) {
	            isNicknameDuplicate = true;
	            errorMessage = "닉네임이 중복되었습니다.";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        errorMessage = "닉네임 중복 확인 중 오류가 발생했습니다.";
	    }

	    return errorMessage;
	}
	
	
	
	// 3. 로그인
	public boolean loginMember(String id, String passwd) throws Exception{
	    boolean loginSuccess = false;
	    
	    try {
	        // SQL 쿼리
	        this.sql = "SELECT id FROM member WHERE id = ? AND passwd = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, passwd);
	        this.rs = pstmt.executeQuery();
	        
	        // 결과 확인
	        if (rs.next()) {
	            // 결과가 존재하면 로그인 성공
	            loginSuccess = true;
	            
	        }else {
	        	// 로그인 실패
	        	loginSuccess = false;
	        }
	    }catch(Exception e) {
			e.printStackTrace();
		}finally {			
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	    if(!loginSuccess) {
	    	throw new Exception("로그인에 실패했습니다. 다시 시도해주세요");
	    }
	    return loginSuccess;
	}

	

	
	// 4. 회원 정보 수정
	public int changePasswd(MemberDO memberDO) throws Exception {
	    int rowCount = 0;
	    boolean isNicknameDuplicate = false;
	    boolean isPasswordMatch = false;

	    try {
	        // 닉네임 중복 확인
	        this.sql = "SELECT id FROM member WHERE nickname = ? AND id != ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memberDO.getNickname());
	        pstmt.setString(2, memberDO.getId());
	        this.rs = pstmt.executeQuery();

	        if (rs.next()) {
	            isNicknameDuplicate = true;
	        }

	        // 비밀번호 확인
	        this.sql = "SELECT passwd FROM member WHERE id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memberDO.getId());
	        this.rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String savedPassword = rs.getString("passwd");
	            if (savedPassword.equals(memberDO.getPasswd())) {
	                isPasswordMatch = true;
	            }
	        }

	        if (!isNicknameDuplicate && !isPasswordMatch) {
	            // 닉네임 중복이 아니고, 비밀번호가 일치하지 않는 경우에만 비밀번호 변경 수행
	            this.sql = "UPDATE member SET passwd = ? WHERE id = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, memberDO.getPasswd());
	            pstmt.setString(2, memberDO.getId());

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

	    if (isNicknameDuplicate) {
	        // 닉네임이 중복되어 수정 불가능
	        throw new Exception("닉네임이 다른 사용자와 중복됩니다. 다른 닉네임을 선택해주세요.");
	    }

	    if (isPasswordMatch) {
	        // 새로운 비밀번호가 이전 비밀번호와 동일하여 수정 불가능
	        throw new Exception("새로운 비밀번호는 이전 비밀번호와 동일할 수 없습니다.");
	    }

	    return rowCount;
	}
	
	
//	// 3-1.회원 정보 수정에서 비밀번호가 이전 비밀번호와 중복확인
//	public int dupliPasswd(MemberDO memberDO) {
//		int rowCount = 0;
//
//		sql = "UPDATE member SET passwd = ? WHERE id = ?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//
//            pstmt.setString(1, memberDO.getId());
//            pstmt.setString(2, memberDO.getPasswd());
//
//			rowCount = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return rowCount;
//	}
	
	
	// 5. 회원 정보 삭제
	public int deleteMember(String id) {
		int rowCount = 0;
		
		this.sql = "delete from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rowCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
	
	public void closeConn() {
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

	
	
	/** 경인 - 유저의 로그인 성공 여부를 boolean값으로 반환하는 메서드 
	 *	로그인 성공시 매개변수로 들어간 MemberDO에 id와 nickname 값을 set */
	public boolean loginCheck(MemberDO memberDO) {
		boolean result = false;
		
		sql = "select id, passwd, nickname from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDO.getId().toLowerCase());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String passwd = rs.getString("passwd");
				
				if(passwd.equals(memberDO.getPasswd())) {
					result = true;
					memberDO.setId(rs.getString("id"));
					memberDO.setNickname(rs.getString("nickname"));
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	
	
	

	
	
}

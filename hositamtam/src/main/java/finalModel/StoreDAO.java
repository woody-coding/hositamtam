package finalModel;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import finalModel.StoreDO;

public class StoreDAO {

// 초기화
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public StoreDAO() {
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

	
	// ㄱ.시장 내 점포 리스트 페이지에서 필요한 데이터 가져오기
	public String getStoreInMarket(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n"
				+ "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n"
				+ "FROM store s\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") c ON s.sno = c.sno\r\n"
				+ "WHERE s.mno = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				
				storeDO.setSavgrating(rs.getDouble("savgrating"));
				storeDO.setSreviewcount(rs.getInt("sreviewcount"));
				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setSlat(rs.getString("slat"));
				storeDO.setSlng(rs.getString("slng"));
				storeDO.setStype(rs.getString("stype"));	
				storeDO.setSphoto(rs.getString("sphoto"));
				storeDO.setSfavoritecount(rs.getInt("sfavoritecount"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setNickname(rs.getString("nickname"));

				storeList.add(storeDO);
	            }
			
				for(StoreDO store : storeList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("savgrating", store.getSavgrating());
					jsonObject.put("sreviewcount", store.getSreviewcount());
					jsonObject.put("sno", store.getSno());
					jsonObject.put("sname", store.getSname());
					jsonObject.put("slat", store.getSlat());
					jsonObject.put("slng", store.getSlng());
					jsonObject.put("stype", store.getStype());
					jsonObject.put("sphoto", store.getSphoto());
					jsonObject.put("sfavoritecount", store.getSfavoritecount());
					jsonObject.put("scategory", store.getScategory());
					jsonObject.put("nickname", store.getNickname());
					
					jsonArray.add(jsonObject);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
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
	
	
	
	
	
	// 해당 시장의 점포 리스트를 리뷰 많은 순서로 가져오기
	public String getManyReview(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n"
				+ "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n"
				+ "FROM store s\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") c ON s.sno = c.sno\r\n"
				+ "WHERE s.mno = ?\r\n"
				+ "order by nvl(sreviewcount, 0) desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				
				storeDO.setSavgrating(rs.getDouble("savgrating"));
				storeDO.setSreviewcount(rs.getInt("sreviewcount"));
				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setSlat(rs.getString("slat"));
				storeDO.setSlng(rs.getString("slng"));
				storeDO.setStype(rs.getString("stype"));	
				storeDO.setSphoto(rs.getString("sphoto"));
				storeDO.setSfavoritecount(rs.getInt("sfavoritecount"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setNickname(rs.getString("nickname"));

				storeList.add(storeDO);
	            }
			
				for(StoreDO store : storeList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("savgrating", store.getSavgrating());
					jsonObject.put("sreviewcount", store.getSreviewcount());
					jsonObject.put("sno", store.getSno());
					jsonObject.put("sname", store.getSname());
					jsonObject.put("slat", store.getSlat());
					jsonObject.put("slng", store.getSlng());
					jsonObject.put("stype", store.getStype());
					jsonObject.put("sphoto", store.getSphoto());
					jsonObject.put("sfavoritecount", store.getSfavoritecount());
					jsonObject.put("scategory", store.getScategory());
					jsonObject.put("nickname", store.getNickname());
					
					jsonArray.add(jsonObject);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
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
	
	
	
	
	
	
	
	// 해당 시장의 점포 리스트를 별점 높은 순서로 가져오기
	public String getManyRating(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n"
				+ "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n"
				+ "FROM store s\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") c ON s.sno = c.sno\r\n"
				+ "WHERE s.mno = ?\r\n"
				+ "order by nvl(savgrating, 0) desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				
				storeDO.setSavgrating(rs.getDouble("savgrating"));
				storeDO.setSreviewcount(rs.getInt("sreviewcount"));
				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setSlat(rs.getString("slat"));
				storeDO.setSlng(rs.getString("slng"));
				storeDO.setStype(rs.getString("stype"));	
				storeDO.setSphoto(rs.getString("sphoto"));
				storeDO.setSfavoritecount(rs.getInt("sfavoritecount"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setNickname(rs.getString("nickname"));

				storeList.add(storeDO);
	            }
			
				for(StoreDO store : storeList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("savgrating", store.getSavgrating());
					jsonObject.put("sreviewcount", store.getSreviewcount());
					jsonObject.put("sno", store.getSno());
					jsonObject.put("sname", store.getSname());
					jsonObject.put("slat", store.getSlat());
					jsonObject.put("slng", store.getSlng());
					jsonObject.put("stype", store.getStype());
					jsonObject.put("sphoto", store.getSphoto());
					jsonObject.put("sfavoritecount", store.getSfavoritecount());
					jsonObject.put("scategory", store.getScategory());
					jsonObject.put("nickname", store.getNickname());
					
					jsonArray.add(jsonObject);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
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
	
	
	
	
	
	
	// 해당 시장의 점포 리스트를 찜 많은 순서로 가져오기
	public String getManyStoreLike(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n"
				+ "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n"
				+ "FROM store s\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") c ON s.sno = c.sno\r\n"
				+ "WHERE s.mno = ?\r\n"
				+ "order by nvl(s.sfavoritecount, 0) desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				
				storeDO.setSavgrating(rs.getDouble("savgrating"));
				storeDO.setSreviewcount(rs.getInt("sreviewcount"));
				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setSlat(rs.getString("slat"));
				storeDO.setSlng(rs.getString("slng"));
				storeDO.setStype(rs.getString("stype"));	
				storeDO.setSphoto(rs.getString("sphoto"));
				storeDO.setSfavoritecount(rs.getInt("sfavoritecount"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setNickname(rs.getString("nickname"));

				storeList.add(storeDO);
	            }
			
				for(StoreDO store : storeList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("savgrating", store.getSavgrating());
					jsonObject.put("sreviewcount", store.getSreviewcount());
					jsonObject.put("sno", store.getSno());
					jsonObject.put("sname", store.getSname());
					jsonObject.put("slat", store.getSlat());
					jsonObject.put("slng", store.getSlng());
					jsonObject.put("stype", store.getStype());
					jsonObject.put("sphoto", store.getSphoto());
					jsonObject.put("sfavoritecount", store.getSfavoritecount());
					jsonObject.put("scategory", store.getScategory());
					jsonObject.put("nickname", store.getNickname());
					
					jsonArray.add(jsonObject);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
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
	
	
	
	
	
	
	
	// 해당 점포에 폐업제보 수 +1 하고, 누적 폐업 제보 수 반환하기
	public String notStore(int sno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n"
				+ "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n"
				+ "FROM store s\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n"
				+ "  GROUP BY sno\r\n"
				+ ") c ON s.sno = c.sno\r\n"
				+ "WHERE s.mno = ?\r\n"
				+ "order by nvl(s.sfavoritecount, 0) desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				
				storeDO.setSavgrating(rs.getDouble("savgrating"));
				storeDO.setSreviewcount(rs.getInt("sreviewcount"));
				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setSlat(rs.getString("slat"));
				storeDO.setSlng(rs.getString("slng"));
				storeDO.setStype(rs.getString("stype"));	
				storeDO.setSphoto(rs.getString("sphoto"));
				storeDO.setSfavoritecount(rs.getInt("sfavoritecount"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setNickname(rs.getString("nickname"));

				storeList.add(storeDO);
	            }
			
				for(StoreDO store : storeList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("savgrating", store.getSavgrating());
					jsonObject.put("sreviewcount", store.getSreviewcount());
					jsonObject.put("sno", store.getSno());
					jsonObject.put("sname", store.getSname());
					jsonObject.put("slat", store.getSlat());
					jsonObject.put("slng", store.getSlng());
					jsonObject.put("stype", store.getStype());
					jsonObject.put("sphoto", store.getSphoto());
					jsonObject.put("sfavoritecount", store.getSfavoritecount());
					jsonObject.put("scategory", store.getScategory());
					jsonObject.put("nickname", store.getNickname());
					
					jsonArray.add(jsonObject);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
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
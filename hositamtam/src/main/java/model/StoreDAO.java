package model;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import model.MarketDO;

public class StoreDAO {
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

	// 1.결제수단 목록 조회
	public List<PaymentDO> getPaymentList() {
		List<PaymentDO> list = new ArrayList<PaymentDO>();
		sql = "SELECT payno, paytype FROM payment";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PaymentDO paymentDO = new PaymentDO();
				paymentDO.setPayno(rs.getInt("payno"));
				paymentDO.setPaytype(rs.getString("paytype"));
				list.add(paymentDO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 2.점포 상세 조회
	public StoreDO getStore(StoreDO storeDO) {

		try {
			sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, m.nickname " + "FROM store s "
					+ "LEFT JOIN member m ON s.id = m.id " + "WHERE s.sno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeDO.getSno());
//				pstmt.setInt(2, storeDO.getMno());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeDO.setSname(rs.getString("sname")); // 점포명
				storeDO.setStype(rs.getString("stype")); // 점포타입 (좌판/매장)
				storeDO.setScategory(rs.getString("scategory")); // 카테고리
				storeDO.setSphoto(rs.getString("sphoto")); // 사진
				storeDO.setNickname(rs.getString("nickname")); // 닉네임
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return storeDO;
	}

	// 3. 점포별 결제수단 목록 조회
	public List<PaymentDO> getStorePaymentList(StoreDO storeDO) {
		List<PaymentDO> listPaymentDO = new ArrayList<PaymentDO>();

		try {
			sql = "SELECT p.payno, p.paytype " + "FROM PAYMENT p JOIN STORE_PAYMENT sp " + "ON p.payno = sp.payno "
					+ "WHERE sp.sno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeDO.getSno());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PaymentDO paymentDO = new PaymentDO();
				paymentDO.setPayno(rs.getInt("payno"));
				paymentDO.setPaytype(rs.getString("paytype"));

				listPaymentDO.add(paymentDO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listPaymentDO;
	}

	// 4. 리뷰 정보 조회
	public StoreDO getStoreReviewAvg(StoreDO storeDO) {
		StoreDO storeReviewAvg = new StoreDO();
		try {
			sql = "SELECT COUNT(r.sno) AS review, AVG(r.rrating) AS rating, COUNT(f.sno) AS favoritecount "
					+ "FROM STORE S " + "JOIN REVIEW R ON s.sno = r.sno "
					+ "LEFT JOIN member_store_favorite f ON s.sno = f.sno " + "WHERE s.sno = ? "
					+ "GROUP BY s.sname, s.stype, s.scategory, s.sphoto";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeDO.getSno());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeReviewAvg.setReview(rs.getInt("review"));
				storeReviewAvg.setRating(rs.getDouble("rating"));
				storeReviewAvg.setSfavoritecount(rs.getInt("favoritecount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return storeReviewAvg;
	}

	// 5. 점포의 리뷰 리스트 ( 해당 점포에 작성한 리뷰 리스트와 리뷰어의 평점 정보를 반환한다. )
	public List<StoreDO> getStoreReviewList(StoreDO storeDO) {
		List<StoreDO> storeReviewList = new ArrayList<StoreDO>();
		try {
			sql = "SELECT R.*, I.REVIEW, I.RATING, M.NICKNAME, M.GRADE "
					+ "FROM STORE S "
					+ "LEFT JOIN ("
						+ "SELECT R.RNO, R.SNO, R.ID, TO_CHAR(R.RREGDATE, 'YYYY-MM-DD HH24:MI') AS RREGDATE, R.RCONTENT, R.RRATING "
						+ "FROM REVIEW R"
						+ ") R ON S.SNO = R.SNO "
					+ "LEFT JOIN ("
						+ "SELECT M.ID, COUNT(R.SNO) AS REVIEW, ROUND(AVG(R.RRATING), 1) AS RATING "
						+ "FROM MEMBER M "
						+ "LEFT JOIN REVIEW R ON R.ID = M.ID "
						+ "GROUP BY M.ID"
					+ ") I ON I.ID = R.ID "
					+ "LEFT JOIN MEMBER M ON R.ID = M.ID "
					+ "WHERE S.SNO = ? "
					+ "ORDER BY R.RREGDATE DESC ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeDO.getSno());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDO store = new StoreDO();
				store.setId(rs.getString("id"));
				store.setRno(rs.getInt("rno"));
				store.setNickname(rs.getString("nickname"));
				store.setGrade(rs.getInt("grade"));
				store.setRregdate(rs.getString("rregdate"));
				store.setContent(rs.getString("rcontent"));
				store.setRating(rs.getDouble("rrating"));
				store.setReview(rs.getInt("review"));
				store.setRating(rs.getDouble("rating"));
				storeReviewList.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return storeReviewList;
	}

	// 6.점포 등록
	public int insertStore(StoreDO storeDO, String[] paytype) {
		int rowCount = 0;
		PreparedStatement storePstmt = null;
		PreparedStatement paymentPstmt = null;
		try {
			String nextVal = "SELECT SEQ_SNO.NEXTVAL AS SEQ FROM DUAL";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(nextVal);
			while (rs.next()) {
				storeDO.setSno(rs.getInt("SEQ"));
			}

			String storeSql = "INSERT INTO store (sno, mno, id, sname, slat, slng, stype, sphoto, scategory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			storePstmt = conn.prepareStatement(storeSql);
			storePstmt.setInt(1, storeDO.getSno());
			storePstmt.setInt(2, storeDO.getMno());
			storePstmt.setString(3, storeDO.getId());
			storePstmt.setString(4, storeDO.getSname());
			storePstmt.setString(5, storeDO.getSlat());
			storePstmt.setString(6, storeDO.getSlng());
			storePstmt.setString(7, storeDO.getStype());
			storePstmt.setString(8, storeDO.getSphoto());
			storePstmt.setString(9, storeDO.getScategory());
			rowCount = storePstmt.executeUpdate();

			String paymentSql = "INSERT INTO store_payment (sno, payno) VALUES (?, ?)";
			paymentPstmt = conn.prepareStatement(paymentSql);
			for (String pay : paytype) {
				paymentPstmt.setInt(1, storeDO.getSno());
				paymentPstmt.setString(2, pay);
				paymentPstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (storePstmt != null) {
					storePstmt.close();
				}
				if (paymentPstmt != null) {
					paymentPstmt.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return rowCount;
	}

	// 7.점포 수정
	public int updateStore(StoreDO storeDO, String[] paytype) {
		int rowCount = 0;
		String updateSql = "UPDATE store SET stype = ?, sname = ?, scategory = ? WHERE sno = ?";
		try {
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setString(1, storeDO.getStype());
			pstmt.setString(2, storeDO.getSname());
			pstmt.setString(3, storeDO.getScategory());
			pstmt.setInt(4, storeDO.getSno());
			pstmt.executeUpdate();

			String deletePaymentSql = "DELETE FROM store_payment WHERE sno = ?";
			pstmt = conn.prepareStatement(deletePaymentSql);
			pstmt.setInt(1, storeDO.getSno());
			pstmt.executeUpdate();

			String paymentSql = "INSERT INTO store_payment (sno, payno) VALUES (?, ?)";
			pstmt = conn.prepareStatement(paymentSql);

			for (String pay : paytype) {
				pstmt.setInt(1, storeDO.getSno());
				pstmt.setString(2, pay);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return rowCount;
	}

// 경인 - JSON DAO ----------------------------------------------------------------------------------------------------------

	// 조회 해당 시장의 점포 리스트를 최신 순서로 가져오기
	public String getRecentInsert(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;

		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n" + "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n" + "FROM store s\r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n" + "  FROM review\r\n" + "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n" + "LEFT JOIN (\r\n" + "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n" + "  GROUP BY sno\r\n" + ") c ON s.sno = c.sno\r\n" + "WHERE s.mno = ?\r\n"
				+ "ORDER BY s.sno DESC"; // 최신 순서로 변경

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

			for (StoreDO store : storeList) {
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

	// ㄱ.시장 내 점포 리스트 페이지에서 필요한 데이터 가져오기
	public String getStoreInMarket(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;

		sql = "SELECT s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory,\r\n"
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n" + "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n" + "FROM store s\r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n" + "  FROM review\r\n" + "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n" + "LEFT JOIN (\r\n" + "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n" + "  GROUP BY sno\r\n" + ") c ON s.sno = c.sno\r\n" + "WHERE s.mno = ? "
				+ "ORDER BY s.sno DESC";

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

			for (StoreDO store : storeList) {
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
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n" + "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n" + "FROM store s\r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n" + "  FROM review\r\n" + "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n" + "LEFT JOIN (\r\n" + "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n" + "  GROUP BY sno\r\n" + ") c ON s.sno = c.sno\r\n" + "WHERE s.mno = ?\r\n"
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

			for (StoreDO store : storeList) {
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
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n" + "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n" + "FROM store s\r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n" + "  FROM review\r\n" + "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n" + "LEFT JOIN (\r\n" + "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n" + "  GROUP BY sno\r\n" + ") c ON s.sno = c.sno\r\n" + "WHERE s.mno = ?\r\n"
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

			for (StoreDO store : storeList) {
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
				+ "  (SELECT nickname FROM member WHERE member.id = s.id) AS nickname,\r\n" + "  r.savgrating,\r\n"
				+ "  c.sreviewcount\r\n" + "FROM store s\r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT sno, AVG(rrating) AS savgrating\r\n" + "  FROM review\r\n" + "  GROUP BY sno\r\n"
				+ ") r ON s.sno = r.sno\r\n" + "LEFT JOIN (\r\n" + "  SELECT sno, COUNT(rno) AS sreviewcount\r\n"
				+ "  FROM review\r\n" + "  GROUP BY sno\r\n" + ") c ON s.sno = c.sno\r\n" + "WHERE s.mno = ?\r\n"
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

			for (StoreDO store : storeList) {
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

	// 해당 점포에 폐업제보 수 +1 하고 해당 id가 해당 점포에 폐업 제보했는지 여부 반환하기
	public String notStore(int sno, String id) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try {
			// 제보 수 조회

			String sqlCount = "SELECT sclosecount FROM store WHERE sno = ?";
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int sclosecount = rs.getInt("sclosecount");

				if (sclosecount < 2) {
					// 제보 안한 경우 제보 추가
					// 제보 수 증가 쿼리 실행
					String sqlUpdate = "UPDATE store SET sclosecount = sclosecount + 1 WHERE sno = ?";
					pstmt = conn.prepareStatement(sqlUpdate);
					pstmt.setInt(1, sno);
					pstmt.executeUpdate();

					// 제보 정보 추가 쿼리 실행
					String sqlInsert = "INSERT INTO member_store_close VALUES (?, ?)";
					pstmt = conn.prepareStatement(sqlInsert);
					pstmt.setInt(1, sno);
					pstmt.setString(2, id);
					pstmt.executeUpdate();
				} else {
					String sql1 = "delete from store_payment where sno = ?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setInt(1, sno);
					pstmt.executeUpdate();

					String sql2 = "delete from review where sno = ?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, sno);
					pstmt.executeUpdate();

					String sql3 = "delete from member_store_favorite where sno = ?";
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, sno);
					pstmt.executeUpdate();

					String sql4 = "delete from member_store_close where sno = ?";
					pstmt = conn.prepareStatement(sql4);
					pstmt.setInt(1, sno);
					pstmt.executeUpdate();

					String sql5 = "delete from store where sno = ?";
					pstmt = conn.prepareStatement(sql5);
					pstmt.setInt(1, sno);
					pstmt.executeUpdate();
				}
			}

			// 최종 카운트 세기
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				jsonObject.put("sclosecount", rs.getInt("sclosecount"));
			}

			// 결과 JSON 객체 생성
			jsonObject.put("sno", sno);
			jsonObject.put("id", id);

			jsonArray.add(jsonObject);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArray.toJSONString();
	}

	// 현재 접속한 id가 해당 sno점포에 제보를 했는지 안했는지 여부 판단 + 해당 sno점포의 최신 제보 개수 가져오기
	public String notStoreStatus(int sno, String id) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try {
			// 제보 상태 확인
			String sqlCheck = "SELECT 1 FROM member_store_close WHERE sno = ? AND id = ?";
			pstmt = conn.prepareStatement(sqlCheck);
			pstmt.setInt(1, sno);
			pstmt.setString(2, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// 이미 제보를 클릭한 경우
				jsonObject.put("closeStatus", "o");
			} else {
				// 제보 안한 경우 (이때만 disabled를 해제하면 됨 -> 버튼 클릭 가능하도록)
				jsonObject.put("closeStatus", "x");
			}

			// 결과 JSON 객체 생성
			jsonObject.put("sno", sno);
			jsonObject.put("id", id);

			// 제보 수 조회
			String sqlCount = "SELECT sclosecount FROM store WHERE sno = ?";
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int sclosecount = rs.getInt("sclosecount");
				jsonObject.put("sclosecount", sclosecount);
			}

			jsonArray.add(jsonObject);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArray.toJSONString();
	}

	// ㅇ.찜하기 - ajax 비동기 통신 - JSON문자열 데이터로
	public String updateLikeStore(int sno, String id) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try {
			// 찜 상태 확인
			String sqlCheck = "SELECT 1 FROM member_store_favorite WHERE sno = ? AND id = ?";
			pstmt = conn.prepareStatement(sqlCheck);
			pstmt.setInt(1, sno);
			pstmt.setString(2, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// 이미 찜을 클릭한 경우: 찜 취소
				// 찜 수 감소 쿼리 실행
				String sqlUpdate = "UPDATE store SET sfavoritecount = sfavoritecount - 1 WHERE sno = ?";
				pstmt = conn.prepareStatement(sqlUpdate);
				pstmt.setInt(1, sno);
				pstmt.executeUpdate();

				// 찜 정보 삭제 쿼리 실행
				String sqlDelete = "DELETE FROM member_store_favorite WHERE sno = ? AND id = ?";
				pstmt = conn.prepareStatement(sqlDelete);
				pstmt.setInt(1, sno);
				pstmt.setString(2, id);
				pstmt.executeUpdate();

				jsonObject.put("likeStatus", "x");
			} else {
				// 찜이 없는 경우 또는 취소된 경우: 찜 추가
				// 찜 수 증가 쿼리 실행
				String sqlUpdate = "UPDATE store SET sfavoritecount = sfavoritecount + 1 WHERE sno = ?";
				pstmt = conn.prepareStatement(sqlUpdate);
				pstmt.setInt(1, sno);
				pstmt.executeUpdate();

				// 찜 정보 추가 쿼리 실행
				String sqlInsert = "INSERT INTO member_store_favorite VALUES (?, ?)";
				pstmt = conn.prepareStatement(sqlInsert);
				pstmt.setInt(1, sno);
				pstmt.setString(2, id);
				pstmt.executeUpdate();

				jsonObject.put("likeStatus", "o");
			}

			// 결과 JSON 객체 생성
			jsonObject.put("sno", sno);
			jsonObject.put("id", id);

			// 찜 수 조회
			String sqlCount = "SELECT sfavoritecount FROM store WHERE sno = ?";
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int sfavoritecount = rs.getInt("sfavoritecount");
				jsonObject.put("sfavoritecount", sfavoritecount);
			}

			jsonArray.add(jsonObject);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArray.toJSONString();
	}

	// 현재 접속한 id가 해당 sno점포에 찜을 했는지 안했는지 여부 판단 + 해당 sno점포의 최신 찜 개수 가져오기
	public String updateLikeStoreStatus(int sno) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try {
			// 좋아요 수 조회
			String sqlCount = "SELECT sfavoritecount FROM store WHERE sno = ?";
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int sfavoritecount = rs.getInt("sfavoritecount");
				jsonObject.put("sfavoritecount", sfavoritecount);
			}

			jsonArray.add(jsonObject);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArray.toJSONString();
	}

	// 내가 등록한 점포의 총 개수 반환
	public int getStoreCount(String id) {
		int storeCount = 0;

		try {
			sql = "select count(sno) storecount from store where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				storeCount = rs.getInt("storecount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return storeCount;
	}

	// 내가 찜한 점포의 총 개수 반환
	public int getStoreLikeCount(String id) {
		int storeLikeCount = 0;

		try {
			sql = "select count(sno) storelikecount from member_store_favorite where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				storeLikeCount = rs.getInt("storelikecount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return storeLikeCount;
	}

	// 해당 사용자가 등록한 모든 점포 정보 조회
	public ArrayList<StoreDO> getAllInfoStoreById(String id) {
		ArrayList<StoreDO> storeDOInfoList = new ArrayList<StoreDO>();

		sql = "SELECT s.sno, s.sname, s.stype, s.scategory, LISTAGG(p.paytype , ' , ') WITHIN GROUP(ORDER BY p.paytype DESC) AS paytype "
				+ "FROM store s " + "JOIN store_payment sp ON s.sno = sp.sno " + "JOIN payment p ON sp.payno = p.payno "
				+ "WHERE s.id = ? " + "group by s.sno, s.sname, s.stype, s.scategory " + "order by s.sno";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StoreDO storeDO = new StoreDO();

				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setStype(rs.getString("stype"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setPaytype(rs.getString("paytype"));

				storeDOInfoList.add(storeDO);
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
		return storeDOInfoList;
	}

	// 해당 사용자가 찜한 모든 점포 정보 조회
	public ArrayList<StoreDO> getAllLikeStoreById(String id) {
		ArrayList<StoreDO> storeDOLikeList = new ArrayList<StoreDO>();
		sql = "WITH member_store_favorite_sno AS (" + "  SELECT sno " + "  FROM member_store_favorite "
				+ "  WHERE id = ?" + ") "
				+ "SELECT s.sno, s.sname, s.stype, s.scategory, LISTAGG(p.paytype, ' , ') WITHIN GROUP(ORDER BY p.paytype DESC) AS paytype "
				+ "FROM store s " + "JOIN store_payment sp ON s.sno = sp.sno " + "JOIN payment p ON sp.payno = p.payno "
				+ "WHERE s.sno IN (SELECT sno FROM member_store_favorite_sno) "
				+ "GROUP BY s.sno, s.sname, s.stype, s.scategory " + "ORDER BY s.sno";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				storeDO.setSno(rs.getInt("sno"));
				storeDO.setSname(rs.getString("sname"));
				storeDO.setStype(rs.getString("stype"));
				storeDO.setScategory(rs.getString("scategory"));
				storeDO.setPaytype(rs.getString("paytype"));
				storeDOLikeList.add(storeDO);
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
		return storeDOLikeList;
	}
}
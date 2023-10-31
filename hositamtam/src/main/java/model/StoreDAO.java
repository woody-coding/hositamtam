package model;

import java.sql.*;
import java.util.*;

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

	// ㄱ.점포 리스트 조회 (리뷰 많은 순, 평점높은 순, 찜 많은 순)
	// ㄴ.점포 상세페이지 조회
	// ㄷ.점포 등록
	// ㄹ.점포 수정
	// ㅁ.폐업 제보
	// ㅂ.찜하기

	// ㄱ-1.점포 리스트 조회 (찜 많은 순)
	public ArrayList<StoreDO> getStorebyFavoritecount(String mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		sql = "SELECT sname, stype, sphoto FROM store WHERE mno = 1 ORDER BY sfavoritecount DESC";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StoreDO storeDO = new StoreDO(); // StoreDO 객체 생성

				storeDO.setSname(rs.getString("name"));
				storeDO.setStype(rs.getString("stype"));
				storeDO.setSphoto(rs.getString("sphoto"));

				storeList.add(storeDO);
			}

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
		return storeList;
	}
	
	// ㄱ-2.점포 리스트 조회 (평점 높은 순)
		public ArrayList<StoreDO> getStorebyRating(String mno) {
			ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

			sql = "SELECT s.sno, s.sname, AVG(r.rrating) AS avg_rating FROM store s JOIN review r ON s.sno = r.sno WHERE s.mno = 1 ORDER BY AVG(r.rrating) DESC"; 
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					StoreDO storeDO = new StoreDO(); // StoreDO 객체 생성

					storeDO.setSname(rs.getString("name"));
					storeDO.setStype(rs.getString("stype"));
					storeDO.setSphoto(rs.getString("sphoto"));

					storeList.add(storeDO);
				}

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
			return storeList;
		}

		// ㄱ-3.점포 리스트 조회 (리뷰 많은 순)
		public ArrayList<StoreDO> getStorebyReview(String mno) {
			ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

			sql = "SELECT s.sno, s.sname, COUNT(r.rregdate) AS rregdate_count FROM store s JOIN review r ON s.sno = r.sno WHERE s.mno = 1 ORDER BY COUNT(r.rregdate) DESC";

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					StoreDO storeDO = new StoreDO(); // StoreDO 객체 생성

					storeDO.setSname(rs.getString("name"));
					storeDO.setStype(rs.getString("stype"));
					storeDO.setSphoto(rs.getString("sphoto"));

					storeList.add(storeDO);
				}

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
			return storeList;
		}

	// ㄴ.점포 상세페이지 조회

	// 작성중
	public ArrayList<StoreDO> getStoreDetail() {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		
		// [가장 최근에 점포를 등록한 두 사람의 닉네임 가져오기]
		sql = "SELECT nickname FROM member WHERE id = (SELECT id FROM member"
				+ "WHERE sno = n ORDER BY rregdate DESC) AND ROWNUM <= 2;";
		
		// [최근 날짜 순서로 2명의 nickname, 해당 nickname의 평균 rating, date 횟수, 최근 date를 출력]
		sql = "SELECT m1.nickname, AVG(r.rrating), COUNT(r.rdate), MAX(r.rdate) FROM member m1 JOIN review r ON m1.id = r.id"
				+ "WHERE r.sno = n AND m1.id IN (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY date DESC) AS row_num"
				+ "FROM review WHERE sno = n) WHERE row_num <= 2) GROUP BY m1.nickname";
			
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StoreDO storeDO = new StoreDO();

				storeDO.setSname(rs.getString("name"));
				storeDO.setStype(rs.getString("stype"));
				storeDO.setSphoto(rs.getString("sphoto"));

				storeList.add(storeDO);
			}

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
		return storeList;
	}

	// ㄷ.점포 등록
	public int insertStore(StoreDO storeDO, int mno, String id) {
		int rowCount = 0;

		String sql = "INSERT INTO store (sno, mno, id, spno, sono, sname, slat, slng, stype, sphoto) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, storeDO.getSno());
			pstmt.setInt(2, storeDO.getMno());
			pstmt.setString(3, storeDO.getId());
			pstmt.setInt(4, storeDO.getSpno());
			pstmt.setInt(5, storeDO.getSono());
			pstmt.setString(6, storeDO.getSname());
			pstmt.setString(7, storeDO.getSlat());
			pstmt.setString(8, storeDO.getSlng());
			pstmt.setString(9, storeDO.getStype());
			pstmt.setString(10, storeDO.getSphoto());

			rowCount = pstmt.executeUpdate();
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
		return rowCount;
	}

	// ㄹ.점포 수정
	public int updateStore(StoreDO storeDO, int mno, String id) {
		int rowCount = 0;

		String sql = "INSERT INTO store (sno, mno, id, spno, sono, sname, slat, slng, stype, sphoto) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, storeDO.getSno());
			pstmt.setInt(2, storeDO.getMno());
			pstmt.setString(3, storeDO.getId());
			pstmt.setInt(4, storeDO.getSpno());
			pstmt.setInt(5, storeDO.getSono());
			pstmt.setString(6, storeDO.getSname());
			pstmt.setString(7, storeDO.getSlat());
			pstmt.setString(8, storeDO.getSlng());
			pstmt.setString(9, storeDO.getStype());
			pstmt.setString(10, storeDO.getSphoto());

			rowCount = pstmt.executeUpdate();
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
		return rowCount;
	}

	// ㅁ.폐업 제보
	public int closeStore(StoreDO storeDO, int sno, String id) {
		int rowCount = 0;

		sql = "INSERT INTO member_store_close (sno, id) + VALUES (?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, storeDO.getSno());
			pstmt.setInt(2, storeDO.getMno());

			rowCount = pstmt.executeUpdate();
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
		return rowCount;
	}

	// ㅂ.찜하기
	
	
}
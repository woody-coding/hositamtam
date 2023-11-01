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

	// ㄱ.점포 리스트
	// ㄴ.점포 순위별 조회 (리뷰 많은 순, 평점높은 순, 찜 많은 순)
	// ㄷ.점포 팝업
	// ㄹ.점포 상세페이지
	// ㅁ.점포 등록
	// ㅂ.점포 수정
	// ㅅ.폐업 제보
	// ㅇ.찜하기
	// ㅈ.점포 삭제
	
	// ㄱ.점포 리스트
		public ArrayList<StoreDO> getStoreList(int mno) {
			ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

			sql = "SELECT sname, stype, scategory, sphoto FROM store WHERE mno = ? ORDER BY sno DESC";

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
	// ㄴ-1.점포 조회 (찜 많은 순)
		public ArrayList<StoreDO> getStorebyFavoritecount(StoreDO storeDO, int mno) {
		    ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		    try {
		        String sql = "SELECT sname, stype, sphoto FROM store WHERE mno = ? ORDER BY sfavoritecount DESC";

		        // SQL 문장을 실행하는 PreparedStatement 생성
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, mno); // mno 매개변수 사용
		        ResultSet rs = pstmt.executeQuery();

		        // 결과셋 처리
		        while (rs.next()) {
		            StoreDO store = new StoreDO();
		            store.setSname(rs.getString("sname")); // 필드가 StoreDO 객체에 추가되었다고 가정
		            store.setStype(rs.getString("stype"));
		            store.setSphoto(rs.getString("sphoto"));

		            storeList.add(store);
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
		return storeList;
	}
	
	// ㄴ-2.점포 조회 (평점 높은 순)
		public ArrayList<StoreDO> getStorebyRating(int mno) {
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

		// ㄴ-3.점포 조회 (리뷰 많은 순)
		public ArrayList<StoreDO> getStorebyReview(int mno) {
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

//	public ArrayList<StoreDO> getStoreDetail(int mno, int sno) {
//		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
//
//		
//		// [가장 최근에 점포를 등록한 두 사람의 닉네임 가져오기]
//		sql = "SELECT nickname FROM member WHERE id = (SELECT id FROM member"
//				+ "WHERE sno = ? ORDER BY rregdate DESC) AND ROWNUM <= 2;";
//		
//		// [최근 날짜 순서로 2명의 nickname, 해당 nickname의 평균 rating, date 횟수, 최근 date를 출력]
//		sql = "SELECT m1.nickname, AVG(r.rrating), COUNT(r.rdate), MAX(r.rdate) FROM member m1 JOIN review r ON m1.id = r.id"
//				+ "WHERE r.sno = n AND m1.id IN (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY date DESC) AS row_num"
//				+ "FROM review WHERE sno = n) WHERE row_num <= 2) GROUP BY m1.nickname";
//			
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				StoreDO storeDO = new StoreDO();
//
//				storeDO.setSname(rs.getString("name"));
//				storeDO.setStype(rs.getString("stype"));
//				storeDO.setSphoto(rs.getString("sphoto"));
//
//				storeList.add(storeDO);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return storeList;
//	}
//	
//	==
		// ㄷ.점포 팝업
		
		// ㄹ.점포 상세페이지
		public ArrayList<StoreDO> getStoreDetail(int mno, int sno) {
	    ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
	   
		try {
			  // [가장 최근에 점포를 등록한 두 사람의 닉네임 가져오기]
			sql = "SELECT nickname FROM member WHERE id = (SELECT id FROM member"
					+ "WHERE mno = ? and sno = ? ORDER BY rregdate DESC) AND ROWNUM <= 2;";
			
			// [해당 점포에 가장 최근에 리뷰를 남긴 2명의 닉네임과 '등록한 리뷰의 평점, 리뷰를 작성한 총횟수, 최근 작성일자'를 각각 출력]
			sql = "SELECT m1.nickname, AVG(r.rrating), COUNT(r.rdate), MAX(r.rdate) FROM member m1 JOIN review r ON m1.id = r.id"
					+ "WHERE r.sno = n AND m1.id IN (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY date DESC) AS row_num"
					+ "FROM review WHERE mno = ? and sno = ?) WHERE row_num <= 2)";

	        // SQL 문장을 실행하는 PreparedStatement 생성
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, mno); // mno 매개변수 사용
	        pstmt.setInt(2, sno); // sno 매개변수 사용
	        ResultSet rs = pstmt.executeQuery();

	        // 결과셋 처리
	        while (rs.next()) {
	            StoreDO storeDO = new StoreDO();
	            storeDO.setSname(rs.getString("sname")); // 필드가 StoreDO 객체에 추가되었다고 가정
	            storeDO.setStype(rs.getString("stype"));
	            storeDO.setScategory(rs.getString("sphoto"));
	            storeDO.setSphoto(rs.getString("sphoto"));

	            storeList.add(storeDO);
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
	return storeList;
}
	

	// ㅁ.점포 등록
	public int insertStore(StoreDO storeDO) {
		int rowCount = 0;

		sql = "INSERT INTO store (sno, mno, id, sname, slat, slng, stype, sphoto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		sql = "INSERT INTO store_payment (sno, payno) VALUES (?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, storeDO.getSno());
			pstmt.setInt(2, storeDO.getMno());
			pstmt.setString(3, storeDO.getId());
			pstmt.setString(4, storeDO.getSname());
			pstmt.setString(5, storeDO.getSlat());
			pstmt.setString(6, storeDO.getSlng());
			pstmt.setString(7, storeDO.getStype());
			pstmt.setString(8, storeDO.getSphoto());
			pstmt.setInt(9,  storeDO.getPayno());

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

	// ㅂ.점포 수정
	public int updateStore(StoreDO storeDO) {
		int rowCount = 0;

		// 점포를 불러오기
		String sql = "UPDATE INTO store (sno, mno, id, sname, slat, slng, stype, sphoto)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, storeDO.getSno());
			pstmt.setInt(2, storeDO.getMno());
			pstmt.setString(3, storeDO.getId());
			pstmt.setString(4, storeDO.getSname());
			pstmt.setString(5, storeDO.getSlat());
			pstmt.setString(6, storeDO.getSlng());
			pstmt.setString(7, storeDO.getStype());
			pstmt.setString(8, storeDO.getSphoto());
			pstmt.setInt(9,  storeDO.getPayno());

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

	// ㅅ.폐업 제보
	public int closeStore(StoreDO storeDO) {
		int rowCount = 0;

		sql = "INSERT INTO member_store_close (sono, id) VALUES (?, ?)";

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

	// ㅇ.찜하기
	public int favoriteStore(StoreDO storeDO) {
		int rowCount = 0;

		sql = "INSERT INTO member_store_favorite (sono, id) VALUES (?, ?)";

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
	
	// ㅈ.점포 삭제
	public void deleteStore() {
		
	}
	
}
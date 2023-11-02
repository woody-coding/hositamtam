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

		try {
			sql = "SELECT mname FROM market WHERE = ?";
			// 시장명 반환

			// sql = "SELECT sname, stype, scategory, sphoto FROM store WHERE mno = ? ORDER BY sno DESC";
			// 점포이름, 타입, 카테고리, 사진링크 반환

			// sql = "SELECT COUNT(sno) AS sno_count, AVG(rrating) AS avg_rrating FROM review WHERE mno = ? GROUP BY sno";
			// review 테이블에서 리뷰수, 평점을 반환

			//	sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno) AS sno_count, AVG(r.rrating) AS avg_rrating"
			//	+ "FROM store s LEFT JOIN review r ON s.sno = r.sno WHERE s.mno = ? GROUP BY s.sname, s.stype, s.scategory, s.sphoto"
			//	+ "ORDER BY s.sno DESC";
			
			sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno), AVG(r.rrating) AS avg_rrating, COUNT(f.sno) AS favorite_count"
			+ "FROM store s LEFT JOIN review r ON s.sno = r.sno LEFT JOIN memver_store_favorite f ON s.sno = f.sno WHERE s.mno = ?"
			+ "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto ORDER BY s.sno DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno); // mno 매개변수 사용
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				storeDO.setMname(rs.getString("mname"));				// 시장명
				storeDO.setSname(rs.getString("sname"));  				// 점포명
				storeDO.setStype(rs.getString("stype")); 				// 점포타입 (좌판/매장)
				storeDO.setSphoto(rs.getString("sphoto"));				// 사진
				storeDO.setScategory(rs.getString("scategory"));		// 카테고리
				storeDO.setReview(rs.getInt("review"));					// 리뷰 수
				storeDO.setRating(rs.getDouble("rating"));				// 가게평점
				storeDO.setSfavoritecount(rs.getInt("favoritecount"));	// 찜한 갯수

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

	// ㄴ-1.점포 조회 (찜 많은 순)
	public ArrayList<StoreDO> getStorebyFavorite(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		try {
			sql = "SELECT mname FROM market WHERE = ?";
			
			sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno), AVG(r.rrating) AS avg_rrating, COUNT(f.sno) AS favorite_count"
			+ "FROM store s LEFT JOIN review r ON s.sno = r.sno LEFT JOIN memver_store_favorite f ON s.sno = f.sno WHERE s.mno = ?"
			+ "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto ORDER BY favorite_count DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno); // mno 매개변수 사용
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				storeDO.setMname(rs.getString("mname"));				// 시장명
				storeDO.setSname(rs.getString("sname"));  				// 점포명
				storeDO.setStype(rs.getString("stype")); 				// 점포타입 (좌판/매장)
				storeDO.setSphoto(rs.getString("sphoto"));				// 사진
				storeDO.setScategory(rs.getString("scategory"));		// 카테고리
				storeDO.setReview(rs.getInt("review"));					// 리뷰 수
				storeDO.setRating(rs.getDouble("rating"));				// 가게평점
				storeDO.setSfavoritecount(rs.getInt("favoritecount"));	// 찜한 갯수

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

	// ㄴ-2.점포 조회 (평점 높은 순)
	public ArrayList<StoreDO> getStorebyRating(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		try {
			sql = "SELECT mname FROM market WHERE = ?";
			
			sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno), AVG(r.rrating) AS avg_rrating, COUNT(f.sno) AS favorite_count"
			+ "FROM store s LEFT JOIN review r ON s.sno = r.sno LEFT JOIN memver_store_favorite f ON s.sno = f.sno WHERE s.mno = ?"
			+ "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto ORDER BY avg_rrating DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno); // mno 매개변수 사용
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				storeDO.setMname(rs.getString("mname"));				// 시장명
				storeDO.setSname(rs.getString("sname"));  				// 점포명
				storeDO.setStype(rs.getString("stype")); 				// 점포타입 (좌판/매장)
				storeDO.setSphoto(rs.getString("sphoto"));				// 사진
				storeDO.setScategory(rs.getString("scategory"));		// 카테고리
				storeDO.setReview(rs.getInt("review"));					// 리뷰 수
				storeDO.setRating(rs.getDouble("rating"));				// 가게평점
				storeDO.setSfavoritecount(rs.getInt("favoritecount"));	// 찜한 갯수

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


	// ㄴ-3.점포 조회 (리뷰 많은 순)
	public ArrayList<StoreDO> getStorebyReview(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		try {
			sql = "SELECT mname FROM market WHERE = ?";
			
			sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno), AVG(r.rrating) AS avg_rrating, COUNT(f.sno) AS favorite_count"
			+ "FROM store s LEFT JOIN review r ON s.sno = r.sno LEFT JOIN memver_store_favorite f ON s.sno = f.sno WHERE s.mno = ?"
			+ "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto ORDER BY COUNT(r.sno) DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno); // mno 매개변수 사용
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				storeDO.setMname(rs.getString("mname"));				// 시장명
				storeDO.setSname(rs.getString("sname"));  				// 점포명
				storeDO.setStype(rs.getString("stype")); 				// 점포타입 (좌판/매장)
				storeDO.setSphoto(rs.getString("sphoto"));				// 사진
				storeDO.setScategory(rs.getString("scategory"));		// 카테고리
				storeDO.setReview(rs.getInt("review"));					// 리뷰 수
				storeDO.setRating(rs.getDouble("rating"));				// 가게평점
				storeDO.setSfavoritecount(rs.getInt("favoritecount"));	// 찜한 갯수

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
//	public ArrayList<StoreDO> StorePopup(int mno, int sno) {
//	    ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
//
//	    try {
//	        sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno) AS review, AVG(r.rrating) AS rating, COUNT(f.sno) AS favoritecount "
//	                + "FROM store s "
//	                + "LEFT JOIN review r ON s.sno = r.sno "
//	                + "LEFT JOIN member_store_favorite f ON s.sno = f.sno "
//	                + "WHERE s.mno = ? AND s.sno = ? "
//	                + "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto "
//	                + "ORDER BY COUNT(r.sno) DESC";
//
//	        PreparedStatement pstmt = conn.prepareStatement(sql);
//	        pstmt.setInt(1, mno);
//	        pstmt.setInt(2, sno);
//	        ResultSet rs = pstmt.executeQuery();
//
//	        while (rs.next()) {
//	            StoreDO storeDO = new StoreDO();
//	            storeDO.setSname(rs.getString("sname"));                   // 점포명
//	            storeDO.setStype(rs.getString("stype"));                   // 점포타입 (좌판/매장)
//	            storeDO.setSphoto(rs.getString("sphoto"));                 // 사진
//	            storeDO.setScategory(rs.getString("scategory"));           // 카테고리
//	            storeDO.setReview(rs.getInt("review"));                   // 리뷰 수
//	            storeDO.setRating(rs.getDouble("rating"));                 // 가게평점
//	            storeDO.setSfavoritecount(rs.getInt("favoritecount"));    // 찜한 갯수
//
//	            storeList.add(storeDO);
//	        }
	        
	// ㄷ.점포 팝업
	public StoreDO[] StorePopup(int sno) {
	    StoreDO[] storeList = new StoreDO[7]; // 원하는 배열 크기를 지정

	    try {
        sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno) AS review, AVG(r.rrating) AS rating, COUNT(f.sno) AS favoritecount "
                + "FROM store s "
                + "LEFT JOIN review r ON s.sno = r.sno "
                + "LEFT JOIN member_store_favorite f ON s.sno = f.sno "
                + "WHERE s.mno = ? AND s.sno = ? "
                + "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto "
                + "ORDER BY COUNT(r.sno) DESC";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, sno);
        ResultSet rs = pstmt.executeQuery();

	        int index = 0;
	        while (rs.next()) {
	            StoreDO storeDO = new StoreDO();
	            storeDO.setSname(rs.getString("sname"));                   // 점포명
	            storeDO.setStype(rs.getString("stype"));                   // 점포타입 (좌판/매장)
	            storeDO.setSphoto(rs.getString("sphoto"));                 // 사진
	            storeDO.setScategory(rs.getString("scategory"));           // 카테고리
	            storeDO.setReview(rs.getInt("review"));                   // 리뷰 수
	            storeDO.setRating(rs.getDouble("rating"));                 // 가게평점
	            storeDO.setSfavoritecount(rs.getInt("favoritecount"));    // 찜한 갯수

	            storeList[index] = storeDO;
	            index++;
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
	
	// ㄹ.점포 상세페이지
	// 등록자의 닉네임 (where = id),  

	// 점포 기본정보 반환
	public StoreDO[] getStoreDetail(int sno) {
	    StoreDO[] storeDetail = new StoreDO[7]; // 원하는 배열 크기를 지정

	    try {
        sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno) AS review, AVG(r.rrating) AS rating, COUNT(f.sno) AS favoritecount "
                + "FROM store s "
                + "LEFT JOIN review r ON s.sno = r.sno "
                + "LEFT JOIN member_store_favorite f ON s.sno = f.sno "
                + "WHERE s.mno = ? AND s.sno = ? "
                + "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto "
                + "ORDER BY COUNT(r.sno) DESC";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, sno);
        ResultSet rs = pstmt.executeQuery();

	        int index = 0;
	        while (rs.next()) {
	            StoreDO storeDO = new StoreDO();
	            storeDO.setNickname(rs.getString("nickname"));             // 닉네임
	            storeDO.setSname(rs.getString("sname"));                   // 점포명
	            storeDO.setStype(rs.getString("stype"));                   // 점포타입 (좌판/매장)
	            storeDO.setSphoto(rs.getString("sphoto"));                 // 사진
	            storeDO.setScategory(rs.getString("scategory"));           // 카테고리
	            storeDO.setReview(rs.getInt("review"));                    // 리뷰 수
	            storeDO.setRating(rs.getDouble("rating"));                 // 가게평점
	            storeDO.setSfavoritecount(rs.getInt("favoritecount"));     // 찜한 갯수

	            storeDetail[index] = storeDO;
	            index++;
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
		return storeDetail;
	}

	// 점포의 결제방식 반환 (현금, 카드, 계좌이체)
	public StoreDO[] getStorePaytype(int sno) {
		StoreDO[] storePaytype = new StoreDO[3]; // 배열 크기 3

		try {
			sql = "SELECT p.paytype FROM store_payment sp JOIN payment p ON sp.payno = p.payno WHERE p.sno = ?";

		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, sno);
		        ResultSet rs = pstmt.executeQuery();

			        int index = 0;
			        while (rs.next()) {
			            StoreDO storeDO = new StoreDO();
			            storeDO.setNickname(rs.getString("nickname"));             // 닉네임
			            storeDO.setSname(rs.getString("sname"));                   // 점포명
			            storeDO.setStype(rs.getString("stype"));                   // 점포타입 (좌판/매장)
			            storeDO.setSphoto(rs.getString("sphoto"));                 // 사진
			            storeDO.setScategory(rs.getString("scategory"));           // 카테고리
			            storeDO.setReview(rs.getInt("review"));                    // 리뷰 수
			            storeDO.setRating(rs.getDouble("rating"));                 // 가게평점
			            storeDO.setSfavoritecount(rs.getInt("favoritecount"));     // 찜한 갯수

			            storePaytype[index] = storeDO;
			            index++;
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
		return storePaytype;
	}
	
	// 점포 하단의 후기 이력 반환 (닉네임, 리뷰 수, 평점, 작성일)
	public ArrayList<StoreDO> getStoreReview(int sno) {
		ArrayList<StoreDO> storeReviewList = new ArrayList<StoreDO>();

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
			pstmt.setInt(1, sno); // sno 매개변수 사용
			ResultSet rs = pstmt.executeQuery();

			// 결과셋 처리
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				storeDO.setSname(rs.getString("sname")); // 필드가 StoreDO 객체에 추가되었다고 가정
				storeDO.setStype(rs.getString("stype"));
				storeDO.setScategory(rs.getString("sphoto"));
				storeDO.setSphoto(rs.getString("sphoto"));

				storeReviewList.add(storeDO);
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
			pstmt.setInt(9, storeDO.getPayno());

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
			pstmt.setInt(9, storeDO.getPayno());

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
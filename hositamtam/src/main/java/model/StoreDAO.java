package model;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

	// ㄹ-1.점포의 기본정보를 반환	(ㄹ-1의 a,b를 합침)
	public StoreDO[] getStoreInfo(int sno) {
	    StoreDO[] storeDetails = new StoreDO[10]; 

	    try {
	        String sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno) AS review, AVG(r.rrating) AS rating, COUNT(f.sno) AS favoritecount, p.paytype "
	                + "FROM store s "
	                + "LEFT JOIN review r ON s.sno = r.sno "
	                + "LEFT JOIN member_store_favorite f ON s.sno = f.sno "
	                + "LEFT JOIN store_payment sp ON s.sno = sp.sno "
	                + "LEFT JOIN payment p ON sp.payno = p.payno "
	                + "WHERE s.mno = ? AND s.sno = ? "
	                + "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto, p.paytype "
	                + "ORDER BY COUNT(r.sno) DESC";

	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, sno);
	        pstmt.setInt(2, sno);
	        ResultSet rs = pstmt.executeQuery();

	        int index = 0;
	        while (rs.next() && index < storeDetails.length) {
	            StoreDO storeDO = new StoreDO();
	            storeDO.setNickname(rs.getString("nickname"));
	            storeDO.setSname(rs.getString("sname"));
	            storeDO.setStype(rs.getString("stype"));
	            storeDO.setSphoto(rs.getString("sphoto"));
	            storeDO.setScategory(rs.getString("scategory"));
	            storeDO.setReview(rs.getInt("review"));
	            storeDO.setRating(rs.getDouble("rating"));
	            storeDO.setSfavoritecount(rs.getInt("favoritecount"));
//	            storeDO.setPaytype(rs.getString("paytype"));

	            storeDetails[index] = storeDO;
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
	    return storeDetails;
	}

	
//	// ㄹ-1-a.점포 기본정보 반환
//	
//	public StoreDO[] getStoreInfo(int sno) {
//	    StoreDO[] storeDetail = new StoreDO[7]; // 원하는 배열 크기를 지정
//
//	    try {
//        sql = "SELECT s.sname, s.stype, s.scategory, s.sphoto, COUNT(r.sno) AS review, AVG(r.rrating) AS rating, COUNT(f.sno) AS favoritecount "
//                + "FROM store s "
//                + "LEFT JOIN review r ON s.sno = r.sno "
//                + "LEFT JOIN member_store_favorite f ON s.sno = f.sno "
//                + "WHERE s.mno = ? AND s.sno = ? "
//                + "GROUP BY s.sno, s.sname, s.stype, s.scategory, s.sphoto "
//                + "ORDER BY COUNT(r.sno) DESC";
//
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1, sno);
//        ResultSet rs = pstmt.executeQuery();
//
//	        int index = 0;
//	        while (rs.next()) {
//	            StoreDO storeDO = new StoreDO();
//	            storeDO.setNickname(rs.getString("nickname"));             // 닉네임
//	            storeDO.setSname(rs.getString("sname"));                   // 점포명
//	            storeDO.setStype(rs.getString("stype"));                   // 점포타입 (좌판/매장)
//	            storeDO.setSphoto(rs.getString("sphoto"));                 // 사진
//	            storeDO.setScategory(rs.getString("scategory"));           // 카테고리
//	            storeDO.setReview(rs.getInt("review"));                    // 리뷰 수
//	            storeDO.setRating(rs.getDouble("rating"));                 // 가게평점
//	            storeDO.setSfavoritecount(rs.getInt("favoritecount"));     // 찜한 갯수
//
//	            storeDetail[index] = storeDO;
//	            index++;
//	        }
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
//		return storeDetail;
//	}
//
//	
//	// ㄹ-1-b.점포의 결제방식 반환 (현금, 카드, 계좌이체)
//	public StoreDO[] getStorePaytype(int sno) {
//		StoreDO[] storePaytype = new StoreDO[3]; // 배열 크기 3
//
//		try {
//			sql = "SELECT p.paytype FROM store_payment sp JOIN payment p ON sp.payno = p.payno WHERE p.sno = ?";
//
//		        PreparedStatement pstmt = conn.prepareStatement(sql);
//		        pstmt.setInt(1, sno);
//		        ResultSet rs = pstmt.executeQuery();
//
//			        int index = 0;
//			        while (rs.next()) {
//			            StoreDO storeDO = new StoreDO();
//			            storeDO.setPaytype(rs.getString("paytype"));   // 결제방식
//
//			            storePaytype[index] = storeDO;
//			            index++;
//			        }	
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
//		return storePaytype;
//	}
//	
	
	// ㄹ-2.점포 상세페이지의 하단에, 후기이력 반환 (닉네임, 리뷰 수, 평점, 작성일)
	
	public ArrayList<ReviewDO> getStoreReviewBySno(int sno) {
	    ArrayList<ReviewDO> storeReviewList = new ArrayList<>();

	    try {
	        String idSql = "SELECT id FROM review WHERE sno = ? ORDER BY rregdate DESC";
	        PreparedStatement idPstmt = conn.prepareStatement(idSql);
	        idPstmt.setInt(1, sno);
	        ResultSet idRs = idPstmt.executeQuery();

	        String reviewSql = "SELECT COUNT(id) AS reviewcount, AVG(rating) AS rating_avg, MAX(regdate) AS last_review_date FROM review WHERE id = ? AND sno = ?";
	        PreparedStatement reviewPstmt = conn.prepareStatement(reviewSql);

	        while (idRs.next()) {
	            String id = idRs.getString("id");
	            reviewPstmt.setString(1, id);
	            reviewPstmt.setInt(2, sno);
	            
	            ResultSet reviewRs = reviewPstmt.executeQuery();
	            ReviewDO reviewDO = new ReviewDO();
	            reviewDO.setNickname(id);

	            while (reviewRs.next()) {
	                reviewDO.setReviewcount(reviewRs.getInt("reviewcount"));
	                reviewDO.setRrating(reviewRs.getDouble("rating_avg"));
	                reviewDO.setRregdate(reviewRs.getString("last_review_date"));
	            }

	            storeReviewList.add(reviewDO);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return storeReviewList;
	}
	
//	// ㄹ-2-a. 특정 sno에 대한 id목록 저장
//	public ArrayList<ReviewDO> getIdList(int sno) {
//	    ArrayList<ReviewDO> idList = new ArrayList<ReviewDO>();
//	    
//	    try {
//	        sql = "SELECT id FROM review WHERE sno = ? ORDER BY rregdate DESC";
//	        
//	     			PreparedStatement pstmt = conn.prepareStatement(sql);
//	     			pstmt.setInt(1, sno); // sno 매개변수 사용
//	     			ResultSet rs = pstmt.executeQuery();
//
//	     			// 결과셋 처리
//	     			while (rs.next()) {
//	     				ReviewDO reviewDO = new ReviewDO();
//	     				reviewDO.setId(rs.getString("id")); 
//
//	     				idList.add(reviewDO);
//	     			}
//	     		} catch (SQLException e) {
//	     			e.printStackTrace();
//	     		} finally {
//	     			try {
//	     				if (pstmt != null)
//	     					pstmt.close();
//	     			} catch (SQLException e) {
//	     				e.printStackTrace();
//	     			}
//	     		}
//	     		return idList;
//	     	}
//	
//	// ㄹ-2-b. 리스트에 있는 모든 ID에 대한 리뷰정보 검색
//	public ArrayList<ReviewDO> getStoreReviewById(ArrayList<String> idList, int sno) {
//	    ArrayList<ReviewDO> storeReviewList = new ArrayList<>();
//
//	    try {
//	        String sql = "SELECT COUNT(id) AS reviewcount, AVG(rating) AS rating_avg, MAX(regdate) AS last_review_date FROM review WHERE id = ? AND sno = ?";
//	        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//	        for (String id : idList) {
//	            pstmt.setString(1, id); // id 리스트 요소 사용
//	            pstmt.setInt(2, sno); // sno 매개변수 사용
//
//	            ResultSet rs = pstmt.executeQuery();
//
//	            // 결과셋 처리
//	            while (rs.next()) {
//	                ReviewDO reviewDO = new ReviewDO();
//	                reviewDO.setNickname(id); // 리뷰를 작성한 ID
//
//	                // 리뷰 수, 평균 평점, 최근 리뷰 작성일 설정
//	                reviewDO.setReviewcount(rs.getInt("reviewcount"));
//	                reviewDO.setRrating(rs.getDouble("rating_avg"));
//	                reviewDO.setRregdate(rs.getString("last_review_date"));
//
//	                storeReviewList.add(reviewDO);
//	            }
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            if (pstmt != null)
//	                pstmt.close();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return storeReviewList;
//	}

	// ㅁ.점포 등록
	public int insertStore(StoreDO storeDO, String[] paytype) {
	    int rowCount = 0;
	    PreparedStatement storePstmt = null;
	    PreparedStatement paymentPstmt = null;

	    try {
	        // store 테이블에 정보 입력
	        String storeSql = "INSERT INTO store (sno, mno, id, sname, slat, slng, stype, sphoto, payno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        storePstmt = conn.prepareStatement(storeSql);

	        // store 테이블에 값 설정
	        storePstmt.setInt(1, storeDO.getSno());
	        storePstmt.setInt(2, storeDO.getMno());
	        storePstmt.setString(3, storeDO.getId());
	        storePstmt.setString(4, storeDO.getSname());
	        storePstmt.setString(5, storeDO.getSlat());
	        storePstmt.setString(6, storeDO.getSlng());
	        storePstmt.setString(7, storeDO.getStype());
	        storePstmt.setString(8, storeDO.getSphoto());
	        storePstmt.setInt(9, storeDO.getPayno());

	        rowCount = storePstmt.executeUpdate();

	        // store_payment 테이블에 정보 입력
	        String paymentSql = "INSERT INTO store_payment (payno, paytype) VALUES (?, ?)";
	        paymentPstmt = conn.prepareStatement(paymentSql);

	        // paytype는 최대 3개까지 입력 가능
	        for (String pay : paytype) {
	            paymentPstmt.setInt(1, storeDO.getPayno());
	            paymentPstmt.setString(2, pay);
	            paymentPstmt.executeUpdate();
	        }


	        // 모든 작업이 완료되면 커밋을 수행합니다.
	        conn.commit();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 에러가 발생했을 경우 롤백을 수행합니다.
	        try {
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        // PreparedStatement 및 연결을 닫습니다.
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

	// ㅂ.점포 수정
	public int updateStore(StoreDO storeDO) {
	    int rowCount = 0;

	    String selectSql = "SELECT * FROM store WHERE sno = ?";
	    PreparedStatement selectPstmt = null;
	    PreparedStatement updatePstmt = null;

	    try {
	        selectPstmt = conn.prepareStatement(selectSql);
	        selectPstmt.setInt(1, storeDO.getSno());
	        ResultSet resultSet = selectPstmt.executeQuery();

	        if (resultSet.next()) {
	            int existingSno = resultSet.getInt("sno");
	            int existingMno = resultSet.getInt("mno");
	            String existingId = resultSet.getString("id");
	            String existingSname = resultSet.getString("sname");
	            String existingSlat = resultSet.getString("slat");
	            String existingSlng = resultSet.getString("slng");
	            String existingStype = resultSet.getString("stype");
	            String existingSphoto = resultSet.getString("sphoto");

	            String updateSql = "UPDATE store SET mno = ?, id = ?, sname = ?, slat = ?, slng = ?, stype = ?, sphoto = ? WHERE sno = ?";
	            updatePstmt = conn.prepareStatement(updateSql);

	            updatePstmt.setInt(1, storeDO.getMno());
	            updatePstmt.setString(2, storeDO.getId());
	            updatePstmt.setString(3, storeDO.getSname());
	            updatePstmt.setString(4, storeDO.getSlat());
	            updatePstmt.setString(5, storeDO.getSlng());
	            updatePstmt.setString(6, storeDO.getStype());
	            updatePstmt.setString(7, storeDO.getSphoto());
	            updatePstmt.setInt(8, storeDO.getSno());

	            rowCount = updatePstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (updatePstmt != null) {
	                updatePstmt.close();
	            }
	            if (selectPstmt != null) {
	                selectPstmt.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// 경인 - JSON DAO ----------------------------------------------------------------------------------------------------------
	
	
	
	
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
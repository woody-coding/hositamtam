package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReviewDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public ReviewDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ㄱ.점포의 리뷰이력 반환 (닉네임, 리뷰 수, 평점, 작성일)
	// 		- 점포의 리뷰이력을 최신순으로 조회하고, 리뷰를 등록한 id와 일치하는
	// 		- 사용자의 '닉네임, 총 리뷰수, 평균평점, 작성일'을 반환한다. 
	

	public ArrayList<ReviewDO> getReviewBySno(int sno) {
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
}
//		// ㄹ-2-a. 특정 sno에 대한 id목록 저장
//		public ArrayList<ReviewDO> getIdList(int sno) {
//		    ArrayList<ReviewDO> idList = new ArrayList<ReviewDO>();
//		    
//		    try {
//		        sql = "SELECT id FROM review WHERE sno = ? ORDER BY rregdate DESC";
//		        
//		     			PreparedStatement pstmt = conn.prepareStatement(sql);
//		     			pstmt.setInt(1, sno); // sno 매개변수 사용
//		     			ResultSet rs = pstmt.executeQuery();
	//
//		     			// 결과셋 처리
//		     			while (rs.next()) {
//		     				ReviewDO reviewDO = new ReviewDO();
//		     				reviewDO.setId(rs.getString("id")); 
	//
//		     				idList.add(reviewDO);
//		     			}
//		     		} catch (SQLException e) {
//		     			e.printStackTrace();
//		     		} finally {
//		     			try {
//		     				if (pstmt != null)
//		     					pstmt.close();
//		     			} catch (SQLException e) {
//		     				e.printStackTrace();
//		     			}
//		     		}
//		     		return idList;
//		     	}
	//	
//		// ㄹ-2-b. 리스트에 있는 모든 ID에 대한 리뷰정보 검색
//		public ArrayList<ReviewDO> getStoreReviewById(ArrayList<String> idList, int sno) {
//		    ArrayList<ReviewDO> storeReviewList = new ArrayList<>();
	//
//		    try {
//		        String sql = "SELECT COUNT(id) AS reviewcount, AVG(rating) AS rating_avg, MAX(regdate) AS last_review_date FROM review WHERE id = ? AND sno = ?";
//		        PreparedStatement pstmt = conn.prepareStatement(sql);
	//
//		        for (String id : idList) {
//		            pstmt.setString(1, id); // id 리스트 요소 사용
//		            pstmt.setInt(2, sno); // sno 매개변수 사용
	//
//		            ResultSet rs = pstmt.executeQuery();
	//
//		            // 결과셋 처리
//		            while (rs.next()) {
//		                ReviewDO reviewDO = new ReviewDO();
//		                reviewDO.setNickname(id); // 리뷰를 작성한 ID
	//
//		                // 리뷰 수, 평균 평점, 최근 리뷰 작성일 설정
//		                reviewDO.setReviewcount(rs.getInt("reviewcount"));
//		                reviewDO.setRrating(rs.getDouble("rating_avg"));
//		                reviewDO.setRregdate(rs.getString("last_review_date"));
	//
//		                storeReviewList.add(reviewDO);
//		            }
//		        }
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		    } finally {
//		        try {
//		            if (pstmt != null)
//		                pstmt.close();
//		        } catch (SQLException e) {
//		            e.printStackTrace();
//		        }
//		    }
//		    return storeReviewList;
//		}}

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

	// 1. 리뷰 등록
	public int insertReview(ReviewDO review) {
		int rowCount = 0;

		this.sql = "INSERT INTO review (rno, sno, id, rregdate, rcontent, rrating) " +
		           "VALUES (seq_rno.nextval, ?, ?, sysdate, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getSno());
			pstmt.setString(2, review.getId());
			pstmt.setString(3, review.getRcontent());
			pstmt.setDouble(4, review.getRrating());		
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(!pstmt.isClosed()) {
						pstmt.close();
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}
		return rowCount;
	}
	
	// 2. 리뷰 삭제
	public int deleteReview(int rno) {
		int rowCount = 0;
		this.sql = "delete from review where rno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	// 3. 리뷰 조회
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
	
	// 3-1. 리뷰 수 조회
	public int getReviewCount(String id) {
		int reviewCount = 0;		
		try {
			sql = "select count(rno) reviewcount from review where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	reviewCount = rs.getInt("reviewcount");
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
		return reviewCount;
	}
	
	// 3-1. 사용자별 리뷰 상세 조회
	public ArrayList<ReviewDO> getAllReviewById(String id) {
		ArrayList<ReviewDO> reviewList = new ArrayList<ReviewDO>();
		
		sql = "SELECT r.sno, s.sname, r.rrating, r.rcontent, TO_CHAR(r.rregdate, 'YYYY-MM-DD') AS rregdate "
				+ "FROM review r join store s on r.sno = s.sno "
				+ "WHERE r.id = ? "
				+ "order by rregdate desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDO reviewDO = new ReviewDO();
				reviewDO.setSno(rs.getInt("sno"));
				reviewDO.setSname(rs.getString("sname"));
				reviewDO.setRrating(rs.getDouble("rrating"));
				reviewDO.setRcontent(rs.getString("rcontent"));
				reviewDO.setRregdate(rs.getString("rregdate"));
				reviewList.add(reviewDO);
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
		return reviewList;
	}
}
package model;

import java.sql.*;
import java.util.*;

public class PostDAO {

	// ㄱ. 전체글 조회 : 인기순, 제목 출력
	// ㄴ. 시장을 선택 후 전체 글 조회 : 인기순, 제목 출력
	// ㄷ. 시장을 선택 후 카테고리에 따른 글 조회
	// ㄹ. 글을 클릭시 글의 본문과 댓글 출력
	// ㅁ. 글을 입력
	// ㅅ. 댓글을 입력
	// ㅇ. 좋아요를 클릭

	// 글번호, 제목, 내용을 15자 + ... 글사진, 좋아요수, 댓글 수

// 초기화
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public PostDAO() {
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

	// ㄱ.전체 글 조회
	public ArrayList<PostDO> getAllPost() {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();

		sql = "SELECT pno, ptitle, pcontent, pphoto, plikecount FROM post ORDER BY plikecount DESC";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
				postDO.setPno(rs.getInt("pno"));
				postDO.setMno(rs.getInt("mno"));
				postDO.setId(rs.getString("id"));
				postDO.setPregdate(rs.getDate("pregdate"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setPcategory(rs.getString("pcategory"));

				// 수정된 PostDO 객체를 리스트에 추가
				postList.add(postDO);
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
		return postList;
	}

	// ㄴ.시장글 조회
	public ArrayList<PostDO> getMarketPost() {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();

		sql = "SELECT pno, ptitle, pcontent, pphoto, plikecount"
				+ " FROM post"
				+ " WHERE mno = '선택한 시장 카테고리'"
				+ " ORDER BY plikecount DESC";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
				postDO.setPno(rs.getInt("pno"));
				postDO.setMno(rs.getInt("mno"));
				postDO.setId(rs.getString("id"));
				postDO.setPregdate(rs.getDate("pregdate"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setPcategory(rs.getString("pcategory"));

				// 수정된 PostDO 객체를 리스트에 추가
				postList.add(postDO);
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
		return postList;
	}

	// ㄷ.시장 선택 후 카테고리로 조회
	public ArrayList<PostDO> getCategoryPost() {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();

		sql = "SELECT pno, ptitle, pcontent, pphoto, plikecount"
				+ " FROM post"
				+ " WHERE mno = 1" // 1번 시장
				+ " AND pcategory = 1" // 1번 카테고리(시장질문)
				+ " ORDER BY plikecount DESC;";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
				postDO.setPno(rs.getInt("pno"));
				postDO.setMno(rs.getInt("mno"));
				postDO.setId(rs.getString("id"));
				postDO.setPregdate(rs.getDate("pregdate"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setPcategory(rs.getString("pcategory"));

				// 수정된 PostDO 객체를 리스트에 추가
				postList.add(postDO);
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
		return postList;
	}

	// ㄹ. 글의 본문과 댓글 출력	
	
	public ArrayList<PostDO> getPostContent() {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();

		sql = "SELECT p.*, c.cno, c.ccontent, c.cregdate FROM post p JOIN comments c ON p.pno = c.pno WHERE p.pno = ?";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
				postDO.setPno(rs.getInt("pno"));
				postDO.setMno(rs.getInt("mno"));
				postDO.setId(rs.getString("id"));
				postDO.setPregdate(rs.getDate("pregdate"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setPcategory(rs.getString("pcategory"));
			    postDO.setCno(rs.getInt("cno"));
                postDO.setCcontent(rs.getString("ccontent"));
                postDO.setCregdate(rs.getDate("cregdate"));

				// 수정된 PostDO 객체를 리스트에 추가
				postList.add(postDO);
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
		return postList;
	}
	
	// ㅁ.글 입력
		public int insertPost(PostDO postDO, String id) {
			int rowCount = 0;
			int nextPno = getPno(); // 'pno+1'을 가져옴

			String sql = "INSERT INTO post (pno, mno, id, pregdate, ptitle, pcontent, pphoto, plikecount, pcategory) " +
						 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, nextPno);
				pstmt.setInt(2, postDO.getMno());
				pstmt.setString(3, id); // member 테이블의 id 값을 사용
				pstmt.setDate(4, new java.sql.Date(postDO.getPregdate().getTime()));
				pstmt.setString(5, postDO.getPtitle());
				pstmt.setString(6, postDO.getPcontent());
				pstmt.setString(7, postDO.getPphoto());
				pstmt.setInt(8, postDO.getPlikecount());
				pstmt.setString(9, postDO.getPcategory());

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
		
		// Post테이블의 'pno + 1' 을 반환함
				public int getPno() {
					int nextPno = 0;

					try {
						// SQL 쿼리: 가장 큰 pno 값을 가져옴
						String maxPnoQuery = "SELECT MAX(pno) FROM post";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(maxPnoQuery);

						// 결과가 있다면 가장 큰 pno 값을 가져와서 1을 더함
						if (rs.next()) {
							nextPno = rs.getInt(1) + 1;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							if (stmt != null)
								stmt.close();
							if (rs != null)
								rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					return nextPno;
				}	
				
		// ㅅ. 댓글 입력
				public int insertComments(PostDO postDO, int pno, String id) {
					int rowCount = 0;
					int nextCno = getCno(); // 'cno+1'을 가져옴

					String sql = "INSERT INTO comments (cno, pno, id, ccontent, cregdate) VALUES (?, ?, ?, ?, ?)";

					try {
						pstmt = conn.prepareStatement(sql);

						pstmt.setInt(1, nextCno);
						pstmt.setString(2, postDO.getId());
						pstmt.setString(3, id); // member 테이블의 id 값을 사용
						pstmt.setString(4, postDO.getCcontent());
						pstmt.setDate(5, new java.sql.Date(postDO.getCregdate().getTime()));

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
					
		// Comments테이블의 'cno + 1' 을 반환함
		public int getCno() {
			int nextCno = 0;

			try {
				// SQL 쿼리: 가장 큰 pno 값을 가져옴
				String maxCnoQuery = "SELECT MAX(cno) FROM comments";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(maxCnoQuery);

				// 결과가 있다면 가장 큰 cno 값을 가져와서 1을 더함
				if (rs.next()) {
					nextCno = rs.getInt(1) + 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return nextCno;
		}	
		
		// ㅇ. 게시글의 좋아요를 클릭
		public int clickPostLike(PostDO postDO, int pno, String id) {
			int rowCount = 0;

			String sql = "INSERT INTO member_post_like (pno, id) VALUES (?, ?)";

			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, postDO.getPno());
				pstmt.setString(2, postDO.getId());

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
}

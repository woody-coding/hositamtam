package model;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PostDAO {
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
	// 1. 시장명 조회
	public ArrayList<MarketDO> getAllMarket() {
		ArrayList<MarketDO> marketNameList = new ArrayList<MarketDO>();
		sql = "select mno, mname from market order by mname asc";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MarketDO marketDO = new MarketDO();
				marketDO.setMno(rs.getInt("mno"));
				marketDO.setMname(rs.getString("mname"));
				marketNameList.add(marketDO);
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
		return marketNameList;
	}

	// 1-1. 시장별 시장명 조회
	public MarketDO getSelectedMarket(int mno) {
		MarketDO marketDO = new MarketDO();
		sql = "select mname, mno from market where mno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				marketDO.setMname(rs.getString("mname"));
				marketDO.setMno(rs.getInt("mno"));
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
		return marketDO;
	}

	// 1-2. 게시글별 시장명 조회
	public MarketDO getMarketNameByPno(int pno) {
		MarketDO marketDO = new MarketDO();
		sql = "SELECT m.mname " + "FROM market m " + "INNER JOIN post p ON m.mno = p.mno " + "WHERE p.pno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				marketDO.setMname(rs.getString("mname"));
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
		return marketDO;
	}

	
	// 2. 게시글 조회
	public PostDO getAllPostInfo(int pno) {
		PostDO postDO = new PostDO();
		sql = "select id, pno, ptitle, pcontent, pphoto, plikecount, to_char(pregdate, 'YYYY-MM-DD HH24:MI:SS') as pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname " + "from post "
				+ "where pno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				postDO.setId(rs.getString("id"));
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPphoto(rs.getString("pphoto"));
				System.out.println(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setNickname(rs.getString("nickname"));
				postDO.setCountcomments(rs.getInt("countcomments"));
				postDO.setPcategory(rs.getString("pcategory"));
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
		return postDO;
	}
	
	// 2-1-1. 게시글 최신순 목록 조회
	public ArrayList<PostDO> getAllPost(int mno) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		sql = "SELECT id, pno, ptitle, pcontent, pphoto, plikecount, to_char(pregdate, 'YYYY-MM-DD HH24:MI:SS') as pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname " + "FROM post "
				+ "WHERE mno = ? " + "ORDER BY pregdate DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDO postDO = new PostDO();
				postDO.setId(rs.getString("id"));
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setNickname(rs.getString("nickname"));
				postDO.setCountcomments(rs.getInt("countcomments"));
				postDO.setPcategory(rs.getString("pcategory"));
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

	// 2-1-2. 게시글 인기글 목록 조회
	public ArrayList<PostDO> getPostHot(int mno) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		sql = "SELECT id, pno, ptitle, pcontent, pphoto, plikecount, to_char(pregdate, 'YYYY-MM-DD') as pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname " + "FROM post "
				+ "WHERE mno = ?" + "ORDER BY plikecount DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDO postDO = new PostDO();
				postDO.setId(rs.getString("id"));
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setNickname(rs.getString("nickname"));
				postDO.setCountcomments(rs.getInt("countcomments"));
				postDO.setPcategory(rs.getString("pcategory"));
				postList.add(postDO);
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
		return postList;
	}

	// 2-1-3. 게시글 카테고리별 목록 조회
	public ArrayList<PostDO> getPostCategory(int mno, String pCategory) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		sql = "select id, pno, ptitle, pcontent, pphoto, plikecount, to_char(pregdate, 'YYYY-MM-DD') as pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname " + "from post "
				+ "where mno = ? and pcategory = ? " + "order by pregdate desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, pCategory);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDO postDO = new PostDO();
				postDO.setId(rs.getString("id"));
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setNickname(rs.getString("nickname"));
				postDO.setCountcomments(rs.getInt("countcomments"));
				postDO.setPcategory(rs.getString("pcategory"));
				postList.add(postDO);
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
		return postList;
	}
	
	// 2-2-1. 사용자별 게시글 수 조회
	public int getPostCount(String id) {
		int postCount = 0;
		try {
			sql = "select count(pno) postcount from post where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("postcount");
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
		return postCount;
	}

	// 2-2-2 사용자별 게시글 상세 정보 조회
	public ArrayList<PostDO> getAllPostById(String id) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		sql = "SELECT pno, ptitle, pcategory, plikecount, TO_CHAR(pregdate, 'YYYY-MM-DD') AS pregdate "
				+ "FROM post " + "WHERE id = ? " + "order by pregdate desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDO postDO = new PostDO();
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setPcategory(rs.getString("pcategory"));
				postList.add(postDO);
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
		return postList;
	}


	// 3. 게시글 등록
	public int insertPost(PostDO post) {
		int rowCount = 0;
		this.sql = "insert into post (pno, mno, id, pregdate, pTitle, pContent, pphoto, plikecount, pcategory)"
				+ "values (seq_pno.nextval, ?, ?, sysdate, ?, ?, NULL, 0, ?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getMno());
			pstmt.setString(2, post.getId());
			pstmt.setString(3, post.getPtitle());
			pstmt.setString(4, post.getPcontent());
			pstmt.setString(5, post.getPcategory());
			rowCount = pstmt.executeUpdate();
			
			if(!post.getPphoto().equals("/finalProject/upload/null")) {
				String photoSql = "update post set pphoto = ?";
				pstmt = conn.prepareStatement(photoSql);
				pstmt.setString(1, post.getPphoto());
				pstmt.executeUpdate();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	// 3-1. 게시글 수정
	public int updatePost(PostDO post) {
		int rowCount = 0;
		this.sql = "UPDATE post SET mno=?, ptitle=?, pcontent=?, pcategory=?, pphoto = null WHERE pno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getMno());
			pstmt.setString(2, post.getPtitle());
			pstmt.setString(3, post.getPcontent());
			pstmt.setString(4, post.getPcategory());
			pstmt.setInt(5, post.getPno());
			rowCount = pstmt.executeUpdate();
			
			if(!post.getPphoto().equals("/finalProject/upload/null")) {
				String photoSql = "update post set pphoto = ?";
				pstmt = conn.prepareStatement(photoSql);
				pstmt.setString(1, post.getPphoto());
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	// 3-2. 게시글 삭제
	public int deletePost(int pno) {
		int rowCount = 0;
		String deleteCommentsSQL = "DELETE FROM comments WHERE pno = (SELECT pno FROM post WHERE pno = ?)";
		String deletePostSQL = "DELETE FROM post WHERE pno = ?";
		try {
			pstmt = conn.prepareStatement(deleteCommentsSQL);
			pstmt.setInt(1, pno);
			rowCount = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(deletePostSQL);
			pstmt.setInt(1, pno);
			rowCount += pstmt.executeUpdate();
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
		return rowCount;
	}

	
	// 4. 댓글 조회
	public ArrayList<PostDO> getComment(int pno) {
		ArrayList<PostDO> commentList = new ArrayList<PostDO>();
		sql = "SELECT pno, cno, (SELECT nickname FROM member WHERE comments.id = member.id) AS cnickname, ccontent, to_char(cregdate, 'YYYY-MM-DD HH24:MI') as cregdate "
				+ "FROM comments " + "WHERE pno = ? " + "ORDER BY cregdate DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			System.out.println();
			while (rs.next()) {
				PostDO postDO = new PostDO();
				postDO.setPno(rs.getInt("pno"));
				postDO.setCno(rs.getInt("cno"));
				postDO.setCcontent(rs.getString("ccontent"));
				postDO.setCregdate(rs.getString("cregdate"));
				postDO.setCnickname(rs.getString("cnickname"));
				commentList.add(postDO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return commentList;
	}

	// 4-1. 댓글 등록
	public int InsertComment(PostDO post) {
		int rowCount = 0;
		this.sql = "INSERT INTO comments (cNo, pNo, id, cContent, cregdate) " +
		           "VALUES (seq_pno.nextval, ?, ?, ?, sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getPno());
			pstmt.setString(2, post.getId());
			pstmt.setString(3, post.getCcontent());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	// 4-2. 댓글 삭제
	public int deleteComment(int cno) {
		int rowCount = 0;
		this.sql = "delete from comments where cno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	
	// 5. 좋아요 등록
	public String updateLike(int pno, String id) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			String sqlCheck = "SELECT 1 FROM member_post_like WHERE pno = ? AND id = ?";
			pstmt = conn.prepareStatement(sqlCheck);
			pstmt.setInt(1, pno);
			pstmt.setString(2, id);
			ResultSet rs = pstmt.executeQuery();		

			if (rs.next()) {			
				String sqlUpdate = "UPDATE post SET plikecount = plikecount - 1 WHERE pno = ?";
				pstmt = conn.prepareStatement(sqlUpdate);
				pstmt.setInt(1, pno);
				pstmt.executeUpdate();		
				String sqlDelete = "DELETE FROM member_post_like WHERE pno = ? AND id = ?";
				pstmt = conn.prepareStatement(sqlDelete);
				pstmt.setInt(1, pno);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				jsonObject.put("likeStatus", "x");

			} else {		
				String sqlUpdate = "UPDATE post SET plikecount = plikecount + 1 WHERE pno = ?";
				pstmt = conn.prepareStatement(sqlUpdate);
				pstmt.setInt(1, pno);
				pstmt.executeUpdate();
				String sqlInsert = "INSERT INTO member_post_like VALUES (?, ?)";
				pstmt = conn.prepareStatement(sqlInsert);
				pstmt.setInt(1, pno);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				jsonObject.put("likeStatus", "o");
			}
			jsonObject.put("pno", pno);
			jsonObject.put("id", id);

			String sqlCount = "SELECT plikecount FROM post WHERE pno = ?";
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int plikecount = rs.getInt("plikecount");
				jsonObject.put("plikecount", plikecount);
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

	// 5-1. 좋아요 수 조회
	public String updateLikeStatus(int pno) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			String sqlCount = "SELECT plikecount FROM post WHERE pno = ?";
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int plikecount = rs.getInt("plikecount");
				jsonObject.put("plikecount", plikecount);
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
}
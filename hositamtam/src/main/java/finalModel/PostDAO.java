package finalModel;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import model.MemberDO;

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


	// 전체 글 조회 (최신순)
	public String getAllPost(int mno) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname "
				+ "FROM post "
				+ "WHERE mno = ? "
				+ "ORDER BY pregdate DESC";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setNickname(rs.getString("nickname"));
				postDO.setCountcomments(rs.getInt("countcomments"));
				postDO.setPcategory(rs.getString("pcategory"));

				// 수정된 PostDO 객체를 리스트에 추가
				postList.add(postDO);
			}
			
			for(PostDO post : postList) {
				jsonObject = new JSONObject(); // jsonObject 초기화
				
				jsonObject.put("pno", post.getPno());
				jsonObject.put("ptitle", post.getPtitle());
				jsonObject.put("pcontent", post.getPcontent());
				jsonObject.put("pregdate", post.getPregdate());
				jsonObject.put("pphoto", post.getPphoto());
				jsonObject.put("plikecount", post.getPlikecount());
				jsonObject.put("nickname", post.getNickname());
				jsonObject.put("countcomments", post.getCountcomments());
				jsonObject.put("pcategory", post.getPcategory());
				
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

	
	// mno, mname 데이터 불러오기
	public String getAllMarket() {
		ArrayList<MarketDO> marketList = new ArrayList<MarketDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "select mno, mname from market";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MarketDO marketDO2 = new MarketDO();

				marketDO2.setMno(rs.getInt("mno"));
				marketDO2.setMname(rs.getString("mname"));

				
				marketList.add(marketDO2);
			}
			
			for(MarketDO market : marketList) {
				jsonObject = new JSONObject(); // jsonObject 초기화
				
				jsonObject.put("mno", market.getMno());
				jsonObject.put("mname", market.getMname());
				
				jsonArray.add(jsonObject);
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
		return jsonArray.toJSONString();
	}


	
	
	
	// 해당 시장의 '인기글' 조회
	public String getPostHot(int mno) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "SELECT pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname "
				+ "FROM post "
				+ "WHERE mno = ? AND plikecount >= 130"
				+ "ORDER BY plikecount DESC";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
				postDO.setPno(rs.getInt("pno"));
				postDO.setPtitle(rs.getString("ptitle"));
				postDO.setPcontent(rs.getString("pcontent"));
				postDO.setPregdate(rs.getString("pregdate"));
				postDO.setPphoto(rs.getString("pphoto"));
				postDO.setPlikecount(rs.getInt("plikecount"));
				postDO.setNickname(rs.getString("nickname"));
				postDO.setCountcomments(rs.getInt("countcomments"));
				postDO.setPcategory(rs.getString("pcategory"));

				// 수정된 PostDO 객체를 리스트에 추가
				postList.add(postDO);
			}
			
			for(PostDO post : postList) {
				jsonObject = new JSONObject(); // jsonObject 초기화
				
				jsonObject.put("pno", post.getPno());
				jsonObject.put("ptitle", post.getPtitle());
				jsonObject.put("pcontent", post.getPcontent());
				jsonObject.put("pregdate", post.getPregdate());
				jsonObject.put("pphoto", post.getPphoto());
				jsonObject.put("plikecount", post.getPlikecount());
				jsonObject.put("nickname", post.getNickname());
				jsonObject.put("countcomments", post.getCountcomments());
				jsonObject.put("pcategory", post.getPcategory());
				
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
	
	
	
	// 해당 시장의 '시장질문' 글 조회
		public String getPostQue(int mno) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "select pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
					+ "where mno = ? and pcategory = '시장질문' "
					+ "order by pregdate desc";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setPtitle(rs.getString("ptitle"));
					postDO.setPcontent(rs.getString("pcontent"));
					postDO.setPregdate(rs.getString("pregdate"));
					postDO.setPphoto(rs.getString("pphoto"));
					postDO.setPlikecount(rs.getInt("plikecount"));
					postDO.setNickname(rs.getString("nickname"));
					postDO.setCountcomments(rs.getInt("countcomments"));
					postDO.setPcategory(rs.getString("pcategory"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("ptitle", post.getPtitle());
					jsonObject.put("pcontent", post.getPcontent());
					jsonObject.put("pregdate", post.getPregdate());
					jsonObject.put("pphoto", post.getPphoto());
					jsonObject.put("plikecount", post.getPlikecount());
					jsonObject.put("nickname", post.getNickname());
					jsonObject.put("countcomments", post.getCountcomments());
					jsonObject.put("pcategory", post.getPcategory());
					
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
		
		
		
		
		// 해당 시장의 '사건사고' 글 조회
		public String getPostAcc(int mno) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "select pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
					+ "where mno = ? and pcategory = '사건사고' "
					+ "order by pregdate desc";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setPtitle(rs.getString("ptitle"));
					postDO.setPcontent(rs.getString("pcontent"));
					postDO.setPregdate(rs.getString("pregdate"));
					postDO.setPphoto(rs.getString("pphoto"));
					postDO.setPlikecount(rs.getInt("plikecount"));
					postDO.setNickname(rs.getString("nickname"));
					postDO.setCountcomments(rs.getInt("countcomments"));
					postDO.setPcategory(rs.getString("pcategory"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("ptitle", post.getPtitle());
					jsonObject.put("pcontent", post.getPcontent());
					jsonObject.put("pregdate", post.getPregdate());
					jsonObject.put("pphoto", post.getPphoto());
					jsonObject.put("plikecount", post.getPlikecount());
					jsonObject.put("nickname", post.getNickname());
					jsonObject.put("countcomments", post.getCountcomments());
					jsonObject.put("pcategory", post.getPcategory());
					
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
		
		
		
		
		// 해당 시장의 '일상' 글 조회
		public String getPostDay(int mno) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "select pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
					+ "where mno = ? and pcategory = '일상' "
					+ "order by pregdate desc";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setPtitle(rs.getString("ptitle"));
					postDO.setPcontent(rs.getString("pcontent"));
					postDO.setPregdate(rs.getString("pregdate"));
					postDO.setPphoto(rs.getString("pphoto"));
					postDO.setPlikecount(rs.getInt("plikecount"));
					postDO.setNickname(rs.getString("nickname"));
					postDO.setCountcomments(rs.getInt("countcomments"));
					postDO.setPcategory(rs.getString("pcategory"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("ptitle", post.getPtitle());
					jsonObject.put("pcontent", post.getPcontent());
					jsonObject.put("pregdate", post.getPregdate());
					jsonObject.put("pphoto", post.getPphoto());
					jsonObject.put("plikecount", post.getPlikecount());
					jsonObject.put("nickname", post.getNickname());
					jsonObject.put("countcomments", post.getCountcomments());
					jsonObject.put("pcategory", post.getPcategory());
					
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
		
		
		
		
		// 해당 시장의 '실종/분실' 글 조회
		public String getPostLost(int mno) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "select pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
					+ "where mno = ? and pcategory = '실종/분실' "
					+ "order by pregdate desc";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setPtitle(rs.getString("ptitle"));
					postDO.setPcontent(rs.getString("pcontent"));
					postDO.setPregdate(rs.getString("pregdate"));
					postDO.setPphoto(rs.getString("pphoto"));
					postDO.setPlikecount(rs.getInt("plikecount"));
					postDO.setNickname(rs.getString("nickname"));
					postDO.setCountcomments(rs.getInt("countcomments"));
					postDO.setPcategory(rs.getString("pcategory"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("ptitle", post.getPtitle());
					jsonObject.put("pcontent", post.getPcontent());
					jsonObject.put("pregdate", post.getPregdate());
					jsonObject.put("pphoto", post.getPphoto());
					jsonObject.put("plikecount", post.getPlikecount());
					jsonObject.put("nickname", post.getNickname());
					jsonObject.put("countcomments", post.getCountcomments());
					jsonObject.put("pcategory", post.getPcategory());
					
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
		
		
		
		
		// 해당 pno에 해당되는 글의 모든 정보 가져오기
		public String getPcontent(int pno) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "select pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
					+ "where pno = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setPtitle(rs.getString("ptitle"));
					postDO.setPcontent(rs.getString("pcontent"));
					postDO.setPregdate(rs.getString("pregdate"));
					postDO.setPphoto(rs.getString("pphoto"));
					postDO.setPlikecount(rs.getInt("plikecount"));
					postDO.setNickname(rs.getString("nickname"));
					postDO.setCountcomments(rs.getInt("countcomments"));
					postDO.setPcategory(rs.getString("pcategory"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("ptitle", post.getPtitle());
					jsonObject.put("pcontent", post.getPcontent());
					jsonObject.put("pregdate", post.getPregdate());
					jsonObject.put("pphoto", post.getPphoto());
					jsonObject.put("plikecount", post.getPlikecount());
					jsonObject.put("nickname", post.getNickname());
					jsonObject.put("countcomments", post.getCountcomments());
					jsonObject.put("pcategory", post.getPcategory());
					
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
		
		
		
		
		// 해당 pno에 해당되는 글의 모든 댓글 정보 최신순으로 가져오기
		public String getComments(int pno) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "SELECT pno, cno, (SELECT nickname FROM member WHERE comments.id = member.id) AS cnickname, ccontent, cregdate "
					+ "FROM comments "
					+ "WHERE pno = ? "
					+ "ORDER BY cregdate DESC";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setCno(rs.getInt("cno"));
					postDO.setCcontent(rs.getString("ccontent"));
					postDO.setCregdate(rs.getString("cregdate"));
					postDO.setCnickname(rs.getString("cnickname"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("cno", post.getCno());
					jsonObject.put("ccontent", post.getCcontent());
					jsonObject.put("cregdate", post.getCregdate());
					jsonObject.put("cnickname", post.getCnickname());
					
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
		
		
		
		
		
		// 해당 pno의 글에 id값을 받아서 좋아요 수 업데이트
		public String updateLike(int pno, String id) {
		    JSONArray jsonArray = new JSONArray();
		    JSONObject jsonObject = new JSONObject();

		    try {
		        // 좋아요 상태 확인
		        String sqlCheck = "SELECT 1 FROM member_post_like WHERE pno = ? AND id = ?";
		        pstmt = conn.prepareStatement(sqlCheck);
		        pstmt.setInt(1, pno);
		        pstmt.setString(2, id);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            // 이미 좋아요를 클릭한 경우: 좋아요 취소
		            // 좋아요 수 감소 쿼리 실행
		            String sqlUpdate = "UPDATE post SET plikecount = plikecount - 1 WHERE pno = ?";
		            pstmt = conn.prepareStatement(sqlUpdate);
		            pstmt.setInt(1, pno);
		            pstmt.executeUpdate();

		            // 좋아요 정보 삭제 쿼리 실행
		            String sqlDelete = "DELETE FROM member_post_like WHERE pno = ? AND id = ?";
		            pstmt = conn.prepareStatement(sqlDelete);
		            pstmt.setInt(1, pno);
		            pstmt.setString(2, id);
		            pstmt.executeUpdate();
		        } 
		        else {
		            // 좋아요가 없는 경우 또는 취소된 경우: 좋아요 추가
		            // 좋아요 수 증가 쿼리 실행
		            String sqlUpdate = "UPDATE post SET plikecount = plikecount + 1 WHERE pno = ?";
		            pstmt = conn.prepareStatement(sqlUpdate);
		            pstmt.setInt(1, pno);
		            pstmt.executeUpdate();

		            // 좋아요 정보 추가 쿼리 실행
		            String sqlInsert = "INSERT INTO member_post_like VALUES (?, ?)";
		            pstmt = conn.prepareStatement(sqlInsert);
		            pstmt.setInt(1, pno);
		            pstmt.setString(2, id);
		            pstmt.executeUpdate();
		        }

		        // 결과 JSON 객체 생성
		        jsonObject.put("pno", pno);
		        jsonObject.put("id", id);

		        // 좋아요 수 조회
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
		
		
/*		
		
		// 해당 cno를 작성한 본인이 해당 댓글 삭제하기
		public String commentModify(int pno, String id) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "SELECT pno, cno, (SELECT nickname FROM member WHERE comments.id = member.id) AS cnickname, ccontent, cregdate "
					+ "FROM comments "
					+ "WHERE pno = ? "
					+ "ORDER BY cregdate DESC";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setCno(rs.getInt("cno"));
					postDO.setCcontent(rs.getString("ccontent"));
					postDO.setCregdate(rs.getString("cregdate"));
					postDO.setCnickname(rs.getString("cnickname"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("cno", post.getCno());
					jsonObject.put("ccontent", post.getCcontent());
					jsonObject.put("cregdate", post.getCregdate());
					jsonObject.put("cnickname", post.getCnickname());
					
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
		
		
		
		
		
		// 해당 cno를 작성한 본인이 해당 댓글 삭제하기
		public String commentDelete(int pno, String id) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			
			sql = "SELECT pno, cno, (SELECT nickname FROM member WHERE comments.id = member.id) AS cnickname, ccontent, cregdate "
					+ "FROM comments "
					+ "WHERE pno = ? "
					+ "ORDER BY cregdate DESC";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setCno(rs.getInt("cno"));
					postDO.setCcontent(rs.getString("ccontent"));
					postDO.setCregdate(rs.getString("cregdate"));
					postDO.setCnickname(rs.getString("cnickname"));

					// 수정된 PostDO 객체를 리스트에 추가
					postList.add(postDO);
				}
				
				for(PostDO post : postList) {
					jsonObject = new JSONObject(); // jsonObject 초기화
					
					jsonObject.put("pno", post.getPno());
					jsonObject.put("cno", post.getCno());
					jsonObject.put("ccontent", post.getCcontent());
					jsonObject.put("cregdate", post.getCregdate());
					jsonObject.put("cnickname", post.getCnickname());
					
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
		
*/
}

package model;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	// 시장 목록
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
	// 전체 글 조회 (최신순)
	public ArrayList<PostDO> getAllPost(int mno) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		
		sql = "SELECT id, pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
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
	// 해당 시장의 '인기글' 조회
	public ArrayList<PostDO> getPostHot(int mno) {
		ArrayList<PostDO> postList = new ArrayList<PostDO>();
		
		sql = "SELECT id, pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
				+ "(select nickname from member where post.id = member.id) as nickname "
				+ "FROM post "
				+ "WHERE mno = ?"
				+ "ORDER BY plikecount DESC";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDO postDO = new PostDO(); // PostDO 객체 생성

				// PostDO 객체의 속성을 설정
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

				// 수정된 PostDO 객체를 리스트에 추가
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
	// 카테고리별 글 조회
		public ArrayList<PostDO> getPostCategory(int mno, String pCategory) {
			ArrayList<PostDO> postList = new ArrayList<PostDO>();
			
			sql = "select id, pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
					+ "where mno = ? and pcategory = ? "
					+ "order by pregdate desc";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				pstmt.setString(2, pCategory);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
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

					// 수정된 PostDO 객체를 리스트에 추가
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
		// 시장 번호로 시장 이름 가져오기
		
		// pno에 해당되는 글의 모든 정보 가져오기
		public PostDO getAllPostInfo(int pno) {
			PostDO postDO = new PostDO(); 

			sql = "select id, pno, ptitle, pcontent, pphoto, plikecount, pregdate, pcategory, (select count(cno) from comments where post.pno = comments.pno) as countcomments, "
					+ "(select nickname from member where post.id = member.id) as nickname "
					+ "from post "
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
		// mno로 mname 불러오기
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
		// pno로 mname 불러오기
				public MarketDO getMarketNameByPno(int pno) {
					MarketDO marketDO = new MarketDO();
					sql = "SELECT m.mname "
						    + "FROM market m "
						    + "INNER JOIN post p ON m.mno = p.mno "
						    + "WHERE p.pno = ?";
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
		// 글등록 기능
		public int insertPost(PostDO post) {
			int rowCount = 0;
			this.sql = "insert into post (pno, mno, id, pregdate, pTitle, pContent, pphoto, plikecount, pcategory)"
						+ "values (seq_pno.nextval, ?, ?, sysdate, ?, ?, ?, 0, ?) ";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, post.getMno());
				pstmt.setString(2, post.getId());
				pstmt.setString(3, post.getPtitle());
				pstmt.setString(4, post.getPcontent());
				pstmt.setString(5, post.getPphoto());
				pstmt.setString(6, post.getPcategory());
				
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
		// 글 수정 기능
		public int updatePost(PostDO post) {
		    int rowCount = 0;
		    this.sql = "UPDATE post SET mno=?, ptitle=?, pcontent=?, pphoto=?, pcategory=? WHERE pno=?";
		    
		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, post.getMno());
		        pstmt.setString(2, post.getPtitle());
		        pstmt.setString(3, post.getPcontent());
		        pstmt.setString(4, post.getPphoto());
		        pstmt.setString(5, post.getPcategory());
		        pstmt.setInt(6, post.getPno());
		        
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
		// 글 삭제
		public int deletePost(int pno) {
		    int rowCount = 0;

		    // 댓글 삭제
		    String deleteCommentsSQL = "DELETE FROM comments WHERE pno = (SELECT pno FROM post WHERE pno = ?)";

		    // 글 삭제
		    String deletePostSQL = "DELETE FROM post WHERE pno = ?";

		    try {
		        // 댓글 삭제
		        pstmt = conn.prepareStatement(deleteCommentsSQL);
		        pstmt.setInt(1, pno);
		        rowCount = pstmt.executeUpdate();

		        // 글 삭제
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
		
		
		// 해당 pno에 해당되는 글의 모든 댓글 정보 최신순으로 가져오기
		public ArrayList<PostDO> getComment(int pno) {
			ArrayList<PostDO> commentList = new ArrayList<PostDO>();
			
			sql = "SELECT pno, cno, (SELECT nickname FROM member WHERE comments.id = member.id) AS cnickname, ccontent, cregdate "
					+ "FROM comments "
					+ "WHERE pno = ? "
					+ "ORDER BY cregdate DESC";
			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();

				System.out.println();
				while (rs.next()) {
					PostDO postDO = new PostDO(); // PostDO 객체 생성

					// PostDO 객체의 속성을 설정
					postDO.setPno(rs.getInt("pno"));
					postDO.setCno(rs.getInt("cno"));
					postDO.setCcontent(rs.getString("ccontent"));
					postDO.setCregdate(rs.getString("cregdate"));
					postDO.setCnickname(rs.getString("cnickname"));
					// 수정된 PostDO 객체를 리스트에 추가
					commentList.add(postDO);
				}
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
			return commentList;
		}
		// 댓글 등록시 insert
		public int InsertComment(PostDO post) {
			int rowCount = 0;
			this.sql = "insert into comments (cNo, pNo, id, cContent, cRegdate)"
						+ "values (seq_pno.nextval, ?, ?, ?, sysdate) ";
			
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
		// 댓글 삭제시 
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
	
		// 경인 - JSON DAO - 해당 pno의 글에 id값을 받아서 좋아요 수 업데이트

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
		            
		            jsonObject.put("likeStatus", "x");
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
		            
		            jsonObject.put("likeStatus", "o");
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
		
		
		
		
		
		
		// 현재 접속한 id가 해당 pno글에 좋아요를 했는지 안했는지 여부 판단 + 해당 pno글의 최신 좋아요 개수 가져오기
		public String updateLikeStatus(int pno) {
		    JSONArray jsonArray = new JSONArray();
		    JSONObject jsonObject = new JSONObject();

		    try {
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
		
		
		
		
		
		
		
		

}
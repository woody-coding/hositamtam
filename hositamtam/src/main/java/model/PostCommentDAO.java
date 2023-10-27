package model;

import java.sql.*;
import java.util.*;

public class PostCommentDAO {

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

	public PostCommentDAO() {
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
    public List<PostCommentDTO> getPostAndComments(int pno) {
        List<PostCommentDTO> comments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 데이터베이스 연결 로직

            String sql = "SELECT p.*, c.cno, c.ccontent, c.cregdate FROM post p " +
                         "JOIN comments c ON p.pno = c.pno " +
                         "WHERE p.pno = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pno);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PostCommentDTO dto = new PostCommentDTO();
                dto.setPno(resultSet.getInt("pno"));
                dto.setId(resultSet.getString("id"));
                dto.setPregdate(resultSet.getDate("pregdate"));
                dto.setPtitle(resultSet.getString("ptitle"));
                dto.setPcontent(resultSet.getString("pcontent"));
                dto.setPphoto(resultSet.getString("pphoto"));
                dto.setPlikecount(resultSet.getInt("plikecount"));
                dto.setPcategory(resultSet.getString("pcategory"));
                dto.setCno(resultSet.getInt("cno"));
                dto.setCcontent(resultSet.getString("ccontent"));
                dto.setCregdate(resultSet.getDate("cregdate"));

                comments.add(dto);
            }
        } catch (SQLException e) {
            // 예외 처리
        } finally {
            // 연결 및 리소스 해제 로직
        }

        return comments;
    }

    // 다른 데이터베이스 연산 메서드 및 연결 관리 코드도 추가할 수 있음
}


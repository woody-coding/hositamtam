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

	// ㄱ.점포 리스트 조회 (리뷰순, 평점순, 찜순)
	// ㄴ.점포 상세페이지 조회
	// ㄷ.점포 등록
	// ㄹ.점포 수정
	// ㅁ.폐업 제보
	
	// ㄱ.점포 리스트 조회
	public ArrayList<StoreDO> getStore() {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		sql = "SELECT sname, stype, sphoto FROM store WHERE mno = 1 ORDER BY sfavoritecount DESC";

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
	
	// [store] 등록자의 아이디, 점포형태, 점포사진 
	// [review] 해당 점포의 최근리뷰 2개, 리뷰 개수, 평균 평점)
	// [payment + store_payment] 결제방식
	// [review의 regdate 내림차순 2개 + 리뷰를 최근 작성한 사람 2명, 그 사람의 리뷰 갯수, 등록평점, 최근 작성일자
	// 최근 작성된 2개의 리뷰의 사용자를 출력하는 쿼리: SELECT id FROM 리뷰테이블 ORDER BY rregdate DESC LIMIT 2;
	// SELECT id, COUNT(*) AS 리뷰개수, AVG(rating) AS 리뷰평균 FROM 리뷰테이블 WHERE id = '다음 id' GROUP BY id;
	// sql = SELECT AVG(rrating) AS 평균평점 FROM review WHERE sno = 선택한 점포;
	//	
	//	store테이블과 review테이블에서,
	//	sno가 n에 해당하는 아아디, 점포명, 점포형태, 점포사진, 평점, 최근리뷰 2개를 출력하고,
	//	해당 최신리뷰에 일치하는 2개의 id가 각각 작성한 리뷰의 개수를 출력하는 쿼리문
	//	
	//	SELECT s.id, s.sname, s.stype, s.sphoto, r.rrating, r.rcontent, r.rregdate
	//	FROM store AS s
	//	LEFT JOIN (
	//    SELECT
	//        r1.sno,
	//        r1.id,
	//        r1.rrating,
	//        r1.rcontent,
	//        r1.rregdate
	//    FROM review AS r1
	//    WHERE r1.rno IN (
	//        SELECT TOP 2 r2.rno
	//        FROM review AS r2
	//        WHERE r2.sno = 'n'
	//        ORDER BY r2.rregdate DESC
	//    )
	//) AS r ON s.sno = r.sno
	//WHERE s.sno = 'n';
	
	// 작성중
	public ArrayList<StoreDO> getDetailStore() {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();

		sql = "SELECT sname, stype, sphoto FROM store WHERE mno = 1 ORDER BY sfavoritecount DESC";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StoreDO storeDO = new StoreDO(); 

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
	
	
}
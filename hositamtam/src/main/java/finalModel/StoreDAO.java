package finalModel;

import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import finalModel.StoreDO;

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

	
	// ㄱ.시장 내 점포 리스트 페이지에서 필요한 데이터 가져오기
	public String getStoreInMarket(int mno) {
		ArrayList<StoreDO> storeList = new ArrayList<StoreDO>();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		sql = "select m.mlat, m.mlng, s.sno, s.sname, s.slat, s.slng, s.stype, s.sphoto, s.sfavoritecount, s.scategory, (select nickname from member where member.id = s.id) as nickname "
				+ "from store s join market m on s.mno = m.mno "
				+ "where m.mno = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StoreDO storeDO = new StoreDO();
				
				storeDO.setMlat(rs.getString("mlat"));
				storeDO.setMlng(rs.getString("mlng"));
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
					
					jsonObject.put("mlat", store.getMlat());
					jsonObject.put("mlng", store.getMlng());
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
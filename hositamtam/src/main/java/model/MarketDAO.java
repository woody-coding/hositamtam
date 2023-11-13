package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MarketDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	public MarketDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1. 전체 시장 조회
	public String getMarketList() {
		ArrayList<MarketDO> marketList = new ArrayList<MarketDO>();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		sql = "select mno, mname, mtype, maddr, mlat, mlng, mtoilet, mparking, mtel, mupdateday from market";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MarketDO marketDO = new MarketDO();

				marketDO.setMno(rs.getInt("mno"));
				marketDO.setMname(rs.getString("mname"));
				marketDO.setMtype(rs.getString("mtype"));
				marketDO.setMaddr(rs.getString("maddr"));
				marketDO.setMlat(rs.getString("mlat"));
				marketDO.setMlng(rs.getString("mlng"));
				marketDO.setMtoilet(rs.getString("mtoilet"));
				marketDO.setMtel(rs.getString("mtel"));
				marketDO.setMupdateday(rs.getString("mupdateday"));

				marketList.add(marketDO);
			}

			for (MarketDO market : marketList) {
				jsonObject = new JSONObject(); // jsonObject 초기화

				jsonObject.put("mno", market.getMno());
				jsonObject.put("mname", market.getMname());
				jsonObject.put("mtype", market.getMtype());
				jsonObject.put("maddr", market.getMaddr());
				jsonObject.put("mlat", market.getMlat());
				jsonObject.put("mlng", market.getMlng());
				jsonObject.put("mtoilet", market.getMtoilet());
				jsonObject.put("mtel", market.getMtel());
				jsonObject.put("mupdateday", market.getMupdateday());

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

	// 1-1. 해당 시장의 이름, 위, 경도 값 가져오기
	public String getMarketLatLng(int mno) {
		ArrayList<MarketDO> marketList = new ArrayList<MarketDO>();

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;

		sql = "select mname, mlat, mlng from market where mno = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MarketDO marketDO = new MarketDO();

				marketDO.setMname(rs.getString("mname"));
				marketDO.setMlat(rs.getString("mlat"));
				marketDO.setMlng(rs.getString("mlng"));

				marketList.add(marketDO);
			}

			if (marketList.isEmpty()) {
				jsonObject = new JSONObject();
				jsonObject.put("storeErrorMsg", "등록된 시장이 없습니다!");
				jsonArray.add(jsonObject);
			}

			for (MarketDO market : marketList) {
				jsonObject = new JSONObject(); // jsonObject 초기화

				jsonObject.put("mname", market.getMname());
				jsonObject.put("mlat", market.getMlat());
				jsonObject.put("mlng", market.getMlng());

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

	// 2.카데고리 유입 시장리스트 조회
	public String getMarketListByItem(int cateno) {
		ArrayList<MarketDO> marketList = new ArrayList<MarketDO>();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		sql = "select m.mno, m.mname, m.mtype, m.maddr, m.mlat, m.mlng, m.mtoilet, m.mparking, m.mtel, m.mupdateday "
				+ "from market m " + "inner join market_category mc on m.mno = mc.mno "
				+ "inner join category c on mc.cateno = c.cateno " + "where c.cateno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cateno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MarketDO marketDO = new MarketDO();
				marketDO.setMno(rs.getInt("mno"));
				marketDO.setMname(rs.getString("mname"));
				marketDO.setMtype(rs.getString("mtype"));
				marketDO.setMaddr(rs.getString("maddr"));
				marketDO.setMlat(rs.getString("mlat"));
				marketDO.setMlng(rs.getString("mlng"));
				marketDO.setMtoilet(rs.getString("mtoilet"));
				marketDO.setMparking(rs.getString("mparking"));
				marketDO.setMtel(rs.getString("mtel"));
				marketDO.setMupdateday(rs.getString("mupdateday"));

				marketList.add(marketDO);
			}
			if (marketList.isEmpty()) {
				jsonObject = new JSONObject();
				jsonObject.put("marketErrorMsg", "잘못된 접근입니다. 올바른 방식으로 카테고리 선택을 해주세요!");
				jsonArray.add(jsonObject);
			}
			for (MarketDO market : marketList) {
				jsonObject = new JSONObject(); // jsonObject 초기화
				jsonObject.put("mno", market.getMno());
				jsonObject.put("mname", market.getMname());
				jsonObject.put("mtype", market.getMtype());
				jsonObject.put("maddr", market.getMaddr());
				jsonObject.put("mlat", market.getMlat());
				jsonObject.put("mlng", market.getMlng());
				jsonObject.put("mtoilet", market.getMtoilet());
				jsonObject.put("mparking", market.getMparking());
				jsonObject.put("mtel", market.getMtel());
				jsonObject.put("mupdateday", market.getMupdateday());
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

	// 3. 검색어 유입 시장리스트 조회
	public String getMarketListBySearch(String keyword) {
		ArrayList<MarketDO> marketList = new ArrayList<MarketDO>();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		sql = "SELECT mno, mname, mtype, maddr, mlat, mlng, mtoilet, mparking, mtel, mupdateday " + "FROM market "
				+ "WHERE mname LIKE ? OR maddr LIKE ? " + "ORDER BY mname";
		try {
			keyword = "%" + keyword + "%";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MarketDO marketDO = new MarketDO();
				marketDO.setMno(rs.getInt("mno"));
				marketDO.setMname(rs.getString("mname"));
				marketDO.setMtype(rs.getString("mtype"));
				marketDO.setMaddr(rs.getString("maddr"));
				marketDO.setMlat(rs.getString("mlat"));
				marketDO.setMlng(rs.getString("mlng"));
				marketDO.setMtoilet(rs.getString("mtoilet"));
				marketDO.setMparking(rs.getString("mparking"));
				marketDO.setMtel(rs.getString("mtel"));
				marketDO.setMupdateday(rs.getString("mupdateday"));
				marketList.add(marketDO);
			}
			if (marketList.isEmpty()) {
				jsonObject = new JSONObject();
				jsonObject.put("marketErrorMsg", "등록된 시장이 없습니다!");
				jsonArray.add(jsonObject);
			}
			for (MarketDO market : marketList) {
				jsonObject = new JSONObject(); // jsonObject 초기화
				jsonObject.put("mno", market.getMno());
				jsonObject.put("mname", market.getMname());
				jsonObject.put("mtype", market.getMtype());
				jsonObject.put("maddr", market.getMaddr());
				jsonObject.put("mlat", market.getMlat());
				jsonObject.put("mlng", market.getMlng());
				jsonObject.put("mtoilet", market.getMtoilet());
				jsonObject.put("mparking", market.getMparking());
				jsonObject.put("mtel", market.getMtel());
				jsonObject.put("mupdateday", market.getMupdateday());
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
}

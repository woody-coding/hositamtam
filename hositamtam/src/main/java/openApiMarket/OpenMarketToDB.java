package openApiMarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OpenMarketToDB {
    private JDBC_Driver jdbc_driver;
    private Connection conn;

    private static final Map<String, Integer> CATEGORY_MAP = new HashMap<>();
    static {
        CATEGORY_MAP.put("농산물", 1);
        CATEGORY_MAP.put("음식점", 2);
        CATEGORY_MAP.put("가공식품", 3);
        CATEGORY_MAP.put("수산물", 4);
        CATEGORY_MAP.put("축산물", 5);
        CATEGORY_MAP.put("가정용품", 6);
        CATEGORY_MAP.put("의류", 7);
        CATEGORY_MAP.put("신발", 8);
    }

    public OpenMarketToDB() {
        this.jdbc_driver = new JDBC_Driver();
        this.conn = this.jdbc_driver.getConn();
    }

    public void insertMarketData() {
        String insertMarketSql = "INSERT INTO market (mno, mname, mtype, maddr, mlat, mlng, mtoilet, mparking, mtel, mupdateday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertMarketCategorySql = "INSERT INTO market_category (mno, cateno) VALUES (?, ?)";
        
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_trdit_mrkt_api");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=Z%2BrUFXa1UGNPUcJSqJR0LfniA9hOC17zHDE7vjxKLoIiKGDiEo%2FfsGkK%2BTdDPdVlizZp3YOf%2BiHCyOFRbgt9XA%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            httpConn.disconnect();

            String openJsonData = sb.toString();

            long dbStartTime = System.currentTimeMillis();

            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(openJsonData);
            JSONObject response = (JSONObject) jsonObj.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONArray itemsArray = (JSONArray) body.get("items");

            pstmt = conn.prepareStatement(insertMarketSql);
            pstmt2 = conn.prepareStatement(insertMarketCategorySql);

            int mno = 1;
            for (int i = 0; i < itemsArray.size(); i++) {
                JSONObject marketObj = (JSONObject) itemsArray.get(i);
                String maddr = (String) marketObj.get("rdnmadr");

                if (maddr.startsWith("부산광역시")) {
                    String mname = (String) marketObj.get("mrktNm");
                    String mtype = (String) marketObj.get("mrktType");
                    String mlat = (String) marketObj.get("latitude");
                    String mlng = (String) marketObj.get("longitude");
                    String mtoilet = (String) marketObj.get("pblicToiletYn");
                    String mparking = (String) marketObj.get("prkplceYn");
                    String mtel = (String) marketObj.get("phoneNumber");
                    String mupdateday = (String) marketObj.get("referenceDate");
                    String pluscategory = (String)marketObj.get("trtmntPrdlst");

                    pstmt.setInt(1, mno);
                    pstmt.setString(2, mname);
                    pstmt.setString(3, mtype);
                    pstmt.setString(4, maddr);
                    pstmt.setString(5, mlat);
                    pstmt.setString(6, mlng);
                    pstmt.setString(7, mtoilet);
                    pstmt.setString(8, mparking);
                    pstmt.setString(9, mtel);
                    pstmt.setString(10, mupdateday);
                    pstmt.executeUpdate();

                    String[] mcateArr = pluscategory.split("\\+");
                    Set<Integer> categorySet = new HashSet<>();

                    for (String category : mcateArr) {
                        int categoryNumber = CATEGORY_MAP.getOrDefault(category.trim(), 9);
                        categorySet.add(categoryNumber);
                    }

                    for (Integer category : categorySet) {
                        pstmt2.setInt(1, mno);
                        pstmt2.setInt(2, category);
                        pstmt2.executeUpdate();
                    }

                    mno++;
                }
            }
            
            long endTime = System.currentTimeMillis();
            long dbTime = endTime - dbStartTime;

            System.out.println("DB에 넣기 성공~");
            System.out.println("DB 처리 시간: " + dbTime + "ms");
            
        } catch (ParseException | IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    	OpenMarketToDB openMarketToDB = new OpenMarketToDB();
    	openMarketToDB.insertMarketData();
    }
}

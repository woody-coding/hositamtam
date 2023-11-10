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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import openApiMarket.JDBC_Driver;

public class OpenMarketToDB {
    private JDBC_Driver jdbc_driver;
    private Connection conn;

    public OpenMarketToDB() {
        this.jdbc_driver = new JDBC_Driver();
        this.conn = this.jdbc_driver.getConn();
    }

    public void insertMarketData() {
        String insertMarketSql = "INSERT INTO market (mno, mname, mtype, maddr, mlat, mlng, mtoilet, mparking, mtel, mupdateday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertMarketCategorySql = "INSERT INTO market_category (mno, cateno) VALUES (?, ?)";
        
        // ----------------------------------------------------------------------------------------
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_trdit_mrkt_api"); /* URL */
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=Z%2BrUFXa1UGNPUcJSqJR0LfniA9hOC17zHDE7vjxKLoIiKGDiEo%2FfsGkK%2BTdDPdVlizZp3YOf%2BiHCyOFRbgt9XA%3D%3D"); /* Service Key */
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /* 한 페이지 결과 수 */
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* XML/JSON 여부 */

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

            // ----------------------------------------------------------------------------------------
            // JSON 파서 초기화
            JSONParser parser = new JSONParser();

            // JSON 데이터 파싱
            JSONObject jsonObj = (JSONObject) parser.parse(openJsonData);
            JSONObject response = (JSONObject) jsonObj.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONArray itemsArray = (JSONArray) body.get("items");

            // PreparedStatement를 준비하고 반복해서 데이터 삽입
            int mno = 1; // 시장 번호 초기값
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

                    // PreparedStatement를 초기화하고 쿼리 설정
                    PreparedStatement pstmt = conn.prepareStatement(insertMarketSql);
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

                    // 쿼리1 실행
                    pstmt.executeUpdate();

                    
                    // 쿼리2 실행준비
                    PreparedStatement pstmt2 = conn.prepareStatement(insertMarketCategorySql);

                    String[] mcateArr = pluscategory.split("\\+");

                    Set<Integer> categorySet = new HashSet<>(); // 중복을 방지하기 위한 Set

                    for (int j = 0; j < mcateArr.length; j++) {
                        
                    	// 각 카테고리 값에 따라 해당 번호를 할당
                        int categoryNumber = 9; // 기본값 설정

                        if (mcateArr[j].equals("농산물")) {
                            categoryNumber = 1;
                        } else if (mcateArr[j].equals("음식점")) {
                            categoryNumber = 2;
                        } else if (mcateArr[j].equals("가공식품")) {
                            categoryNumber = 3;
                        } else if (mcateArr[j].equals("수산물")) {
                            categoryNumber = 4;
                        } else if (mcateArr[j].equals("축산물")) {
                            categoryNumber = 5;
                        } else if (mcateArr[j].equals("가정용품")) {
                            categoryNumber = 6;
                        } else if (mcateArr[j].equals("의류")) {
                            categoryNumber = 7;
                        } else if (mcateArr[j].equals("신발")) {
                            categoryNumber = 8;
                        }

                        // 중복을 방지하기 위해 Set에 추가
                        categorySet.add(categoryNumber);
                    }

                    
                    pstmt2.setInt(1, mno);
                    
                    // 중복을 제거한 카테고리 번호를 삽입
                    for (Integer category : categorySet) {
                        pstmt2.setInt(2, category);
                        pstmt2.addBatch();
                    }

                    // 쿼리2 실행
                    pstmt2.executeBatch();
                    
                    
                    // PreparedStatement 닫기
                    pstmt2.close();
                    pstmt.close();
                    

                    // 시장 번호 증가
                    mno++;
                }
            }
            
            System.out.println("DB에 넣기 성공~");
            
        } catch (ParseException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OpenMarketToDB openMarketToDB = new OpenMarketToDB();
        openMarketToDB.insertMarketData();
        
    }
}
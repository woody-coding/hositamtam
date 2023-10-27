package finalModel;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Driver {
	
	private Connection conn;
	
	public JDBC_Driver() {
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";

		if (this.conn == null) {
			try {
				Class.forName(jdbc_driver);
				this.conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConn() {
		return this.conn;
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
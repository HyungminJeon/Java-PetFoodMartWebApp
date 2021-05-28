package com.petMart.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String user = "pet";
	private String passwd = "pet";
	private String url = "jdbc:oracle:thin@192.168.0.67:1521:xe";
	public Connection conn;
	
	public DAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

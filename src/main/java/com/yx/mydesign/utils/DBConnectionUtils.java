package com.yx.mydesign.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionUtils {
	static String driver = "com.mysql.jdbc.Driver";
	static String dbUrl = "jdbc:mysql://localhost:3306/db_mydesign?useUnicode=true&characterEncoding=utf8";
	static String dbUser = "root";
	static String dbPassword = "root";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static Statement getStatement(Connection conn) {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

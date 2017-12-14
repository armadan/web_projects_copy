package com.library.gcit.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static Connection conn = null;

	public static Connection getConnection() throws SQLException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/library", "root", "Madan9632");
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}

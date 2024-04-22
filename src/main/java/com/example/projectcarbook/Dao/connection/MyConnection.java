package com.example.projectcarbook.Dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class MyConnection {
	private static Connection connection;
	private static final String URL ="jdbc:mysql://localhost:3307/carbook";
	private static final String username="root";
	private static final String password="1234";
	public static Connection getInstance() {
		if (connection == null) {
			connection = getConnection();
		}
		return connection;
	}

	public static Connection getConnection() {
		Connection connection1 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection1 = DriverManager.getConnection(URL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection1;
	}
}

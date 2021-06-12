package com.bankingutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://database-project0.cgjdktnpvary.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String USERNAME = "nilsev";
	private static final String PASSWORD = "password";

	private static Connection conn;

	public static Connection getConnection() {

		try {

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return conn;
	}


}

package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import org.mariadb.jdbc.Driver;
import java.sql.SQLException;

public class ConnectionUtility {
	
	//Makes it so the connection cannot be instantiated
	private ConnectionUtility() {
		
	}
	
	//Gets the connection from the db_url, db_username, db_password System variables.
	public static Connection getConnection() throws SQLException {
		
		
		DriverManager.registerDriver(new Driver());
		
		String url = System.getenv("db_url");
		String username = System.getenv("db_username");
		String password = System.getenv("db_password");
		
		Connection connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
	
}

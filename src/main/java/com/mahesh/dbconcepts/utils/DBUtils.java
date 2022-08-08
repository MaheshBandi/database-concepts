package com.mahesh.dbconcepts.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	
	private static final String DB_DRIVER_CLASS ="db.driver.classname";
	private static final String DB_URL = "db.url";
	private static final String DB_USER_NAME = "db.username";
	private static final String DB_PASSWORD = "db.password";
	private static Connection connection =null;
	
	static {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("src/main/resources/database.properties"));
			
			Class.forName(props.getProperty(DB_DRIVER_CLASS));
			
			connection = DriverManager.getConnection(
					props.getProperty(DB_URL), 
					props.getProperty(DB_USER_NAME), 
					props.getProperty(DB_PASSWORD));
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}

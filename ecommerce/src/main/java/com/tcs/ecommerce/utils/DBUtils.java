package com.tcs.ecommerce.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//Whenever we will come across utility classes then we should mark these classes with @component
//singleton
@Component
public class DBUtils {
	
	@PostConstruct
	public void init() {
		System.out.println("init called");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.print("Destroy called");
	}
//	@Autowired
//	private static Environment environment;
	
	@Autowired
	DataSource dataSource;
	public Connection getConnection() {

		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Connection connection = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			
//			connection = DriverManager
//					.getConnection("jdbc:mysql://localhost:3306/tcs?useSSL=false", "root","MYSQL123$");
//			connection.setAutoCommit(false);
//			return connection;
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return connection;
		return null;
		
	}
	
	public void closeConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

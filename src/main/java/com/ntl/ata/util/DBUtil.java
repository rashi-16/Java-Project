package com.ntl.ata.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/ ataproject";
	static final String USER = "root";
	   static final String PASS = "root";
	  static  Connection conn=null;
	public static Connection getDBConnection(String dtype) {
		
		if(dtype.equals("mysql")) {
		
		try{
			 Class.forName(JDBC_DRIVER);
		 
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 }
		 catch (ClassNotFoundException e) {
				System.out.println("CLASS NOt found   "+e);
			}
		 catch(SQLException s){
				System.out.println("Sql exception" + s);
				
			}
		 
		}
		else {
			System.out.println(" Invalid DataBase Type");
		}
		
		
		return conn;	
	}
	

}

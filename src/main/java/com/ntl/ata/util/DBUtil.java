package com.ntl.ata.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ntl.ata.client.ATAClient;


public class DBUtil {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/ ataproject";
	static final String USER = "root";
	   static final String PASS = "root";
	  static  Connection conn=null;
	  //static DataSource dataSource=null;
	  static Logger log = Logger.getLogger(DBUtil.class);
	 
		
	public static Connection getDBConnection(String dtype) {
		
		if(dtype.equals("mysql")) {
		
		try{
			 Class.forName(JDBC_DRIVER);
		 
	    log.info("Connecting to database...");
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

package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.util.DBUtil;

public class CredentialsDaoImpl implements CredentialsDao {
	
	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	DataSource dataSource;
	CredentialsBean userCred;
	
	public CredentialsDaoImpl(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
		try {
			this.connection=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CredentialsDaoImpl() {
		connection=DBUtil.getDBConnection("mysql");
		
	}

	

	



	public String createCredentials(CredentialsBean credentials) {
		// TODO Auto-generated method stub
		try {
			
			
			
			pstmt =connection.prepareStatement("insert into ata_tbl_user_credentials values(?,?,?,?)");
			 pstmt.setString(1,credentials.getUserID());
		     pstmt.setString(2,credentials.getPassword());
		     pstmt.setString(3,credentials.getUserType());
		     pstmt.setInt(4,credentials.getLoginStatus());
		     
		     int z=pstmt.executeUpdate();
		     
		     
		     if(z==1) {
		    	 
		    	 return "Success";
		     }
		     else {
		    	 
		    	 return "Fail";
		     }
			}
		catch (SQLException e1){
				System.out.println("Sql exception"+ e1);
				return "ERROR";
			}catch(Exception e) {
				System.out.println("Exception occurred"+e);
				return "ERROR";
			}
	}

	

	public boolean updateCredentials(CredentialsBean credentials) {
		// TODO Auto-generated method stub
		try {
			
			pstmt =connection.prepareStatement("Update  ata_tbl_user_credentials set password=?, loginstatus=? where Userid=?");
			 pstmt.setString(1,credentials.getPassword());
		     pstmt.setInt(2,credentials.getLoginStatus());
		     pstmt.setString(3,credentials.getUserID());
		     int z=pstmt.executeUpdate();
		     
		     if(z!=0) {
		    	 
		    	 return true;
		     }
		     else {
		    	 
		    	 return false;
		     }
			}
		catch (SQLException e1){
				System.out.println("Sql exception"+ e1);
				return false;
			}catch(Exception e) {
				System.out.println("Exception occurred"+e);
				return false;
			}
		     
	}

	public CredentialsBean findByID(String userid) {
		// TODO Auto-generated method stub
		try {
		
		//System.out.println("Connection in DAO "+connection);
		pstmt=connection.prepareStatement("select * from ata_tbl_user_credentials where userid=?");
		pstmt.setString(1,userid);
		rst=pstmt.executeQuery();
		while(rst.next()) {
			
		String uid=	rst.getString(1);
		String pass=rst.getString(2);
		String type=rst.getString(3);
		int status=rst.getInt(4);
		userCred= new CredentialsBean(uid, pass, type, status);
		}
		}
		catch (SQLException e) {
			System.out.println(" Exception while retrieving data from the database ");
			}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			
		}
		return userCred;
	}
		 
	public CredentialsBean findLoggedIn(int loginstatus) {
		// TODO Auto-generated method stub
		try {
			
			pstmt = connection.prepareStatement("select * from ata_tbl_user_credentials where loginstatus=?");
			pstmt.setInt(1,loginstatus);
			rst=pstmt.executeQuery();
			while(rst.next()) {
				
			String uid=	rst.getString(1);
			String pass=rst.getString(2);
			String type=rst.getString(3);
			int status=rst.getInt(4);
			userCred= new CredentialsBean(uid, pass, type, status);
		}
			
		}
			catch (SQLException e) {
				System.out.println(" Exception while retrieving data from the database ");
				}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			
		}
		return userCred;
	}
}



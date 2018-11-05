package com.ntl.ata.dao.impl;

import java.sql.*;
import java.util.ArrayList;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.ProfileBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.ProfileDao;
import com.ntl.ata.util.DBUtil;

public class ProfileDaoImpl implements ProfileDao{
	//ProfileBean profileBean = new ProfileBean();
	CredentialsDao credDao = new CredentialsDaoImpl();
	CredentialsBean credB;
	Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;

	public String createProfile(ProfileBean profile) {
		// TODO Auto-generated method stub
		try {
			connection=DBUtil.getDBConnection("mysql");
			pstmt =connection.prepareStatement("insert into ata_tbl_user_profile values(?,?,?,?,?,?,?,?,?,?,?,?)");
			 pstmt.setString(1,profile.getUserId() );
		     pstmt.setString(2, profile.getFirstName() );
		     pstmt.setString(3, profile.getLastName());
		     pstmt.setDate(4, Date.valueOf(profile.getDateOfBirth()));
		     pstmt.setString(5, profile.getGender());
		     pstmt.setString(6, profile.getStreet());
		     pstmt.setString(7, profile.getLocation());
		     pstmt.setString(8, profile.getCity());
		     pstmt.setString(9, profile.getState());
		     pstmt.setString(10, profile.getPincode());
		     pstmt.setString(11, profile.getMobileNo());
		     pstmt.setString(12, profile.getEmailID());
		    
		     int z=pstmt.executeUpdate();
				pstmt.close();
				connection.close();
		     if(z!=0) {
		    	 
		    	 return "Success";
		     }
		     else {
		    	 
		    	 return "Fail";
		     }
			}
		catch (SQLException e1){
				System.out.println("Sql exception"+ e1);
				return "ERROR";
			}
	}

	
	
	
	

}

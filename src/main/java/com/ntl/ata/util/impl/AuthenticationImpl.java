package com.ntl.ata.util.impl;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.util.Authentication;


import java.sql.*;
public class AuthenticationImpl implements Authentication{

	CredentialsDao credDao ;
	static String status1="Invalid";
	 static CredentialsBean credb;
	
	 public AuthenticationImpl() {
		credDao= new CredentialsDaoImpl();	
		}

	public AuthenticationImpl(CredentialsDao credDao) {
		// TODO Auto-generated constructor stub
		this.credDao = credDao;
	}

	public AuthenticationImpl(CredentialsBean credb) {
		this.credb = credb;
		// TODO Auto-generated constructor stub
	}

	public boolean authenticate(CredentialsBean credentialsBean) {
		// TODO Auto-generated method stub
		 	credb = credDao.findByID(credentialsBean.getUserID());
			
		 	if(credb.getPassword().equals(credentialsBean.getPassword())) {
		 		System.out.println("correct user Id and Password");
				return true;
		 	}
			else {
				status1="Fail";
			System.out.println(" wrong userid or password ");
				return false;
			}
	 }

	public String authorize(String userID) {
		credb = credDao.findByID(userID);
		if(credb.getUserID().equals(userID))
		return credb.getUserType();
		else
			return "invalid";
	}

	public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus) {
		CredentialsBean credentials=new CredentialsBean();
			credentials = credDao.findByID(credentialsBean.getUserID());
			if(credentials.getLoginStatus()==credentialsBean.getLoginStatus()) {
		credentials.setLoginStatus(loginStatus);
		credDao.updateCredentials(credentials);
		return true;
			}
			else
				return false;

		
	}

}

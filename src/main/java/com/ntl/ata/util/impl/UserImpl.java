package com.ntl.ata.util.impl;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.ProfileBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.ProfileDao;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.dao.impl.ProfileDaoImpl;
import com.ntl.ata.util.Authentication;

import com.ntl.ata.util.User;


public class UserImpl implements User {
	
	
	CredentialsBean credB;
	CredentialsDao credDao; 
	ProfileDao profileDao;
	static String uniqueId;
	Authentication auth;
	
	public UserImpl() {
		credDao=new CredentialsDaoImpl();
		profileDao= new ProfileDaoImpl();
		auth = new AuthenticationImpl();
	}
	
	//public UserImpl(AuthenticationImpl auth2) {
		
	//}

	public UserImpl(AuthenticationImpl auth) {
		// TODO Auto-generated constructor stub
		this.auth = auth;
	}

	public String login(CredentialsBean credentialsBean) {
		String userType=null;
		// will call Authenticate to get
		
	boolean result=	auth.authenticate(credentialsBean);
	
	if(result) {
	 userType=	auth.authorize(credentialsBean.getUserID());
	 credentialsBean.setUserType(userType);
	 boolean lstat=auth.changeLoginStatus(credentialsBean, 1);
	 if(lstat)
		 System.out.println("Login status changed");
	 else
		 System.out.println("Failed to change login status");
	}
	else {
		userType=AuthenticationImpl.status1;
	}
	
	return userType;
	}

	public boolean logout(String userId) {
		// TODO Auto-generated method stub
		CredentialsBean credentials =credDao.findByID(userId);
		boolean stat=auth.changeLoginStatus(credentials, 0);
		if(stat)
		{return true;
		
		}
		else
		return false;
	}

	public String changePassword(CredentialsBean credentialsBean, String newPassword)
	{			// TODO Auto-generated method stub
		credB= credDao.findByID(credentialsBean.getUserID());
		if((credB.getUserID().equals(credentialsBean.getUserID())) && (credB.getPassword().equals(credentialsBean.getPassword())))
		{		
			if(credentialsBean.getPassword().equals(newPassword)) {
			System.out.println("Old password and new password are same");
			System.out.println("Re-enter the new password");
			return "Invalid";
		}
			else if(!credentialsBean.getPassword().equals(newPassword)) {
				credentialsBean.setPassword(newPassword);
				boolean res=credDao.updateCredentials(credentialsBean);
				if(res) {
					return "Success";
							}
					else
						{return "Fail";}
		}
		}
		return "Invalid";
	}
		

	public String register(ProfileBean profileBean) {
		// TODO Auto-generated method stub
		credB=new CredentialsBean();

		String charf = profileBean.getFirstName().substring(0,2);
		int id=(int) Math.round(Math.random()*10000);
		uniqueId=charf+id;
		profileBean.setUserId(uniqueId);
		String result=profileDao.createProfile(profileBean);
		
		credB.setUserID(profileBean.getUserId());
	    credB.setPassword(profileBean.getPassword());
	    credB.setUserType("C");
	    credB.setLoginStatus(0);
	    String result1 = credDao.createCredentials(credB);
		//String result=credDao.createCredentials(credentials)
		if(result=="Success" && result1=="Success")
		return uniqueId;
		else if(result=="Fail")
			return "Fail";
		else
			return "Error";
	}
		
		
		
	}



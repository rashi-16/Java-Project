package com.ntl.ata.util;

import com.ntl.ata.bean.CredentialsBean;

public interface Authentication {
	
	public boolean authenticate(CredentialsBean credentialsBean);
	public String authorize(String userID);
	public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus);
	
	

}

package com.ntl.ata.dao;

import java.util.ArrayList;

import com.ntl.ata.bean.CredentialsBean;

public interface CredentialsDao {
	 
	public String createCredentials(CredentialsBean credentials);
	public int deleteCredentials(ArrayList<String> arr );
	public boolean updateCredentials(CredentialsBean credentials);
	public CredentialsBean findByID(String userid);
	public CredentialsBean findLoggedIn(int loginstatus);

}

package com.ntl.ata.util;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.ProfileBean;

public interface User {
	public String login(CredentialsBean credentialsBean);
	public boolean logout(String userId);
	public String changePassword(CredentialsBean credentialsBean, String newPassword);
	public String register(ProfileBean profileBean);

}

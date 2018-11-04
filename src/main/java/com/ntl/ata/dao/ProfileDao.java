package com.ntl.ata.dao;

import java.util.ArrayList;


import com.ntl.ata.bean.ProfileBean;

public interface ProfileDao {
	public String createProfile(ProfileBean profile);
	public int deleteCredentials(ArrayList<String> arr );
	public boolean updateProfile(ProfileBean credentials);
	public ProfileBean findByID(String userid);
	public ArrayList<ProfileBean> findAll();

}

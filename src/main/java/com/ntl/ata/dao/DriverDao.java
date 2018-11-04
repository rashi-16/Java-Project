package com.ntl.ata.dao;

import java.util.ArrayList;

import com.ntl.ata.bean.DriverBean;

public interface DriverDao {
	
	public String createDriver(DriverBean driverBean);
	public int deleteDriver(ArrayList<String> driverId );
	public boolean updateDriver(DriverBean driverBean);
	public DriverBean findByID(String driverId);
	public ArrayList<DriverBean> findAll();



}

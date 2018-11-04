package com.ntl.ata.dao;

import java.util.ArrayList;

import com.ntl.ata.bean.VehicleBean;



public interface VehicleDao {
	
	
	public String createVehicle(VehicleBean vehicleBean);
	public int deleteVehicle(ArrayList<String> vehicleId );
	public boolean updateVehicle(VehicleBean vehicleBean);
	public VehicleBean findByID(String vehicleId);
	public ArrayList<VehicleBean> findByVehicleType(String vehicleType);
	public ArrayList<VehicleBean> findBySeats(int noOfSeats);


}

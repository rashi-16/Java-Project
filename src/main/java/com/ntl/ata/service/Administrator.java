package com.ntl.ata.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;

public interface Administrator {
	
	public String addVehicle(VehicleBean vehicleBean);
	public int deleteVehicle(ArrayList<String> vehicleID);
	public VehicleBean viewVehicle(String vehicleID);
	public boolean modifyVehicle(VehicleBean vehicleBean);
	public String addDriver(DriverBean driverBean);
	public int deleteDriver(ArrayList<String> driverID);
	public boolean allotDriver(String reservationID, String driverID);//change the status
	public boolean modifyDriver(DriverBean driverBean);
	public String addRoute(RouteBean routeBean);
	public int deleteRoute(ArrayList<String> routeID);
    public RouteBean viewRoute(String routeID);
	public boolean modifyRoute(RouteBean routeBean);
	public ArrayList<ReservationBean> viewBookingDetails(LocalDate journeyDate,String source, String destination);


}

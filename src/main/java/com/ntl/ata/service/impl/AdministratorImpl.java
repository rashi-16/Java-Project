package com.ntl.ata.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;
import com.ntl.ata.dao.DriverDao;
import com.ntl.ata.dao.ReservationDao;
import com.ntl.ata.dao.RouteDao;
import com.ntl.ata.dao.VehicleDao;
import com.ntl.ata.dao.impl.DriverDaoImpl;
import com.ntl.ata.dao.impl.ReservationDaoImpl;
import com.ntl.ata.dao.impl.RouteDaoImpl;
import com.ntl.ata.dao.impl.VehicleDaoImpl;
import com.ntl.ata.service.Administrator;

public class AdministratorImpl implements Administrator {
	
	VehicleDao vehicleDetails = new VehicleDaoImpl();
	DriverDao driverDetails = new DriverDaoImpl();
	RouteDao routeDetails = new RouteDaoImpl();
	ReservationDao reservation = new ReservationDaoImpl();
	
	public String addVehicle(VehicleBean vehicleBean) {
		// TODO Auto-generated method stub
		String temp=vehicleBean.getName().substring(0, 2);
		int num=(int)(Math.round(Math.random()*10000));
		String unique=temp+num;
		vehicleBean.setVehicleID(unique);
		String result = vehicleDetails.createVehicle(vehicleBean);
		return result;
	}

	public int deleteVehicle(ArrayList<String> vehicleID) {
		// TODO Auto-generated method stub
		return vehicleDetails.deleteVehicle(vehicleID);
	}

	public VehicleBean viewVehicle(String vehicleID) {
		// TODO Auto-generated method stub
		return vehicleDetails.findByID(vehicleID);
	}

	public boolean modifyVehicle(VehicleBean vehicleBean) {
		// TODO Auto-generated method stub
		boolean res= vehicleDetails.updateVehicle(vehicleBean);
		return res;
	}

	public String addDriver(DriverBean driverBean) {
		// TODO Auto-generated method stub
		
		String temp=driverBean.getName().substring(0, 2);
		int num=(int)(Math.round(Math.random()*10000));
		String unique=temp+num;
		driverBean.setDriverID(unique);
		String result = driverDetails.createDriver(driverBean);
		return result;
	}

	
	public int deleteDriver(ArrayList<String> driverID) {
		// TODO Auto-generated method stub
		int result = driverDetails.deleteDriver(driverID);
		return result;
	}

	
	public boolean allotDriver(String reservationID, String driverID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean modifyDriver(DriverBean driverBean) {
		// TODO Auto-generated method stub
		boolean res= driverDetails.updateDriver(driverBean);
		return res;
	}

	
	public String addRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		String source=routeBean.getSource().substring(0,2);
		String dest=routeBean.getDestination().substring(0,2);
		int num = (int)(Math.round(Math.random()*10000));
		String uniqueId = source+dest+num;
		routeBean.setRouteID(uniqueId);
		String res= routeDetails.createRoute(routeBean);	
		return res;
	}

	
	public int deleteRoute(ArrayList<String> routeID) {
		// TODO Auto-generated method stub
		return routeDetails.deleteRoute(routeID);
	}

	
	public RouteBean viewRoute(String routeID) {
		// TODO Auto-generated method stub
		return routeDetails.findByID(routeID);
	}

	
	public boolean modifyRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		return routeDetails.updateRoute(routeBean);
	}

	
	public ArrayList<ReservationBean> viewBookingDetails(LocalDate journeyDate, String source, String destination) {
		// TODO Auto-generated method stub
		return reservation.bookingDetails(journeyDate, source, destination);
	}

}

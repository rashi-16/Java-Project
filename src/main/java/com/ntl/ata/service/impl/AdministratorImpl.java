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
	
	public AdministratorImpl() {
		
	}
	public AdministratorImpl(VehicleDaoImpl vehicleDetails) {
		this.vehicleDetails=vehicleDetails;
	}

	public AdministratorImpl(DriverDaoImpl driverDetails) {
		this.driverDetails = driverDetails;
	}
	public AdministratorImpl(RouteDaoImpl routeDetails) {
		this.routeDetails= routeDetails;
	}
	public String addVehicle(VehicleBean vehicleBean) {
		// TODO Auto-generated method stub
		String temp=vehicleBean.getName().substring(0, 2);
		int num=(int)(Math.round(Math.random()*10000));
		
		String unique=temp+num;
		vehicleBean.setVehicleID(unique);
		try {
		String result = vehicleDetails.createVehicle(vehicleBean);
		if(result.equals("Success"))
		return unique;
		else 
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception " +e);
			return null;
		}
	}

	public int deleteVehicle(ArrayList<String> vehicleID) {
		// TODO Auto-generated method stub
		try {
		return vehicleDetails.deleteVehicle(vehicleID);
		}
		catch(Exception e){
			System.out.println("Exception occurred "+e);
			return -1;
		}
	}

	public VehicleBean viewVehicle(String vehicleID) {
		// TODO Auto-generated method stub
		try {
		return vehicleDetails.findByID(vehicleID);
		}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	public boolean modifyVehicle(VehicleBean vehicleBean) {
		// TODO Auto-generated method stub
		try {
		boolean res= vehicleDetails.updateVehicle(vehicleBean);
		return res;
		}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

	public String addDriver(DriverBean driverBean) {
		// TODO Auto-generated method stub
		
		String temp=driverBean.getName().substring(0, 2);
		int num=(int)(Math.round(Math.random()*10000));
		String unique=temp+num;
		driverBean.setDriverID(unique);
		try {
		String result = driverDetails.createDriver(driverBean);
		if(result.equals("Success"))
			return unique;
			else 
				return result;
		}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	
	public int deleteDriver(ArrayList<String> driverID) {
		// TODO Auto-generated method stub
		try {
		int result = driverDetails.deleteDriver(driverID);
		return result;
	}catch(Exception e) {
		System.out.println("Exception occurred"+e);
		return -1;
	}
		}
	

	
	public boolean findByDriverStatus(String reservationID ) {
		ArrayList<DriverBean> drivers= driverDetails.findByDriverStatus();
		DriverBean driver = drivers.get(0);
		String DriverId = driver.getDriverID();
		driver.setDriverStatus(1);
		driverDetails.updateDriver(driver);
		try {
		boolean result= allotDriver(DriverId, reservationID);
		return result;
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

	
	public boolean allotDriver( String driverID, String reservationID) {
		// TODO Auto-generated method stub
		
	    ReservationBean rb = reservation.viewBooking(reservationID);
	    rb.setDriverID(driverID);
	    rb.setBookingStatus("Confirmed");
	    try {
	    return reservation.updateReservation(rb);
	    }catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
	    }
	    
	}

	
	public boolean modifyDriver(DriverBean driverBean) {
		// TODO Auto-generated method stub
		try {
		boolean res= driverDetails.updateDriver(driverBean);
		return res;
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

	
	public String addRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		String source=routeBean.getSource().substring(0,2);
		String dest=routeBean.getDestination().substring(0,2);
		int num = (int)(Math.round(Math.random()*10000));
		String uniqueId = source+dest+num;
		routeBean.setRouteID(uniqueId);
		
		try {
		String result= routeDetails.createRoute(routeBean);	
		if(result.equals("Success"))
			return uniqueId;
			else 
				return result;
	}catch(Exception e) {
		System.out.println("Exception occurred"+e);
		return null;
	}
	}

	
	public int deleteRoute(ArrayList<String> routeID) {
		// TODO Auto-generated method stub
		try {
		return routeDetails.deleteRoute(routeID);
		}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return -1;
		}
	}

	
	public RouteBean viewRoute(String routeID) {
		// TODO Auto-generated method stub
		try {
		return routeDetails.findByID(routeID);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	
	public boolean modifyRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		try {
		return routeDetails.updateRoute(routeBean);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

	
	public ArrayList<ReservationBean> viewBookingDetails(LocalDate journeyDate, String source, String destination) {
		// TODO Auto-generated method stub
		try {
		return reservation.bookingDetails(journeyDate, source, destination);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

}

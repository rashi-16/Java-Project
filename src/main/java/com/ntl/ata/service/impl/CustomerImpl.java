package com.ntl.ata.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ntl.ata.bean.PaymentBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;
import com.ntl.ata.dao.PaymentDao;
import com.ntl.ata.dao.ReservationDao;
import com.ntl.ata.dao.RouteDao;
import com.ntl.ata.dao.VehicleDao;
import com.ntl.ata.dao.impl.PaymentDaoImpl;
import com.ntl.ata.dao.impl.ReservationDaoImpl;
import com.ntl.ata.dao.impl.RouteDaoImpl;
import com.ntl.ata.dao.impl.VehicleDaoImpl;
import com.ntl.ata.service.Administrator;
import com.ntl.ata.service.Customer;

public class CustomerImpl implements Customer{

	VehicleDao vehicleDetails = new VehicleDaoImpl();
	RouteDao routeDetails = new RouteDaoImpl();
	ReservationDao reservation = new ReservationDaoImpl();
	PaymentDao paymentd =new PaymentDaoImpl();
	VehicleBean vehicle = new VehicleBean();
	
	public CustomerImpl(VehicleDaoImpl vehicleDetails) {
		// TODO Auto-generated constructor stub
		this.vehicleDetails=vehicleDetails;
	}

	public CustomerImpl() {
		// TODO Auto-generated constructor stub
	}

	public CustomerImpl(RouteDaoImpl routeDetails) {
		// TODO Auto-generated constructor stub
		this.routeDetails=routeDetails;
	}

	//where its vehicle id is not in reservation bean
	public ArrayList<VehicleBean> viewVehiclesByType(String vehicleType) {
		// TODO Auto-generated method stub
		try {
		return vehicleDetails.findByVehicleType(vehicleType);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	//where its vehicle id is not in reservation bean
	public ArrayList<VehicleBean> viewVehicleBySeats(int noOfSeats) {
		// TODO Auto-generated method stub
		try {
		return vehicleDetails.findBySeats(noOfSeats);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	public ArrayList<RouteBean> viewAllRoutes() {
		// TODO Auto-generated method stub	
		try {
		return routeDetails.findAll();
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	public String bookVehicle(ReservationBean reservationBean) {
		// TODO Auto-generated method stub
		String part1 = reservationBean.getUserID().substring(0, 1);
		String part2 = reservationBean.getRouteID().substring(0, 1);
		int num = (int) (Math.round(Math.random()*10000));
		String uniqueId = part1+part2+num;
		reservationBean.setReservationID(uniqueId);
		
		reservationBean.setBookingStatus("Pending");
		
		double fareperkm = vehicleDetails.findByID(reservationBean.getVehicleID()).getFarePerKM();
		int distance  = routeDetails.findByID(reservationBean.getRouteID()).getDistance();
		double fare = distance*fareperkm;
		reservationBean.setTotalFare(fare);
		try {
		String result = reservation.createReservation(reservationBean);
		if(result=="Success")
			return uniqueId;
			else
				return "Fail";
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return "ERROR";
		}
	}

	public boolean cancelBooking(String userID, String reservationID) {
		// TODO Auto-generated method stub
		try {
		return reservation.deleteReservation(userID, reservationID);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

	public ReservationBean viewBookingDetails(String reservationID) {
		// TODO Auto-generated method stub
		try {
		return reservation.viewBooking(reservationID);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	public ReservationBean printBookingDetails(String reservationID) {
		// TODO Auto-generated method stub
		try {
		return reservation.viewBooking(reservationID);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
		}
	}

	public String viewBookingStatus(String userID) {
		// TODO Auto-generated method stub
		try {
		return reservation.viewBookingStatus(userID);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return "ERROR";
		}
	}
	
	
	public boolean findByCardNumber(String userID, String cardNumber) {
		// TODO Auto-generated method stub
		try {
		return paymentd.findByCardNumber(userID, cardNumber);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

	
	public String processPayment(PaymentBean paymentBean) {
		// TODO Auto-generated method stub
		try {
		return paymentd.processPayment(paymentBean);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return "ERROR";
		}
	}

	
	

}

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

	public ArrayList<VehicleBean> viewVehiclesByType(String vehicleType) {
		// TODO Auto-generated method stub
		return vehicleDetails.findByVehicleType(vehicleType);
	}

	public ArrayList<VehicleBean> viewVehicleBySeats(int noOfSeats) {
		// TODO Auto-generated method stub
		return vehicleDetails.findBySeats(noOfSeats);
	}

	public ArrayList<RouteBean> viewAllRoutes() {
		// TODO Auto-generated method stub	
		return routeDetails.findAll();
	}

	public String bookVehicle(ReservationBean reservationBean) {
		// TODO Auto-generated method stub
		String part1 = reservationBean.getUserID().substring(0, 2);
		String part2 = reservationBean.getRouteID().substring(0, 2);
		int num = (int) (Math.round(Math.random()*10000));
		String uniqueId = part1+part2+num;
		reservationBean.setReservationID(uniqueId);
		reservationBean.setBookingDate(LocalDate.now());
		reservationBean.setBookingStatus("Booked");
		double fareperkm = vehicleDetails.findByID(reservationBean.getVehicleID()).getFarePerKM();
		int distance  = routeDetails.findByID(reservationBean.getRouteID()).getDistance();
		double fare = distance*fareperkm;
		reservationBean.setTotalFare(fare);
		String result = reservation.createReservation(reservationBean);
		if(result=="Success")
			return uniqueId;
			else
				return "Fail";
	}

	public boolean cancelBooking(String userID, String reservationID) {
		// TODO Auto-generated method stub
		return reservation.deleteReservation(userID, reservationID);
	}

	public ReservationBean viewBookingDetails(String reservationID) {
		// TODO Auto-generated method stub
		return reservation.viewBooking(reservationID);
	}

	public ReservationBean printBookingDetails(String reservationID) {
		// TODO Auto-generated method stub
		return reservation.viewBooking(reservationID);
	}

	public String viewBookingStatus(String userID) {
		// TODO Auto-generated method stub
		return reservation.viewBookingStatus(userID);
	}
	
	
	public boolean findByCardNumber(String userID, String cardNumber) {
		// TODO Auto-generated method stub
		return paymentd.findByCardNumber(userID, cardNumber);
	}

	
	public String processPayment(PaymentBean paymentBean) {
		// TODO Auto-generated method stub
		return paymentd.processPayment(paymentBean);
	}

	
	

}

package com.ntl.ata.service;

import java.util.ArrayList;

import com.ntl.ata.bean.PaymentBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;

public interface Customer {
	
	
	public ArrayList<VehicleBean> viewVehiclesByType(String vehicleType);
	public ArrayList<VehicleBean> viewVehicleBySeats(int noOfSeats);
	public ArrayList<RouteBean> viewAllRoutes();
	public String bookVehicle(ReservationBean reservationBean);
	public boolean cancelBooking(String userID, String reservationID);
	public ReservationBean viewBookingDetails(String reservationID);
	public ReservationBean printBookingDetails(String reservationID);
	public String viewBookingStatus(String userID);
	public boolean findByCardNumber(String userID, String cardNumber);
	public String processPayment(PaymentBean paymentBean);

}

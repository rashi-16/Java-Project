package com.ntl.ata.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ntl.ata.bean.ReservationBean;


public interface ReservationDao {

	public String createReservation(ReservationBean reservation);
	public boolean deleteReservation(String UserId, String reservationID);
	public ReservationBean viewBooking(String reservationID);
	public ArrayList<ReservationBean> bookingDetails(LocalDate date, String source, String destination);
	public String viewBookingStatus(String Userid);
	public boolean updateReservation(ReservationBean reservation);
}

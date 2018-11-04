package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.dao.ReservationDao;
import com.ntl.ata.util.DBUtil;

public class ReservationDaoImpl implements ReservationDao{
	
	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	ReservationBean reservationBean;
	
	static {
		connection=DBUtil.getDBConnection("mysql");
		}

	


	public String createReservation(ReservationBean reservation) {
		// TODO Auto-generated method stub
		try {
			pstmt =connection.prepareStatement("insert into ata_tbl_reservation values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, reservation.getReservationID());
			pstmt.setString(2, reservation.getUserID());
			pstmt.setString(3, reservation.getRouteID());
			pstmt.setDate(4, Date.valueOf(reservation.getBookingDate()));
			pstmt.setDate(5, Date.valueOf(reservation.getJourneyDate()));
			pstmt.setString(6, reservation.getVehicleID());
			pstmt.setString(7, reservation.getDriverID());
			pstmt.setString(8, reservation.getBookingStatus());
			pstmt.setDouble(9, reservation.getTotalFare());
			pstmt.setString(10, reservation.getBoardingPoint());
			pstmt.setString(11, reservation.getDropPoint());
			int z=pstmt.executeUpdate();
		     if(z!=0) {
		    	 
		    	 return "Success";
		     }
		     else {
		    	 
		    	 return "Fail";
		     }
			}
		catch (SQLException e1){
				System.out.println("Sql exception"+ e1);
				return "ERROR";
			}
	}

	public boolean deleteReservation(String UserId, String reservationID) {
		// TODO Auto-generated method stub
		try {
		pstmt = connection.prepareStatement("delete from ata_tbl_reservation where userid = ? and reservationid=?");
		pstmt.setString(1,UserId);
		pstmt.setString(2,reservationID);
		int z= pstmt.executeUpdate();
		if(z>0)
			return true;
		else return false;
		}catch(SQLException e)
		{
			System.out.println("sql error "+e);
			return false;
		}
		
	}


	public ArrayList<ReservationBean> bookingDetails(LocalDate date, String source, String destination) {
		// TODO Auto-generated method stub
		ArrayList<ReservationBean> bookingDetails = new ArrayList();
		try {
			pstmt= connection.prepareStatement("select * from ata_tbl_reservation where date =? and source=? and destination = ?");
			pstmt.setDate(1,Date.valueOf(date));
			pstmt.setString(2, source);
			pstmt.setString(3, destination);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String reservationId= rst.getString(1);
				String userid = rst.getString(2);
				String routeid = rst.getString(3);
				LocalDate bookingD = rst.getDate(4).toLocalDate();
				LocalDate journeyD =rst.getDate(5).toLocalDate();
				String vehiceid = rst.getString(6);
				String driverid=rst.getString(7);
				String bookingstatus = rst.getString(8);
				Double totalFare = rst.getDouble(9);
				String pickup = rst.getString(10);
				String drop=rst.getString(11);
				reservationBean = new ReservationBean(reservationId,userid,routeid,bookingD,journeyD,vehiceid,driverid,bookingstatus,totalFare,pickup, drop);
				bookingDetails.add(reservationBean);
				
			}
		}catch(SQLException e) {
			System.out.println("sql error "+e);
		}
		return bookingDetails;
	}

	public ReservationBean viewBooking(String reservationID) {
		// TODO Auto-generated method stub
		try {
			pstmt= connection.prepareStatement("select * from ata_tbl_reservation where reservationid=?");
			pstmt.setString(1,reservationID);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String reservationId= rst.getString(1);
				String userid = rst.getString(2);
				String routeid = rst.getString(3);
				LocalDate bookingD = rst.getDate(4).toLocalDate();
				LocalDate journeyD =rst.getDate(5).toLocalDate();
				String vehiceid = rst.getString(6);
				String driverid=rst.getString(7);
				String bookingstatus = rst.getString(8);
				Double totalFare = rst.getDouble(9);
				String pickup = rst.getString(10);
				String drop=rst.getString(11);
				reservationBean = new ReservationBean(reservationId,userid,routeid,bookingD,journeyD,vehiceid,driverid,bookingstatus,totalFare,pickup, drop);
				
			}
		}catch(SQLException e) {
			System.out.println("sql error "+e);
		}
		return reservationBean;
	}

	public String viewBookingStatus(String Userid) {
		// TODO Auto-generated method stub
		String bookingstatus=null;
		try {
			pstmt= connection.prepareStatement("select * from ata_tbl_reservation where userid=?");
			pstmt.setString(1,Userid);
			rst = pstmt.executeQuery();
			while(rst.next()) {
			 bookingstatus = rst.getString(8);
			}}catch(SQLException e) {
				System.out.println("sql error "+e);}
			if(bookingstatus.equals("Booked"))
					return "Booking Successful";
			else
				return "No vehicle booked";
	}

}

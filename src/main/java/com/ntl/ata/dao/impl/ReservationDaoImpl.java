package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.dao.ReservationDao;
import com.ntl.ata.util.DBUtil;

public class ReservationDaoImpl implements ReservationDao{
	
	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	ReservationBean reservationBean;
	DataSource dataSource;
	

	public ReservationDaoImpl(DataSource dataSource) {
	this.dataSource=dataSource;
	try {
		this.connection=dataSource.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	/**
	 * 
	 */
	public ReservationDaoImpl() {
		super();
		connection=DBUtil.getDBConnection("mysql");
		// TODO Auto-generated constructor stub
	}

	public String createReservation(ReservationBean reservation) {
		// TODO Auto-generated method stub
		try {
			pstmt =connection.prepareStatement("insert into ata_tbl_reservation values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, reservation.getReservationID());
			pstmt.setString(2, reservation.getUserID());
			pstmt.setString(3, reservation.getVehicleID());
			pstmt.setString(4, reservation.getRouteID());
			pstmt.setDate(5, Date.valueOf(reservation.getBookingDate()));
			pstmt.setDate(6, Date.valueOf(reservation.getJourneyDate()));
			
			pstmt.setString(7, reservation.getDriverID());
			pstmt.setString(8, reservation.getBookingStatus());
			pstmt.setDouble(9, reservation.getTotalFare());
			pstmt.setString(10, reservation.getBoardingPoint());
			pstmt.setString(11, reservation.getDropPoint());
			int z=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
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
			}catch(Exception e) {
				System.out.println("Exception occurred"+e);
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
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
		
	}


	public ArrayList<ReservationBean> bookingDetails(LocalDate date, String source, String destination) {
		// TODO Auto-generated method stub
		ArrayList<ReservationBean> bookingDetails = new ArrayList();
		try {
			pstmt= connection.prepareStatement("select * from ata_tbl_reservation where JourneyDate =? and BoardingPoint=? and DropPoint = ?");
			pstmt.setDate(1,Date.valueOf(date));
			pstmt.setString(2, source);
			pstmt.setString(3, destination);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String reservationId= rst.getString(1);
				String userid = rst.getString(2);
				String routeid = rst.getString(3);
				String vehiceid = rst.getString(4);
				LocalDate bookingD = rst.getDate(5).toLocalDate();
				LocalDate journeyD =rst.getDate(6).toLocalDate();
				
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
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
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
				String vehiceid = rst.getString(3);
				String routeid = rst.getString(4);
				
				
				LocalDate bookingD = rst.getDate(5).toLocalDate();
				LocalDate journeyD =rst.getDate(6).toLocalDate();
				String driverid =rst.getString(7);
				String bookingstatus = rst.getString(8);
				Double totalFare = rst.getDouble(9);
				String pickup = rst.getString(10);
				String drop=rst.getString(11);
				reservationBean = new ReservationBean(reservationId,userid,routeid,bookingD,journeyD,vehiceid,driverid,bookingstatus,totalFare,pickup, drop);
				
			}
			
		}catch(SQLException e) {
			System.out.println("sql error "+e);
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return null;
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
			}
			}catch(SQLException e) {
				System.out.println("sql error "+e);}
		catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return "ERROR";
		}
			if(bookingstatus.equals("Confirmed"))
					return "Booked Successfully";
			else
				return "Pending";
	}

	public boolean updateReservation(ReservationBean reservation) {
		// TODO Auto-generated method stub
		try {
			pstmt = connection.prepareStatement("update ata_tbl_reservation set driverid=?, bookingstatus=? where reservationid=?");
			pstmt.setString(1,reservation.getDriverID());
			pstmt.setString(2, reservation.getBookingStatus());
			pstmt.setString(3, reservation.getReservationID());
			int z=pstmt.executeUpdate();
			
			if(z>0)
				return true;
			else 
				return false;
		}catch(SQLException e)
		{
			System.out.println("Sql exception"+ e);
			return false;
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
	}

}

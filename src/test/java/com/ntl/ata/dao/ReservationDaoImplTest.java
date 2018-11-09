package com.ntl.ata.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.dao.impl.ReservationDaoImpl;

public class ReservationDaoImplTest {
	
	
	static  DateTimeFormatter formatter= DateTimeFormatter.ISO_DATE;
	
	
	 DataSource mockDataSource=mock(DataSource.class);

	    Connection mockConn=mock(Connection.class);
		
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    
	    Statement mockCreateStmt=mock(Statement.class);
	    
	    ResultSet mockResultSet=mock(ResultSet.class);
	    ReservationBean rBean = new ReservationBean();
	    LocalDate jdate=LocalDate.parse("12/12/2018", formatter);
	    LocalDate bdate =LocalDate.parse("12/12/2018", formatter);
	    
	    ReservationBean rBean2 = new ReservationBean("ra9885","ra2725","abc",jdate,bdate,"ft2131","fde","Confirmed",144.67,"sd","fd");
	    

	  
	
	
	
	
	@Before
	public void setUp() throws Exception {
		
		when(mockDataSource.getConnection()).thenReturn(mockConn);
   	 when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
      when(mockPreparedStmnt.executeQuery(anyString())).thenReturn(mockResultSet);
      when(mockPreparedStmnt.executeUpdate(anyString())).thenReturn(1);
      doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
      doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
      when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
      when(mockResultSet.first()).thenReturn(true);
      when(mockResultSet.getString(1)).thenReturn("ra9885");
      when(mockResultSet.getString(2)).thenReturn("ra2725");
      when(mockResultSet.getString(3)).thenReturn("abc");
      when(mockResultSet.getString(5)).thenReturn("ft2131");
      when(mockResultSet.getDate(6)).thenReturn(Date.valueOf(jdate));
      when(mockResultSet.getDate(7)).thenReturn(Date.valueOf(bdate));
      when(mockResultSet.getString(8)).thenReturn("110006");
      when(mockResultSet.getString(9)).thenReturn("98546345678");
      when(mockResultSet.getString(10)).thenReturn("123456dygc");
      when(mockResultSet.getInt(11)).thenReturn(0);
	}
		

	@Test
	public void testCreateReservation() {
		 ReservationDaoImpl reservation = new ReservationDaoImpl();
		 assertEquals("ERROR", reservation.createReservation(rBean));
		
	}

	@Test
	public void testCreateReservationV() {
		 ReservationDaoImpl reservation = new ReservationDaoImpl(mockDataSource);
		 assertEquals("Success", reservation.createReservation(rBean2));
		
	}
	@Test
	public void testDeleteReservation() {
		ReservationDaoImpl reservation = new ReservationDaoImpl();
		assertEquals(false, reservation.deleteReservation("2536", "5363"));
	}

	@Test
	public void testBookingDetails() {
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dateJ="12/12/2018";
		LocalDate dateJourney = LocalDate.parse(dateJ, formatter);
		ReservationDaoImpl reservation = new ReservationDaoImpl();
		
		ArrayList<ReservationBean> rList2=reservation.bookingDetails(dateJourney, "sd", "fd");
		assertEquals( "ra9885",rList2.get(0).getReservationID());
				
		
	}

	@Test
	public void testViewBooking() {
		ReservationDaoImpl reservation = new ReservationDaoImpl();
		rBean =reservation.viewBooking("ra9885");
		assertEquals("abc",rBean.getRouteID());
	}

	@Test
	public void testViewBookingStatus() {
		ReservationDaoImpl reservation = new ReservationDaoImpl();
		assertEquals("Booked Successfully",reservation.viewBookingStatus("ra9885") );
		
	}

	@Test
	public void testUpdateReservation() {
		ReservationDaoImpl reservation = new ReservationDaoImpl();
		assertEquals(true,reservation.updateReservation(rBean2) );
	}

}

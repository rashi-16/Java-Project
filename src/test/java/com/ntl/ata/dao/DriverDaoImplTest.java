package com.ntl.ata.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;

import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.dao.impl.DriverDaoImpl;

public class DriverDaoImplTest {

	
	static  DateTimeFormatter formatter= DateTimeFormatter.ISO_DATE;
	
	
	 DataSource mockDataSource=mock(DataSource.class);

	    Connection mockConn=mock(Connection.class);
		
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    
	    Statement mockCreateStmt=mock(Statement.class);
	    
	    ResultSet mockResultSet=mock(ResultSet.class);

	    DriverBean driver = new DriverBean("Is1234","Ishaa","Hush","north","delhi","newDelhi","110006","98546345678","123456dygc",0);
	
	
	
	
	@Before
	public void setUp() throws Exception {
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dateB="15/03/1997";
		LocalDate dob = LocalDate.parse(dateB, formatter);
		when(mockDataSource.getConnection()).thenReturn(mockConn);
    	 when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
       when(mockPreparedStmnt.executeQuery(anyString())).thenReturn(mockResultSet);
       when(mockPreparedStmnt.executeUpdate(anyString())).thenReturn(1);
       doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
       doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
       when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
       when(mockResultSet.first()).thenReturn(true);
       when(mockResultSet.getString(1)).thenReturn("Is1234");
       when(mockResultSet.getString(2)).thenReturn("Ishaa");
       when(mockResultSet.getString(3)).thenReturn("Hush");
       when(mockResultSet.getString(5)).thenReturn("north");
       when(mockResultSet.getString(6)).thenReturn("delhi");
       when(mockResultSet.getString(7)).thenReturn("newDelhi");
       when(mockResultSet.getString(8)).thenReturn("110006");
       when(mockResultSet.getString(9)).thenReturn("98546345678");
       when(mockResultSet.getString(10)).thenReturn("123456dygc");
       when(mockResultSet.getInt(11)).thenReturn(0);
		
		
		
	}

	

	@Test
	public void testCreateDriver() {
		DriverDaoImpl driverDao = new DriverDaoImpl(mockDataSource);
		assertEquals("Success",driverDao.createDriver(driver));
	}

	@Test
	public void testDeleteDriver() {
		DriverDaoImpl driverDao = new DriverDaoImpl(mockDataSource);
		ArrayList<String> driverIdList = new ArrayList();
		driverIdList.add(driver.getDriverID());
		assertEquals(1,driverDao.deleteDriver(driverIdList));
	}

	@Test
	public void testUpdateDriver() {
		DriverDaoImpl driverDao = new DriverDaoImpl(mockDataSource);
		assertEquals(true, driverDao.updateDriver(driver));
	}

	@Test
	public void testFindByID() {
		DriverDaoImpl driverDao = new DriverDaoImpl(mockDataSource);
		assertEquals("123456dygc", driverDao.findByID("Is1234").getLicenseNumber());
	}

	@Test
	public void testFindByDriverStatus() {
		
	}

}

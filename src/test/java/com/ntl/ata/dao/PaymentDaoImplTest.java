package com.ntl.ata.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.ntl.ata.bean.PaymentBean;
import com.ntl.ata.dao.impl.PaymentDaoImpl;

public class PaymentDaoImplTest {
	
	DataSource mockDataSource=mock(DataSource.class);

    Connection mockConn=mock(Connection.class);
	
    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
    
    Statement mockCreateStmt=mock(Statement.class);
    
    ResultSet mockResultSet=mock(ResultSet.class);
    PaymentBean payment = new PaymentBean("2436727","2/11","6/35",46373);

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
      when(mockResultSet.getString(1)).thenReturn("2436727");
       when(mockResultSet.getString(2)).thenReturn("2/11");
       when(mockResultSet.getString(3)).thenReturn("6/35");
       when(mockResultSet.getInt(4)).thenReturn(46373);
       when(mockResultSet.getString(5)).thenReturn("rashi123");
       
	}

	@Test
	public void testFindByCardNumber() {
		PaymentDaoImpl pDao = new PaymentDaoImpl();
		boolean result =pDao.findByCardNumber("rashi123","2436727" );
		assertEquals(true, result);
		
	}

	@Test
	public void testProcessPayment() {
		PaymentDaoImpl pDao = new PaymentDaoImpl(mockDataSource);
		assertEquals("Success", pDao.processPayment(payment));
	}

}

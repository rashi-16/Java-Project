package com.ntl.ata.dao;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;


import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.util.DBUtil;


public class CredentialsDaoImplTest {

	
    DataSource mockDataSource=mock(DataSource.class);

    Connection mockConn=mock(Connection.class);
	
    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
    
    Statement mockCreateStmt=mock(Statement.class);
    
    ResultSet mockResultSet=mock(ResultSet.class);
    CredentialsBean credentials=new CredentialsBean("rashi5","rashi@16","A",0);
    
    @Before
    public void setUp() throws SQLException {
    	
    	when(mockDataSource.getConnection()).thenReturn(mockConn);
    	
         when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
        when(mockPreparedStmnt.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockPreparedStmnt.executeUpdate(anyString())).thenReturn(1);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mockResultSet.first()).thenReturn(true);
       when(mockResultSet.getString(1)).thenReturn("rashi5");
        when(mockResultSet.getString(2)).thenReturn("rashi@16");
        when(mockResultSet.getString(3)).thenReturn("A");
        when(mockResultSet.getInt(4)).thenReturn(0);
        
    }
	@Test
	public void testFindByID() {
		
		CredentialsDaoImpl c=new CredentialsDaoImpl(mockDataSource);
		//System.out.println(credentials.getUserID());
		CredentialsBean cb =c.findByID("rashi5");
    	assertEquals("A" ,cb.getUserType());
	}
	
	@Test
	public void testCreateCredentials() {
		CredentialsDaoImpl c=new CredentialsDaoImpl();
		String result = c.createCredentials(credentials);
		assertEquals("Success",result );
	}

	@Test
	public void testUpdateCredentials() {
		CredentialsDaoImpl c=new CredentialsDaoImpl();
		boolean result =c.updateCredentials(credentials);
		assertEquals(true,result );
	}

	
}

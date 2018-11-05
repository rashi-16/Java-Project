package com.ntl.ata.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.util.DBUtil;

public class CredentialsDaoImplTest {

	
    DBUtil mockDataSource=mock(DBUtil.class);
   
    Connection mockConn=mock(Connection.class);
    
    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
    
    Statement mockCreateStmt=mock(Statement.class);
    
    ResultSet mockResultSet=mock(ResultSet.class);
    @Before
    public void setUp() throws SQLException {
        
        when(mockConn.createStatement()).thenReturn(mockCreateStmt);
       
        when(mockCreateStmt.executeQuery(anyString())).thenReturn(mockResultSet);
       
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        
    }
	@Test
	public void testFindByID() {
		CredentialsDaoImpl c=new CredentialsDaoImpl();
    	CredentialsBean cr=new CredentialsBean("ra1232","rashi@16","A",0);
    	CredentialsBean cb =c.findByID("ra1232");
    	assertEquals(cr, cb);
	}

	
}

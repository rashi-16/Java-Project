package com.ntl.ata.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.util.impl.AuthenticationImpl;

public class AuthenticationTest {

	 CredentialsBean cred1 =new CredentialsBean("12Rashi16","Rashi@16","A",0);
	 CredentialsBean cred2 =new CredentialsBean("Ra2345","rashi@123","C",0);

	
	CredentialsDaoImpl creddao=mock(CredentialsDaoImpl.class);
	AuthenticationImpl auth = new AuthenticationImpl(creddao);
	

	@Test
	public final void testAuthenticate() {
	
		when(creddao.findByID("12Rashi16")).thenReturn(cred1);
		
		AuthenticationImpl auth = new AuthenticationImpl(creddao);
		boolean result = auth.authenticate(cred1);
		assertEquals(true,result);
		}
	
	
	
	

	@Test
	public final void testAuthorize() {
	
		
		when(creddao.findByID("12Rashi16")).thenReturn(cred1);
		when(creddao.findByID("Ra2345")).thenReturn(cred2);
		when(creddao.findByID("Ra2367")).thenReturn(cred2);
		String result = auth.authorize("12Rashi16");
		 assertEquals("A",result);
		 result = auth.authorize("Ra2345");
		 assertEquals("C",result);
		 result = auth.authorize("Ra2367");
		 assertEquals("invalid",result);
	}
	
	
	
	

	@Test
	public final void testChangeLoginStatus() {
		
		//CredentialsDaoImpl creddao=mock(CredentialsDaoImpl.class);
		when(creddao.findByID("12Rashi16")).thenReturn(cred1);
		creddao.updateCredentials(cred1);
		boolean res = auth.changeLoginStatus(cred1, 1);
		assertEquals(true,res);
		 res = auth.changeLoginStatus(cred1, 0);
		assertEquals(true,res);
			}

}

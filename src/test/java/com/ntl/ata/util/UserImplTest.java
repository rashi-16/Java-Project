package com.ntl.ata.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.ProfileBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.dao.impl.ProfileDaoImpl;
import com.ntl.ata.util.impl.AuthenticationImpl;
import com.ntl.ata.util.impl.UserImpl;

import static org.mockito.Mockito.*;

import java.time.LocalDate;


public class UserImplTest {
	CredentialsBean credb =new CredentialsBean("rayad","rashi@16","A",0);
	CredentialsBean credb2 =new CredentialsBean("rayad1","rashi@16","A",1);
	CredentialsBean credb3 =new CredentialsBean("rayad3","rashi@16","C",0);
	ProfileBean profile = new ProfileBean(" ","Rohan", "Mehra",LocalDate.parse("1997-03-12"), "male", "blue","north","rewari", "haryana","123401","9774825237","rohan@gmail.com","rohan123");
	
	
	
	@Test
	public final void testLoginA() {
		

		//CredentialsDaoImpl credentialsDao=mock(CredentialsDaoImpl.class);
		
		AuthenticationImpl auth=mock(AuthenticationImpl.class);
        //mocking service method1 
		when(auth.authenticate(credb)).thenReturn(true);
		
		//mocking service method2
		when(auth.authorize("rayad")).thenReturn("A");
		when(auth.changeLoginStatus(credb, 1)).thenReturn(true);
		
		
		UserImpl user = new UserImpl(auth);
		//testing User.login method
		String result = user.login(credb);
		assertEquals("A", result);
		
		
	}
	@Test
	public final void testLoginC() {
		

		//CredentialsDaoImpl credentialsDao=mock(CredentialsDaoImpl.class);
		
		AuthenticationImpl auth=mock(AuthenticationImpl.class);
        //mocking service method1 
		when(auth.authenticate(credb3)).thenReturn(true);
		
		//mocking service method2
		when(auth.authorize("rayad3")).thenReturn("C");
		when(auth.changeLoginStatus(credb, 1)).thenReturn(true);
		
		
		UserImpl user = new UserImpl(auth);
		//testing User.login method
		String result = user.login(credb3);
		assertEquals("C", result);
		
		
	}
		
	
		


	@Test
	public final void testLogout() {
		CredentialsDaoImpl credentialsDao=mock(CredentialsDaoImpl.class);
		when(credentialsDao.findByID("rayad1")).thenReturn(credb2);
		AuthenticationImpl auth=mock(AuthenticationImpl.class);
		when(auth.changeLoginStatus(credb2,0)).thenReturn(true);
		UserImpl user = new UserImpl(credentialsDao);
		 user = new UserImpl(auth);
		boolean res= user.logout("rayad1");
		assertEquals(true,res);
		
		
	}

	@Test
	public final void testChangePassword() {
		CredentialsDaoImpl credentialsDao=mock(CredentialsDaoImpl.class);
		UserImpl user =new UserImpl(credentialsDao);
		when(credentialsDao.findByID("rayad")).thenReturn(credb);
		String res = user.changePassword(credb, "rashi@16");
		assertEquals("Invalid", res);
		when(credentialsDao.updateCredentials(credb)).thenReturn(true);
		res = user.changePassword(credb, "dudc");
		assertEquals("Success", res);
		when(credentialsDao.findByID("rayad")).thenReturn(credb);
		when(credentialsDao.updateCredentials(credb)).thenReturn(false);
		res = user.changePassword(credb, "rhftfh");
		assertEquals("Fail", res);
		
		
			}

	
	

}

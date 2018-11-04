package com.ntl.ata.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.impl.CredentialsDaoImpl;
import com.ntl.ata.util.impl.AuthenticationImpl;
import com.ntl.ata.util.impl.UserImpl;

import static org.mockito.Mockito.*;


public class UserImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testUserImpl() {
		
	}

	@Test
	public final void testLogin() {
		

		CredentialsDaoImpl credentialsDao=mock(CredentialsDaoImpl.class);
		
		AuthenticationImpl auth=mock(AuthenticationImpl.class);
        //mocking service method1 
		when(auth.authenticate(new CredentialsBean("rayad","rashi@16","A",0))).thenReturn(true);
		
		//mocking service method2
		when(auth.authorize("rayad")).thenReturn("A");
		when(auth.changeLoginStatus(new CredentialsBean("rayad","rashi@16","A",0), 1)).thenReturn(true);
		
		
		UserImpl user = new UserImpl(auth);
		//testing User.login method
		String result = user.login(new CredentialsBean("rayad","rashi@16","A",0));
		assertEquals("A", result);
		
		
	}
		


	@Test
	public final void testLogout() {
		
	}

	@Test
	public final void testChangePassword() {
			}

	@Test
	public final void testRegister() {
		
	}

}

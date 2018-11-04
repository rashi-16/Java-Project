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
	public final void testAuthenticate() {
		CredentialsBean cred  =new CredentialsBean("12Rashi16","Rashi@16","A",0);
		CredentialsBean mockCb = mock(CredentialsBean.class);
		CredentialsDaoImpl creddao=mock(CredentialsDaoImpl.class);
		creddao = spy(CredentialsDaoImpl.class);
		when(creddao.findByID("12Rashi16")).thenReturn(cred);
		AuthenticationImpl auth = new AuthenticationImpl(creddao);
		when(auth.authenticate(cred)).thenReturn(true);
		
	}

	@Test
	public final void testAuthorize() {
		
	}

	@Test
	public final void testChangeLoginStatus() {
		
	}

}

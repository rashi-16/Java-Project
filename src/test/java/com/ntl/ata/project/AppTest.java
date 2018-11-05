package com.ntl.ata.project;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ntl.ata.dao.CredentialsDaoImplTest;
import com.ntl.ata.dao.DriverDao‫ImplTest;
import com.ntl.ata.service.AdministratorTest;
import com.ntl.ata.service.CustomerTest;
import com.ntl.ata.util.AuthenticationTest;
import com.ntl.ata.util.UserImplTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	CredentialsDaoImplTest.class,
	DriverDao‫ImplTest.class,
	AdministratorTest.class,
	CustomerTest.class,
	AuthenticationTest.class,
	UserImplTest.class
})
public class AppTest 
extends TestCase{

	public AppTest( String testName )
    {
        super( testName );
    }
	public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

}

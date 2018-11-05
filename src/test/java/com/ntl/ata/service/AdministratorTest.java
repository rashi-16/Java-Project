package com.ntl.ata.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;
import com.ntl.ata.dao.impl.DriverDaoImpl;
import com.ntl.ata.dao.impl.ReservationDaoImpl;
import com.ntl.ata.dao.impl.RouteDaoImpl;
import com.ntl.ata.dao.impl.VehicleDaoImpl;
import com.ntl.ata.service.impl.AdministratorImpl;

public class AdministratorTest {
	VehicleBean vehicle = new VehicleBean("vehicleid", "name", "type", "registrationnum", 4, 11.78);
	DriverBean driver = new DriverBean("driverid", "name", "street", "location", "city", "state", "pincode", "mobileno", "license",0);
	RouteBean route = new RouteBean("routeid", "source", "destination", 45, 345);
	ReservationBean reservation = new ReservationBean("reservationId","userid","routeid",LocalDate.parse("2018-11-12"),LocalDate.parse("2018-11-23"),"vehiceid","driverid","bookingstatus",1000.23,"pickup", "drop");
			
	VehicleDaoImpl vehicleDao =mock(VehicleDaoImpl.class);
	DriverDaoImpl driverDao = mock(DriverDaoImpl.class);
	RouteDaoImpl routeDao = mock(RouteDaoImpl.class);
	ReservationDaoImpl reservationDao =mock(ReservationDaoImpl.class);

	@Test
	public final void testAddVehicle() {
		AdministratorImpl admin = new AdministratorImpl(vehicleDao);
		when(vehicleDao.createVehicle(vehicle)).thenReturn("Success");
		String result = admin.addVehicle(vehicle);
		assertEquals("Success", result);
		
	}

	@Test
	public final void testDeleteVehicle() {
		ArrayList<String> vIds = new ArrayList();
		vIds.add("vehicleid");
		AdministratorImpl admin = new AdministratorImpl(vehicleDao);
		when(vehicleDao.deleteVehicle(vIds)).thenReturn(1);
		int r = admin.deleteVehicle(vIds);
		assertEquals(1,r);
		
	}

	@Test
	public final void testViewVehicle() {
		AdministratorImpl admin = new AdministratorImpl(vehicleDao);
		when(vehicleDao.findByID("vehicleid")).thenReturn(vehicle);
		VehicleBean b =admin.viewVehicle("vehicleid");
		assertEquals(vehicle, b);
		
	}

	@Test
	public final void testModifyVehicle() {
		AdministratorImpl admin = new AdministratorImpl(vehicleDao);
		when(vehicleDao.updateVehicle(vehicle)).thenReturn(true);
		boolean res = admin.modifyVehicle(vehicle);
		assertEquals(true,res);
		
		
	}

	@Test
	public final void testAddDriver() {
		
		AdministratorImpl admin = new AdministratorImpl(driverDao);
		when(driverDao.createDriver(driver)).thenReturn("Success");
		String result = admin.addDriver(driver);
		assertEquals("Success", result);
	}

	@Test
	public final void testDeleteDriver() {
		ArrayList<String> dIds = new ArrayList();
		dIds.add("driverid");
		AdministratorImpl admin = new AdministratorImpl(driverDao);
		when(driverDao.deleteDriver(dIds)).thenReturn(1);
		int r = admin.deleteDriver(dIds);
		assertEquals(1,r);
	}

	

	@Test
	public final void testModifyDriver() {
		AdministratorImpl admin = new AdministratorImpl(driverDao);
		when(driverDao.updateDriver(driver)).thenReturn(true);
		boolean res = admin.modifyDriver(driver);
		assertEquals(true,res);
	}

	@Test
	public final void testAddRoute() {
		AdministratorImpl admin = new AdministratorImpl(routeDao);
		when(routeDao.createRoute(route)).thenReturn("Success");
		String result = admin.addRoute(route);
		assertEquals("Success", result);
		
	}
	@Test
	public final void testDeleteRoute() {
		ArrayList<String> rIds = new ArrayList();
		rIds.add("routeid");
		AdministratorImpl admin = new AdministratorImpl(routeDao);
		when(routeDao.deleteRoute(rIds)).thenReturn(1);
		int r = admin.deleteRoute(rIds);
		assertEquals(1,r);
	}

	@Test
	public final void testViewRoute() {
		AdministratorImpl admin = new AdministratorImpl(routeDao);
		when(routeDao.findByID("routeid")).thenReturn(route);
		RouteBean b =admin.viewRoute("routeid");
		assertEquals(route, b);
		
	}

	@Test
	public final void testModifyRoute() {
		AdministratorImpl admin = new AdministratorImpl(routeDao);
		when(routeDao.updateRoute(route)).thenReturn(true);
		boolean res = admin.modifyRoute(route);
		assertEquals(true,res);
	}

	
}

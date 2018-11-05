package com.ntl.ata.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;
import com.ntl.ata.dao.impl.ReservationDaoImpl;
import com.ntl.ata.dao.impl.RouteDaoImpl;
import com.ntl.ata.dao.impl.VehicleDaoImpl;
import com.ntl.ata.service.impl.CustomerImpl;

public class CustomerTest {

	VehicleBean vehicle = new VehicleBean("vehicleid", "name", "type", "registrationnum", 4, 11.78);
	RouteBean route = new RouteBean("routeid", "source", "destination", 45, 345);
	VehicleDaoImpl vehicleDao =mock(VehicleDaoImpl.class);
	RouteDaoImpl routeDao = mock(RouteDaoImpl.class);
	ReservationDaoImpl reservationDao =mock(ReservationDaoImpl.class);

	@Test
	public final void testViewVehiclesByType() {
		CustomerImpl cust = new CustomerImpl(vehicleDao);
		ArrayList<VehicleBean> vlist = new ArrayList();
		vlist.add(vehicle);
		when(vehicleDao.findByVehicleType("type")).thenReturn(vlist);
		ArrayList<VehicleBean> v = cust.viewVehiclesByType("type");
		assertEquals(vlist,v);
		
	}

	@Test
	public final void testViewVehicleBySeats() {
		CustomerImpl cust = new CustomerImpl(vehicleDao);
		ArrayList<VehicleBean> vlist = new ArrayList();
		vlist.add(vehicle);
		when(vehicleDao.findBySeats(4)).thenReturn(vlist);
		ArrayList<VehicleBean> v = cust.viewVehicleBySeats(4);
		assertEquals(vlist,v);
	}

	@Test
	public final void testViewAllRoutes() {
		CustomerImpl cust = new CustomerImpl(routeDao);
		ArrayList<RouteBean> rlist = new ArrayList();
		rlist.add(route);
		when(routeDao.findAll()).thenReturn(rlist);
		ArrayList<RouteBean> r = cust.viewAllRoutes();
		assertEquals(rlist,r);
	}

	
}

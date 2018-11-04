package com.ntl.ata.dao;

import java.util.ArrayList;

import com.ntl.ata.bean.RouteBean;

public interface RouteDao {

	
	public String createRoute(RouteBean routeBean);
	public int deleteRoute(ArrayList<String> routeId );
	public boolean updateRoute(RouteBean routeBean);
	public RouteBean findByID(String routeId);
	public ArrayList<RouteBean> findAll();

}

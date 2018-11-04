package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.dao.RouteDao;
import com.ntl.ata.util.DBUtil;

public class RouteDaoImpl implements RouteDao {
	
	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	
	RouteBean route;
	
	static {
		connection=DBUtil.getDBConnection("mysql");
		}
	
	
	
	public String createRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		try {
			pstmt =connection.prepareStatement("insert into ata_tbl_route values(?,?,?,?,?)");
			pstmt.setString(1, routeBean.getRouteID());
			pstmt.setString(2, routeBean.getSource());
			pstmt.setString(3, routeBean.getDestination());
			pstmt.setInt(4, routeBean.getDistance());
			pstmt.setInt(5, routeBean.getTravelDuration());
			int z=pstmt.executeUpdate();
		     if(z!=0) {
		    	 
		    	 return "Success";
		     }
		     else {
		    	 
		    	 return "Fail";
		     }
			}
		catch (SQLException e1){
				System.out.println("Sql exception"+ e1);
				return "ERROR";
			}
	}
	
	
	

	public int deleteRoute(ArrayList<String> routeId) {
		// TODO Auto-generated method stub
		int z=0;
		try {
			for(String x:routeId) {
		pstmt =connection.prepareStatement("delete from ata_tbl_route where routeid = ?");
		pstmt.setString(1,x);
		 z= z+ pstmt.executeUpdate();
			}
		return z;
		}catch(SQLException e)
		{
			System.out.println("Sql exception"+ e);
			return -1;
		}
	}

	
	
	
	public boolean updateRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		try {
			pstmt = connection.prepareStatement("update ata_tbl_route set source=?, destination=?, distance=?, travelduration=? where routeid=?");
			pstmt.setString(1,routeBean.getSource());
			pstmt.setString(2,routeBean.getDestination());
			pstmt.setInt(3, routeBean.getDistance());
			pstmt.setInt(4,routeBean.getTravelDuration());
			pstmt.setString(5,routeBean.getRouteID());
			int z=pstmt.executeUpdate();
			if(z>0)
				return true;
			else 
				return false;
		}catch(SQLException e)
		{
			System.out.println("Sql exception"+ e);
			return false;
		}
	}

	
	
	
	public RouteBean findByID(String routeId) {
		// TODO Auto-generated method stub
		
		try{
			pstmt=connection.prepareStatement("select * from ata_tbl_route where routeid=?");
			pstmt.setString(1, routeId);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String routeid=rst.getString(1);
				String source=rst.getString(2);
				String destination=rst.getString(3);
				int distance=rst.getInt(4);
				int travelDuration=rst.getInt(5);
				route= new RouteBean(routeid, source, destination, distance, travelDuration);
				
			}}
		catch(SQLException e) {
			System.out.println("Sql exception "+ e);
		}
	return route;
	}

	
	
	
	public ArrayList<RouteBean> findAll() {
		// TODO Auto-generated method stub
		ArrayList<RouteBean> routeList = new ArrayList();
		try {
			pstmt=connection.prepareStatement("select * from ata_tbl_route");
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String routeid=rst.getString(1);
				String source=rst.getString(2);
				String destination=rst.getString(3);
				int distance=rst.getInt(4);
				int travelDuration=rst.getInt(5);
				route= new RouteBean(routeid, source, destination, distance, travelDuration);
				routeList.add(route);
			}
		}catch(SQLException e)
		{
			System.out.println("sqlException " +e);
		}
		return routeList;
	}

}

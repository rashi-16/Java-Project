package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.VehicleBean;
import com.ntl.ata.dao.VehicleDao;

public class VehicleDaoImpl implements VehicleDao {
	
	Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	
	VehicleBean vehicleDetails;

	public String createVehicle(VehicleBean vehicleBean) {
		// TODO Auto-generated method stub
		try {
			pstmt =connection.prepareStatement("insert into ata_tbl_route values(?,?,?,?,?,?)");
			pstmt.setString(1, vehicleBean.getVehicleID());
			pstmt.setString(2, vehicleBean.getName());
			pstmt.setString(3, vehicleBean.getType());
			pstmt.setString(4, vehicleBean.getRegistrationNumber());
			pstmt.setInt(5, vehicleBean.getSeatingCapacity());
			pstmt.setDouble(6, vehicleBean.getFarePerKM());
			
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

	
	
	
	public int deleteVehicle(ArrayList<String> vehicleId) {
		// TODO Auto-generated method stub
		int z=0;
		try {
			for(String x:vehicleId) {
		pstmt =connection.prepareStatement("delete from ata_tbl_Vehicle where vehicleid = ?");
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
	
	
	
	

	public boolean updateVehicle(VehicleBean vehicleBean) {
		// TODO Auto-generated method stub
		try {
			pstmt = connection.prepareStatement("update ata_tbl_vehicle set name=?, type=?, registrationnumber=?, seatingcapacity=?, fareperkm=? where vehicleid=?");
			pstmt.setString(1,vehicleBean.getName());
			pstmt.setString(2, vehicleBean.getType());
			pstmt.setString(3, vehicleBean.getRegistrationNumber());
			pstmt.setInt(4, vehicleBean.getSeatingCapacity());
			pstmt.setDouble(5, vehicleBean.getFarePerKM());
			pstmt.setString(6, vehicleBean.getVehicleID());
			
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

	
	
	
	
	public VehicleBean findByID(String vehicleId) {
		// TODO Auto-generated method stub
		try{
			pstmt=connection.prepareStatement("select * from ata_tbl_vehicle where vehicleid=?");
			pstmt.setString(1, vehicleId);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String vehicleid=rst.getString(1);
				String name=rst.getString(2);
				String type=rst.getString(3);
				String registrationnum=rst.getString(4);
				int seatingcapacity=rst.getInt(5);
				double fareperkm=rst.getDouble(6);				
				vehicleDetails= new VehicleBean(vehicleid,name,type,registrationnum,seatingcapacity,fareperkm);
			}}
		catch(SQLException e) {
			System.out.println("Sql exception "+ e);
		}
	return vehicleDetails;

	}
	
	
	
	
	public ArrayList<VehicleBean> findByVehicleType(String vehicleType) {
		// TODO Auto-generated method stub
		ArrayList<VehicleBean> vehicleList = new ArrayList<VehicleBean>();
		
		try {
			pstmt= connection.prepareStatement("Select * from ata_tbl_vehicle where type=?");
			pstmt.setString(1, vehicleType);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String vehicleid=rst.getString(1);
				String name=rst.getString(2);
				String type=rst.getString(3);
				String registrationnum=rst.getString(4);
				int seatingcapacity=rst.getInt(5);
				double fareperkm=rst.getDouble(6);		
				vehicleDetails = new VehicleBean(vehicleid, name, type, registrationnum, seatingcapacity, fareperkm);
				vehicleList.add(vehicleDetails);
		}
		}catch(SQLException e) {
			System.out.println("Sql exception "+ e);
		}
	return vehicleList;
	}




	public ArrayList<VehicleBean> findBySeats(int noOfSeats) {
		// TODO Auto-generated method stub
ArrayList<VehicleBean> vehicleList2 = new ArrayList<VehicleBean>();
		
		try {
			pstmt= connection.prepareStatement("Select * from ata_tbl_vehicle where SeatingCapacity=?");
			pstmt.setInt(1, noOfSeats);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String vehicleid=rst.getString(1);
				String name=rst.getString(2);
				String type=rst.getString(3);
				String registrationnum=rst.getString(4);
				int seatingcapacity=rst.getInt(5);
				double fareperkm=rst.getDouble(6);		
				vehicleDetails = new VehicleBean(vehicleid, name, type, registrationnum, seatingcapacity, fareperkm);
				vehicleList2.add(vehicleDetails);
		}
		}catch(SQLException e) {
			System.out.println("Sql exception "+ e);
		}
	return vehicleList2;
	}
	
}

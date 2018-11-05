package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.dao.DriverDao;
import com.ntl.ata.util.DBUtil;

public class DriverDaoImpl implements DriverDao {

	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	
	DriverBean driverDetails;
	
	static {
	connection=DBUtil.getDBConnection("mysql");
	}
	
	
	
	//inserting a record into driver table
	
	public String createDriver(DriverBean driverBean) {
		// TODO Auto-generated method stub
		try {
		pstmt =connection.prepareStatement("insert into ata_tbl_Driver values(?,?,?,?,?,?,?,?,?,?)");
		pstmt.setString(1, driverBean.getDriverID());
		pstmt.setString(2, driverBean.getName());
		pstmt.setString(3, driverBean.getStreet());
		pstmt.setString(4, driverBean.getLocation());
		pstmt.setString(5, driverBean.getCity());
		pstmt.setString(6, driverBean.getState());
		pstmt.setString(7, driverBean.getPincode());
		pstmt.setString(8, driverBean.getMobileNo());
		pstmt.setString(9, driverBean.getLicenseNumber());
		pstmt.setInt(10, 0);
		int z=pstmt.executeUpdate();
		pstmt.close();
		connection.close();
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

	
	//deleting record/(s) from the driver table
	
	
	public int deleteDriver(ArrayList<String> driverIdList) {
		// TODO Auto-generated method stub
		int z=0;
		try {
			for(String x:driverIdList) {
		pstmt =connection.prepareStatement("delete from ata_tbl_Driver where driverid = ?");
		pstmt.setString(1,x);
		 z= z+ pstmt.executeUpdate();
			}
			pstmt.close();
			connection.close();
		return z;
		}catch(SQLException e)
		{
			System.out.println("Sql exception"+ e);
			return -1;
		}
	}

	
	
	//Updating the records in the driver table
	
	
	public boolean updateDriver(DriverBean driverBean) {
		// TODO Auto-generated method stub
		try {
			pstmt = connection.prepareStatement("update ata_tbl_driver set name=?, street=?, location=?, city=?, state=?, pincode=?, mobileno=?,LicenseNumber=?, driverstatus=? where driverid=?");
			pstmt.setString(1,driverBean.getName());
			pstmt.setString(2, driverBean.getStreet());
			pstmt.setString(3, driverBean.getLocation());
			pstmt.setString(4, driverBean.getCity());
			pstmt.setString(5, driverBean.getState());
			pstmt.setString(6, driverBean.getPincode());
			pstmt.setString(7, driverBean.getMobileNo());
			pstmt.setString(8, driverBean.getLicenseNumber());
			pstmt.setString(9, driverBean.getDriverID());
			pstmt.setInt(10, driverBean.getDriverStatus());
			int z=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
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
	
	
	
	//finding a record using driver id

	public DriverBean findByID(String driverId) {
		// TODO Auto-generated method stub
		try{
			pstmt=connection.prepareStatement("select * from ata_tbl_driver where driverid=?");
			pstmt.setString(1, driverId);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String driverid=rst.getString(1);
				String name=rst.getString(2);
				String street=rst.getString(3);
				String location=rst.getString(4);
				String city=rst.getString(5);
				String state=rst.getString(6);
				String pincode=rst.getString(7);
				String mobileno=rst.getString(8);
				String license=rst.getString(9);
				int driverStatus= rst.getInt(10);
				driverDetails= new DriverBean(driverid, name, street, location, city, state, pincode, mobileno, license,driverStatus);
			}
			rst.close();
			pstmt.close();
			connection.close();}
		catch(SQLException e) {
			System.out.println("Sql exception "+ e);
		}
	return driverDetails;
	}

	
	
	
	public ArrayList<DriverBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<DriverBean> findByDriverStatus() {
		ArrayList<DriverBean> driverList = new ArrayList();
		try{
			pstmt=connection.prepareStatement("select * from ata_tbl_driver where driverstatus=?");
			pstmt.setInt(1, 1);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				String driverid=rst.getString(1);
				String name=rst.getString(2);
				String street=rst.getString(3);
				String location=rst.getString(4);
				String city=rst.getString(5);
				String state=rst.getString(6);
				String pincode=rst.getString(7);
				String mobileno=rst.getString(8);
				String license=rst.getString(9);
				int driverStatus= rst.getInt(10);
				driverDetails= new DriverBean(driverid, name, street, location, city, state, pincode, mobileno, license,driverStatus);
				driverList.add(driverDetails);
			}
			rst.close();
			pstmt.close();
			connection.close();
		}catch(SQLException e) {
			System.out.println("Sql exception "+ e);
		}
		
		return driverList;
	}

}

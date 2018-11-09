package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.ntl.ata.bean.PaymentBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.client.ATAClient;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.PaymentDao;
import com.ntl.ata.util.DBUtil;

public class PaymentDaoImpl implements PaymentDao  {
	
	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	ATAClient client;
	DataSource dataSource;
	CredentialsDao credDao = new CredentialsDaoImpl();
	
	static {
		connection=DBUtil.getDBConnection("mysql");
		}

	

	/**
	 * 
	 */
	public PaymentDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDaoImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource=dataSource;
	}

	public boolean findByCardNumber(String userID, String cardNumber) {
		// TODO Auto-generated method stub
		String userid=null;
		try {
			pstmt=connection.prepareStatement("Select * from ata_tbl_creditcard where creditcardnumber=?");
			pstmt.setString(1, cardNumber);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				userid=rst.getString(5);
			}
			
			if(userid.equals(userID))
				return true;
			else return false;
			
		}catch(SQLException e) {
			System.out.println("sql error "+e);
			return false;		
		}catch(Exception e) {
			System.out.println("Exception occurred"+e);
			return false;
		}
		
	}

	public String processPayment(PaymentBean paymentBean) {
		// TODO Auto-generated method stub
		
		try {
			pstmt=connection.prepareStatement("insert into ata_tbl_creditcard values(?,?,?,?,?)");
			pstmt.setString(1, paymentBean.getCardNumber());
			pstmt.setString(2, paymentBean.getValidFrom());
			pstmt.setString(3, paymentBean.getValidTo());
			pstmt.setDouble(3, paymentBean.getCreditBalance());
			pstmt.setString(5, client.loggedInUser.getUserID());
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
			}catch(Exception e) {
				System.out.println("Exception occurred"+e);
				return "ERROR";
			}

}
}

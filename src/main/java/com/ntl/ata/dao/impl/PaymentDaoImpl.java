package com.ntl.ata.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ntl.ata.bean.PaymentBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.dao.CredentialsDao;
import com.ntl.ata.dao.PaymentDao;
import com.ntl.ata.util.DBUtil;

public class PaymentDaoImpl implements PaymentDao  {
	
	static Connection connection;
	Statement statement;
	PreparedStatement pstmt;
	ResultSet rst;
	
	CredentialsDao credDao = new CredentialsDaoImpl();
	
	static {
		connection=DBUtil.getDBConnection("mysql");
		}


	public boolean findByCardNumber(String userID, String cardNumber) {
		// TODO Auto-generated method stub
		String userid=null;
		try {
			pstmt=connection.prepareStatement("Select * from ata_tbl_creditcard where creditcardnumber=? ");
			pstmt.setString(1, cardNumber);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				userid=rst.getString(5);
			}
			rst.close();
			pstmt.close();
			connection.close();
			if(userid.equals(userID))
				return true;
			else return false;
			
		}catch(SQLException e) {
			System.out.println("sql error "+e);
			return false;
		}
		
	}

	public String processPayment(PaymentBean paymentBean) {
		// TODO Auto-generated method stub
		String userid=credDao.findLoggedIn(1).getUserID();
		try {
			pstmt=connection.prepareStatement("insert into ata_tbl_creditcard values(?,?,?,?,?)");
			pstmt.setString(1, paymentBean.getCardNumber());
			pstmt.setString(2, paymentBean.getValidFrom());
			pstmt.setString(3, paymentBean.getValidTo());
			pstmt.setDouble(3, paymentBean.getCreditBalance());
			pstmt.setString(5, userid);
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
}

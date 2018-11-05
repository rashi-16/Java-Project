package com.ntl.ata.bean;

public class DriverBean {
	
	
	private String driverID;
	private String name;
	private String street;
	private String location;
	private String city;
	private String state;
	private String pincode;
	private String mobileNo;
	private String licenseNumber;
	private int driverStatus;
	
	
	public DriverBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DriverBean(String driverID, String name, String street, String location, String city, String state,
			String pincode, String mobileNo, String licenseNumber, int driverStatus) {
		super();
		this.driverID = driverID;
		this.name = name;
		this.street = street;
		this.location = location;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.mobileNo = mobileNo;
		this.licenseNumber = licenseNumber;
		this.driverStatus=driverStatus;
	}


	
	public int getDriverStatus() {
		return driverStatus;
	}
	public void setDriverStatus(int driverStatus) {
		this.driverStatus = driverStatus;
	}
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
}

package com.ntl.ata.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//import java.util.logging.Logger;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.apache.log4j.Logger;

import java.util.Date;


import com.ntl.ata.bean.CredentialsBean;
import com.ntl.ata.bean.DriverBean;
import com.ntl.ata.bean.PaymentBean;
import com.ntl.ata.bean.ProfileBean;
import com.ntl.ata.bean.ReservationBean;
import com.ntl.ata.bean.RouteBean;
import com.ntl.ata.bean.VehicleBean;
import com.ntl.ata.service.Administrator;
import com.ntl.ata.service.Customer;
import com.ntl.ata.service.impl.AdministratorImpl;
import com.ntl.ata.service.impl.CustomerImpl;
import com.ntl.ata.util.User;
import com.ntl.ata.util.impl.UserImpl;


public class ATAClient {
	
	static Logger log  =Logger.getLogger(ATAClient.class);
	 static Scanner sc =new Scanner(System.in);
	 static  User user= new UserImpl();
	 static Administrator admin = new AdministratorImpl();
	 static Customer customer= new CustomerImpl();
	 static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	 
	  public static CredentialsBean loggedInUser;
	  public static ProfileBean profile;
	  public static VehicleBean vehicle;
	  public static RouteBean route;
	  public static DriverBean driver;
	  public static PaymentBean payment;
	  public static ReservationBean reserve;
	
	  
	  
	  
	static String validateUser() {
		loggedInUser = new CredentialsBean();
		
		System.out.println("Enter your login details");
		System.out.println("Enter your user ID: ");
		String userid=sc.next();
		System.out.println("Enter password: ");
		String pass=sc.next();
		loggedInUser.setUserID(userid);
		loggedInUser.setPassword(pass);
		///loggedInUser.setLoginStatus(0);
			// Calling login from util
		String result= user.login(loggedInUser);
		return result;
	}
	
	
	
	static void passwordChange() {
		
		int flag=0;
		String passResult=null;
		while(flag==0) {
		System.out.println("Enter new Password");
		String newPass=sc.next();
		System.out.println("Confirm new Password");
		String cpass=sc.next();
		if(newPass.equals(cpass)) {
			passResult = user.changePassword(loggedInUser,newPass);
			flag=1;
		}
		else{
		System.out.println("Passwords do not match, re-enter the Password");}
		}
		if(passResult.equals("Success"))
		{
			System.out.println("Password changes sucessfully!");
		}
		else if(passResult.equals("Invalid"))
		{
			passwordChange();
			
		}
		else {
			System.out.println("There is some issue, password not changed.");
		System.out.println("Try again later");
		}
		
	}
	
	
	
	
	
	static void registerUser() {
		 profile = new ProfileBean();
		
		System.out.println("Enter your details");
		System.out.println("Enter your First name");
		String fname =sc.next();
		profile.setFirstName(fname);
		System.out.println("Enter your Last name");
		String lname =sc.next();
		profile.setLastName(lname);
		System.out.println("Enter your Date of Birth (Format:dd/MM/yyyy)");
		String dob =sc.next();
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dob, formatter);
		profile.setDateOfBirth(date);
		System.out.println("Enter your Gender");
		String gender =sc.next();
		profile.setGender(gender);
		System.out.println("Enter your Street");
		String street =sc.next();
		profile.setStreet(street);
		System.out.println("Enter your Location");
		String location =sc.next();
		profile.setLocation(location);
		System.out.println("Enter your city");
		String city =sc.next();
		profile.setCity(city);
		System.out.println("Enter your state");
		String state =sc.next();
		profile.setState(state);
		System.out.println("Enter your pincode");
		String pincode =sc.next();
		profile.setPincode(pincode);
		System.out.println("Enter your Mobile Number");
		String mobileno =sc.next();
		profile.setMobileNo(mobileno);
		System.out.println("Enter your Email Id");
		String emailid =sc.next();
		profile.setEmailID(emailid);
		System.out.println("Enter your Password");
		String password =sc.next();
		profile.setPassword(password);
		
		String result=user.register(profile);
		if(result=="FAIL") {
			registerUser();
		}
		else {
			profile.setUserId(result);
			System.out.println("Your user id is: "+result);
			System.out.println("Login to continue");
			validateUser();
		}
	}
	
	
	
	
	static void logout(String userId) {
		boolean result=user.logout(userId);
		if(result)
		{System.out.println("Logout successful");
		}
		else
			System.out.println("Logout failed");
	}
	
	
	
	
	
	static void enterVehicle() {
		vehicle = new VehicleBean();
		System.out.println("Enter the vehicle details");
		System.out.println("Enter the vehicle name");
		String vname =sc.next();
		vehicle.setName(vname);
		System.out.println("Enter the vehicle type");
		String vtype =sc.next();
		vehicle.setType(vtype);
		System.out.println("Enter the vehicle registration nuumber");
		String regNum=sc.next();
		vehicle.setRegistrationNumber(regNum);
		System.out.println("Enter the seating capacity");
		int seatcap =sc.nextInt();
		vehicle.setSeatingCapacity(seatcap);
		System.out.println("Enter fare per KM");
		Double fare =sc.nextDouble();
		vehicle.setFarePerKM(fare);
		
		
		}
	
	
	
	
	
	static void enterRoute() {
		route = new RouteBean();
		System.out.println("Enter the route details");
		System.out.println("Enter the source");
		String source =sc.next();
		route.setSource(source);
		System.out.println("Enter the destination");
		String destination =sc.next();
		route.setDestination(destination);
		System.out.println("Enter the distance");
		int distance =sc.nextInt();
		route.setDistance(distance);
		System.out.println("Enter the travel duration");
		int travelDur =sc.nextInt();
		route.setTravelDuration(travelDur);
	}
	
	
	
	static void enterDriver() {
		
		driver = new DriverBean();
		System.out.println("Enter the driver details");
		System.out.println("Enter the driver name");
		String dname =sc.next();
		driver.setName(dname);
		System.out.println("Enter Street");
		String street =sc.next();
		driver.setStreet(street);
		System.out.println("Enter Location");
		String loc =sc.next();
		driver.setLocation(loc);
		System.out.println("Enter the city");
		String city =sc.next();
		driver.setCity(city);
		System.out.println("Enter the state");
		String state =sc.next();
		driver.setState(state);
		System.out.println("Enter pincode");
		String pin =sc.next();
		driver.setPincode(pin);
		System.out.println("Enter the mobile number");
		String mobile =sc.next();
		driver.setMobileNo(mobile);
		System.out.println("Enter the license number");
		String license=sc.next();
		driver.setLicenseNumber(license);
	}
	
	
	
	static void enterPaymentDetails() {
		payment = new PaymentBean();
		System.out.println("Enter the payment details");
		System.out.println("Enter the card number");
		String cardNum =sc.next();
		payment.setCardNumber(cardNum);
		System.out.println("Valid from:");
		String validFrom =sc.next();
		payment.setValidFrom(validFrom);
		System.out.println("Valid till:");
		String validTo =sc.next();
		payment.setValidTo(validTo);
		System.out.println("Enter the credit Balance");
		Double bal =sc.nextDouble();
		payment.setCreditBalance(bal);
		}
	
	
	
	
	static void enterReservationDetails() {
		reserve = new ReservationBean();
		System.out.println("Enter the details");
		System.out.println("Enter the User ID");
		String userId =sc.next();
		reserve.setUserID(userId);
		System.out.println("Enter the Route ID");
		String routeId =sc.next();
		reserve.setRouteID(routeId);
		System.out.println("Enter the Booking Date (Format-dd/MM/yyyy):");
		String d =sc.next();
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate bdate = LocalDate.parse(d, formatter);
		reserve.setBookingDate(bdate);
		System.out.println("Enter the Journey Date (Format-dd/MM/yyyy):");
		String j =sc.next();
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate jdate = LocalDate.parse(j, formatter);
		reserve.setJourneyDate(jdate);
		System.out.println("Enter the Boarding Point");
		String bPoint =sc.next();
		reserve.setBoardingPoint(bPoint);
		System.out.println("Enter the Drop Point");
		String dPoint =sc.next();
		reserve.setDropPoint(dPoint);
		System.out.println("Enter the vehicle Id");
		String vid=sc.next();
		reserve.setVehicleID(vid);

	}
	
	static public void LogMeOut()
	{
			logout(loggedInUser.getUserID());
			System.out.println("Sucessfully logged out");
			System.out.println("Thank you for visiting EasyGo Travel Agency!");
	}
	
	static public int admin_menu() {
		System.out.println("Choose your action");
		System.out.println("1. Vehicle");
		System.out.println("2. Route");
		System.out.println("3. Driver");
		System.out.println("4. View Reservation Details");
		System.out.println("5. Change Password");
		System.out.println("6. Logout");
		int x=sc.nextInt();
				return x;
		}
	
	static public int adminNestedMenuV() {
		System.out.println("Choose the task");
		System.out.println("1. Add Vehicle");
		System.out.println("2. Delete Vehicle");
		System.out.println("3. Veiw Vehicle");
		System.out.println("4. Modify Vehicle");
		System.out.println("5. Main menu");
		int z=sc.nextInt();
		return z;
	}
	static public int adminNestedMenuR() {
		System.out.println("Choose the task");
		System.out.println("1. Add Route");
		System.out.println("2. Delete Route");
		System.out.println("3. Veiw Route");
		System.out.println("4. Modify Route");
		System.out.println("5. Main menu");
		int z=sc.nextInt();
		return z;
	}
	static public int adminNestedMenuD() {
		System.out.println("Choose the task");
		System.out.println("1. Add Driver");
		System.out.println("2. Delete Driver");
		System.out.println("3. Allot Driver");
		System.out.println("4. Modify Driver");
		System.out.println("5. Main menu");
		int z=sc.nextInt();
		return z;
	}
	
	
	static public int customer_menu() {
		System.out.println("Choose your action");
		System.out.println("1. View Vehicle by type");
		System.out.println("2. View vehicle by number of seats");
		System.out.println("3. View all routes");
		System.out.println("4. Book Vehicle");
		System.out.println("5. Cancel Booking");
		System.out.println("6. View Booking Details");
		System.out.println("7. Print Booking Details");
		System.out.println("8. View Booking Status");
		System.out.println("9. Make payment by saved card");
		System.out.println("10. Make payment by a new card");
		System.out.println("11. Change Password");
		System.out.println("12. Logout");
		int x=sc.nextInt();
		return x;
		}
	
	static public void adminFunctions() {
		
		int z;
		boolean loop=true, loop2=true;
		while(loop) {
			int x=admin_menu();	
		switch(x) {
		case 1:
			z=adminNestedMenuV();
			while(loop2) {
			
			switch(z) {
			case 1:
				enterVehicle();
				String d =admin.addVehicle(vehicle);
				System.out.println("Vehicle Id is: "+d);
				z=adminNestedMenuV();
				break;
			
			case 2:
				
				System.out.println("Enter the number of vehicles to be deleted");
				int num = sc.nextInt();
				System.out.println("Enter the Vehicle ID of the vehicles to be deleted");
				ArrayList<String> idList = new ArrayList<String>();
				for(int i=0; i<num; i++) {
					String vId=sc.next();
					idList.add(vId);
				}	
				int out = admin.deleteVehicle(idList);
				if(out>0)
					System.out.println(out+ " records deleted");
				z=adminNestedMenuV();
				break;
			case 3:
				System.out.println("Enter the vehicle ID of the vehicle you want to veiw");
				String vehicleID = sc.next();
				VehicleBean rVehicle=new VehicleBean();
				rVehicle=admin.viewVehicle(vehicleID);
				System.out.println("Vehicle ID: " +rVehicle.getVehicleID());
				System.out.println("Vehicle Name: " +rVehicle.getName());
				System.out.println("Vehicle Type: " +rVehicle.getType());
				System.out.println("Vehicle Registration Number: "+rVehicle.getRegistrationNumber());
				System.out.println("Vehicle Seating Capacity: " +rVehicle.getSeatingCapacity());
				System.out.println("FareperKm:"+rVehicle.getFarePerKM());
				z=adminNestedMenuV();
				break;
			case 4:
				
				System.out.println("Enter the Id of the vehicle to be modified");
				String vid = sc.next();
				vehicle.setVehicleID(vid);
				System.out.println("what is the issue?");
				enterVehicle();
				boolean v =admin.modifyVehicle(vehicle);
				if(v)
					System.out.println("Vehicle modified successfully");
				else
					System.out.println("Failed to modify vehicle");
				z=adminNestedMenuV();
				break;

			default:
				loop2=false;
				 x=admin_menu();
				break;
			}
			}
			
		case 2:
			z=adminNestedMenuR();
			loop2=true;
			while(loop2)
			{
				switch(z) {
				
				case 1:
					enterRoute();
					String re =admin.addRoute(route);
					System.out.println("Route Id is: "+re);
					z=adminNestedMenuR();
					break;
				case 2:
					System.out.println("Enter the number of Routes to be deleted");
					int num = sc.nextInt();
					System.out.println("Enter the Route ID of the routes to be deleted");
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0; i<num; i++) {
						String rId=sc.next();
						idList.add(rId);
					}	
					int out = admin.deleteRoute(idList);
					if(out>0)
						System.out.println(out+ " records deleted");
					z=adminNestedMenuR();
					break;
				case 3:
					System.out.println("Enter the Route ID of the route you want to veiw");
					String routeID = sc.next();
					RouteBean newroute=new RouteBean();
					newroute=admin.viewRoute(routeID);
					System.out.println("Route ID: "+newroute.getRouteID());
					System.out.println("Source: "+newroute.getSource());
					System.out.println("Destination: "+newroute.getDestination());
					System.out.println("Distance: "+newroute.getDistance());
					System.out.println("Travel Duration: "+newroute.getTravelDuration());
					z=adminNestedMenuR();
					break;
					
				case 4:
					System.out.println("Enter the Id of the route to be modified");
					String id = sc.next();
					route.setRouteID(id);
					enterRoute();
					boolean l=admin.modifyRoute(route);
					if(l)
						System.out.println("Route modified successfully");
					else
						System.out.println("Failed to update route");
					z=adminNestedMenuR();
					break;
				default:
					loop2=false;
					 x=admin_menu();
					break;
				}
			}
			
		case 3:
			z=adminNestedMenuD();
			loop2=true;
			while(loop2) {
				switch(z) {
				case 1:
					enterDriver();
					String res =admin.addDriver(driver);
					System.out.println("Driver Id is: "+res);
					z=adminNestedMenuD();
					break;
				case 2:
					System.out.println("Enter the number of drivers to be deleted");
					int num = sc.nextInt();
					System.out.println("Enter the Driver ID of the drivers to be deleted");
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0; i<num; i++) {
						String dId=sc.next();
						idList.add(dId);
					}	
					int out = admin.deleteDriver(idList);
					if(out>0)
						System.out.println(out+ " records deleted");
					z=adminNestedMenuD();
					break;
				case 3:
					System.out.println("Enter the Reservation Id");
					try {
					String reservationID = sc.next();
					boolean r = admin.findByDriverStatus(reservationID);
					if(r)
						System.out.println("Driver alloted!");
					else
						System.out.println("Driver allocation failed");
					}catch(Exception e) {
						System.out.println(e);
					}
					
					
					z=adminNestedMenuD();
					break;
				case 4:
					System.out.println("Enter the Id of the driver to be modified");
					String id = sc.next();
					driver.setDriverID(id);
					enterDriver();
					boolean u= admin.modifyDriver(driver);
					if(u)
					System.out.println("Driver Updated Successfully!");
					else
						System.out.println("Error in updating the driver.");
					z=adminNestedMenuD();
					break;
				default:
					loop2=false;
					 x=admin_menu();
					break;
					}
				}
		case 4:
			System.out.println("Enter the journey date :(Format:dd/MM/yyyy)");
			String d =sc.next();
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate jDate = LocalDate.parse(d, formatter);
			System.out.println("Enter the source location");
			String s=sc.next();
			System.out.println("Enter the drop location");
			String dr = sc.next();
			ArrayList<ReservationBean> arr = admin.viewBookingDetails(jDate, s, dr);
			for(ReservationBean rb : arr) {
				System.out.println("Reservation Id: "+rb.getReservationID());
				System.out.println("UserId: "+rb.getUserID());
				System.out.println("RouteId "+rb.getRouteID());
				System.out.println("VehicleId "+rb.getVehicleID());
				System.out.println("Journey Date "+rb.getJourneyDate());
				System.out.println("Booking Date:"+rb.getBookingDate());
				System.out.println("Driver Id "+rb.getDriverID());
				System.out.println("Booking Status "+rb.getBookingStatus());
				System.out.println("Total fare "+rb.getTotalFare());
				System.out.println("Boarding Point "+rb.getBoardingPoint());
				System.out.println("Drop Point "+rb.getDropPoint());
				System.out.println(" ");
			}
			x=admin_menu();
			break;
		case 5:
			passwordChange();
			 x=admin_menu();
			 break;
		case 6:
			LogMeOut();
			loop=false;
			break;			
		}
		}
		
	}
	
	
	
	
	
	static public void customerFunctions(){
		
		int k;
		boolean loopc=true;
		while(loopc) {
			k=customer_menu();	
		switch(k) {
		case 1:
			System.out.println("Enter the vehicle type:(bike/scooty/Share/Hatchback/Sedan/MPV/SUV/Crossover/Convertible/)");
			String type = sc.next();
			ArrayList<VehicleBean> vList = customer.viewVehiclesByType(type);
			for(VehicleBean v :vList) {
				System.out.println("Vehicle ID: " +v.getVehicleID());
				System.out.println("Vehicle Name: " +v.getName());
				System.out.println("Vehicle Type: " +v.getType());
				System.out.println("Vehicle Registration Number: "+v.getRegistrationNumber());
				System.out.println("Vehicle Seating Capacity: " +v.getSeatingCapacity());
				System.out.println("FareperKm:"+v.getFarePerKM());
				//System.out.println(v.toString());
			}
			k=customer_menu();
			break;
		case 2:
			System.out.println("Enter the number of seats(1/2/4/6/7)");
			int seats = sc.nextInt();
			ArrayList<VehicleBean> vList2 = customer.viewVehicleBySeats(seats);
			for(VehicleBean v :vList2) {
				System.out.println("Vehicle ID: " +v.getVehicleID());
				System.out.println("Vehicle Name: " +v.getName());
				System.out.println("Vehicle Type: " +v.getType());
				System.out.println("Vehicle Registration Number: "+v.getRegistrationNumber());
				System.out.println("Vehicle Seating Capacity: " +v.getSeatingCapacity());
				System.out.println("FareperKm:"+v.getFarePerKM());
				//System.out.println(v.toString());
			}
			k=customer_menu();
			break;
		case 3:
			System.out.println("Following are the routes");
			ArrayList<RouteBean> routes = customer.viewAllRoutes();
			for(RouteBean r :routes) {
				System.out.println("Route ID: "+r.getRouteID());
				System.out.println("Source: "+r.getSource());
				System.out.println("Destination: "+r.getDestination());
				System.out.println("Distance: "+r.getDistance());
				System.out.println("Travel Duration: "+r.getTravelDuration());
				//System.out.println(r.toString());
			}
			k=customer_menu();
			break;
		case 4:
			System.out.println("VEHICLE BOOKING FORM");
		
			enterReservationDetails();
			String res=customer.bookVehicle(reserve);
			System.out.println("Vehicle Booking Id: "+res);
			k=customer_menu();
			break;
		case 5:
			System.out.println("Cancel Booking");
			System.out.println("Enter userID");
			String uid = sc.next();
			System.out.println("Enter reservationID");
			String rid = sc.next();
			boolean s =customer.cancelBooking(uid, rid);
			if(s) {
				System.out.println("Booking cancelled successfully!");
			}
			
			else
				System.out.println("Booking Cancellation Failed!");
			k=customer_menu();
			break;
		case 6:
			System.out.println("View booking details");
			System.out.println("Enter reservation Id: ");
			String resevationId = sc.next();
			ReservationBean rb = customer.viewBookingDetails(resevationId);
			System.out.println(rb);
			k=customer_menu();
			break;
		case 7:
			System.out.println("Booking details");
			System.out.println("Enter reservation Id:");
			String reserveid = sc.next();
			ReservationBean rbean = customer.printBookingDetails(reserveid);
			System.out.println(rbean);
			k=customer_menu();
			break;
		case 8:
			System.out.println("View booking status");
			System.out.println("Enter your user ID");
			String userid = sc.next();
			String r=customer.viewBookingStatus(userid);
			System.out.println("Booking status: "+r);
			k=customer_menu();
			break;
		case 9:
			System.out.println("Pay using your saved card");
			System.out.println("Enter your user Id");
			uid =sc.next();
			System.out.println("Enter your card number");
			String cnum = sc.next();
			boolean out = customer.findByCardNumber(uid,cnum);
			if(out)
			System.out.println("Payment done successfully");
			else 
				System.out.println("Payment failed!");
			k=customer_menu();
			break;
		case 10:
			System.out.println("Make payment");
			enterPaymentDetails();
			String stat =customer.processPayment(payment);
			System.out.println("Payment status: "+stat);
			k=customer_menu();
			break;
		case 11:
			passwordChange();
			k=customer_menu();
			 break;
		case 12:
			LogMeOut();
			loopc=false;
			break;	
			
		
		}}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		log.info("Program is getting started");
		
		System.out.println("				* * * * * * * * * * * *");
		System.out.println("				* EasyGo Travel Agency *");
		System.out.println("				* * * * * * * * * * * *");
		System.out.println("Welcome to EasyGo Travels Ltd., an Automated Travel Agency ");
		System.out.print("\n\n\n");
		System.out.print("1.Login(Press L)\n2.Register(Press R)\n ");
		String choice=sc.next();
		if(choice.equals("L")) {
		String result=validateUser();
		if(result.equals("A")) {
		System.out.println(" Welcome Administrator ");
		adminFunctions();
		
		}
		
		
	else if(result.equals("C")) {
			//return result;
			System.out.println("Welcome customer");
			customerFunctions();
		
			}
		else if(result.equals("Invalid"))
		{
			System.out.println("Invalid user Please SignUP");
			registerUser();
		}
		else {
			System.out.println(" Invalid Password , Please try again");
			validateUser();
			}
		}
		
		else if(choice.equals("R")) {
			registerUser();
			customerFunctions();
		}
		else {
			System.out.println("Wrong option: ");
		System.out.println("Change your password");
			passwordChange();
			validateUser();
			customerFunctions();

		}
		

	}
	
	

	
}

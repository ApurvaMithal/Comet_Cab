package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbManager;
import model.Booking;
import model.CabType;
import model.Customer;
import model.Driver;
import model.Location;
import views.BookingRequestView;

public class BookingDAOImpl implements BookingDAO {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();

	public int getDistance(Location location) {

		int res = -1;
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement("select * from distancematrix where pick=?");
			ps.setString(1, location.getPickUpLocation().name());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String result = (String) rs.getString(location.getDropOffLocation().name());
				res = Integer.valueOf(result);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return res;
	}

	@Override
	public float estimateFare(Location location, CabType cabType) {
		int distance=getDistance(location);
		return (distance*cabType.getMultiplier());
	}
	
	
	@Override
	public boolean checkBalance(String netId, float fare) {
		// TODO Auto-generated method stub
		float balance = 0.0f;
		try {

			conn = db.getConnection();
			ps = conn.prepareStatement("Select balance from carddetails where netId=?");
			ps.setString(1, netId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				balance = Float.valueOf(rs.getString(1));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return (balance >= fare ? true : false);
	
	}

	@Override
	public boolean checkCabAvailability(CabType cabType) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(
					"select * from driver d, cab c where c.licenseNo=d.licenseNo and cabType=? and c.availability='T'");
			ps.setString(1, cabType.name());
			ResultSet rs = ps.executeQuery();
			if (rs.next() != false) {
				return true;

			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	@Override
	public Driver getAvailableDriver(CabType cabType) {
	//	Driver driver = new Driver();
		Driver driver = null;
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(
					"select firstName, lastName,phoneNo,d.licenseNo from driver d, cab c where c.licenseNo=d.licenseNo and cabType=? and c.availability='T'");
			ps.setString(1, cabType.name());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				    driver = new Driver();
					driver.setFirstName((rs.getString(1)));
					driver.setLastName((rs.getString(2)));
					driver.setPhoneNo((rs.getString(3)));
					driver.setLicenseNo((rs.getString(4)));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return driver;
	}

	
	@Override 	
   public Driver allocateRide(CabType cabType) {
		
		Driver availableDriver=getAvailableDriver(cabType);
		return availableDriver;
	}
	
	@Override
	public int saveBooking(Booking booking) {

		int id = 0;
		String generatedColumns[] = { "ID" };
		try {
			conn = db.getConnection();
			ps=conn.prepareStatement("Insert into bookings(netId,pickupLocation,dropOffLocation,fare,cabtype) values(?,?,?,?,?)",generatedColumns);
			ps.setString(1, booking.getNetId());
			ps.setString(2, booking.getLocation().getPickUpLocation().name());
			ps.setString(3, booking.getLocation().getDropOffLocation().name());
			ps.setFloat(4, booking.getFare());
			ps.setString(5, booking.getCabType().name());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id=rs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;

	}

	
	@Override
	public BookingRequestView getBookingRequests() {
		conn = db.getConnection();
	/*	Booking booking = new Booking();
		Location location = new Location();
		Customer customer = new Customer();
		try {
			ps = conn.prepareStatement(
					"select b.bookingId,b.netId,b.pickUpLocation,b.dropOffLocation,c.firstName,c.lastName,c.phoneNo from bookings b, customer c where b.driverId is null and b.netId=c.netId");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				booking.setBookingId(Integer.valueOf((rs.getString(1))));
				booking.setNetId((rs.getString(2)));
				location.setPickUpLocation(Place.valueOf(rs.getString(3)));
				location.setDropOffLocation(Place.valueOf(rs.getString(4)));
				booking.setLocation(location);
				customer.setFirstName(rs.getString(5));
				customer.setLastName(rs.getString(6));
				customer.setPhoneNo(rs.getString(7));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		*/
		return null;

	}

	
	
	
}
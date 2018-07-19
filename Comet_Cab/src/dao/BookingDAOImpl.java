package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbManager;
import exception.ApplicationException;
import model.Booking;
import model.Cab;
import model.CabType;
import model.Customer;
import model.Driver;
import model.Location;
import model.Place;

public class BookingDAOImpl implements BookingDAO {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();

	@Override
	public float reserveBooking(Customer customer, Location location, CabType cabType) throws ApplicationException {
		float fare = this.estimateFare(location, cabType);
		if (!this.checkBalance(customer.getNetId(), fare)) {
			throw new ApplicationException("Error: Balance not Sufficient");
		}
		Driver availableDriver = getAvailableDriver(cabType);
		if (null == availableDriver) {
			throw new ApplicationException("Error: Cab not available");
		}
		setDriverStatus(availableDriver.getDriverId(), "P"); // Setting the driver status to pending
		Booking newBooking = new Booking(location, fare, cabType,customer,availableDriver);
		saveBooking(newBooking);
		return fare;
	}

	@Override
	public Booking confirmBooking(Booking booking) {

		int id = 0;
		String generatedColumns[] = { "ID" };
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(
					"update driver set availability = ? where driverId = (select driverId from bookings where bookingId = ?)");
			ps.setString(1, "NA");
			ps.setLong(2, booking.getBookingId());
			ps.executeUpdate();
			ps = conn.prepareStatement(
					"select c.licenseNo,c.model, c.cabType,d.firstName, d.lastName,d.phoneNo,d.licenseNo,d.driverId from bookings b, cab c, driver d where b.bookingId=? and b.driverId=d.driverId and c.licenseNo=d.licenseNo");
			ps.setLong(1, booking.getBookingId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cab cab = new Cab();
				CabType cabType = CabType.valueOf(rs.getString(3).trim());
				cab.setLicensePlateNo((rs.getString(1)));
				cab.setModel((rs.getString(2)));
				cab.setCabType(cabType);
				Driver driver = new Driver();
				driver.setFirstName((rs.getString(1)));
				driver.setLastName((rs.getString(2)));
				driver.setPhoneNo((rs.getString(3)));
				driver.setLicenseNo((rs.getString(4)));
				driver.setDriverId((rs.getString(5)));
				driver.setCab(cab);
				booking.setDriver(driver);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
						// Available
		return booking;

	}

	@Override
	public Booking getBookingRequests(Integer driverId) {
		conn = db.getConnection();
		Booking booking = null;
		Location location = null;
		Customer customer = null;
		try {
			ps = conn.prepareStatement(
					"select b.bookingId,b.netId,b.pickUpLocation,b.dropOffLocation,c.firstName, c.middleName, c.lastName,c.phoneNo from bookings b, customer c where b.driverId =?  and b.netId=c.netId and b.tripstatus='B'");
			ps.setString(1, driverId.toString());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				booking = new Booking();
				location = new Location();
				customer = new Customer();
				booking.setBookingId(Integer.valueOf((rs.getString(1))));
				customer.setNetId((rs.getString(2)));
				location.setPickUpLocation(Place.valueOf(rs.getString(3)));
				location.setDropOffLocation(Place.valueOf(rs.getString(4)));
				booking.setLocation(location);
				customer.setFirstName(rs.getString(5));
				customer.setMiddleName(rs.getString(6));
				customer.setLastName(rs.getString(7));
				customer.setPhoneNo(rs.getString(8));
				booking.setCustomer(customer);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return booking;

	}

	@Override
	public boolean endRide(String bookingid) throws ApplicationException {
		System.out.println("Begin endRide");
		boolean flag = true;
		if (!this.setDriverStatus(bookingid, "A")) // Setting the driver status
													// to Available
			throw new ApplicationException("Error: No ride is in progress");
		if (!this.makePayment(bookingid))
			throw new ApplicationException("Error: Payment unsuccessful");
		return flag;
	}

	private boolean setDriverStatus(String driverID, String status) {
		conn = db.getConnection();
		int queryFlag = 0;
		try {
			String sqlQuery1 = "update driver set availability = ? where driverId = ?)";
			//String sqlQuery2 = "update driver set availability = ?";
			if (driverID == null) {
				ps = conn.prepareStatement(sqlQuery1);
				ps.setString(1, status);
				ps.setString(2, driverID);
			} 
			queryFlag = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println(queryFlag);
		return (queryFlag == 1 ? true : false);
	}

	private boolean makePayment(String bookingid) {
		float updatedBal = 0;
		float fare = 0;
		String netId;
		float balance = 0;
		int paymentSuccessFlag = 0;
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(
					"select b.fare,b.netId,c.balance from bookings b,carddetails c where bookingId = ? and b.netId=c.netId");
			ps.setString(1, bookingid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fare = (Float.valueOf((rs.getString(1))));
				netId = ((rs.getString(2)));
				balance = (Float.valueOf((rs.getString(3))));
				updatedBal = balance - fare;

				ps = conn.prepareStatement("update carddetails set balance = ? where netId = ? ");
				ps.setFloat(1, (updatedBal));
				ps.setString(2, netId);
				paymentSuccessFlag = ps.executeUpdate();
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return (paymentSuccessFlag == 1 ? true : false);
	}

	private float estimateFare(Location location, CabType cabType) {
		conn = db.getConnection();
		Integer distance = 0;
		try {
			ps = conn.prepareStatement("select * from distancematrix where pick=?");
			ps.setString(1, location.getPickUpLocation().name());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String result = (String) rs.getString(location.getDropOffLocation().name());
				distance = Integer.valueOf(result);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return (distance * cabType.getMultiplier());
	}

	private boolean checkBalance(String netId, float fare) {
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

	private Driver getAvailableDriver(CabType cabType) {
		// Driver driver = new Driver();
		Driver driver = null;
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(
					"select firstName, lastName,phoneNo,d.licenseNo,d.driverId from driver d, cab c where c.licenseNo=d.licenseNo and c.cabType=? and d.availability='A'");
			ps.setString(1, cabType.name());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				driver = new Driver();
				driver.setDriverId((rs.getString(5)));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return driver;
	}

	private void saveBooking(Booking booking) {

		String generatedColumns[] = { "ID" };
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(
					"Insert into bookings(netId,pickupLocation,dropOffLocation,fare,cabtype,driverId) values(?,?,?,?,?,?)",
					generatedColumns);
			ps.setString(1, booking.getCustomer().getNetId());
			ps.setString(2, booking.getLocation().getPickUpLocation().name());
			ps.setString(3, booking.getLocation().getDropOffLocation().name());
			ps.setFloat(4, booking.getFare());
			ps.setString(5, booking.getCabType().name());
			ps.setString(6, booking.getDriver().getDriverId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

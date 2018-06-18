package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbManager;
import model.Booking;
import model.CabType;
import model.Driver;
import model.Location;

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
}
package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbManager;
import model.Location;

public class BookingDAOImpl implements BookingDAO {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();

	public int getDistance(Location location) {

		conn = db.getConnection();
		try {
			ps = conn.prepareStatement("select * from distancematrix where pick=?");
			ps.setString(1, location.getPickUpLocation().name());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String result = (String) rs.getString(location.getDropOffLocation().name());
				return Integer.valueOf(result);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return 0;
	}
}

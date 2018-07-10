package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Driver;
import db.DbManager;



public class DriverDAOImpl implements DriverDAO{

	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	@Override
	public int register(Driver d) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("insert into driver values(?,?,?,?,?,?,?)");
			ps.setString(1, d.getDriverId());
			ps.setString(2, d.getFirstName());
			ps.setString(3, d.getMiddleName());
			ps.setString(4, d.getLastName());
			ps.setString(5, d.getPassword());
			ps.setString(6, d.getPhoneNo());
			ps.setString(7, d.getLicenseNo());
			status = ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}

	@Override
	public Driver validateDriver(DriverLogin dlogin) {
		Driver d = new Driver();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select * from driver where driverId=? and password=?");
			ps.setString(1, dlogin.getUsername());
			ps.setString(2, dlogin.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				d.setDriverId(rs.getString(1));
				d.setFirstName(rs.getString(2));
				d.setMiddleName(rs.getString(3));
				d.setLastName(rs.getString(4));
				d.setPassword(rs.getString(5));
				d.setPhoneNo(rs.getString(6));
				d.setLicenseNo(rs.getString(6));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return d;
	}

}

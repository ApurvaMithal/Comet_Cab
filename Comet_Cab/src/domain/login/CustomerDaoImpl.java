package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Customer;
import db.DbManager;



public class CustomerDaoImpl implements CustomerDao {

	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	@Override
	public int register(Customer c) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
			ps.setString(1, c.getNetId());
			ps.setString(2, c.getFirstName());
			ps.setString(3, c.getMiddleName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getPassword());
			ps.setString(6, c.getPhoneNo());
			status = ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}

	@Override
	public Customer validateCustomer(Login login) {
		Customer c = new Customer();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select * from customer where netId=? and password=?");
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setNetId(rs.getString(1));
				c.setFirstName(rs.getString(2));
				c.setMiddleName(rs.getString(3));
				c.setLastName(rs.getString(4));
				c.setPassword(rs.getString(5));
				c.setPhoneNo(rs.getString(6));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return c;
	}

}

package dao;
/**
 * 
 * @author mehra
 * The methods that we need to save and retrieve data from the database
 */
import model.Driver;

public interface DriverDAO {

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int register(Driver d);
	public Driver validateDriver(DriverLogin dlogin);
	
	
}


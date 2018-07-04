package domain.login;
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
	
	/*
	 * Retrieve the Customer object from the database
	 */
	public Driver validateDriver(DriverLogin dlogin);

	
	//public Customer getCustomer(String username, String pass); This method does not needed as we have the Login object

	
}


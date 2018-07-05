package dao;

/**
 * 
 * @author mehra
 * This is the Customer before the validation.
 */
public class CustomerLogin {
	private String username;
	private String password;
	
	public CustomerLogin(String username, String pass){
		this.username = username;
		this.password = pass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

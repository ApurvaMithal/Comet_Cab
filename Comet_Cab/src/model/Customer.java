package model;

public class Customer extends Person {

	private String firstName;
	private String middleName;
	private String lastName;
	private String netId;
	private String password;
	private String phoneNo;

	public Customer(String firstName, String middleName, String lastName, String netId, String password,
			String phoneNo) {
		super(firstName,middleName,lastName);
		this.netId = netId;
		this.password = password;
		this.phoneNo = phoneNo;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}

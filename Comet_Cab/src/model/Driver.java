package model;

public class Driver extends Person {
	
	private String driverId;
	private String password;
	private String phoneNo;
	private String licenseNo;
	private boolean availability;
	
	
	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Driver(String firstNm, String middleNm, String lastNm, String driverId, String password, String phoneNo,  String licenseNo){
		super(firstNm,middleNm,lastNm);
		this.driverId = driverId;
		this.password = password;
		this.phoneNo=phoneNo;
		this.licenseNo = licenseNo;

	}

	public Driver() {
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

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}


	public String getPhoneNo() {
		return phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

}

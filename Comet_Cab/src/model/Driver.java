package model;

public class Driver extends Person {
	
	private String driverId;
	private float milesDriven;
	private String licenseNo;
	private String phoneNo;
	
	public Driver(String firstNm, String middleNm, String lastNm, String driverId, float miles, String licenseNo,String phoneNo){
		super(firstNm,middleNm,lastNm);
		this.driverId = driverId;
		this.milesDriven = miles; 
		this.licenseNo = licenseNo;
		this.phoneNo=phoneNo;
	}

	public Driver() {
		// TODO Auto-generated constructor stub
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

	public float getMilesDriven() {
		return milesDriven;
	}

	public void setMilesDriven(float milesDriven) {
		this.milesDriven = milesDriven;
	}
	

	public String getPhoneNo() {
		return phoneNo;
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

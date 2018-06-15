package model;

public class Driver extends Person {
	
	private String driverId;
	private boolean availabilty;
	private float milesDriven;
	private Cab cab;
	
	public Driver(String firstNm, String middleNm, String lastNm, String driverId, boolean avail, float miles, Cab cab){
		super(firstNm,middleNm,lastNm);
		this.driverId = driverId;
		this.availabilty = avail;
		this.milesDriven = miles; 
		this.cab = cab;
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

	public boolean isAvailabilty() {
		return availabilty;
	}

	public void setAvailabilty(boolean availabilty) {
		this.availabilty = availabilty;
	}

	public float getMilesDriven() {
		return milesDriven;
	}

	public void setMilesDriven(float milesDriven) {
		this.milesDriven = milesDriven;
	}
	
	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

}

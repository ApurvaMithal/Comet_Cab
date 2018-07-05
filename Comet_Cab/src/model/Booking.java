package model;

public class Booking {

	private int bookingId;
	private Location location;
	private float fare;
	private CabType cabType;
	private String netId;
	private String driverId;
	private String licenseNo;
	private String tripStatus;
	
	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Booking(Location location, float fare, CabType cabType, String netId) {
		super();
		this.location = location;
		this.fare = fare;
		this.cabType = cabType;
		this.netId = netId;
	}
	
	public Booking() {
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	
	public CabType getCabType() {
		return cabType;
	}

	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
}

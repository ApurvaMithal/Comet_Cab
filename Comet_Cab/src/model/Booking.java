package model;

public class Booking {

	private int bookingId;
	private Location location;
	private float fare;
	private CabType cabType;
	Customer customer;
	private Driver driver;
	private String tripStatus;
	
	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Booking(Location location, float fare, CabType cabType,Customer customer,Driver driver) {
		super();
		this.location = location;
		this.fare = fare;
		this.cabType = cabType;
		this.customer=customer;
		this.driver=driver;
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

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}

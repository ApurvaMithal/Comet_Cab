package model;

public class Trip {
	private String driverId;
	private Cab cab;
	private Booking book;
	
	public Trip(String driverId, Cab cab, Booking book) {
		super();
		this.driverId = driverId;
		this.cab = cab;
		this.book = book;
	}
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public Cab getCab() {
		return cab;
	}
	public void setCab(Cab cab) {
		this.cab = cab;
	}
	public Booking getBook() {
		return book;
	}
	public void setBook(Booking book) {
		this.book = book;
	}
	
}

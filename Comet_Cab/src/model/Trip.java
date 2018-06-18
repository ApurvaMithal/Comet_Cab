package model;

public class Trip {
	private Driver driver;
	private Booking book;

	public Trip(Driver driver, Booking book) {
		super();
		this.driver = driver;
		this.book = book;
	}

	public Trip() {
		// TODO Auto-generated constructor stub
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Booking getBook() {
		return book;
	}

	public void setBook(Booking book) {
		this.book = book;
	}

}

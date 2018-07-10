package views;
import model.Booking;
import model.Driver;
import model.Cab;

public class ConfirmBookingView {
	public ConfirmBookingView(Driver driver, Booking booking, Cab cab) {
		super();
		this.driver = driver;
		this.booking = booking;
		this.cab = cab;
	}
	Driver driver;
	Booking booking;
	Cab cab;
	
	
    public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public Cab getCab() {
		return cab;
	}
	public void setCab(Cab cab) {
		this.cab = cab;
	}
	

} 

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

} 

package views;

import model.Booking;
import model.Customer;

public class BookingRequestView {

	public BookingRequestView(Customer customer, Booking booking) {
		super();
		this.customer = customer;
		this.booking = booking;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	private Customer customer;
	private Booking booking;
}

package dao;

import exception.ApplicationException;
import model.Booking;
import model.CabType;
import model.Customer;
import model.Location;

public interface BookingDAO {

	Booking reserveBooking(Customer customer,Location location, CabType cabType) throws ApplicationException;
	Booking confirmBooking(Booking booking);
	Booking getBookingRequests(Integer driverId);
	boolean endRide(String bookingid) throws ApplicationException;
	void cancelBooking(Booking booking);
}

package service;
import model.Booking;
import model.CabType;
import model.CardDetails;
import model.Location;
import views.BookingRequestView;
import views.ConfirmBookingView;

public interface BookingService {

	float makeBooking(String netId, Location location, CabType cabType);
	ConfirmBookingView confirmBooking(String netId, Location location, float fare, CabType cabType);
	boolean startRide(String bookingid);
	boolean endRide(String bookingid);
	BookingRequestView getRequest(Integer driverId);
}

package service;
import model.Booking;
import model.CabType;
import model.CardDetails;
import model.Location;
import views.BookingRequestView;

public interface BookingService {

	float makeBooking(String netId, Location location, CabType cabType);
	Booking confirmBooking(String netId, Location location, float fare, CabType cabType);
	void startRide(String bookingid);
	void endRide(String bookingid);
	BookingRequestView getRequest(Integer driverId);
}

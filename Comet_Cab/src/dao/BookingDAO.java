package dao;

import model.Booking;
import model.CabType;
import model.Driver;
import model.Location;
import views.BookingRequestView;

public interface BookingDAO {

	abstract int getDistance(Location location);
	float estimateFare(Location location, CabType cabType);
	abstract int saveBooking(Booking booking);
	abstract Driver getAvailableDriver(CabType cabType);
	abstract boolean checkBalance(String netId, float fare);	
	abstract boolean checkCabAvailability(CabType cabType);
	abstract Driver allocateRide(CabType cabType);
	abstract BookingRequestView getBookingRequests(Integer driverId);

}
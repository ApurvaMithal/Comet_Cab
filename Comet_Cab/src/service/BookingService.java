package service;
import model.CabType;
import model.Driver;
import model.Location;
import model.Trip;

public interface BookingService {

	float estimateFare(Location location, CabType cabType);

	String makeBooking(String netId, Location location, float fare);
	
	

}

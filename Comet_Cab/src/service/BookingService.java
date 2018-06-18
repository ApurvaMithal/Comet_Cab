package service;
import model.CabType;
import model.Location;
import model.Trip;

public interface BookingService {

	float estimateFare(Location location, CabType cabType);

	Trip makeBooking(String netId, Location location, float fare,CabType cabType);
	
	

}

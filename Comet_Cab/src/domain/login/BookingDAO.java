package domain.login;

import model.Booking;
import model.CabType;
import model.Driver;
import model.Location;

public interface BookingDAO {

	abstract int getDistance(Location location);

	abstract int saveBooking(Booking booking);

	abstract Driver getAvailableDriver(CabType cabType);
}

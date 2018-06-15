package domain.login;

import model.Location;

public interface BookingDAO {

	abstract int getDistance(Location location);
}

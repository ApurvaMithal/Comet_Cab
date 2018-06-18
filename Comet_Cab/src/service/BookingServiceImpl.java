package service;
import domain.login.BookingDAO;
import domain.login.BookingDAOImpl;
import model.Booking;
import model.CabType;
import model.Driver;
import model.Location;
import model.Trip;

public class BookingServiceImpl implements BookingService{

	BookingDAO bookingDao = new BookingDAOImpl();
	@Override
	public float estimateFare(Location location, CabType cabType) {
		int distance=bookingDao.getDistance(location);
		return (distance*cabType.getMultiplier());
	}

	@Override
	public Trip makeBooking(String netId, Location location, float fare, CabType cabType) {
		Driver allocatedDriver=allocateRide(cabType);
		Trip trip=null;
		if(allocatedDriver == null) {
			return trip;
		}
		Booking newBooking = new Booking(location, fare, cabType, netId);
		int id=bookingDao.saveBooking(newBooking);
		newBooking.setBookingId(id);
		return new Trip(allocatedDriver, newBooking);
	}

	
	private Driver allocateRide(CabType cabType) {
		
		Driver availableDriver=bookingDao.getAvailableDriver(cabType);
		return availableDriver;
	}


}

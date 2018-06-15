package service;
import domain.login.BookingDAO;
import domain.login.BookingDAOImpl;
import model.CabType;
import model.Location;

public class BookingServiceImpl implements BookingService{

	BookingDAO bookingDao = new BookingDAOImpl();
	@Override
	public float estimateFare(Location location, CabType cabType) {
		int distance=bookingDao.getDistance(location);
		return (distance*cabType.getMultiplier());
	}

	@Override
	public String makeBooking(String netId, Location location, float fare) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Driver allocateRide(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}*/


}

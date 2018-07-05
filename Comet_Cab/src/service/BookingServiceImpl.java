package service;
import dao.BookingDAO;
import dao.BookingDAOImpl;
import exception.ApplicationException;
import model.Booking;
import model.CabType;
import model.CardDetails;
import model.Driver;
import model.Location;
import views.BookingRequestView;

public class BookingServiceImpl implements BookingService{

	BookingDAO bookingDao = new BookingDAOImpl();
	
	@Override
	public float makeBooking(String netId, Location location, CabType cabType) { // return fare
		// TODO Auto-generated method stub
		
		float fare = bookingDao.estimateFare(location, cabType);
		if (!bookingDao.checkBalance(netId, fare)) {
			throw new ApplicationException("Balance not Sufficient");
		}
		if (!bookingDao.checkCabAvailability(cabType)) {
			throw new ApplicationException("Cab not available");
		}

		return fare;
	}

	
	@Override
	public Booking confirmBooking(String netId, Location location, float fare, CabType cabType) {
		Driver allocatedDriver=bookingDao.allocateRide(cabType);
	/*	if(allocatedDriver == null) {
			return trip;
		}
		*/
		Booking newBooking = new Booking(location, fare, cabType, netId);
		int id=bookingDao.saveBooking(newBooking);
		newBooking.setBookingId(id);
	//	return new Trip(allocatedDriver, newBooking);
		return null;
	}

	
	
	@Override
	public void startRide(String bookingid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void endRide(String bookingid) {
		// TODO Auto-generated method stub
		/*
		@Override
		public String processPayment(CardDetails crd, float fare) {
			// TODO Auto-generated method stub
			return null;
		}
	*/
	}
	
	@Override
	public BookingRequestView getRequest(Integer driverId) {
		BookingRequestView bookingRequests = bookingDao.getBookingRequests(driverId);
		return bookingRequests;
	}
}

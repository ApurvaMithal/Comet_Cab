package service;
import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.DriverDAO;
import dao.DriverDAOImpl;
import exception.ApplicationException;
import model.Booking;
import model.Cab;
import model.CabType;
import model.CardDetails;
import model.Driver;
import model.Location;
import views.BookingRequestView;
import views.ConfirmBookingView;

public class BookingServiceImpl implements BookingService{

	BookingDAO bookingDao = new BookingDAOImpl();
	DriverDAO driverDao = new DriverDAOImpl();

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
	public ConfirmBookingView confirmBooking(String netId, Location location, float fare, CabType cabType) {
		Driver allocatedDriver = bookingDao.allocateRide(cabType);
		boolean availability=false;
		Booking newBooking = new Booking(location, fare, cabType, netId, allocatedDriver.getDriverId());
		int id = bookingDao.saveBooking(newBooking);
		newBooking.setBookingId(id);
		boolean driverStatus=bookingDao.setDriverStatus((String.valueOf(newBooking.getBookingId())),"F","T"); 
		allocatedDriver.setAvailability(availability);
		Cab cab = new Cab();
		cab = bookingDao.fetchCabDetails(allocatedDriver.getDriverId());
		newBooking.setBookingId(id);
		newBooking.setTripStatus("R");
		return new ConfirmBookingView(allocatedDriver, newBooking, cab );
	}

		
		
	@Override
	public boolean startRide(String bookingid) {
		boolean flag = true;
		if(!bookingDao.setRideStatus(bookingid, "P", "R"))
			throw new ApplicationException("Booking Error");
		return flag;
	}

	@Override
	public boolean endRide(String bookingid) {
		System.out.println("Begin endRide");
		boolean flag = true;
		if(!bookingDao.setDriverStatus(bookingid, "T","F"))
			throw new ApplicationException("No ride is in progress");
		if(!bookingDao.makePayment(bookingid))
		    throw new ApplicationException("Payment unsuccessful");
		if(!bookingDao.setRideStatus(bookingid,"C","P"))
		    throw new ApplicationException("Booking Error");
		System.out.println("End endRide");
		return flag;
		}
		
	
	@Override
	public BookingRequestView getRequest(Integer driverId) {
		BookingRequestView bookingRequests = bookingDao.getBookingRequests(driverId);
		return bookingRequests;
	}
}

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
		System.out.println("Booking ID before DAO  : "+allocatedDriver.getDriverId());
		/*
		 * if(allocatedDriver == null) { return trip; }
		 */
		Booking newBooking = new Booking(location, fare, cabType, netId, allocatedDriver.getDriverId());
		int id = bookingDao.saveBooking(newBooking);
		newBooking.setBookingId(id);
		// return new Trip(allocatedDriver, newBooking);
		Cab cab = new Cab();
		cab = bookingDao.fetchCabDetails(allocatedDriver.getDriverId());
		newBooking.setBookingId(id);
		newBooking.setTripStatus("R");
		System.out.println("netid:"+newBooking.getNetId());
		System.out.println("fare:"+newBooking.getFare());
		System.out.println("trip status:"+newBooking.getTripStatus());
		System.out.println("prick up:"+newBooking.getLocation().getPickUpLocation());
		System.out.println("drop off:"+newBooking.getLocation().getDropOffLocation());
		System.out.println("cab model:"+cab.getModel());
		
		
		return new ConfirmBookingView(allocatedDriver, newBooking, cab );
		//return null;
	}

		
		
	@Override
	public void startRide(String bookingid) {
		driverDao.setDriverStatus(bookingid, "F");
		bookingDao.setRideStatus(bookingid, "P");
	}

	@Override
	public void endRide(String bookingid) {
		driverDao.setDriverStatus(bookingid, "T");
		driverDao.makePayment(bookingid);
		bookingDao.setRideStatus(bookingid,"C"); 
		}
		
	
	@Override
	public BookingRequestView getRequest(Integer driverId) {
		BookingRequestView bookingRequests = bookingDao.getBookingRequests(driverId);
		return bookingRequests;
	}
}

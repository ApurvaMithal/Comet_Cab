package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.ServletException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import exception.ApplicationException;
import model.Booking;
import model.Cab;
import model.CabType;
import model.Customer;
import model.Driver;
import model.Location;
import model.Place;

public class BookingDAOImplTest {

	static BookingDAO bookDao;
		
	@BeforeClass
	public static void begin() {
		bookDao= new BookingDAOImpl();
		
	}
	@Test
	public void makeBookingTest() throws ServletException {
		bookDao= new BookingDAOImpl();
		Location location=new Location(Place.AIRPORT, Place.SSN);
		Customer c = new Customer("Apurva", "", "Mithal", "axm174531", "apurva", "4699297012");
		assertNotNull(bookDao.reserveBooking(c, location, CabType.SEDAN));
	}
	@Test(expected = ServletException.class)
	public void makeBookingTest_FareNotSuff() throws ServletException {
		Customer c = new Customer("Komal", "", "Gupta", "kxg177777", "komal", "9988776655");
		Location location=new Location(Place.AIRPORT, Place.UTD);
		bookDao.reserveBooking(c, location, CabType.SEDAN);
	}
	@Test(expected = ServletException.class)
	public void makeBookingTest_CabNotAvailable() throws ServletException {
		Customer c = new Customer("Apurva", "", "Mithal", "axm174531", "apurva", "4699297012");
		Location location=new Location(Place.AIRPORT, Place.DPS);
		bookDao.reserveBooking(c, location, CabType.SUV);
	}

	@Test
	public void confirmBookingTest() throws ApplicationException {
		BookingDAO bookDao= new BookingDAOImpl();
		Location location=new Location(Place.UTD, Place.SSN);
		Float fare=(float)2.00;
		Cab cab = new Cab("TX8712","Kia",CabType.SEDAN);// ------>1
		Driver driver = new Driver("Pete","","Davies", "5", "pete", "767555111","TX8712",cab); // Make sure license no in 1 is only given here
		
		Customer c = new Customer("Apurva", "", "Mithal", "axm174531", "apurva", "4699297012");
		Booking book = new Booking(location,fare,CabType.SEDAN,c,driver);
		book.setBookingId(62);
		//book.setTripStatus("B");

		Booking viewObj = bookDao.confirmBooking(book);
		assertEquals(viewObj.getTripStatus(),"B");
	}
	
	@Test
	public void cancelBookingTest_PositiveCase() throws ApplicationException {
		Location location=new Location(Place.UTD, Place.SSN);
		Cab cab = new Cab("TX8712", "Kia", CabType.SEDAN);
		Driver d = new Driver("Pete","","Davies","5", "pete", "767555111", "TX8712", cab);
		Customer c = new Customer("Apurva", "", "Mithal", "axm174531", "apurva", "4699297012");
		Booking booking = new Booking(location, (float)2.40, CabType.SEDAN, c, d );
		booking.setBookingId(62);
		assertEquals(bookDao.cancelBooking(booking),true);
		
}
	@Test
	public void cancelBookingTest_NegativeCase() throws ApplicationException {
		Location location=new Location(Place.UTD, Place.SSN);
		Cab cab = new Cab("TX8712", "Kia", CabType.SEDAN);
		Driver d = new Driver("Pete","","Davies","5", "pete", "767555111", "TX8712", cab);
		Customer c = new Customer("Apurva", "", "Mithal", "axm174531", "apurva", "4699297012");
		Booking booking = new Booking(location, (float)2.40, CabType.SEDAN, c, d );
		booking.setBookingId(9);
		assertEquals(bookDao.cancelBooking(booking),false);
		
}
	
	public void tearDown()
	{
		bookDao=null;
	}
	
}
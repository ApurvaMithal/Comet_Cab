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
	static Customer c;
	
	@BeforeClass
	public static void begin() {
		bookDao= new BookingDAOImpl();
		c = new Customer("SAI SANDHYA", "REDDY", "LAKSHMIPATHY", "sandhya", "1234", "9728060453");
	}
	@Test
	public void makeBookingTest() throws ServletException {
		Location location=new Location(Place.AIRPORT, Place.SSN);
		assertNotNull(bookDao.reserveBooking(c, location, CabType.SUV));
	}
	@Test(expected = ServletException.class)
	public void makeBookingTest_FareNotSuff() throws ServletException {
		Location location=new Location(Place.AIRPORT, Place.UTD);
		bookDao.reserveBooking(c, location, CabType.SEDAN);
	}
	@Test(expected = ServletException.class)
	public void makeBookingTest_CabNotAvailable() throws ServletException {
		Location location=new Location(Place.AIRPORT, Place.DPS);
		bookDao.reserveBooking(c, location, CabType.HATCHBACK);
	}
	
	@Test
	public void confirmBookingTest() throws ApplicationException {
		BookingDAO bookDao= new BookingDAOImpl();
		Location location=new Location(Place.UTD, Place.SSN);
		Float fare=(float)2.00;
		Customer customer= new Customer("Pallavi", "", "Sinha", "pxs170003", "pallavi","8045018625");
		Cab cab = new Cab("TX6777","Toyota",CabType.SUV);// ------>1
		Driver driver = new Driver("Tim","N","Williams", "2", "0", "565441111","TX6777",cab); // Make sure license no in 1 is only given here
		

		Booking book = new Booking(location,fare,CabType.SUV,customer,driver);
		book.setBookingId(44);
		//book.setTripStatus("B");

		Booking viewObj = bookDao.confirmBooking(book);
		
		assertEquals(viewObj.getLocation().getPickUpLocation().name(),"UTD");
		assertEquals(viewObj.getLocation().getDropOffLocation().name(),"SSN");
		assertEquals(viewObj.getCustomer().getNetId(),"pxs170003");
		assertEquals((viewObj.getBookingId()),book.getBookingId());
		System.out.println("in test - " +viewObj.getDriver().isAvailability());
		//assertEquals(viewObj.getDriver().isAvailability(),"U");
		assertEquals(viewObj.getCabType().name(),"SUV");
		System.out.println("in test - " +viewObj.getDriver().isAvailability());
		//assertEquals(viewObj.getTripStatus(),"B");
	}
	
	@Test
	public void cancelBookingTest_PositiveCase() throws ApplicationException {
		Location location=new Location(Place.UTD, Place.SSN);
		Cab cab = new Cab("TX8712", "Kia", CabType.SEDAN);
		Driver d = new Driver("Pete",null,"Davies","5", "0", "767555111", "TX8712", cab);
		Booking booking = new Booking(location, (float)2.40, CabType.SEDAN, c, d );
		booking.setBookingId(3);
		assertEquals(bookDao.cancelBooking(booking),true);
		
}
	@Test
	public void cancelBookingTest_NegativeCase() throws ApplicationException {
		Location location=new Location(Place.UTD, Place.SSN);
		Cab cab = new Cab("TX8712", "Kia", CabType.SEDAN);
		Driver d = new Driver("Pete",null,"Davies","5", "0", "767555111", "TX8712", cab);
		Booking booking = new Booking(location, (float)2.40, CabType.SEDAN, c, d );
		booking.setBookingId(9);
		assertEquals(bookDao.cancelBooking(booking),false);
		
}
	@AfterClass
	public void tearDown()
	{
		c=null;
		bookDao=null;
	}
	
}
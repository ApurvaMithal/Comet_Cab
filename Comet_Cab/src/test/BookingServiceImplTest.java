package test;
/*import javax.servlet.ServletException;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.CabType;
import model.Location;
import model.Place;
import service.BookingService;
import service.BookingServiceImpl;
import views.BookingRequestView;

public class BookingServiceImplTest {

	static BookingService bookingService;

	@BeforeClass
	public static void begin() {
		bookingService = new BookingServiceImpl();
	}

	@Test
	public void makeBookingTest() throws ServletException {
		Location location=new Location(Place.AIRPORT, Place.SSN);
		assertNotNull(bookingService.makeBooking("svx1234", location, CabType.SUV));
	}
	@Test(expected = ServletException.class)
	public void makeBookingTest_FareNotSuff() throws ServletException {
		Location location=new Location(Place.AIRPORT, Place.UTD);
		bookingService.makeBooking("sxb1234", location, CabType.SEDAN);
	}
	@Test(expected = ServletException.class)
	public void makeBookingTest_CabNotAvailable() throws ServletException {
		Location location=new Location(Place.AIRPORT, Place.DPS);
		bookingService.makeBooking("sxb1234", location, CabType.HATCHBACK);
	}
	@Test
	public void getRequestTest() {
		BookingRequestView requestView = bookingService.getRequest(1);
		assertEquals(1,requestView.getBooking().getBookingId());
		assertEquals("firstname",requestView.getCustomer().getFirstName());
		assertEquals("9999999999",requestView.getCustomer().getPhoneNo());
	}
	@Test
	public void getRequestTest_Null() {
		BookingRequestView requestView = bookingService.getRequest(1);//not present
		assertNull(requestView.getBooking());
		assertNull(requestView.getCustomer());
	}

}
*/

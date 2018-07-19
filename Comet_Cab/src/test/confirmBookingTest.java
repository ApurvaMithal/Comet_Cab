package test;
/*
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exception.ApplicationException;
import model.CabType;
import model.Location;
import model.Place;
import service.BookingService;
import service.BookingServiceImpl;
import views.ConfirmBookingView;


public class confirmBookingTest {

	static BookingService bookingService;

	@BeforeClass
	public static void begin() {
		bookingService = new BookingServiceImpl();
	}
	
	@Test
	public void confirmBookingTest() throws ApplicationException {
		Location location=new Location(Place.UTD, Place.SSN);

		ConfirmBookingView viewObj = bookingService.confirmBooking("a", location, (float)3.0, CabType.HATCHBACK);
		
		assertEquals(viewObj.getBooking().getNetId(),"a");
		assertEquals(viewObj.getBooking().getLocation().getPickUpLocation().name(),"UTD");
		assertEquals(viewObj.getBooking().getLocation().getDropOffLocation().name(),"SSN");
		assertNotNull((viewObj.getBooking().getBookingId()));
		System.out.println("in test - " +viewObj.getDriver().isAvailability());
		assertEquals(viewObj.getDriver().isAvailability(),false);
		assertEquals(viewObj.getCab().getCabType().name(),"HATCHBACK");
		assertEquals(viewObj.getBooking().getTripStatus(),"R");
	}
	
	@Test
	public void confirmBookingNullTest() throws ApplicationException {
		Location location=new Location(Place.UTD, Place.SSN);

		ConfirmBookingView viewObj = bookingService.confirmBooking("test", location, (float)3.0, CabType.HATCHBACK);

		assertNotNull((viewObj.getBooking().getBookingId()));
	}

}
*/
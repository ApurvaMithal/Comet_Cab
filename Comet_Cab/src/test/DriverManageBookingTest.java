package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import service.BookingService;
import service.BookingServiceImpl;
public class DriverManageBookingTest {

	static BookingService bookingService;

	@BeforeClass
	public static void begin() {
		bookingService = new BookingServiceImpl();
	}
	

	@Test
	public void startRideTest()
	{
		assertEquals(bookingService.startRide("2"),true);
	}
	
	@Test
	public void startRideNullTest()
	{
		assertEquals(bookingService.startRide("1000"),true);
	}
	
	
	@Test
	public void endRideTest()
	{
		assertEquals(bookingService.endRide("2"),true);
				 
	}
	
	@Test
	public void endRideNullTest()
	{
		assertEquals(bookingService.endRide("1000"),true);
		 
	}

}

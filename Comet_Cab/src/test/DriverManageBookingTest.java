package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import model.Booking;
import model.Customer;
import dao.BookingDAO;
import dao.BookingDAOImpl;

import exception.ApplicationException;

public class DriverManageBookingTest {

	@BeforeClass
	public static void begin() {
		BookingDAO bookDao= new BookingDAOImpl();
	}
	

	@Test
	public void getBookingRequestsTest() throws ApplicationException
	{
		BookingDAO bookDao= new BookingDAOImpl();
	    Booking viewObj = new Booking();
   		viewObj= bookDao.getBookingRequests((int)4);
	    assertEquals(viewObj.getBookingId(),17);
	    assertEquals(viewObj.getCustomer().getFirstName(),"a" );
	    assertEquals(viewObj.getCustomer().getLastName(),"a" );
	    assertEquals(viewObj.getLocation().getPickUpLocation().name(),"DPS"  );
	    assertEquals(viewObj.getLocation().getDropOffLocation().name() ,"AIRPORT"  );
		assertEquals(viewObj.getCustomer().getPhoneNo(),"a");
	}	

	
	@Test
	public void endRideTest() throws ApplicationException
	{   
		BookingDAO bookDao= new BookingDAOImpl();
		assertEquals(bookDao.endRide("17"),true);
				 
	}
	
	
	}
	


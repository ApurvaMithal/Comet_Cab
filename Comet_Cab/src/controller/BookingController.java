package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CabType;
import model.Location;

import service.BookingService;
import service.BookingServiceImpl;
import model.Place;
import model.Trip;

/**
 * Servlet implementation class Login
 */
@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingController() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submitType = request.getParameter("submit");
		float fare;
		String res = "";
		Trip trip = new Trip();
		if (submitType.equals("estimateFare")) {
			String pickLoc = request.getParameter("pick");
			System.out.println("pickup " + pickLoc);
			String dropLoc = request.getParameter("drop");
			System.out.println("drop " + dropLoc);
			String cab = request.getParameter("cab");
			System.out.println("cab " + cab);
			String netId = request.getParameter("netId");
			System.out.println("netID " + netId);
			BookingService bookingService = new BookingServiceImpl();
			CabType cabType = CabType.valueOf(cab.trim());
			Location location = new Location(Place.valueOf(pickLoc.trim()), Place.valueOf(dropLoc.trim()));
		//	fare = bookingService.estimateFare(location, cabType);
			fare=0;
			res = String.valueOf(fare);
			response.setContentType("application/text");
			response.getWriter().print(res);
		} else if (submitType.equals("makeBooking")) {
			String pickLoc = request.getParameter("pick");
			System.out.println("pickup " + pickLoc);
			String dropLoc = request.getParameter("drop");
			System.out.println("drop " + dropLoc);
			String cab = request.getParameter("cab");
			System.out.println("cab " + cab);
			String netId = request.getParameter("netId");
			System.out.println("netID " + netId);
			String estfare = request.getParameter("fare");
			System.out.println("fare " + estfare);
			BookingService bookingService = new BookingServiceImpl();
			CabType cabType = CabType.valueOf(cab.trim());
			Location location = new Location(Place.valueOf(pickLoc.trim()), Place.valueOf(dropLoc.trim()));
			bookingService.confirmBooking(netId, location, Float.valueOf(estfare), cabType);
			
			response.setContentType("application/text");
			String tripDetails="";
		/*	if(trip==null) {
				tripDetails = "Currently "+ cab + " is not available. Please choose another Cab Type or try after sometime. ";
			}
			else {
				tripDetails = "Booking Confirmed!!!</br>";
				tripDetails+= "Booking Id: "+trip.getBook().getBookingId()+"</br>";
				tripDetails += "Driver Name: "+ trip.getDriver().getFirstName() + " "+ trip.getDriver().getLastName()+"</br>";
				tripDetails += "Cab Number: "+ trip.getDriver().getLicenseNo()+"</br>";
				tripDetails += "Your Cab will arrive in 5 minutes.\n";
			}
			*/
			System.out.println(tripDetails);
			response.getWriter().print(tripDetails);
	}

	}
}

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
/**
 * Servlet implementation class Login
 */
@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BookingController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pickLoc = request.getParameter("pick");
		System.out.println("pickup "+pickLoc);
		String dropLoc = request.getParameter("drop");
		System.out.println("drop "+dropLoc);
		String cab = request.getParameter("cab");
		System.out.println("cab "+cab);
		String netId = request.getParameter("netId");
		System.out.println("netID "+netId);
	
		float fare;
		BookingService bookingService = new BookingServiceImpl();
		CabType cabType = CabType.valueOf(cab.trim());
		Location location = new Location(Place.valueOf(pickLoc.trim()),
				Place.valueOf(dropLoc.trim()));
		fare = bookingService.estimateFare(location, cabType);  
	/*	request.setAttribute("estimatedFare", fare);
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	*/	
		String res = "Estimated Fare Is: "+fare;
		response.setContentType("application/text");
		response.getWriter().print(res); 
	
	}
}


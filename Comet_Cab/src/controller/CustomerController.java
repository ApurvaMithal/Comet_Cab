package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Booking;
import model.CabType;
import model.Customer;
import model.Location;
import model.Place;
import service.BookingService;
import service.BookingServiceImpl;
import domain.login.CustomerDao;
import domain.login.CustomerDaoImpl;
import domain.login.CustomerLogin;

/**
 * Servlet implementation class Login
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CustomerController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String submitType = request.getParameter("submit");
		
		if(submitType.equals("login") || submitType.equals("register")) {
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			CustomerLogin login = new CustomerLogin(username, pass);
			CustomerDao customerDao = new CustomerDaoImpl();
			Customer c = customerDao.validateCustomer(login);
			if(submitType.equals("login") && c!=null && c.getFirstName()!=null){
				request.setAttribute("message", "Hello "+c.getFirstName());
				request.setAttribute("netId", c.getNetId());
				request.getRequestDispatcher("customerWelcome.jsp").forward(request, response);
			}else if(submitType.equals("register")){
				c.setFirstName(request.getParameter("firstName"));
				c.setMiddleName(request.getParameter("middleName"));
				c.setLastName(request.getParameter("lastName"));
				c.setPhoneNo(request.getParameter("phoneNo"));
				c.setNetId(request.getParameter("username"));
				c.setPassword(request.getParameter("password"));
				customerDao.register(c);
				request.setAttribute("successMessage", "Registration done, please login!");
				request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "Data Not Found! Please register!");
				request.getRequestDispatcher("customerRegister.jsp").forward(request, response);
			}
			
		}
		else if (submitType.equals("makeBooking")) {
			String pickLoc = request.getParameter("pick");
			String dropLoc = request.getParameter("drop");
			String cab = request.getParameter("cab");
			String netId = request.getParameter("netId");
			CabType cabType = CabType.valueOf(cab.trim());
			Location location = new Location(Place.valueOf(pickLoc.trim()), Place.valueOf(dropLoc.trim()));
			BookingService bookingService = new BookingServiceImpl();
			float fare = bookingService.makeBooking(netId, location, cabType);
			String res = String.valueOf(fare);
			response.setContentType("application/text");
			response.getWriter().print(res);
		} else if (submitType.equals("confirmBooking")) {
			String pickLoc = request.getParameter("pick");
			String dropLoc = request.getParameter("drop");
			String cab = request.getParameter("cab");
			String netId = request.getParameter("netId");
			String estfare = request.getParameter("fare");
			CabType cabType = CabType.valueOf(cab.trim());
			Location location = new Location(Place.valueOf(pickLoc.trim()), Place.valueOf(dropLoc.trim()));
			BookingService bookingService = new BookingServiceImpl();
			Booking book = bookingService.confirmBooking(netId, location, Float.valueOf(estfare), cabType);
			
			String bookingDetails="";
			bookingDetails = "Booking Confirmed!!!</br>";
		/*	bookingDetails+= "Booking Id: "+book.getBookingId()+"</br>";
			bookingDetails += "Driver Name: "+ book.getFirstName() + " "+ trip.getDriver().getLastName()+"</br>";
			bookingDetails += "Cab Number: "+ book.getLicenseNo()+"</br>";
			bookingDetails += "Your Cab will arrive in 5 minutes.\n";
		*/	response.setContentType("application/text");
		
			response.getWriter().print(bookingDetails);
	}
			
	}

}

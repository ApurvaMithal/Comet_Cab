package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import dao.CustomerLogin;
import exception.ApplicationException;
import model.Booking;
import model.CabType;
import model.Customer;
import model.Location;
import model.Place;
import views.ConfirmBookingView;

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
		System.out.println("Submit "+submitType);
		if(submitType.equals("login") || submitType.equals("register")) {
			System.out.println("Inside login");
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
		else if (submitType.equals("reserveBooking")) {
			BookingDAO bokDao= new BookingDAOImpl();
			String pickLoc = request.getParameter("pick");
			String dropLoc = request.getParameter("drop");
			String cab = request.getParameter("cab");
			String netId = request.getParameter("netId");
			CabType cabType = CabType.valueOf(cab.trim());
			Location location = new Location(Place.valueOf(pickLoc.trim()), Place.valueOf(dropLoc.trim()));
			Customer customer=new Customer();
			customer.setNetId(netId);
			try {
				Booking book = bokDao.reserveBooking(customer, location, cabType);
			//	String res = String.valueOf(book.getFare());
			//	request.setAttribute("bookingId", book.getBookingId());
			//	response.setContentType("application/text");
			//	response.getWriter().print(res);

				System.out.println("book.getBookingId() "+book.getBookingId());
				String requests =  new Gson().toJson(book);
				System.out.println("ReserveBooking: "+requests);
				response.setContentType("application/json");
				response.getWriter().print(requests);
			}
			catch(ApplicationException e) {
				//String requests =  new Gson().toJson(e);
				JSONObject obj= new JSONObject();
				try {
					obj.put("error", e.getErrorMessage());
					response.setContentType("application/json");
					response.getWriter().print(obj);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (submitType.equals("confirmBooking")) {
			BookingDAO bookDao= new BookingDAOImpl();
			Integer bookingId = Integer.valueOf(request.getParameter("bookingId"));
			Booking booking=new Booking();
			booking.setBookingId(bookingId);
			Booking confirmedBooking = bookDao.confirmBooking(booking);
			String requests =  new Gson().toJson(confirmedBooking);
			System.out.println("ConfirmBooking: "+requests);
			response.setContentType("application/json");
			response.getWriter().print(requests);
	}
		 else if (submitType.equals("cancelBooking")) {
			 	BookingDAO bookDao= new BookingDAOImpl();
				Integer bookingId = Integer.valueOf(request.getParameter("bookingId"));
				Booking booking=new Booking();
				booking.setBookingId(bookingId);
				bookDao.cancelBooking(booking);
				response.setContentType("application/text");
				response.getWriter().print("BOOKING CANCELLED");
		}
			
	}

}

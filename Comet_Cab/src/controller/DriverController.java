package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.DriverDAO;
import dao.DriverDAOImpl;
import dao.DriverLogin;
import model.Booking;
import model.Driver;

/**
 * Servlet implementation class DriverController
 */
@WebServlet("/DriverController")
public class DriverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookingDAO bookingDao = new BookingDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DriverController() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String submitType = request.getParameter("submit");
		if (submitType.equals("login") || submitType.equals("register")) {
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			DriverDAO driverDao = new DriverDAOImpl();
			DriverLogin dlogin = new DriverLogin(username, pass);
			Driver d = driverDao.validateDriver(dlogin);
			if (submitType.equals("login") && d != null && d.getFirstName() != null) {
				request.setAttribute("message", "Hello " + d.getFirstName());
				request.setAttribute("driverId", d.getDriverId());
				request.getRequestDispatcher("driverWelcome.jsp").forward(request, response);
			} else if (submitType.equals("register")) {
				d.setFirstName(request.getParameter("firstName"));
				d.setMiddleName(request.getParameter("middleName"));
				d.setLastName(request.getParameter("lastName"));
				d.setPhoneNo(request.getParameter("phoneNo"));
				d.setDriverId(request.getParameter("username"));
				d.setPassword(request.getParameter("password"));
				d.setLicenseNo(request.getParameter("licenseNo"));
				driverDao.register(d);
				request.setAttribute("successMessage", "Registration done, please login!");
				request.getRequestDispatcher("driverLogin.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Data Not Found! Please register!");
				request.getRequestDispatcher("driverRegister.jsp").forward(request, response);
			}

		}

		else if (submitType.equals("getRequest")) {
			Integer driverId = Integer.valueOf(request.getParameter("driverId"));
			Booking booking = bookingDao.getBookingRequests(driverId);
			String requests = new Gson().toJson(booking);
			response.getWriter().print(requests);
		}

		else if (submitType.equals("endRide")) {
			String bookingId = request.getParameter("bookingId");
			bookingDao.endRide(bookingId);
		}
	}

}

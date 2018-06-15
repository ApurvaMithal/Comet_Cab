package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import domain.login.CustomerDao;
import domain.login.CustomerDaoImpl;
import domain.login.Login;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerDao customerDao = new CustomerDaoImpl();
		
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String submitType = request.getParameter("submit");
		Login login = new Login(username, pass);
		Customer c = customerDao.validateCustomer(login);
		System.out.println("FirstName "+ c.getFirstName()+ " LastName "+c.getLastName() + " Phone "+c.getPhoneNo());
		if(submitType.equals("login") && c!=null && c.getFirstName()!=null){
			request.setAttribute("message", "Hello "+c.getFirstName());
			request.setAttribute("netId", c.getNetId());
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else if(submitType.equals("register")){
			c.setFirstName(request.getParameter("firstName"));
			c.setMiddleName(request.getParameter("middleName"));
			c.setLastName(request.getParameter("lastName"));
			c.setPhoneNo(request.getParameter("phoneNo"));
			c.setNetId(request.getParameter("username"));
			c.setPassword(request.getParameter("password"));
			customerDao.register(c);
			request.setAttribute("successMessage", "Registration done, please login!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "Data Not Found! Please register!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

}

package com.event.eagle.controller;

import java.io.IOException;
import java.util.List;import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.eagle.dao.UserDaoImpl;
import com.event.eagle.dto.User;

/**
 * Servlet implementation class PeopleServlet
 */
@WebServlet("/employ")
public class EmployServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<User> userData = new UserDaoImpl().findAll();
		request.setAttribute("userData", userData);

		request.getRequestDispatcher("/employee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		if (action.equals("add")) {

			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String role = request.getParameter("role");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			User user_data = new User();
			user_data.setEmail(email);
			user_data.setFirstname(firstname);
			user_data.setLastname(lastname);
			user_data.setRole(Integer.parseInt(role));
			user_data.setEmail(email);
			user_data.setPassword(password);

			UserDaoImpl user_dataDaoImpl = new UserDaoImpl();
			user_dataDaoImpl.create(user_data);
			request.setAttribute("activetab", "2");
			request.setAttribute("message", "Employee Added successfully");

		}

		else if (action.equals("getperson")) {
			String id = request.getParameter("id");
			UserDaoImpl user_dataDaoImpl = new UserDaoImpl();
			request.setAttribute("editdata", user_dataDaoImpl.read(Integer.parseInt(id)));
			request.setAttribute("activetab", "3");
		}

		else if (action.equals("edit")) {

			request.setAttribute("activetab", "3");

			String id = request.getParameter("id");

			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String role = request.getParameter("role");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserDaoImpl user_dataDaoImpl = new UserDaoImpl();

			if (user_dataDaoImpl.read(Integer.parseInt(id)) == null) {

				request.setAttribute("message", "Incorrect Employee id");

			} else {

				User user_data = new User();
				user_data.setEmail(email);
				user_data.setFirstname(firstname);
				user_data.setLastname(lastname);
				user_data.setRole(Integer.parseInt(role));
				user_data.setId(Integer.parseInt(id));
				user_data.setEmail(email);
				user_data.setPassword(password);
				user_dataDaoImpl.update(user_data);
				request.setAttribute("message", "Employee edited successfully");
			}

		}

		else if (action.equals("delete")) {
			
			request.setAttribute("activetab", "4");
			String id = request.getParameter("id");
			UserDaoImpl user_dataDaoImpl = new UserDaoImpl();
			User user_data = user_dataDaoImpl.read(Integer.parseInt(id));
			user_dataDaoImpl.delete(user_data);
//			if (user_data != null) {
//				user_data.setActive(0);
//				user_dataDaoImpl.update(user_data);
//			} else {
//				request.setAttribute("message", "Invalid employee id");
//			}
			request.setAttribute("message", "Employee deleted successfully");


		}

		doGet(request, response);

	}

}

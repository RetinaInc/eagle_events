package com.event.eagle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.eagle.dao.EventOrganizerDaoImpl;
import com.event.eagle.dao.EventOrganizerDaoImpl;
import com.event.eagle.dto.Eventorganizer;
import com.event.eagle.dto.Eventorganizer;

/**
 * Servlet implementation class EventOrganizerServlet
 */
@WebServlet("/eventOrganizer")
public class EventOrganizerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventOrganizerServlet() {
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
		List<Eventorganizer> userData = new EventOrganizerDaoImpl().findAll();
		request.setAttribute("userData", userData);

		request.getRequestDispatcher("/eventorganizer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equals("add")) {

			String hostname = request.getParameter("hostname");
			String hostphone = request.getParameter("hostphone");

			Eventorganizer user_data = new Eventorganizer();
			user_data.setHostname(hostname);
			user_data.setHostphone(hostphone);

			EventOrganizerDaoImpl eventOrganizerDaoImpl = new EventOrganizerDaoImpl();
			eventOrganizerDaoImpl.create(user_data);
			request.setAttribute("activetab", "2");
			request.setAttribute("message", "Eventorganizer Added successfully");

		}

		else if (action.equals("getperson")) {
			String id = request.getParameter("id");
			EventOrganizerDaoImpl eventOrganizerDaoImpl = new EventOrganizerDaoImpl();
			request.setAttribute("editdata", eventOrganizerDaoImpl.read(Integer.parseInt(id)));
			request.setAttribute("activetab", "3");
		}

		else if (action.equals("edit")) {

			request.setAttribute("activetab", "3");

			String id = request.getParameter("id");

			String hostname = request.getParameter("hostname");
			String hostphone = request.getParameter("hostphone");

			EventOrganizerDaoImpl eventOrganizerDaoImpl = new EventOrganizerDaoImpl();

			if (eventOrganizerDaoImpl.read(Integer.parseInt(id)) == null) {

				request.setAttribute("message", "Incorrect Eventorganizer id");

			} else {

				Eventorganizer user_data = new Eventorganizer();
				user_data.setHostname(hostname);
				user_data.setHostphone(hostphone);
				user_data.setId(Integer.parseInt(id));

				eventOrganizerDaoImpl.update(user_data);
				request.setAttribute("message", "Eventorganizer edited successfully");
			}

		}

		else if (action.equals("delete")) {
			request.setAttribute("activetab", "4");
			String id = request.getParameter("id");
			EventOrganizerDaoImpl eventOrganizerDaoImpl = new EventOrganizerDaoImpl();
			Eventorganizer user_data = eventOrganizerDaoImpl.read(Integer.parseInt(id));
			if (user_data != null) {
				eventOrganizerDaoImpl.delete(user_data);
				request.setAttribute("message", "Eventorganizer deleted successfully");
			} else {
				request.setAttribute("message", "Invalid Eventorganizer id");
			}

		}

		doGet(request, response);

	}

}

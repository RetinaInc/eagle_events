package com.event.eagle.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.eagle.dao.EventDaoImpl;
import com.event.eagle.dao.EventOrganizerDaoImpl;
import com.event.eagle.dto.Event;
import com.event.eagle.dto.Eventorganizer;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/event")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventServlet() {
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
		List<Eventorganizer> eventOrganizers = new EventOrganizerDaoImpl().findAll();
		request.setAttribute("eventOrganizers", eventOrganizers);

		List<Event> events = new EventDaoImpl().findAll();
		request.setAttribute("userData", events);

		request.getRequestDispatcher("/event.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equals("add")) {

			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String organizerid = request.getParameter("organizerid");
			String dateparam = request.getParameter("date");
			System.out.println(dateparam);
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateparam);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String numberofguests = request.getParameter("numberofguests");
			String tablescount = request.getParameter("tablescount");
			String chairspertable = request.getParameter("chairspertable");

			Event user_data = new Event();
			user_data.setName(name);
			user_data.setDescription(description);
			Eventorganizer eventorganizer = new Eventorganizer();
			eventorganizer.setId(Integer.parseInt(organizerid));
			user_data.setEventorganizer(eventorganizer);
			System.out.println("****"+date);
			user_data.setDate(date);
			user_data.setChairspertable(Integer.parseInt(chairspertable));

			Event event = new Event();
			new EventDaoImpl().create(user_data);
			request.setAttribute("activetab", "2");
			request.setAttribute("message", "Event Added successfully");

		}

		else if (action.equals("getperson")) {
			String id = request.getParameter("eventid");
			EventDaoImpl eventDaoImpl = new EventDaoImpl();
			request.setAttribute("editdata", eventDaoImpl.read(Integer.parseInt(id)));
			request.setAttribute("activetab", "3");
		}

		else if (action.equals("edit")) {

			request.setAttribute("activetab", "3");

			String id = request.getParameter("id");

			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String organizerid = request.getParameter("organizerid");
			String dateparam = request.getParameter("date");
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateparam);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String numberofguests = request.getParameter("numberofguests");
			String tablescount = request.getParameter("tablescount");
			String chairspertable = request.getParameter("chairspertable");

			EventOrganizerDaoImpl eventOrganizerDaoImpl = new EventOrganizerDaoImpl();

			if (eventOrganizerDaoImpl.read(Integer.parseInt(id)) == null) {

				request.setAttribute("message", "Incorrect Event id");

			} else {

				Event user_data = new Event();
				user_data.setName(name);
				user_data.setDescription(description);
				Eventorganizer eventorganizer = new Eventorganizer();
				eventorganizer.setId(Integer.parseInt(organizerid));
				user_data.setEventorganizer(eventorganizer);
				user_data.setDate(date);
				user_data.setChairspertable(Integer.parseInt(chairspertable));
				user_data.setId(Integer.parseInt(id));

				new EventDaoImpl().update(user_data);
				request.setAttribute("message", "Event edited successfully");
			}

		}

		else if (action.equals("delete")) {
			request.setAttribute("activetab", "4");
			String id = request.getParameter("id");
			EventDaoImpl eventDaoImpl = new EventDaoImpl();
			Event user_data = eventDaoImpl.read(Integer.parseInt(id));
			if (user_data != null) {
				eventDaoImpl.delete(user_data);
				request.setAttribute("message", "Event deleted successfully");
			} else {
				request.setAttribute("message", "Invalid Event id");
			}

		}

		doGet(request, response);
	}

}

package com.event.eagle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.eagle.dao.EventOrganizerDaoImpl;
import com.event.eagle.dao.GuestDaoImpl;
import com.event.eagle.dto.SeatingOrder;
import com.event.eagle.dto.Eventorganizer;
import com.event.eagle.dto.Guest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class ManageGuestsServlet
 */
@WebServlet("/manageguests")
public class ManageGuestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageGuestsServlet() {
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

		request.getRequestDispatcher("/manageguests.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Guest> userData = new GuestDaoImpl().getGuests(Integer.parseInt(request.getParameter("eventid")));
		request.setAttribute("userData", userData);
		
		JsonArray result = (JsonArray) new Gson().toJsonTree(userData, new TypeToken<List<Guest>>() {
		}.getType());

		response.getWriter().write(result.toString());
	}

}

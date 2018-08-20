package com.event.eagle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.eagle.dto.SeatingOrder;
import com.event.eagle.service.GeneticAlgorithmForSeating;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class PrintCardsServlet
 */
@WebServlet("/printcards")
public class PrintCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrintCardsServlet() {
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
		request.getRequestDispatcher("/printcards.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		GeneticAlgorithmForSeating geneticAlgorithmForSeating = new GeneticAlgorithmForSeating();
		List<SeatingOrder> eventSeatings = geneticAlgorithmForSeating.evaluation(
				Integer.parseInt(request.getParameter("eventid")));
		JsonArray result = (JsonArray) new Gson().toJsonTree(eventSeatings, new TypeToken<List<SeatingOrder>>() {
		}.getType());

		response.getWriter().write(result.toString());	}

}

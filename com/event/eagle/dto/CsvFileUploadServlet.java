package com.event.eagle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.event.eagle.dao.EventDaoImpl;
import com.event.eagle.dao.EventOrganizerDaoImpl;
import com.event.eagle.dao.GuestDaoImpl;
import com.event.eagle.dao.UserDaoImpl;
import com.event.eagle.dto.Event;
import com.event.eagle.dto.Eventorganizer;
import com.event.eagle.dto.User;
import com.event.eagle.service.GuestFileReader;

/**
 * Servlet implementation class CsvFileUploadServlet
 */
@WebServlet("/csvfileupload")
public class CsvFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CsvFileUploadServlet() {
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
		List<Event> eventData = new EventDaoImpl().findAll();
		request.setAttribute("eventData", eventData);

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

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			Event user_data = new Event();

			String eventid = "";
			InputStream inputStream = null;
			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();

					if (fieldName.equals("name"))
						user_data.setName(fieldValue);

					if (fieldName.equals("description"))
						user_data.setDescription(fieldValue);

					if (fieldName.equals("organizerid")) {
						Eventorganizer eventorganizer = new Eventorganizer();
						eventorganizer.setId(Integer.parseInt(fieldValue));
						user_data.setEventorganizer(eventorganizer);
					}

					if (fieldName.equals("date")) {
						Date date = null;
						try {
							date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fieldValue);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						user_data.setDate(date);
					}
					if (fieldName.equals("chairspertable"))
						user_data.setChairspertable(Integer.parseInt(fieldValue));
					
					if (fieldName.equals("id"))
						
						user_data.setId(Integer.parseInt(fieldValue));
				} else {
					String fieldName = item.getFieldName();

					String fileName = FilenameUtils.getName(item.getName());

					inputStream = item.getInputStream();

				}
			}

			Event event = new Event();

			
			event = new EventDaoImpl().update(user_data);
			eventid = "" + event.getId();
			new GuestDaoImpl().deleteGuests(Integer.parseInt(eventid));

			System.out.println("******" + eventid);
			GuestFileReader fileParser = new GuestFileReader(inputStream, Integer.parseInt(eventid));
			fileParser.guestFileParser();
			request.setAttribute("activetab", "2");
			request.setAttribute("message", "Event Added successfully");

		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}
		doGet(request, response);
	}

}

package com.event.eagle.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.event.eagle.dao.GuestDaoImpl;
import com.event.eagle.dao.SeatingconstraintsDaoImpl;
import com.event.eagle.dto.Event;
import com.event.eagle.dto.Guest;
import com.event.eagle.dto.Seatingconstraints;

public class GuestFileReader {

	InputStream is;
	private int eventid;

	public GuestFileReader(InputStream is, int eventid) {

		this.is = is;
		this.eventid = eventid;
	}

	public void guestFileParser() throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String data;
		boolean firstline = true;
		String[] headerarray = null;

		List<Guest> guestsList = new ArrayList<>();

		while ((data = reader.readLine()) != null) {

			if (firstline) {
				firstline = false;
				headerarray = data.split(",");
				continue;
			}
			String[] array = data.split(",");

			Guest guest = new Guest();

			guest.setGuestnumber(Integer.parseInt(array[0]));
			guest.setFirstname(array[1]);
			guest.setLastname(array[2]);
			Event event = new Event();
			event.setId(eventid);
			guest.setEvent(event);

			List<Seatingconstraints> guestpreferences = new ArrayList<>();
			GuestDaoImpl guestDao = new GuestDaoImpl();

			guestDao.create(guest);

			for (int i = 3; i < array.length; i++) {

				Seatingconstraints seatConstraint = new Seatingconstraints();
				Event guestEvent = new Event();
				guestEvent.setId(eventid);
				seatConstraint.setEvent(guestEvent);

				seatConstraint.setGuestOne(Integer.parseInt(array[0]));

				seatConstraint.setGuesttwo(Integer.parseInt(array[i]));

				if (headerarray[i].trim().equals("Same Table")) {
					seatConstraint.setCansit(1);
				} else {
					seatConstraint.setCansit(0);

				}
				guestpreferences.add(seatConstraint);
				// SeatingconstraintsDaoImpl seatingconstraintsDaoImpl = new
				// SeatingconstraintsDaoImpl();
				// seatingconstraintsDaoImpl.create(seatConstraint);

			}

			guest.setGuestpreferences(guestpreferences);
			guestsList.add(guest);

		}
		for (Guest guest : guestsList) {
			List<Seatingconstraints> seatingconstraints = guest.getGuestpreferences();
			for (Seatingconstraints seatingconstraints2 : seatingconstraints) {
				SeatingconstraintsDaoImpl seatingconstraintsDaoImpl = new SeatingconstraintsDaoImpl();
				seatingconstraintsDaoImpl.create(seatingconstraints2);
			}
		}
		reader.close();
	}
}

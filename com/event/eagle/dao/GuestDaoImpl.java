package com.event.eagle.dao;

import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.event.eagle.dto.Event;
import com.event.eagle.dto.Guest;
import com.event.eagle.dto.GuestInterests;

public class GuestDaoImpl extends GenericDaoJpaImpl<Guest, Integer> {

	public List<Guest> getGuests(int eventid) {

		List<Guest> guests = entityManager.createQuery("SELECT u FROM Guest u WHERE u.event.id=:eventid")
				.setParameter("eventid", eventid).getResultList();

		return guests;
	}

	public Map<Integer, GuestInterests> readGuestswithPreferences(int eventid) {

		Map<Integer, GuestInterests> map = new HashMap<>();
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DB.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM eagleevents.guest where eventid=" + eventid);
		
			System.out.println("****");
			while (rs.next()) {

				Guest guest = new Guest();
				Event event = new Event();
				event.setId(eventid);
				guest.setEvent(event);
				guest.setFirstname(rs.getString("firstname"));
				guest.setLastname(rs.getString("lastname"));
				guest.setGuestnumber(rs.getInt("guestnumber"));
				System.out.println(guest.getFirstname());

				int guestid = rs.getInt("guestnumber");

				Statement st1 = con.createStatement();

				GuestInterests guestDetails = new GuestInterests();
				ResultSet rs1 = st1.executeQuery("SELECT * FROM eagleevents.seatingconstraints where eventid=" + eventid
						+ " and guestone=" + guestid);
				List<Integer> hatedsittig = new ArrayList<>();
				List<Integer> lovesitting = new ArrayList<>();

				while (rs1.next()) {
					short preferred = rs1.getShort("cansit");
					if (preferred == 1) {
						lovesitting.add(rs1.getInt("guesttwo"));

					} else {
						hatedsittig.add(rs1.getInt("guesttwo"));
					}
				}
				
				

				guestDetails.setHatedsittig(hatedsittig);
				guestDetails.setLovesitting(lovesitting);
				guestDetails.setGuest(guest);

				map.put(guestid, guestDetails);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;

	}
}

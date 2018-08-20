package com.event.eagle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.event.eagle.dao.EventDaoImpl;
import com.event.eagle.dao.GuestDaoImpl;
import com.event.eagle.dto.Event;
import com.event.eagle.dto.SeatingOrder;
import com.event.eagle.dto.EventSeatingAlphaSorting;
import com.event.eagle.dto.Guest;
import com.event.eagle.dto.GuestInterests;
import com.event.eagle.dto.Table;

public class GeneticAlgorithmForSeating {

	Map<Integer, GuestInterests> guestInterests;
	List<Table> tables = new ArrayList<>();
	Event currentEvent;

	public Table assignNewTable() {

		Table table = new Table();
		table.setTableno((tables.size() + 1));
		table.setTableCapacity(currentEvent.getChairspertable());
		tables.add(table);
		return table;
	}

	public void setup(int eventid) {

		GuestDaoImpl guestDao = new GuestDaoImpl();
		guestInterests = guestDao.readGuestswithPreferences(eventid);

		EventDaoImpl eventDao = new EventDaoImpl();
		currentEvent = eventDao.read(eventid);

		assignNewTable();
	}

	private boolean isTableFull(Table table) {
		return table.getGuestnumbers().size() >= table.getTableCapacity();
	}

	public void crossOver() {
		guestInterests.entrySet().stream().forEach(entry -> {
			GuestInterests nextGuest = entry.getValue();
			if (nextGuest.getTableassisned() == 0) {

				findNextTable(nextGuest);

			}
		});

		guestInterests.entrySet().stream().forEach(entry -> {

			GuestInterests nextGuest = entry.getValue();
			if (nextGuest.getTableassisned() == 0) {

				parentLoop: for (Table nextTable : tables) {
					if (isTableFull(nextTable)) {
						continue parentLoop;
					}
					if (!isTableFull(nextTable)) {
						int canfit = fitnessCalculation(nextGuest, nextTable);
						if (canfit == -1) {
							continue parentLoop;
						}
						addGuesttoTable(nextGuest.getGuest().getGuestnumber(), nextTable);
						List<Integer> sameTalbeSitting = nextGuest.getWillings();
						for (Integer integer : sameTalbeSitting) {
							addGuesttoTable(integer, nextTable);
						}
					}
				}

				if (nextGuest.getTableassisned() == 0) {

					Table newTable = assignNewTable();
					addGuesttoTable(nextGuest.getGuest().getGuestnumber(), newTable);
				}
			}

		});
	}

	public void setTableAssigned(int guestnumber) {

		guestInterests.get(guestnumber).setTableassisned(1);
	}

	public void addGuesttoTable(int guestno, Table table) {

		if (guestInterests.get(guestno).getTableassisned() == 0) {
			if (!isTableFull(table)) {

				int canfit = fitnessCalculation(guestInterests.get(guestno), table);

				if (canfit != -1) {
					guestInterests.get(guestno).getGuest().setTableNumber(table.getTableno());

					table.addGuest(guestno);
					setTableAssigned(guestno);
				}

			}
		}
	}

	public void findNextTable(GuestInterests guest) {

		int capacity = 1 + guest.getWillings().size();

		for (Table table : tables) {

			int maxCapacity = table.getTableCapacity() - table.getGuestnumbers().size();
			if (maxCapacity >= capacity) {

				addGuesttoTable(guest.getGuest().getGuestnumber(), table);

				List<Integer> sameTalbeSitting = guest.getWillings();
				for (Integer integer : sameTalbeSitting) {

					addGuesttoTable(integer, table);

				}

				break;

			}

		}
	}

	public int fitnessCalculation(GuestInterests guest, Table table) {

		int guestid = guest.getGuest().getGuestnumber();
		List<Integer> guestnumbers = table.getGuestnumbers();

		for (Integer guestnumber : guestnumbers) {

			List<Integer> notSameTableSitting = guestInterests.get(guestnumber).getNotWillings();

			if (notSameTableSitting.contains(guestid)) {
				return -1;
			}

			List<Integer> guesthatedsittig = guestInterests.get(guestid).getNotWillings();

			if (guesthatedsittig.contains(guestnumber)) {
				return -1;
			}
		}

		return 0;
	}

	public List<EventSeatingAlphaSorting> evaluationCrossOver() {

		List<EventSeatingAlphaSorting> eventSeatings = new ArrayList<>();

		List<GuestInterests> guestslist = new ArrayList<GuestInterests>(guestInterests.values());
		HashMap<String, List<Guest>> guestsMap = new HashMap<String, List<Guest>>();
		guestslist.forEach(guestDetails -> {

			String alphabet = guestDetails.getGuest().getFirstname().substring(0, 1).toUpperCase();
			if (!guestsMap.containsKey(alphabet)) {
				List<Guest> list = new ArrayList<Guest>();
				list.add(guestDetails.getGuest());

				guestsMap.put(alphabet, list);
			} else {
				guestsMap.get(alphabet).add(guestDetails.getGuest());
			}

		});

		guestsMap.entrySet().stream().forEach(entry -> {
			EventSeatingAlphaSorting eventSeating = new EventSeatingAlphaSorting();
			eventSeating.setAlphabet(entry.getKey());
			eventSeating.setGuests(entry.getValue());
			eventSeatings.add(eventSeating);
		});

		return eventSeatings;
	}

	public List<SeatingOrder> evaluation(int eventid) {
		setup(eventid);
		crossOver();
		List<SeatingOrder> eventSeatings = new ArrayList<>();
		tables.forEach(table -> {

			List<Integer> guestNumbers = table.getGuestnumbers();

			SeatingOrder eventSeating = new SeatingOrder();
			eventSeating.setTableno(table.getTableno());
			List<Guest> guests = new ArrayList<>();
			guestNumbers.forEach(guestNumber -> guests.add(guestInterests.get(guestNumber).getGuest()));

			eventSeating.setGuests(guests);
			eventSeatings.add(eventSeating);

		});

		return eventSeatings;

	}

}

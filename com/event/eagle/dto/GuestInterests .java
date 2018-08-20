package com.event.eagle.dto;

import java.util.List;

public class GuestInterests {

	private Guest guest;

	private List<Integer> willings;
	private List<Integer> notWillings;

	private int tableassisned = 0;

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	
	public List<Integer> getWillings() {
		return willings;
	}

	public void setWillings(List<Integer> willings) {
		this.willings = willings;
	}

	public List<Integer> getNotWillings() {
		return notWillings;
	}

	public void setNotWillings(List<Integer> notWillings) {
		this.notWillings = notWillings;
	}

	public int getTableassisned() {
		return tableassisned;
	}

	public void setTableassisned(int tableassisned) {
		this.tableassisned = tableassisned;
	}

}

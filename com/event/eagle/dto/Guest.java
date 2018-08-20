package com.event.eagle.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "guest")
public class Guest {

	@Id
	@Column(name = "id")
	private int id;

	private int guestnumber;

	@ManyToOne
	@JoinColumn(name = "eventid")
	private Event event;

	private String firstname;
	private String lastname;

	@Transient
	private int tableNumber;
	
	@Transient
	List<Seatingconstraints> guestpreferences;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGuestnumber() {
		return guestnumber;
	}

	public void setGuestnumber(int guestnumber) {
		this.guestnumber = guestnumber;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public List<Seatingconstraints> getGuestpreferences() {
		return guestpreferences;
	}

	public void setGuestpreferences(List<Seatingconstraints> guestpreferences) {
		this.guestpreferences = guestpreferences;
	}

	
}

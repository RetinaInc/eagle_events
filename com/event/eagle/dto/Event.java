package com.event.eagle.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@Column(name = "id")
	private int id;

	private String name;
	private String description;

	@ManyToOne
	@JoinColumn(name = "organizerid")
	private Eventorganizer eventorganizer;

	private int plannerid;
	@Column(name="eventdate")
	private Date date;
	private int numberofguests;
	private int tablescount;
	private int chairspertable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Eventorganizer getEventorganizer() {
		return eventorganizer;
	}

	public void setEventorganizer(Eventorganizer eventorganizer) {
		this.eventorganizer = eventorganizer;
	}

	public int getPlannerid() {
		return plannerid;
	}

	public void setPlannerid(int plannerid) {
		this.plannerid = plannerid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberofguests() {
		return numberofguests;
	}

	public void setNumberofguests(int numberofguests) {
		this.numberofguests = numberofguests;
	}

	public int getTablescount() {
		return tablescount;
	}

	public void setTablescount(int tablescount) {
		this.tablescount = tablescount;
	}

	public int getChairspertable() {
		return chairspertable;
	}

	public void setChairspertable(int chairspertable) {
		this.chairspertable = chairspertable;
	}

}

package com.event.eagle.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventorganizer")
public class Eventorganizer {

	@Id
	@Column(name = "id")
	private int id;

	private String hostname;
	private String hostphone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getHostphone() {
		return hostphone;
	}

	public void setHostphone(String hostphone) {
		this.hostphone = hostphone;
	}

}

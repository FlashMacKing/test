package com.akampany.api.Map;

import java.util.Date;

import com.akampany.api.User.AppUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
@Entity
@Data
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Latitude")
	private float lat;
	
	@Column(name="Longitude")
	private float lng;
	//@Temporal(TemporalType.DATE)
	//@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="Event_Date")
	private String event_date;
	
	@Column(name="Event_Price")
	private Double price;
	
	@OneToOne
	private AppUser user;
	

}

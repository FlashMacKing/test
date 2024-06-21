package com.akampany.api.Map;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

	private Long id;

	private String description;

	private float lat;

	private float lng;

	private String event_date;

	private Double price;

	private Long userId;
	
	private String firstName;
	
	private String lastName;
	
	

}

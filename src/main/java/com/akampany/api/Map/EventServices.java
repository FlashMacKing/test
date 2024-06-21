package com.akampany.api.Map;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;

@Service
public class EventServices {
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private AppUserRepository userRepo;

	public Event addEvent(Event event) {
		
		eventRepo.save(event);

		return event;
	}

	public Event updateEvent(EventRequest eventReq) {
		Event event = new Event();
		AppUser user = userRepo.findUserById(eventReq.getUserId());
		
		event.setId(eventReq.getId());
		event.setLat(eventReq.getLat());
		event.setLng(eventReq.getLng());
		event.setDescription(eventReq.getDescription());
		event.setPrice(eventReq.getPrice());
		event.setEvent_date(eventReq.getEvent_date());
		event.setUser(user);
		eventRepo.save(event);
		return event;
	}

	public void deleteEventById(Long eventId) {

		eventRepo.deleteById(eventId);
	}

	public Event getEventExistById(Long eventId) {

		return eventRepo.findEventExistById(eventId);
	}
	
	
	public Optional<Event> getEventById(Long eventId) {

		return eventRepo.findEventById(eventId);
	}

	public Optional<List<Event>> getEventsByUserId(String userId) {
		return null;
	}
	
	public List<EventResponse> getAllEvents() {
	    List<Event> events =eventRepo.findAll(); 
	    List<EventResponse> eventResponses = new ArrayList<>();
	    for (Event event : events) {
	        EventResponse eventResponse = new EventResponse();
	        eventResponse.setId(event.getId());
	        eventResponse.setDescription(event.getDescription());
	        eventResponse.setLat(event.getLat());
	        eventResponse.setLng(event.getLng());
	        eventResponse.setEvent_date(event.getEvent_date());
	        eventResponse.setPrice(event.getPrice());
	        eventResponse.setUserId(event.getUser().getId());
	        eventResponse.setFirstName(event.getUser().getFirstname());
	        eventResponse.setLastName(event.getUser().getLastname());

	        eventResponses.add(eventResponse);
	    }

	    return eventResponses;
	}
	
	
	

	

}

package com.akampany.api.Map;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;

@RestController
@RequestMapping("/events")
public class MapController {
    @Autowired
    private EventServices eventServices;
    
    @Autowired
    private AppUserRepository userRepo;
    
    @Autowired
    private EventRepository eventRepo;
    
    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody EventRequest request) {
    	AppUser user = userRepo.findUserById(request.getUserId());
    	Event event= new Event();
    	event.setDescription(request.getDescription());
    	event.setLat(request.getLat());
    	event.setLng(request.getLng());
    	event.setEvent_date(request.getEvent_date());
    	event.setPrice(request.getPrice());
    	event.setUser(user);
        Event addedEvent = eventServices.addEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEvent);
    }

    @PutMapping("/update")
    public void updateEvent(@RequestBody EventRequest eventRequest) {
        eventServices.updateEvent(eventRequest);
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        Optional<Event> existingEvent = eventServices.getEventById(eventId);
        if (existingEvent.isPresent()) {
            eventServices.deleteEventById(eventId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{eventId}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long eventId) {
        Event event = eventServices.getEventExistById(eventId);
        
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
        	
        	
            return ResponseEntity.ok(eventResponse);
        }
    
    
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll(){
    	return ResponseEntity.ok().body(eventServices.getAllEvents());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Event>> getEventsByUserId(@PathVariable String userId) {
        Optional<List<Event>> userEvents = eventServices.getEventsByUserId(userId);
        if (userEvents.isPresent()) {
            return ResponseEntity.ok(userEvents.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    //TODOO
    //Get All events is the most important
}


package com.akampany.api.Map;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
	
	Event findEventExistById(Long id);
	
	Optional<Event> findEventById(Long id);

	

}

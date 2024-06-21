package com.akampany.api.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
    
    
   
}

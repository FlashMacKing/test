package com.akampany.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@SpringBootApplication
public class AkampanyBackendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AkampanyBackendApplication.class, args);
		
	}
}

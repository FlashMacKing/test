package com.akampany.api.Security;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akampany.api.Exceptions.UserAlreadyExistsException;
import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {
	
	@Autowired
	private  AppUserRepository repository;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private  JwtService jwtService;

	public RegisterResponse register(AppUser request) throws IOException {

		if (repository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("User already exists");
		}
			
		var user = AppUser.builder().firstname(request.getFirstname()).lastname(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).birthday(request.getBirthday()).hobbies(request.getHobbies())
				.role(request.getRole()).build();

		repository.save(user);

		return new RegisterResponse("REGISTERED SUCCESSFULLY");
	}
	
	
	
	public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	    	
	    	UserDetailsService userService = new UserDetailsServiceImpl(repository);
	    	AppUser appUser = repository.findByEmail(userEmail);
			UserDetails user = userService.loadUserByUsername(userEmail);
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user,appUser );
	        var authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	}
	

}

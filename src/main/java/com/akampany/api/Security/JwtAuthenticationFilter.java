package com.akampany.api.Security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private AppUserRepository userRepo;
	
	
	private AuthenticationManager authenticationManager;
	
	
	private JwtService jwtService;
	
	
	
	
	public JwtAuthenticationFilter(AppUserRepository userRepo, AuthenticationManager authenticationManager,
			JwtService jwtService) {
		super();
		this.userRepo = userRepo;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		
		String userName = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    AppUser appUser =  userRepo.findByEmail(userName);
	    
	    if(appUser==null) {
	    	throw new UsernameNotFoundException("USER NOT FOUND");
	    }
	    
	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
        
	    return authenticationManager.authenticate(authenticationToken);
        
	    
	}

	@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication authResult) throws IOException, ServletException {

			UserDetails user = (UserDetails) authResult.getPrincipal();
			AppUser appUser = userRepo.findByEmail(user.getUsername());

			String jwtAccessToken = jwtService.generateToken(user,appUser);
			String jwtRefreshToken = jwtService.generateRefreshToken(user,appUser);

			Map<String, String> idToken = new HashMap<>();
			idToken.put("AccessToken", jwtAccessToken);
			idToken.put("RefreshToken", jwtRefreshToken);
			response.setContentType("application/json");
			new ObjectMapper().writeValue(response.getOutputStream(), idToken);

		}
	}

package com.akampany.api.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.akampany.api.User.AppUserRepository;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  
  private  JwtService jwtService;
  
  private AppUserRepository userRepo;
  
  
  public JwtAuthorizationFilter(JwtService jwtService, AppUserRepository userRepo) {
	super();
	this.jwtService = jwtService;
	this.userRepo = userRepo;
}


@Override
  protected void doFilterInternal(
       HttpServletRequest request,
       HttpServletResponse response,
       FilterChain filterChain
	) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {

			jwt = authHeader.substring(7);
			userEmail = jwtService.extractUsername(jwt);


			UserDetailsService userService = new UserDetailsServiceImpl(userRepo);

			UserDetails user = userService.loadUserByUsername(userEmail);

			if (jwtService.isTokenValid(jwt, user)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				filterChain.doFilter(request, response);
			} else {

				throw new JwtException("INVALID TOKEN");
			}

		} else {

			filterChain.doFilter(request, response);

		}

	}
}

package com.akampany.api.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;

@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {
	
	AppUserRepository userRepo;
	
	
	
	public UserDetailsServiceImpl(AppUserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			
			AppUser user = userRepo.findByEmail(username);
			if (user== null) {
				throw new UsernameNotFoundException("Username not found");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			//Grant User authority for all users
			authorities.add(new SimpleGrantedAuthority(user.getRole().name()));		
			
			User userDetails = new User(user.getEmail(), user.getPassword(),authorities);
		    
		    return userDetails;
			
	}
	
	
	
}

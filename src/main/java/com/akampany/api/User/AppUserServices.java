package com.akampany.api.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppUserServices {

	@Autowired
	AppUserRepository userRepo;

	// getUserById
	public Optional<AppUser> getUserById(Long id) {

		return userRepo.findById(id);
	};

	// getUserByEmail
	public AppUser getUserByEmail(String email) {

		return userRepo.findByEmail(email);
	};

	// getAllUsers
	public List<AppUser> getAllUsers() {

		return userRepo.findAll();
	};

	// AddUser
	public void addUser(AppUser user) {

		userRepo.save(user);
	};

	// UpdateUser
	public void updateUser(AppUser user) {
		
		userRepo.updateUser(user);
	};

	// DeleteUser
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	};
}

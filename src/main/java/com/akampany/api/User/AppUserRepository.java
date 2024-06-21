package com.akampany.api.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	@Transactional
	@Modifying
    @Query("UPDATE AppUser u SET u.firstname = :#{#user.firstname}, u.lastname = :#{#user.lastname}, u.email = :#{#user.email}, u.password = :#{#user.password}, u.birthday = :#{#user.birthday},  u.hobbies = :#{#user.hobbies} WHERE u.id = :#{#user.id}")
    void updateUser(AppUser user);
	
	public AppUser findUserById(Long id);
	
	public AppUser findByEmail(String email);
	
	
	Boolean existsByEmail(String email);

}

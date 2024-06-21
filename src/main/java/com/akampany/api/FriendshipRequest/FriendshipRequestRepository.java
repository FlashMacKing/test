package com.akampany.api.FriendshipRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akampany.api.User.AppUser;

@Repository
public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {
	
    List<FriendshipRequest> findByReceiverIdAndStatus(Long receiverId, String status);
    
    Optional<List<FriendshipRequest>> findByStatus(String status);
    
    
}


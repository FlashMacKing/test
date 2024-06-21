package com.akampany.api.FriendshipRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akampany.api.User.AppUser;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FriendshipRequestService {
	
	@Autowired
    private FriendshipRequestRepository friendshipRequestRepository;

    

    public FriendshipRequest sendFriendshipRequest(AppUser sender, AppUser receiver) {
        FriendshipRequest request = new FriendshipRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setStatus("pending");
        Date date=new Date();
        request.setRequestDate(date);
        return friendshipRequestRepository.save(request);
    }

    public FriendshipRequest respondToFriendshipRequest(Long requestId, String response) {
        FriendshipRequest request = friendshipRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Friendship request not found"));

        if (!request.getStatus().equals("pending")) {
            throw new IllegalArgumentException("Friendship request is not pending");
        }

        if (response.equalsIgnoreCase("accept")) {
            request.setStatus("accepted");
        } else if (response.equalsIgnoreCase("decline")) {
            request.setStatus("declined");
        } else {
            throw new IllegalArgumentException("Invalid response");
        }

        return friendshipRequestRepository.save(request);
    }

    public List<FriendshipRequest> getPendingFriendshipRequests(AppUser user) {
        return friendshipRequestRepository.findByReceiverIdAndStatus(user.getId(), "pending");
    }
    
    public List<AppUser> getAcceptedFriendshipRequests (Long userId){
    	List<AppUser> friends = new ArrayList<>();
    	List<FriendshipRequest> requests = friendshipRequestRepository.findByReceiverIdAndStatus(userId, "accepted");
    	
    		for (FriendshipRequest request : requests) {
    			AppUser friend = request.getSender();
    			friends.add(friend);
    			
    		}
    		return friends;
    	}
    	
    }


package com.akampany.api.FriendshipRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;

@RestController
@RequestMapping("/friendship")
public class FriendshipRequestController {
	
	@Autowired
    private  FriendshipRequestService friendshipRequestService;
    @Autowired
    private AppUserRepository userRepo;

    

    @PostMapping("/send")
    public ResponseEntity<String> sendFriendshipRequest(
            @RequestParam("senderId") Long senderId,
            @RequestParam("receiverId") Long receiverId) {

        AppUser sender = userRepo.findUserById(senderId);
        AppUser receiver = userRepo.findUserById(receiverId);

        friendshipRequestService.sendFriendshipRequest(sender, receiver);

        return ResponseEntity.ok("Friendship request sent");
    }

    @PostMapping("/respond")
    public ResponseEntity<String> respondToFriendshipRequest(
            @RequestParam("requestId") Long requestId,
            @RequestParam("response") String response) {

        friendshipRequestService.respondToFriendshipRequest(requestId, response);

        return ResponseEntity.ok("Friendship request responded");
    }

    @GetMapping("/pending")
    public List<FriendshipRequest> getPendingFriendshipRequests(@RequestParam("userId") Long userId) {
        AppUser user = userRepo.findUserById(userId);

        return friendshipRequestService.getPendingFriendshipRequests(user);
    }
    
    @GetMapping("/getFriends")
    public ResponseEntity<List<AppUser>> getFriends(@RequestParam("userId")Long userId ){
    	
    	return ResponseEntity.ok().body(friendshipRequestService.getAcceptedFriendshipRequests(userId));
    }
}


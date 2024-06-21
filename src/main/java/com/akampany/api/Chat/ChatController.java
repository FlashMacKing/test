package com.akampany.api.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akampany.api.User.AppUserRepository;

import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
    private MessageRepository messageRepository;
	
	@Autowired
	private AppUserRepository userRepo;

    
    

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageRequest messageReq) {
    	Message message = new Message();
        message.setTimestamp(LocalDateTime.now());
        message.setSender(userRepo.findUserById(messageReq.getSenderId()));
        message.setContent(messageReq.getContent());
        message.setChatRoomId(messageReq.getChatRoomId());
        messageRepository.save(message);
    }

    @GetMapping("/messages/{chatRoomId}")
    public List<Message> getMessagesByChatRoom(@PathVariable String chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId);
    }
    
    
    @GetMapping("/last-message/{chatRoomId}")
    public ResponseEntity<String> getLastMessage(@PathVariable String chatRoomId) {
       
        Message lastMessage = messageRepository.findFirstByChatRoomIdOrderByTimestampDesc(chatRoomId);

        if (lastMessage != null) {
            return ResponseEntity.ok(lastMessage.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No message found for the chatroom.");
        }
    }
}


package com.akampany.api.Chat;

import java.time.LocalDateTime;

import com.akampany.api.User.AppUser;

import lombok.Data;


@Data
public class MessageRequest {
	
	
	private Long senderId;
    private String content;
    
    private String chatRoomId;

}

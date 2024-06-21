package com.akampany.api.Chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
List<Message> findByChatRoomId(String chatRoomId);
Message findFirstByChatRoomIdOrderByTimestampDesc(String chatRoomId);

}

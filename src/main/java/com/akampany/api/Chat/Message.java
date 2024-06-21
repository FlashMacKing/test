package com.akampany.api.Chat;

import java.time.LocalDateTime;
import java.util.Date;

import com.akampany.api.User.AppUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private AppUser sender;
    private String content;
    private LocalDateTime timestamp;
    private String chatRoomId;

    // Constructors, getters, and setters
}



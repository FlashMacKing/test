package com.akampany.api.FriendshipRequest;

import java.util.Date;

import com.akampany.api.User.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "friendship_requests")
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    
    private AppUser sender;
    
    @ManyToOne
    
    private AppUser receiver;
    
    @Column(name="request_status")
    private String status; // "pending", "accepted", "declined"
    
    @Column(name="request_date")
    private Date requestDate;
}

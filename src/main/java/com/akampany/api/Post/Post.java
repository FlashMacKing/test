package com.akampany.api.Post;

import java.util.Date;

import com.akampany.api.User.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="Post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="passion")
	private String passion;
	
	@Column(name="price")
	private double price;
	
	@Column(name="city")
	private String city;
	
	@OneToOne
	private AppUser user;
}

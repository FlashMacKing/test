package com.akampany.api.User;

import java.util.List;

import com.akampany.api.Map.Event;
import com.akampany.api.Post.Post;
import com.akampany.api.Security.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstname;

	private String lastname;

	private String email;

	private String password;
	@Temporal(TemporalType.TIME.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String birthday;
	
	@Column(name="bio")
	private String bio;

	@JsonDeserialize(using = HobbiesDeserializer.class)
	private List<String> hobbies;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	@OneToMany
	@JoinTable(name = "user_posts", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> posts;

	@OneToMany
	@JoinTable(name = "user_events", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> events;

}

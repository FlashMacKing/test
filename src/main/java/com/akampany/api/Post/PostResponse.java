package com.akampany.api.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	private Long id;
	
	private String description;
	
	private String passion;
	
	private double price;
	
	private String city;
		
	private	Long creatorId;
	
	private String username;
	
	
}

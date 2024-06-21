package com.akampany.api.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	
	
	
	
	@Transactional
    Post save(Post post);
	
	List<Post> findByuserId(Long userId);
	
	List<Post> findByPassion(String passion);
	}

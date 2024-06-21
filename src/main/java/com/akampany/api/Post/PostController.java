package com.akampany.api.Post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService annonceService;

	@GetMapping("/user/{creatorId}")
	public ResponseEntity<List<PostResponse>> getPostsByUserId(@PathVariable Long creatorId) {

		return ResponseEntity.ok().body(annonceService.getPostsByCreatorId(creatorId));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PostResponse>> getAllAnnonces() {
		List<PostResponse> posts = annonceService.getAllPosts();

		return ResponseEntity.ok(posts);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<PostResponse> getAnnonceById(@PathVariable Long id) {
		Optional<PostResponse> post = annonceService.getPostById(id);
		return post.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/add")
	public ResponseEntity<Post> createAnnonce(@RequestBody PostRequest post) {
		Post createdPost = annonceService.createPost(post);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
	}

	@PutMapping("/update")
	public void updatePost(@RequestBody PostRequest postreq) {
		annonceService.updatePost(postreq);
	
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		annonceService.deletePost(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getByPassion")
	public ResponseEntity<List<PostResponse>> getAnnonceByPassion(@RequestParam String passion) {
		List<PostResponse> posts = annonceService.getPostByPassion(passion);
		return ResponseEntity.ok(posts);
	}
}

package com.akampany.api.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akampany.api.User.AppUser;
import com.akampany.api.User.AppUserRepository;

@Service
public class PostService {
	
	@Autowired
    private  PostRepository postRepository;
	
	@Autowired
	private AppUserRepository userRepo;
	
    public void updatePost(PostRequest postreq) {
    	Post post = new Post();
    	post.setId(postreq.getId());
    	post.setCity(postreq.getCity());
    	post.setDescription(postreq.getDescription());
    	post.setPassion(postreq.getPassion());
    	post.setPrice(postreq.getPrice());
    	AppUser user=userRepo.findUserById(postreq.getCreatorId());
    	post.setUser(user);
    	  postRepository.save(post);
    	  
    	  
    }
    
    public List<PostResponse> getPostByPassion(String passion){
    	
    	
    	List<Post> posts=  postRepository.findByPassion(passion);
        List<PostResponse> postsResList = new ArrayList<>();
        
	    for (Post post : posts) {
	    	
	    	PostResponse postRes = new PostResponse();
	    	postRes.setId(post.getId());
	    	postRes.setDescription(post.getDescription());
	    	postRes.setCity(post.getCity());
	    	postRes.setPassion(post.getPassion());
	    	postRes.setPrice(post.getPrice());
	    	postRes.setCreatorId(post.getUser().getId());
	    	postRes.setUsername(post.getUser().getFirstname()+' '+post.getUser().getLastname());
	    	//postRes.setProfilePic(post.getUser().getProfilePic());
	    	
	    	postsResList.add(postRes);
	    }
	    
	    return postsResList;
    }
    

    public List<PostResponse> getAllPosts() {
        List<Post> posts= postRepository.findAll();
        List<PostResponse> postsResList = new ArrayList<>();
        
	    for (Post post : posts) {
	    	
	    	PostResponse postRes = new PostResponse();
	    	postRes.setId(post.getId());
	    	postRes.setDescription(post.getDescription());
	    	postRes.setCity(post.getCity());
	    	postRes.setPassion(post.getPassion());
	    	postRes.setPrice(post.getPrice());
	    	postRes.setCreatorId(post.getUser().getId());
	    	postRes.setUsername(post.getUser().getFirstname()+' '+post.getUser().getLastname());
	    	//postRes.setProfilePic(post.getUser().getProfilePic());
	    	
	    	postsResList.add(postRes);
	    }
	    
	    return postsResList;
    }

    public Optional<PostResponse> getPostById(Long id) {
        Optional<Post> post= postRepository.findById(id);
        try {
        	if (post.isPresent()) {
        	      PostResponse postRes = new PostResponse();
        	      Post postEntity = post.get();
        	      
        	      postRes.setId(postEntity.getId());
        	      postRes.setDescription(postEntity.getDescription());
        	      postRes.setCity(postEntity.getCity());
        	      postRes.setPassion(postEntity.getPassion());
        	      postRes.setPrice(postEntity.getPrice());
        	      postRes.setCreatorId(postEntity.getUser().getId());
        	      postRes.setUsername(postEntity.getUser().getFirstname() + ' ' + postEntity.getUser().getLastname());
        	      //postRes.setProfilePic(postEntity.getUser().getProfilePic());
	    	
	    	return Optional.of(postRes);
        }}
        	catch(Exception e) {
        	
        
        }
        
        return Optional.empty();
        
    }

    public Post createPost(PostRequest postReq) {
    	Post post=new Post();
    	
    	post.setCity(postReq.getCity());
    	post.setDescription(postReq.getDescription());
    	post.setPassion(postReq.getPassion());
    	post.setPrice(postReq.getPrice());
    	AppUser user=userRepo.findUserById(postReq.getCreatorId());
    	post.setUser(user);
    	
        return postRepository.save(post);
    }
    
    

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    
    
    public List<PostResponse> getPostsByCreatorId(Long creatorId){
    	
    	
    	
    	List<Post> posts= postRepository.findByuserId(creatorId);
        List<PostResponse> postsResList = new ArrayList<>();
        
	    for (Post post : posts) {
	    	
	    	PostResponse postRes = new PostResponse();
	    	postRes.setId(post.getId());
	    	postRes.setDescription(post.getDescription());
	    	postRes.setCity(post.getCity());
	    	postRes.setPassion(post.getPassion());
	    	postRes.setPrice(post.getPrice());
	    	postRes.setCreatorId(post.getUser().getId());
	    	postRes.setUsername(post.getUser().getFirstname()+' '+post.getUser().getLastname());
	    	//postRes.setProfilePic(post.getUser().getProfilePic());
	    	
	    	postsResList.add(postRes);
	    }
	    
	    return postsResList;
    }
}

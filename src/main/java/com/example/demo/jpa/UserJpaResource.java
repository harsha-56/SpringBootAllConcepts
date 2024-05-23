package com.example.demo.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.user.Post;
import com.example.demo.user.User;
import com.example.demo.user.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	//private UserDaoService service;
	
	private UserRepository repository;
	
	private PostRepository postRepository;
	
//	public UserJpaResource(UserDaoService service,UserRepository repository) {
//		this.service= service;
//		this.repository= repository;
//		
//	}
	
	public UserJpaResource(UserRepository repository, PostRepository postRepository) {
	this.repository= repository;
	this.postRepository = postRepository;
	
}
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
		return repository.findAll();
		
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriveUsers(@PathVariable int id){
		Optional<User> user=  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id" +id);
		}
		EntityModel<User> entitymodel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entitymodel.add(link.withRel("all-users"));
		return entitymodel;
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUsers(@PathVariable int id){
		repository.deleteById(id);
		
		
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostsForUser(@PathVariable int id){
		Optional<User> user=  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id" +id);
		}
		
		return user.get().getPosts();	
		
	}
	
	
//	@GetMapping("/users/{id}")
//	public ResponseEntity<User> retriveUsers(@PathVariable int id){
//		User user=  service.findOne(id);
//		if(user==null) {
//			//throw new UserNotFoundException("id" +id);
//			return ResponseEntity.internalServerError().body(user);
//		}
//		return ResponseEntity.accepted().body(user);
//		
//	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		Optional<User> user=  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id" +id);
		}
		post.setUser(user.get());
		Post savedPost= postRepository.save(post);
		  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(savedPost.getId()).toUri();
			return ResponseEntity.created(location).build();
		//return user.get().getPosts();
		
		
		
		
	}

}

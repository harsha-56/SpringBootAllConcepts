package com.example.demo.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service= service;
		
	}
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return service.findAll();
		
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retriveUsers(@PathVariable int id){
		User user=  service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id" +id);
		}
		EntityModel<User> entitymodel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entitymodel.add(link.withRel("all-users"));
		return entitymodel;
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUsers(@PathVariable int id){
		service.deleteByid(id);
		
		
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
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}

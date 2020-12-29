package com.example.RestDemo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RestDemo.exception.UserNotFoundException;

@RestController
public class UserController {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> getAllUsers(){
		return service.findAll();
	}
	
	@PostMapping(path = "/user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
				
	}
	
	@GetMapping(path = "users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		
		User user = service.findUser(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+id);
		}
		
//		adding the resource the EntityModel 
		EntityModel<User> resource = EntityModel.of(user);
		
//		Creating a link to the method
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());;
		
//		adding a resource to the entity model 
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@DeleteMapping(path ="users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		
		if(user == null) {
			throw new UserNotFoundException("id does not exists - "+id);
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping(path="/internalization")
	public String goodMorning(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		
		return messageSource.getMessage("good.morning.message",null, locale);
	}
	
}

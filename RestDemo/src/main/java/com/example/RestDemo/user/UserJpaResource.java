package com.example.RestDemo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
public class UserJpaResource {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository; 
	
	@GetMapping(path = "/jpa/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping(path = "/jpa/user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
				
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<Optional<User>> getUser(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
//		adding the resource the EntityModel 
		EntityModel<Optional<User>> resource = EntityModel.of(user);
		
//		Creating a link to the method
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());;
		
//		adding a resource to the entity model 
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@DeleteMapping(path ="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);		
	}
	
	@GetMapping("/jpa/user/{id}/posts")
	public List<Posts> reteriveAllPosts(@PathVariable int id){
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		return user.get().getPost();
	}
	
	@PostMapping("/jpa/user/{id}/posts")
	public ResponseEntity<Object> createUser(@Valid @PathVariable int id,@RequestBody Posts post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
		
	}
	
	@GetMapping(path="/jpa/internalization")
	public String goodMorning(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		
		return messageSource.getMessage("good.morning.message",null, locale);
	}
	
}

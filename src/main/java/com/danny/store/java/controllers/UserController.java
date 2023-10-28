package com.danny.store.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danny.store.java.entities.AppUser;
import com.danny.store.java.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	ResponseEntity<AppUser> getCurrentUser(){
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userService.getCurrentUserDetails());
	}
	
	@GetMapping("{id}")
	ResponseEntity<AppUser> getUser(@PathVariable("id") Long id){
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userService.getUser(id));
	}
	
	@GetMapping
	ResponseEntity<List<AppUser>> getAllUsers(@RequestParam("page") Integer page){
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userService.getAllUsers(page));
	}
	
	@PutMapping
	ResponseEntity<AppUser> updateCurrentUser(@RequestBody AppUser user){
			
			return ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(userService.updateCurrentUser(user));
	}
	
	@DeleteMapping
	ResponseEntity<String> deleteCurrentUser(){
			
			return ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(userService.deleteCurrentUser());
	}

}

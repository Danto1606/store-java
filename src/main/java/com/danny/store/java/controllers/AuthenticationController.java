package com.danny.store.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danny.store.java.models.AuthenticationResponseModel;
import com.danny.store.java.models.LoginModel;
import com.danny.store.java.models.RegistrationModel;
import com.danny.store.java.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("register")
	ResponseEntity<AuthenticationResponseModel> registerUser(
			@RequestBody RegistrationModel details
	) {
		return ResponseEntity 
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(authenticationService.registerUser(details));
	}
	
	@PostMapping("login")
	ResponseEntity<AuthenticationResponseModel> loginUser(
			@RequestBody LoginModel details
	) {
		
		return ResponseEntity 
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(authenticationService.loginUser(details));
	}
}

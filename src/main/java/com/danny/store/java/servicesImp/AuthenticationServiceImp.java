package com.danny.store.java.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.danny.store.java.config.JwtService;
import com.danny.store.java.entities.AppUser;
import com.danny.store.java.models.AuthenticationResponseModel;
import com.danny.store.java.models.LoginModel;
import com.danny.store.java.models.RegistrationModel;
import com.danny.store.java.repositories.UserRepository;
import com.danny.store.java.services.AuthenticationService;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponseModel registerUser(RegistrationModel details) {
		
		AppUser user = userRepository.save(
			details
			.toUser(
				passwordEncoder.encode(details.getPassword()
			)
		));
		
		
		String jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponseModel
				.builder()
				.token(jwtToken)
				.details(user)
				.build();
	}

	@Override
	public AuthenticationResponseModel loginUser(LoginModel details) {

		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				details.getEmail(),
				details.getPassword()
			)
		);
		
		AppUser user = userRepository
				.findByEmail(details.getEmail())
				.orElseThrow( () -> new UsernameNotFoundException("user not found"));
		
		
		String jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponseModel
				.builder()
				.token(jwtToken)
				.details(user)
				.build();
	}

}

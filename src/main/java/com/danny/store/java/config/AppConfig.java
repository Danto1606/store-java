package com.danny.store.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.danny.store.java.repositories.UserRepository;

@Configuration
public class AppConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
	throws Exception {
		return config.getAuthenticationManager();	
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider =
				new DaoAuthenticationProvider(passwordEncoder());
		
		authProvider.setUserDetailsService(userDetailsService());
		
		return authProvider;		
	}
	
	@Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    } 
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return (username) -> userRepository
				.findByEmail(username)
				.orElseThrow(
					() -> new UsernameNotFoundException("user not found")
				);
	}
	
	
	
}

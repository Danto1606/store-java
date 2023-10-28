package com.danny.store.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private AuthenticationProvider authProvider;
	@Autowired
    MvcRequestMatcher.Builder mvc;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
		throws Exception {
		
		httpSecurity
		.csrf(
				csrf -> csrf.disable()
		).authorizeHttpRequests(
				authRequest -> authRequest
				.requestMatchers(mvc.pattern("/api/v1/auth/**"))
				.permitAll()
				.anyRequest()
				.authenticated()
								
								
		).sessionManagement(
				authRequest -> authRequest
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
		).authenticationProvider(authProvider)
		.addFilterBefore(
				jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
		);
		
		
		return httpSecurity.build();
	}
}

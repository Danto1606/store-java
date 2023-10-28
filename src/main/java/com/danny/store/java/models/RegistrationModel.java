package com.danny.store.java.models;

import com.danny.store.java.entities.AppUser;
import com.danny.store.java.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationModel {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public AppUser toUser(String encodedPassword) {
		
		return AppUser
				.builder()
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.password(encodedPassword)
				.role(Roles.USER)
				.build();
	}
}

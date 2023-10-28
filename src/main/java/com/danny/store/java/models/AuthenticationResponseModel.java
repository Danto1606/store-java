package com.danny.store.java.models;

import com.danny.store.java.entities.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationResponseModel {
	
	private String token;
	
	private AppUser details;
}

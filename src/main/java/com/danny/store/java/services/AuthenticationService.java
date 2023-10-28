package com.danny.store.java.services;

import com.danny.store.java.models.AuthenticationResponseModel;
import com.danny.store.java.models.LoginModel;
import com.danny.store.java.models.RegistrationModel;

public interface AuthenticationService {

	public AuthenticationResponseModel registerUser(
			RegistrationModel details
	);
	
	public AuthenticationResponseModel loginUser(
			LoginModel details
	);
}

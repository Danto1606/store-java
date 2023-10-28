package com.danny.store.java.services;

import java.util.List;

import com.danny.store.java.entities.AppUser;

public interface UserService {

	public AppUser getCurrentUserDetails();
	
	public AppUser getUser(Long id);
	
	public List<AppUser> getAllUsers(Integer PageNumber);
	
	public AppUser updateCurrentUser(AppUser user);
	
	public String deleteCurrentUser();
}

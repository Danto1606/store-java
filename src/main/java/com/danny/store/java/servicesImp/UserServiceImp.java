package com.danny.store.java.servicesImp;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.danny.store.java.entities.AppUser;
import com.danny.store.java.repositories.UserRepository;
import com.danny.store.java.services.UserService;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public AppUser getCurrentUserDetails()  {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}

	@Override
	public AppUser getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser updateCurrentUser(AppUser user) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		AppUser userDb = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
		if(Objects.nonNull(user.getFirstName())) {
			userDb.setFirstName(user.getFirstName());
		}
		
		if(Objects.nonNull(user.getLastName())) {
			userDb.setLastName(email);
		}
		
		if(Objects.nonNull(user.getEmail())) {
			userDb.setEmail(user.getEmail());
		}
		
		return userRepository.save(userDb);
	}

	@Override
	public String deleteCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Boolean exists = userRepository.existsByEmail(email);
		
		if(exists) {
			userRepository.deleteByEmail(email);
			
			return "user deleted";
		}
		
		throw new UsernameNotFoundException("User not found");
	}

	@Override
	public List<AppUser> getAllUsers(Integer pageNumber) {
		
		pageNumber = Objects.isNull(pageNumber) ? 0: pageNumber;
		
		Pageable page = PageRequest.of(
				pageNumber,
				20,
				Sort.by("firstName", "lastName", "email"));
		return userRepository.findAll(page).getContent();
	}
	

}

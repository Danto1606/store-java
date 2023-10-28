package com.danny.store.java.repositories;

import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.units.qual.N;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danny.store.java.entities.AppUser;
import com.danny.store.java.enums.Roles;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser> findByEmail(String email);
	
	List<AppUser> findAllByRole(Roles role);
	
	Boolean existsByEmail(String email);

	void deleteByEmail(String email);
}

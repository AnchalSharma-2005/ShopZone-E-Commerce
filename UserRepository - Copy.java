package com.shopzone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopzone.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	
		Optional<User>findByEmail(String Email);
}

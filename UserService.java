package com.shopzone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.entity.User;
import com.shopzone.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	//to save user records
	public User registerUser(User user) {
		return userRepo.save(user);
	}
	
	//to fetch user records
	public List<User>getAllUsers(){
		return userRepo.findAll();
	}
	//get records on basis of id
	public User getUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	//login user
	public User loginUser(String email,String password) {
		User user=userRepo.findByEmail(email).orElse(null);
		if(user==null) {
			return null;
		}
		if(!user.getPassword().equals(password)) {
			return null;
		}
		return user;
		
	}
	
	
}

package com.shopzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.dto.LoginRequest;
import com.shopzone.entity.User;
import com.shopzone.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//to save user records
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	//to fetch user record
	@GetMapping
	public List<User>getAllUsers(){
		return userService.getAllUsers();
	}
	
	//get record on basis of id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	//login user
	@PostMapping("/login")
	public User loginUser(@RequestBody LoginRequest request) {
		return userService.loginUser(request.getEmail(), request.getPassword());
	}
}

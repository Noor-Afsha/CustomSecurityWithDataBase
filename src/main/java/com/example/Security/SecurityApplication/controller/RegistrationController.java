package com.example.Security.SecurityApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Security.SecurityApplication.model.Users;
import com.example.Security.SecurityApplication.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserRepository repository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/create")
	public Users createUser(@RequestBody Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		return repository.save(users);
	}
}

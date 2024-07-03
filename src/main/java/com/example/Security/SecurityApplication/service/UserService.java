package com.example.Security.SecurityApplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Security.SecurityApplication.model.Users;
import com.example.Security.SecurityApplication.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		Optional<Users> user = userRepository.findByName(name);

		if (user.isPresent()) {

			var userObj = user.get();

			return User.builder().username(userObj.getName()).password(userObj.getPassword()).roles(userObj.getRole())
					.build();
		} else {
			throw new UsernameNotFoundException(name);
		}

	}
}

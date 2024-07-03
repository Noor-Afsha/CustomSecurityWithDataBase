package com.example.Security.SecurityApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

	@GetMapping("/home")
	public String home() {
		return "home";// html page
	}

	@GetMapping("/admin/home")
	public String handleAdmin() {
		return "admin_home";// html page
	}

	@GetMapping("/user/home")
	public String handleUser() {
		return "user_home";// html page
	}

	@GetMapping("/login")
	public String login() {
		return "custom_Login_Page";
	}
}

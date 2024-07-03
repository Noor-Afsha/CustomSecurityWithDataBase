package com.example.Security.SecurityApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Security.SecurityApplication.service.UserService;

@Configuration
public class SpringConfig {

	@Autowired
	private UserService userService;

	// This user and role is temporary based
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	//This is hard coded user and admin, for this i need to create our own service here so below is created .
//	// this interface is used to create custom user
//	public UserDetailsService userDetailsService() {
//
//		// this is also a interface to create users
//
//		UserDetails admin = User.builder().username("noor").password(passwordEncoder().encode("noor@123"))
//				.roles("ADMIN").build();
//
//		UserDetails user = User.builder().username("noori").password(passwordEncoder().encode("noor@1234"))
//				.roles("USER").build();
//
//		// InMemoryUserDetailsManager is used for store temporary data.
//		return new InMemoryUserDetailsManager(admin, user);
//
//	}

	// SecurityFilterChain is a interface, is used for giving authorization that
	// admin can access both admin and user but user can only access user page.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                    .requestMatchers("/security/home", "/security/register/**").permitAll()
	                    .requestMatchers("/security/admin/**").hasRole("ADMIN")
	                    .requestMatchers("/security/user/**").hasRole("ROLE")
	                    .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                    .loginPage("/security/login")
	                    .loginProcessingUrl("/security/login")
	                    .defaultSuccessUrl("/security/home", true)
	                    .permitAll()
	            )
	            .logout(logout -> logout
	                    .logoutUrl("/security/logout")
	                    .logoutSuccessUrl("/security/home")
	            )
	            .build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		return userService;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}
}

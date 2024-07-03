package com.example.Security.SecurityApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Security.SecurityApplication.model.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByName(String name);

}

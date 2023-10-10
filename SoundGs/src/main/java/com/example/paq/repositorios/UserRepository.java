package com.example.paq.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.paq.entidades.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	
}
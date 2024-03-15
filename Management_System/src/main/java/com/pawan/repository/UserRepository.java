package com.pawan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	User findByEmail(String email);

}

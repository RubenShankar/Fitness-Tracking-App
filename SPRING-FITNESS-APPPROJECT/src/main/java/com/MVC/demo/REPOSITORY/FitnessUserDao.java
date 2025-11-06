package com.MVC.demo.REPOSITORY;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MVC.demo.ENTITY.User;

public interface FitnessUserDao extends JpaRepository<User,String> {
	
	 Optional<User> findByName(String name);


}

package com.MVC.demo.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MVC.demo.ENTITY.User;
import com.MVC.demo.ENTITY.Workout;


public interface WorkoutDAO extends JpaRepository<Workout ,Long>{
	
	 List<Workout> findByUser(User user);   

}

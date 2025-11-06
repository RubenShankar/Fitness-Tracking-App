package com.MVC.demo.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MVC.demo.ENTITY.Goals;
import com.MVC.demo.ENTITY.User;

public interface GoalsDAO extends JpaRepository<Goals, Long>{ // takes Wrapper type of primitive datatype
         List<Goals> findByUser(User user);
}

package com.MVC.demo.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Goals {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;   // Primary key

	    private double caloriesToBurn;
	    private double caloriesBurned;

	    // Many goals → One user
	    @ManyToOne
	    @JoinColumn(name = "user_email", referencedColumnName = "email")  // foreign key mapping to User.email
	    private User user;    

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }

	    public double getCaloriesToBurn() {
	        return caloriesToBurn;
	    }
	    public void setCaloriesToBurn(double caloriesToBurn) {
	        this.caloriesToBurn = caloriesToBurn;
	    }

	    public double getCaloriesBurned() {
	        return caloriesBurned;
	    }
	    public void setCaloriesBurned(double caloriesBurned) {
	        this.caloriesBurned = caloriesBurned;
	    }

	    public User getUser() {
	        return user;
	    }
	    public void setUser(User user) {
	        this.user = user;
	    }

}

package com.MVC.demo.ENTITY;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Workout {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")   // foreign key referencing User.email
    @JsonBackReference
    private User user;
    
    
    @ManyToOne
    @JoinColumn(name = "goal_id")  // foreign key column in Workout table
    private Goals goal;

    public Goals getGoal() {
		return goal;
	}
	public void setGoal(Goals goal) {
		this.goal = goal;
	}
	
	
	
	// Getters & Setters for workout
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    
    
    // for user
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

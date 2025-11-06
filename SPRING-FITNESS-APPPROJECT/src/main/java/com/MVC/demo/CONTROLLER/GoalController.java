package com.MVC.demo.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.MVC.demo.ENTITY.Goals;
import com.MVC.demo.ENTITY.User;
import com.MVC.demo.REPOSITORY.GoalsDAO;

import jakarta.servlet.http.HttpSession;

public class GoalController {
	

	    @Autowired
	    private GoalsDAO goalsDAO;

	    // Show form to create a goal
	    @GetMapping("/goals/new")
	    public String showGoalForm(Model model) {
	        model.addAttribute("goal", new Goals());
	        return "Goals";
	    }

	    // Handle form submission
	    @PostMapping("/goals")
	    public String createGoal(@ModelAttribute("goal") Goals goal, HttpSession session, Model model) {
	        User loggedUser = (User) session.getAttribute("USERLOGGEDIN");

	        if (loggedUser != null) {
	            goal.setUser(loggedUser);
	            goalsDAO.save(goal);
	            model.addAttribute("msg", "Goal created successfully!");
	        } else {
	            model.addAttribute("msg", "Please login to create a goal!");
	        }

	        model.addAttribute("goal", new Goals());
	        return "Goals";
	    }

	    // View all goals for logged-in user
	    @GetMapping("/goals")
	    public String viewGoals(HttpSession session, Model model) {
	        User loggedUser = (User) session.getAttribute("USERLOGGEDIN");

	        if (loggedUser != null) {
	            List<Goals> userGoals = goalsDAO.findByUser(loggedUser);
	            model.addAttribute("goalsList", userGoals);
	        } else {
	            model.addAttribute("msg", "Please login to view goals!");
	        }

	        return "ViewGoals";
	    }
	}




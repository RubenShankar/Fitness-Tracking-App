package com.MVC.demo.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MVC.demo.ENTITY.Workout;
import com.MVC.demo.REPOSITORY.WorkoutDAO;


@Controller
@RequestMapping("/workout")
public class WorkoutController {
	
	@Autowired
    private WorkoutDAO wdao;

    // ==================== Redirect /workout to /workout/new ===================
    @GetMapping
    public String redirectToNewWorkout() {
        return "redirect:/workout/new";
    }

    // ==================== Show Workout Form ===================
    @GetMapping("/new")
    public String showWorkoutForm(Model model) {
        model.addAttribute("workout", new Workout());
        return "Workout"; // Thymeleaf template: Workout.html
    }

    // ==================== Save Workout (POST) ===================
    @PostMapping("/new")
    public String saveWorkout(@ModelAttribute Workout workout, Model model) {
        wdao.save(workout);
        model.addAttribute("msg", "Workout saved successfully!");
        model.addAttribute("workout", new Workout()); // Reset form
        return "Workout";
    }

    // ==================== List All Workouts ===================
    @GetMapping("/list")
    public String listWorkouts(Model model) {
        List<Workout> workouts = wdao.findAll();
        model.addAttribute("workouts", workouts);
        return "Viewworkout"; // Thymeleaf template: Viewworkout.html
    }

    // ==================== Exit to Fitness Home ===================
    @GetMapping("/exit")
    public String exit() {
        return "FitnessHome"; // Thymeleaf template: FitnessHome.html
    }
}



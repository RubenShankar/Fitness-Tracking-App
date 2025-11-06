package com.MVC.demo.CONTROLLER;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.MVC.demo.ENTITY.Goals;
import com.MVC.demo.ENTITY.User;
import com.MVC.demo.ENTITY.Workout;
import com.MVC.demo.REPOSITORY.FitnessUserDao;
import com.MVC.demo.REPOSITORY.GoalsDAO;
import com.MVC.demo.REPOSITORY.WorkoutDAO;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
    private FitnessUserDao userDao;

    @Autowired
    private WorkoutDAO workoutDao;
    
    @Autowired
    private GoalsDAO goalsDao;
    
    // ============ HOME PAGE ==================
    @GetMapping("/welcome")
    public String landingPage() {
        return "Home";
    }

    /* ==================== Registration ==================== */

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, 
                               Model model, 
                               HttpSession session) {
        if (userDao.findByName(user.getName()).isPresent()) {
            model.addAttribute("msg", "Username already exists!");
            return "Register";
        }
        userDao.save(user);
        session.setAttribute("USERLOGGEDIN", user);
        return "Login";
    }

    /* ==================== Login & Logout ==================== */

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); 
        return "Login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, 
                            HttpSession session, 
                            Model model) {
        Optional<User> optionalUser = userDao.findByName(user.getName());

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(user.getPassword())) {
            session.setAttribute("USERLOGGEDIN", optionalUser.get());
            return "FitnessHome";
        } else {
            model.addAttribute("msg", "Invalid Username or Password");
            model.addAttribute("user", new User()); // Thymeleaf ko lagi
            return "Login";
        }
    }

    @PostMapping("/logout")
    public String logoutUser(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("user",new User());
        return "Login";
    }

   
    
    
    /* ==================== Workout ==================== */

   

    @PostMapping("/workout")
    public String createWorkout(@ModelAttribute("workout") Workout workout, 
                                HttpSession session, 
                                Model model) {
        User loggedUser = (User) session.getAttribute("USERLOGGEDIN");

        if (loggedUser != null) {
            workout.setUser(loggedUser);
            workoutDao.save(workout);
            model.addAttribute("msg", "Workout Created Successfully!");
        } else {
            model.addAttribute("msg", "Please login to create a workout!");
        }

        model.addAttribute("workout", new Workout());
        return "Workout";
    }

    /* ==================== Goals ==================== */

    @GetMapping("/goals/new")
    public String showGoalForm(Model model) {
        model.addAttribute("goal", new Goals());
        return "Goals";
    }

    @PostMapping("/goals")
    public String createGoal(@ModelAttribute("goal") Goals goal, 
                             HttpSession session,
                             Model model) {
        User loggedUser = (User) session.getAttribute("USERLOGGEDIN");

        if (loggedUser != null) {
            goal.setUser(loggedUser);
            goalsDao.save(goal);
            model.addAttribute("msg", "Goal Created Successfully!");
            model.addAttribute("goal", new Goals());
        } else {
            model.addAttribute("msg", "Please login to create a goal!");
        }

        return "Goals";
    }

    @GetMapping("/goals")
    public String viewGoals(HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("USERLOGGEDIN");
        List<Goals> goalsList = goalsDao.findByUser(loggedUser);
        model.addAttribute("goalsList", goalsList);
        return "ViewGoals";
    }
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

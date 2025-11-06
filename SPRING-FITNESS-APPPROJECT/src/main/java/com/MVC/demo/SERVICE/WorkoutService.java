package com.MVC.demo.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MVC.demo.ENTITY.Workout;
import com.MVC.demo.REPOSITORY.WorkoutDAO;

@Service
public class WorkoutService {


	    @Autowired
	    private WorkoutDAO wdao;

	    // ==================== Get all workouts =======================
	    public List<Workout> getAllWorkouts() {
	        return wdao.findAll();
	    }

	    //======================== Get workout by id =======================
	    public Workout getWorkoutById(Long id) {
	        return wdao.findById(id)
	            .orElseThrow(() -> new RuntimeException("Workout not found"));
	    }

	    // ========================= Create new workout ======================
	    public Workout createWorkout(Workout workout) {
	        return wdao.save(workout);
	    }

	    // Update existing workout
	    public Workout updateWorkout(Long id, Workout updatedWorkout) {
	        Workout existingWorkout = getWorkoutById(id);
//	        existingWorkout.setName(updatedWorkout.getName());
	       
	        existingWorkout.setDuration(updatedWorkout.getDuration());
	      
	        return wdao.save(existingWorkout);
	    }

	    // Delete workout
	    public void deleteWorkout(Long id) {
	        wdao.deleteById(id);
	    }
	    
	    

	}




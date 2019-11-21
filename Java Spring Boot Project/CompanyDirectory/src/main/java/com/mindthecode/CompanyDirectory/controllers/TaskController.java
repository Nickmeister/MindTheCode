package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Task;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/tasks/{difficulty}/{numberOfEmployees")
    public ResponseEntity getTasksByDiffAndNumOfEmployees(@PathVariable String difficulty,@PathVariable int numberOfEmployees) {
        try {
            System.out.println("###Loading tasks...");
            return new ResponseEntity<>(service.getTasksByDiffAndNumOfEmployees(difficulty, numberOfEmployees), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update
     **/

    @PutMapping("/updateTask")
    public ResponseEntity updateTask(@RequestBody Task task) {
        try {
            System.out.println("###Updating task: " + task.toString());
            var response = service.saveTask(task);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating task #" + task.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTasks")
    public ResponseEntity updateTasks(@RequestBody Iterable<Task> newTasks) {
        try {
            System.out.println("###Updating multiple tasks");
            var response = service.saveTasks(newTasks);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating tasks"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.TaskResource;
import com.example.projectmanagementtool.services.TaskResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-resources")
public class TaskResourceController {

    @Autowired
    private TaskResourceService taskResourceService;

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTaskResources() {
        List<TaskResource> taskResources = taskResourceService.getAllTaskResources();
        return new ResponseEntity<>(taskResources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResource> getTaskResourceById(@PathVariable int id) {
        TaskResource taskResource = taskResourceService.getTaskResourceById(id);
        if (taskResource != null) {
            return new ResponseEntity<>(taskResource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addTaskResource(@RequestBody TaskResource taskResource) {
        taskResourceService.addTaskResource(taskResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTaskResource(@PathVariable int id, @RequestBody TaskResource taskResource) {
        // Set the ID of taskResource from the path variable
        taskResource.setId(id);
        taskResourceService.updateTaskResource(taskResource);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskResource(@PathVariable int id) {
        boolean deleted = taskResourceService.deleteTaskResource(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

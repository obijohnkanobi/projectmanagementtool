package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.TaskResource;
import com.example.projectmanagementtool.services.TaskResourceService;
import com.example.projectmanagementtool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/task-resources")
public class TaskResourceController {

    @Autowired
    private TaskResourceService taskResourceService;

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTaskResources(HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            List<TaskResource> taskResources = taskResourceService.getAllTaskResources();
            return new ResponseEntity<>(taskResources, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResource> getTaskResourceById(@PathVariable int id, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            TaskResource taskResource = taskResourceService.getTaskResourceById(id);
            if (taskResource != null) {
                return new ResponseEntity<>(taskResource, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping
    public ResponseEntity<Void> addTaskResource(@RequestBody TaskResource taskResource, HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            taskResourceService.addTaskResource(taskResource);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTaskResource(@PathVariable int id, @RequestBody TaskResource taskResource, HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            taskResource.setId(id);
            taskResourceService.updateTaskResource(taskResource);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskResource(@PathVariable int id, HttpSession session) {
        if (UserUtils.isAdmin(session)) {
            boolean deleted = taskResourceService.deleteTaskResource(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

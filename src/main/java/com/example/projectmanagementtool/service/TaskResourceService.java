package com.example.projectmanagementtool.service;

import com.example.projectmanagementtool.models.TaskResource;
import com.example.projectmanagementtool.repositories.TaskResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskResourceService {
    @Autowired
    private TaskResourceRepository taskResourceRepository;

    public List<TaskResource> getAllTaskResources() {
        return taskResourceRepository.findAll();
    }

    public TaskResource getTaskResourceById(int id) {
        return taskResourceRepository.findById(id).orElse(null);
    }

    public TaskResource saveTaskResource(TaskResource taskResource) {
        return taskResourceRepository.save(taskResource);
    }

    public void deleteTaskResource(int id) {
        taskResourceRepository.deleteById(id);
    }

    // Add other business logic methods if needed
}

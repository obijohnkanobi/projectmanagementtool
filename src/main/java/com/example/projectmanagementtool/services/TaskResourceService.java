package com.example.projectmanagementtool.services;

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
        return taskResourceRepository.fetchAll();
    }

    public TaskResource getTaskResourceById(int id) {
        return taskResourceRepository.findTaskResourceById(id);
    }

    public void addTaskResource(TaskResource taskResource) {
        taskResourceRepository.addTaskResource(taskResource);
    }

    public void updateTaskResource(TaskResource taskResource) {
        taskResourceRepository.updateTaskResource(taskResource);
    }


    public boolean deleteTaskResource(int id) {
        return taskResourceRepository.deleteTaskResource(id);
    }
}

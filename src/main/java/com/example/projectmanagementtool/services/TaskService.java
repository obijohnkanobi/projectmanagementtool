package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.models.Task;

import com.example.projectmanagementtool.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> fetchAll(){
        return taskRepository.fetchAll();
    }

    public void addTask(Task task){
        taskRepository.addTask(task);
    }

    public Task findTaskById(int id){
        return taskRepository.findTaskById(id);
    }

    public Boolean deleteTask(int id){
        return taskRepository.deleteTask(id);
    }

    public void updateTask(int id, Task task){
        taskRepository.updateTask(id, task);
    }
}

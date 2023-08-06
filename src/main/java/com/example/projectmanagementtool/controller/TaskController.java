package com.example.projectmanagementtool.controller;

import com.example.projectmanagementtool.models.Project;
import com.example.projectmanagementtool.models.Task;
import com.example.projectmanagementtool.repositories.ProjectRepository;
import com.example.projectmanagementtool.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    com.example.projectmanagementtool.services.TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/taskindex")
    public String index(Model model){
        List<Task> taskList = taskService.fetchAll();
        model.addAttribute("tasks", taskList);
        return "taskindex";
    }

    @GetMapping("/createtask")
    public String create(){
        return "createtask";
    }

    @PostMapping("/createtask")
    public String createNew(@ModelAttribute Task task){
        taskService.addTask(task);
        return "redirect:/taskindex";
    }

    @GetMapping("/viewonetask/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("task", taskService.findTaskById(id));
        return "viewonetask";
    }

    @GetMapping("/tasks/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = taskService.deleteTask(id);
        if (deleted){
            return "redirect:/taskindex";
        } else {
            return "redirect:/taskindex";
        }
    }

    @GetMapping("/updatetask/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("task", taskService.findTaskById(id));
        return "updatetask";
    }

    @PostMapping("/updatetask")
    public String updateTask(@ModelAttribute Task task){
        taskService.updateTask(task.getId(), task);
        return "redirect:/taskindex";
    }

}

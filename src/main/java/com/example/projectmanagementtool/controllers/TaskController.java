package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.Task;
import com.example.projectmanagementtool.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String index(Model model) {
        List<Task> taskList = taskService.fetchAll();
        model.addAttribute("tasks", taskList);
        return "taskindex";
    }

    @GetMapping("/create")
    public String create() {
        return "createtask";
    }

    @PostMapping
    public String createNew(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String viewOne(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "viewonetask";
    }

    @GetMapping("/delete/{id}")
    public String deleteOne(@PathVariable int id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    public String updateOne(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "updatetask";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task.getId(), task);
        return "redirect:/tasks";
    }
}

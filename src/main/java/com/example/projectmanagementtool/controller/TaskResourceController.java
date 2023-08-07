package com.example.projectmanagementtool.controller;

import com.example.projectmanagementtool.models.TaskResource;
import com.example.projectmanagementtool.service.TaskResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taskresources")
public class TaskResourceController {
    @Autowired
    private TaskResourceService taskResourceService;

    @GetMapping("/index")
    public String showTaskResources(Model model) {
        List<TaskResource> taskResources = taskResourceService.getAllTaskResources();
        model.addAttribute("taskResources", taskResources);
        return "taskresourceindex";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        TaskResource taskResource = new TaskResource();
        model.addAttribute("taskResource", taskResource);
        // Add necessary data for dropdowns (e.g., tasks, resources) if needed
        return "taskresourcecreate";
    }

    @PostMapping("/create")
    public String createTaskResource(@ModelAttribute("taskResource") TaskResource taskResource) {
        taskResourceService.saveTaskResource(taskResource);
        return "redirect:/taskresources/index";
    }

    @GetMapping("/view/{id}")
    public String viewTaskResource(@PathVariable int id, Model model) {
        TaskResource taskResource = taskResourceService.getTaskResourceById(id);
        if (taskResource == null) {
            // Handle not found case
            return "redirect:/taskresources/index";
        }
        model.addAttribute("taskResource", taskResource);
        return "taskresourceviewone";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        TaskResource taskResource = taskResourceService.getTaskResourceById(id);
        if (taskResource == null) {
            // Handle not found case
            return "redirect:/taskresources/index";
        }
        model.addAttribute("taskResource", taskResource);
        // Add necessary data for dropdowns (e.g., tasks, resources) if needed
        return "taskresourceupdateone";
    }

    @PostMapping("/update/{id}")
    public String updateTaskResource(@PathVariable int id, @ModelAttribute("taskResource") TaskResource updatedTaskResource) {
        TaskResource taskResource = taskResourceService.getTaskResourceById(id);
        if (taskResource == null) {
            // Handle not found case
            return "redirect:/taskresources/index";
        }
        taskResource.setTask(updatedTaskResource.getTask());
        taskResource.setResource(updatedTaskResource.getResource());
        taskResource.setHoursAllocated(updatedTaskResource.getHoursAllocated());
        taskResourceService.saveTaskResource(taskResource);
        return "redirect:/taskresources/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskResource(@PathVariable int id) {
        taskResourceService.deleteTaskResource(id);
        return "redirect:/taskresources/index";
    }
}


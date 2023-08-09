package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.Task;
import com.example.projectmanagementtool.services.TaskService;
import com.example.projectmanagementtool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String index(Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            List<Task> taskList = taskService.fetchAll();
            model.addAttribute("tasks", taskList);
            return "taskindex";
        }
        return "redirect:/login"; // or any other unauthorized view
    }

    @GetMapping("/create")
    public String create(HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            return "createtask";
        }
        return "redirect:/tasks";
    }

    @PostMapping
    public String createNew(@ModelAttribute Task task, HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            taskService.addTask(task);
        }
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String viewOne(@PathVariable int id, Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("task", taskService.findTaskById(id));
            return "viewonetask";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteOne(@PathVariable int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isAdmin(session)) {
            taskService.deleteTask(id);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    public String updateOne(@PathVariable int id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("task", taskService.findTaskById(id));
            return "updatetask";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        return "redirect:/tasks";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            taskService.updateTask(task.getId(), task);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/tasks";
    }
}

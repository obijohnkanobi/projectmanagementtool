package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.*;
import com.example.projectmanagementtool.repositories.ProjectRepository;
import com.example.projectmanagementtool.services.*;
import com.example.projectmanagementtool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @Autowired
    SubProjectService subprojectService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    SkillService skillService;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        // Fetching lists and adding to model
        model.addAttribute("projects", projectService.fetchAll());
        model.addAttribute("subprojects", subprojectService.fetchAll());
        model.addAttribute("tasks", taskService.fetchAll());
        model.addAttribute("resources", resourceService.fetchAll());
        model.addAttribute("skills", skillService.fetchAll());

        return "index";
    }

    @GetMapping("/projectindex")
    public String projectindex(Model model) {
        model.addAttribute("projects", projectService.fetchAll());
        return "projectindex";
    }

    @GetMapping("/createproject")
    public String create(HttpSession session) {
        if (!UserUtils.isAdmin(session) && !UserUtils.isProjectLeader(session)) {
            return "redirect:/index";
        }
        return "createproject";
    }

    @PostMapping("/createproject")
    public String createNew(@ModelAttribute Project project, HttpSession session) {
        if (!UserUtils.isAdmin(session) && !UserUtils.isProjectLeader(session)) {
            return "redirect:/index";
        }
        projectService.addProject(project);
        return "redirect:/projectindex";
    }

    @GetMapping("/viewoneproject/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.findProjectById(id));
        return "viewoneproject";
    }

    @GetMapping("/deleteproject/{id}")
    public String deleteOne(@PathVariable("id") int id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!UserUtils.isAdmin(session) && !UserUtils.isProjectLeader(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
            return "redirect:/projectindex";
        }
        boolean deleted = projectService.deleteProject(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Project deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Project could not be deleted.");
        }
        return "redirect:/projectindex";
    }

    @GetMapping("/updateproject/{id}")
    public String updateOne(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!UserUtils.isAdmin(session) && !UserUtils.isProjectLeader(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
            return "redirect:/projectindex";
        }
        model.addAttribute("project", projectService.findProjectById(id));
        return "updateproject";
    }

    @PostMapping("/updateproject")
    public String updateProject(@ModelAttribute Project project, HttpSession session) {
        if (!UserUtils.isAdmin(session) && !UserUtils.isProjectLeader(session)) {
            return "redirect:/index";
        }
        projectService.updateProject(project.getId(), project);
        return "redirect:/projectindex";
    }

    @GetMapping("/{id}")
    public String viewProject(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.findProjectById(id));
        return "projectdetails";
    }

    @GetMapping("/projects")
    public String showAllProjects(Model model) {
        model.addAttribute("projects", projectRepository.fetchAll());
        return "index";
    }
}

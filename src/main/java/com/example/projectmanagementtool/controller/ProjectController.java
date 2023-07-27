package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.Project;
import com.example.projectmanagementtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/projectindex")
    public String index(Model model){
        List<Project> projectList = projectService.fetchAll();
        model.addAttribute("project", projectList);
        return "/projectindex";
    }

    @GetMapping("/createproject")
    public String create(){
        return "createproject";
    }

    @PostMapping("/createproject")
    public String createNew(@ModelAttribute Project project){
        projectService.addProject(project);
        return "redirect:/createproject";
    }

    @GetMapping("/projects/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("project", projectService.findProjectById(id));
        return "projects/viewOne";
    }

    @GetMapping("/projects/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = projectService.deleteProject(id);
        if (deleted){
            return "redirect:/projects";
        }else {
            return "redirect:/projects";
        }
    }

    @GetMapping("/projects/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("project", projectService.findProjectById(id));
        return "projects/updateOne";
    }

    @PostMapping("/projects/updateProject")
    public String updateProject(@ModelAttribute Project project){
        projectService.updateProject(project.getId(), project);
        return "redirect:/projects";
    }
}

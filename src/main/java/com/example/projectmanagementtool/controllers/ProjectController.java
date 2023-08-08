package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.*;
import com.example.projectmanagementtool.repositories.ProjectRepository;
import com.example.projectmanagementtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.projectmanagementtool.services.TaskService;
import com.example.projectmanagementtool.services.SubProjectService;
import com.example.projectmanagementtool.services.SkillService;
import com.example.projectmanagementtool.services.ResourceService;
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
        // Check om brugeren er logget ind ved at se efter "username" i sessionen
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // Hvis brugeren ikke er logget ind, omdiriger til login-siden
            return "redirect:/login";
        }

        // Hvis brugeren er logget ind, fortsæt til index-siden som normalt
        List<Project> projectList = projectService.fetchAll();
        model.addAttribute("projects", projectList);

        List<SubProject> subprojectList = subprojectService.fetchAll();
        model.addAttribute("subprojects", subprojectList);

        List<Task> taskList = taskService.fetchAll();
        model.addAttribute("tasks", taskList);

        List<Resource> resourceList = resourceService.fetchAll();
        model.addAttribute("resources", resourceList);

        List<Skill> skillList = skillService.fetchAll();
        model.addAttribute("skills", skillList);

        return "index";
    }



    @GetMapping("/projectindex")
    public String projectindex(Model model){
        List<Project> projectList = projectService.fetchAll();
        model.addAttribute("projects", projectList);
        return "projectindex";
    }

    @GetMapping("/createproject")
    public String create(){
        return "createproject";
    }

    @PostMapping("/createproject")
    public String createNew(@ModelAttribute Project project){
        projectService.addProject(project);
        return "redirect:/projectindex";
    }

    @GetMapping("/viewoneproject/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("project", projectService.findProjectById(id));
        return "viewoneproject";
    }

    @GetMapping("/deleteproject/{id}")
    public String deleteOne(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        try {
            boolean deleted = projectService.deleteProject(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute("successMessage", "Project deleted successfully.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Project could not be deleted.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error while deleting project.");
        }
        return "redirect:/projectindex";
    }

    @GetMapping("/updateproject/{id}")
    public String updateOne(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes){
        try {
            model.addAttribute("project", projectService.findProjectById(id));
            return "updateproject";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error fetching project details.");
            return "redirect:/projectindex";
        }
    }

    @PostMapping("/updateproject")
    public String updateProject(@ModelAttribute Project project){
        projectService.updateProject(project.getId(), project);
        return "redirect:/projectindex";
    }
    @GetMapping("/{id}")
    public String viewProject(@PathVariable("id") int id, Model model) {
        try {
            Project project = projectService.findProjectById(id);
            model.addAttribute("project", project);
            return "projectdetails";
        } catch (Exception e) {
            return "error";
        }

    }
    @GetMapping("/projects")
    public String showAllProjects(Model model) {
        List<Project> projects = projectRepository.fetchAll();
        model.addAttribute("projects", projects);
        return "index";
    }

}

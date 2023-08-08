package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.SubProject;
import com.example.projectmanagementtool.services.SubProjectService; // Corrected the import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SubProjectController {

    @Autowired
    SubProjectService subProjectService;

    @GetMapping("/subprojectindex")
    public String index(Model model){
        List<SubProject> subProjectList = subProjectService.fetchAll();
        model.addAttribute("subprojects", subProjectList);
        return "subprojectindex";
    }

    @GetMapping("/createsubproject")
    public String create(){
        return "createsubproject";
    }

    @PostMapping("/createsubproject")
    public String createNew(@ModelAttribute SubProject subProject){
        subProjectService.addSubProject(subProject);
        return "redirect:/subprojectindex";
    }

    @GetMapping("/viewonesubproject/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("subproject", subProjectService.findSubProjectWithTasksById(id)); // Corrected the method name
        return "viewonesubproject";
    }

    @GetMapping("/deleteSubProject/{id}")
    public String deleteOne(@PathVariable("id") int id, Model model){ // Added Model for potential messages
        boolean deleted = subProjectService.deleteSubProject(id);
        if (!deleted){
            // Potentially add a model attribute with a message saying "SubProject could not be deleted"
            model.addAttribute("message", "SubProject could not be deleted");
        }
        return "redirect:/subprojectindex";
    }

    @GetMapping("/updateSubProject/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("subproject", subProjectService.findSubProjectWithTasksById(id)); // Corrected the method name
        return "updatesubproject";
    }

    @PostMapping("/updateSubProject")
    public String updateSubProject(@ModelAttribute SubProject subProject){
        subProjectService.updateSubProject(subProject.getId(), subProject);
        return "redirect:/subprojectindex";
    }
}

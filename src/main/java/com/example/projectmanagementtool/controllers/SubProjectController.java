package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.SubProject;
import com.example.projectmanagementtool.services.SubProjectService;
import com.example.projectmanagementtool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SubProjectController {

    @Autowired
    SubProjectService subProjectService;

    @GetMapping("/subprojectindex")
    public String index(Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            List<SubProject> subProjectList = subProjectService.fetchAll();
            model.addAttribute("subprojects", subProjectList);
            return "subprojectindex";
        }
        return "redirect:/login";  // or any other unauthorized view
    }

    @GetMapping("/createsubproject")
    public String create(HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            return "createsubproject";
        }
        return "redirect:/subprojectindex";
    }

    @PostMapping("/createsubproject")
    public String createNew(@ModelAttribute SubProject subProject, HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            subProjectService.addSubProject(subProject);
        }
        return "redirect:/subprojectindex";
    }

    @GetMapping("/viewonesubproject/{id}")
    public String viewOne(@PathVariable("id") int id, Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("subproject", subProjectService.findSubProjectWithTasksById(id));
            return "viewonesubproject";
        }
        return "redirect:/subprojectindex";
    }

    @GetMapping("/deleteSubProject/{id}")
    public String deleteOne(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isAdmin(session)) {
            boolean deleted = subProjectService.deleteSubProject(id);
            if (!deleted) {
                redirectAttributes.addFlashAttribute("errorMessage", "SubProject could not be deleted.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/subprojectindex";
    }

    @GetMapping("/updateSubProject/{id}")
    public String updateOne(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("subproject", subProjectService.findSubProjectWithTasksById(id));
            return "updatesubproject";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        return "redirect:/subprojectindex";
    }

    @PostMapping("/updateSubProject")
    public String updateSubProject(@ModelAttribute SubProject subProject, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            subProjectService.updateSubProject(subProject.getId(), subProject);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/subprojectindex";
    }
}

package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.exceptions.ResourceNotFoundException;
import com.example.projectmanagementtool.models.Resource;
import com.example.projectmanagementtool.services.ResourceService;
import com.example.projectmanagementtool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resourceindex")
    public String index(Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            List<Resource> resourceList = resourceService.fetchAll();
            model.addAttribute("resources", resourceList);
            return "resourceindex";
        }
        return "redirect:/login";  // or any other unauthorized view
    }

    @GetMapping("/createresource")
    public String create(HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            return "createresource";
        }
        return "redirect:/resourceindex";
    }

    @PostMapping("/createresource")
    public String createNew(@ModelAttribute Resource resource, HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            resourceService.addResource(resource);
        }
        return "redirect:/resourceindex";
    }

    @GetMapping("/viewoneresource/{id}")
    public String viewOne(@PathVariable("id") int id, Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            try {
                model.addAttribute("resource", resourceService.findResourceById(id));
                return "viewoneresource";
            } catch (ResourceNotFoundException e) {
                model.addAttribute("errorMessage", "Resource not found");
                return "errorPage"; // or redirect them to another appropriate page
            }
        }
        return "redirect:/resourceindex";
    }

    @PostMapping("/deleteresource/{id}")
    public String deleteOne(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isAdmin(session)) {
            boolean deleted = resourceService.deleteResource(id);
            if (!deleted) {
                redirectAttributes.addFlashAttribute("errorMessage", "Resource could not be deleted.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/resourceindex";
    }

    @GetMapping("/updateresource/{id}")
    public String updateOne(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("resource", resourceService.findResourceById(id));
            return "updateresource";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        return "redirect:/resourceindex";
    }

    @PostMapping("/updateresource")
    public String updateResource(@ModelAttribute Resource resource, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            resourceService.updateResource(resource.getId(), resource);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/resourceindex";
    }
}

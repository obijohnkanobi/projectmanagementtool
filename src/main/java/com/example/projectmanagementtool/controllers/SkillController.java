package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.Skill;
import com.example.projectmanagementtool.services.SkillService;
import com.example.projectmanagementtool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/skillindex")
    public String index(Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            List<Skill> skillList = skillService.fetchAll();
            model.addAttribute("skills", skillList);
            return "skillindex";
        }
        return "redirect:/login";  // or any other unauthorized view
    }

    @GetMapping("/createskill")
    public String create(HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            return "createskill";
        }
        return "redirect:/skillindex";
    }

    @PostMapping("/createskill")
    public String createNew(@ModelAttribute Skill skill, HttpSession session) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            skillService.addSkill(skill);
        }
        return "redirect:/skillindex";
    }

    @GetMapping("/viewoneskill/{id}")
    public String viewOne(@PathVariable("id") int id, Model model, HttpSession session) {
        if (UserUtils.isUser(session) || UserUtils.isDeveloper(session) || UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("skill", skillService.findSkillById(id));
            return "viewoneskill";
        }
        return "redirect:/skillindex";
    }

    @PostMapping("/deleteSkill/{id}")
    public String deleteOne(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isAdmin(session)) {
            boolean deleted = skillService.deleteSkill(id);
            if (!deleted) {
                redirectAttributes.addFlashAttribute("errorMessage", "Skill could not be deleted.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/skillindex";
    }

    @GetMapping("/updateskill/{id}")
    public String updateOne(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            model.addAttribute("skill", skillService.findSkillById(id));
            return "updateskill";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        return "redirect:/skillindex";
    }

    @PostMapping("/updateskill")
    public String updateSkill(@ModelAttribute Skill skill, HttpSession session, RedirectAttributes redirectAttributes) {
        if (UserUtils.isProjectLeader(session) || UserUtils.isAdmin(session)) {
            skillService.updateSkill(skill.getId(), skill);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Insufficient privileges.");
        }
        return "redirect:/skillindex";
    }
}

package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SkillController {
    @Autowired
    com.example.projectmanagementtool.services.SkillService skillService;

    @GetMapping("/skillindex")
    public String index(Model model){
        List<Skill> skillList = skillService.fetchAll();
        model.addAttribute("skills", skillList);
        return "skillindex";
    }

    @GetMapping("/createskill")
    public String create(){
        return "createskill";
    }

    @PostMapping("/createskill")
    public String createNew(@ModelAttribute Skill skill){
        skillService.addSkill(skill);
        return "redirect:/createskill";
    }

    @GetMapping("/viewoneskill/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("skill", skillService.findSkillById(id));
        return "viewoneskill";
    }

    @PostMapping("/deleteSkill/{id}")
    public String deleteOne(@PathVariable("id") int id, Model model){
        boolean deleted = skillService.deleteSkill(id);
        if (deleted){
            return "redirect:/skillindex";
        } else {
            model.addAttribute("error", "Could not delete skill with id " + id);
            return "redirect:/skillindex";
        }
    }


    @GetMapping("/updateskill/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("skill", skillService.findSkillById(id));
        return "updateskill";
    }

    @PostMapping("/updateskill")
    public String updateSkill(@ModelAttribute Skill skill){
        skillService.updateSkill(skill.getId(), skill);
        return "redirect:/skillindex";
    }
}

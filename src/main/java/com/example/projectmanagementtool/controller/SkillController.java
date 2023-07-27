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

    @GetMapping("/skills")
    public String index(Model model){
        List<Skill> skillList = skillService.fetchAll();
        model.addAttribute("skills", skillList);
        return "skills/index";
    }

    @GetMapping("/skills/create")
    public String create(){
        return "skills/create";
    }

    @PostMapping("/skills/createNew")
    public String createNew(@ModelAttribute Skill skill){
        skillService.addSkill(skill);
        return "redirect:/skills";
    }

    @GetMapping("/skills/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("skill", skillService.findSkillById(id));
        return "skills/viewOne";
    }

    @GetMapping("/skills/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = skillService.deleteSkill(id);
        if (deleted){
            return "redirect:/skills";
        } else {
            return "redirect:/skills";
        }
    }

    @GetMapping("/skills/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("skill", skillService.findSkillById(id));
        return "skills/updateOne";
    }

    @PostMapping("/skills/updateSkill")
    public String updateSkill(@ModelAttribute Skill skill){
        skillService.updateSkill(skill.getId(), skill);
        return "redirect:/skills";
    }
}

import com.example.projectmanagementtool.models.SubProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubProjectController {
    @Autowired
    com.example.projectmanagementtool.services.SubProjectService subProjectService;

    @GetMapping("/subprojects")
    public String index(Model model){
        List<SubProject> subProjectList = subProjectService.fetchAll();
        model.addAttribute("subprojects", subProjectList);
        return "subprojectindex";
    }

    @GetMapping("/createSubProject")
    public String create(){
        return "createsubproject";
    }

    @PostMapping("/createNewSubProject")
    public String createNew(@ModelAttribute SubProject subProject){
        subProjectService.addSubProject(subProject);
        return "redirect:/subprojects";
    }

    @GetMapping("/viewSubProject/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("subproject", subProjectService.findSubProjectById(id));
        return "viewonesubproject";
    }

    @GetMapping("/deleteSubProject/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = subProjectService.deleteSubProject(id);
        if (deleted){
            return "redirect:/subprojects";
        } else {
            return "redirect:/subprojects";
        }
    }

    @GetMapping("/updateSubProject/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("subproject", subProjectService.findSubProjectById(id));
        return "updatesubproject";
    }

    @PostMapping("/updateSubProject")
    public String updateSubProject(@ModelAttribute SubProject subProject){
        subProjectService.updateSubProject(subProject.getId(), subProject);
        return "redirect:/subprojects";
    }
}

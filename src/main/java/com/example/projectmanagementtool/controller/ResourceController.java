import com.example.projectmanagementtool.models.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ResourceController {
    @Autowired
    com.example.projectmanagementtool.services.ResourceService resourceService;

    @GetMapping("/resourceindex")
    public String index(Model model){
        List<Resource> resourceList = resourceService.fetchAll();
        model.addAttribute("resources", resourceList);
        return "resourceindex";
    }

    @GetMapping("/createresource")
    public String create(){
        return "createresource";
    }

    @PostMapping("/createresource")
    public String createNew(@ModelAttribute Resource resource){
        resourceService.addResource(resource);
        return "redirect:/resourceindex";
    }

    @GetMapping("/viewoneresource/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("resource", resourceService.findResourceById(id));
        return "viewoneresource";
    }

    @GetMapping("/deleteresource/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = resourceService.deleteResource(id);
        if (deleted){
            return "redirect:/resourceindex";
        } else {
            return "redirect:/resourceindex";
        }
    }

    @GetMapping("/updateresource/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("resource", resourceService.findResourceById(id));
        return "updateresource";
    }

    @PostMapping("/updateresource")
    public String updateResource(@ModelAttribute Resource resource){
        resourceService.updateResource(resource.getId(), resource);
        return "redirect:/resourceindex";
    }
}

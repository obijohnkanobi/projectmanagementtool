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

    @GetMapping("/resources")
    public String index(Model model){
        List<Resource> resourceList = resourceService.fetchAll();
        model.addAttribute("resources", resourceList);
        return "resources/index";
    }

    @GetMapping("/resources/create")
    public String create(){
        return "resources/create";
    }

    @PostMapping("/resources/createNew")
    public String createNew(@ModelAttribute Resource resource){
        resourceService.addResource(resource);
        return "redirect:/resources";
    }

    @GetMapping("/resources/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("resource", resourceService.findResourceById(id));
        return "resources/viewOne";
    }

    @GetMapping("/resources/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = resourceService.deleteResource(id);
        if (deleted){
            return "redirect:/resources";
        } else {
            return "redirect:/resources";
        }
    }

    @GetMapping("/resources/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("resource", resourceService.findResourceById(id));
        return "resources/updateOne";
    }

    @PostMapping("/resources/updateResource")
    public String updateResource(@ModelAttribute Resource resource){
        resourceService.updateResource(resource.getId(), resource);
        return "redirect:/resources";
    }
}

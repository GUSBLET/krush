package krush.ua.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class SystemController {
    private final SystemService systemService;

    @GetMapping("/")
    public String getShowcase(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "25") int size,
                              Model model){
        model.addAttribute("title", "Вітрина");
        model.addAttribute("content", "showcase");
        model.addAttribute("systems", systemService.getTablePage(page, size));

        return "layout";
    }


    @GetMapping("/description")
    public String getSystemDescription(
                              @RequestParam(name = "id") int id,
                              Model model){
        System system = systemService.getSystemById(id);
        model.addAttribute("title", system.getName());
        model.addAttribute("content", "description");
        model.addAttribute("id", system.getId());
        model.addAttribute("system", system);
        return "layout";
    }

    @GetMapping("/supplements")
    public String getSystemSupplements(
            @RequestParam(name = "id") int id,
            Model model){
        System system = systemService.getSystemById(id);
        model.addAttribute("title", system.getName());
        model.addAttribute("content", "supplements");
        model.addAttribute("id", system.getId());
        model.addAttribute("system", system);

        return "layout";
    }
}

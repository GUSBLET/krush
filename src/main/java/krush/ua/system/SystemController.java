package krush.ua.system;

import jakarta.validation.Valid;
import krush.ua.account.dtos.SignUpDTO;
import krush.ua.classifier.computer.systems.ClassifierComputerSystem;
import krush.ua.classifier.computer.systems.type.ClassifierComputerSystemType;
import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import krush.ua.system.dto.EnteringSystemMainInformationDTO;
import krush.ua.system.dto.UpdatingSystemDescriptionDTO;
import krush.ua.system.dto.UpdatingSystemSupplementsDTO;
import krush.ua.system.expert.function.ExpertFunction;
import krush.ua.system.literature.Literature;
import krush.ua.technical.attribute.ModelAttributeManager;
import krush.ua.technical.attribute.ModelPageAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SystemController {
    private final SystemService systemService;

    @GetMapping("/")
    public String getShowcase(@RequestParam(name = "name", defaultValue = "") String name,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "25") int size,
                              Model model) {
        model.addAttribute("title", "Вітрина");
        model.addAttribute("content", "showcase");
        model.addAttribute("systems", systemService.getTablePage(page, size, name));

        return "layout";
    }


    @GetMapping("/system/description")
    public String getSystemDescription(
            @RequestParam(name = "id") int id,
            Model model) {
        System system = systemService.getSystemById(id);
        model.addAttribute("title", system.getName());
        model.addAttribute("content", "description");
        model.addAttribute("id", system.getId());
        model.addAttribute("system", system);
        return "layout";
    }

    @GetMapping("/system/supplements")
    public String getSystemSupplements(
            @RequestParam(name = "id") int id,
            Model model) {
        System system = systemService.getSystemById(id);
        model.addAttribute("title", system.getName());
        model.addAttribute("content", "supplements");
        model.addAttribute("id", system.getId());
        model.addAttribute("system", system);

        return "layout";
    }

    @GetMapping("/technical/system/systems-controller-panel")
    public String getSystemsControllerPanel(@RequestParam(name = "name", defaultValue = "") String name,
                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "25") int size,
                                            Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .content("systems-controller-panel")
                .entity(systemService.getTablePage(page, size, name))
                .title("Управління")
                .build());
        return "layout";
    }


    @GetMapping("/technical/system/entering-system-main-information-page")
    public String getEnteringSystemMainInformationPage(Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Створення системи")
                .content("entering-system-main-information-page")
                .entity(EnteringSystemMainInformationDTO.builder()
                        .build())
                .build());

        return "layout";
    }



    @PostMapping("/technical/system/save-main-system-information")
    public String saveMainSystemInformation(@Valid @ModelAttribute("entity") EnteringSystemMainInformationDTO dto,
                                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                    .title("Створення системи")
                    .content("entering-system-main-information-page")
                    .entity(dto)
                    .build());

            return "layout";
        } else if (systemService.systemNameIsExist(dto.getName())) {
            ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                    .title("Створення системи")
                    .content("entering-system-main-information-page")
                    .entity(dto)
                    .build());
            bindingResult.rejectValue("name", "error", "Назва вже зайнята");
            return "layout";
        }

        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Створення системи")
                .content("update-system")
                .entity(systemService.createSystem(dto))
                .build());
        return "layout";
    }

    @PostMapping("/technical/system/update-main-system-information")
    public String saveMainSystemInformation(@Valid @ModelAttribute("entity") UpdatingSystemDescriptionDTO dto,
                                            Model model) {


        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Створення системи")
                .content("update-system")
                .entity(systemService.updateSystem(dto))
                .build());
        return "layout";
    }

    @PostMapping("/technical/system/update-supplements-system-information")
    public String saveSupSystemInformation(@Valid @ModelAttribute("entity") UpdatingSystemSupplementsDTO entity,
                                            Model model) {

        systemService.updateSystem(entity);
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Створення системи")
                .content("update-system")
//                .entity()
                .build());
        return "layout";
    }




    @GetMapping("/technical/system/create-system")
    public String getCreateSystem(Model model) {

        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("entering-system-main-information-page")
                .entity(new EnteringSystemMainInformationDTO())
                .build());
        return "layout";
    }

    @GetMapping("/technical/system/get-system")
    public String getTechById(@RequestParam Integer id, Model model) {
        if (id == null) {

            return "layout";
        }
        UpdatingSystemDescriptionDTO dto = systemService.getUpdatingSystemById(id);

        model.addAttribute("id", dto.getId());
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("tech-description")
                .entity(dto)
                .build());
        return "layout";
    }


    @GetMapping("/technical/system/get-system-supplements")
    public String getTechSupplementsById(@RequestParam Integer id, Model model) {
        if (id == null) {

            return "layout";
        }
        UpdatingSystemSupplementsDTO dto = systemService.getUpdatingSystemSupplementsById(id);
        model.addAttribute("id", dto.getId());
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("tech-supplements")
                .entity(dto)
                .build());
        return "layout";
    }

    @GetMapping("/technical/system/add-literature")
    public String getAddLiterById(@RequestParam Integer id,
                                  Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("tech-lit")
                .entity(id)
                .build());
        return "layout";
    }

    @GetMapping("/technical/system/add-classifier")
    public String getAddClassById(@RequestParam Integer id,
                                  Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("tech-class")
                .entity(id)
                .build());
        return "layout";
    }

    @GetMapping("/technical/system/add-function")
    public String getAddFuncById(@RequestParam Integer id,
                                  Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("tech-func")
                .entity(id)
                .build());
        return "layout";
    }

    @GetMapping("/technical/system/add-environmental")
    public String getAddEnvById(@RequestParam Integer id,
                                 Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Нова система")
                .content("tech-env")
                .entity(id)
                .build());
        return "layout";
    }

    @GetMapping("/technical/system/add-new-func")
    public String getAddLiterById(@RequestParam Integer id,
                                  @RequestParam String name) {
        systemService.addExpertFunction(id, ExpertFunction.builder()
                .name(name)
                .build());
        return "redirect:/technical/system/get-system-supplements?id=" + id;
    }

    @GetMapping("/technical/system/add-new-env")
    public String getAddEnvById(@RequestParam Integer id,
                                  @RequestParam String name) {
        systemService.addEnvironmental(id, EnvironmentalMonitoringClassifier.builder()
                .name(name)
                .build());
        return "redirect:/technical/system/get-system-supplements?id=" + id;
    }

    @GetMapping("/technical/system/add-new-literature")
    public String getAddLiterById(@RequestParam Integer id,
                                  @RequestParam String name,
                                  @RequestParam String author,
                                  @RequestParam String description,
                                  @RequestParam String url,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Optional<Date> dateOfLastAccess) {
        systemService.addLiterature(id, Literature.builder()
                .name(name)
                .description(description)
                .author(author)
                .url(url)
                .dateOfLastAccess(dateOfLastAccess.orElse(null))
                .build());
        return "redirect:/technical/system/get-system-supplements?id=" + id;
    }

    @GetMapping("/technical/system/add-new-class")
    public String getAddClassById(@RequestParam Integer id,
                                  @RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String description) {
        systemService.addClass(id, ClassifierComputerSystem.builder()
                .name(name)
                .description(description)

                .classifierComputerSystemType(ClassifierComputerSystemType.builder().name(type).build())
                .build());
        return "redirect:/technical/system/get-system-supplements?id=" + id;
    }

    @GetMapping("/technical/system/remove-item")
    public String getAddClassById(@RequestParam Integer id,
                                  @RequestParam Integer itemId,
                                  @RequestParam String type) {
        systemService.removeItem(type, itemId, id);
        return "redirect:/technical/system/get-system-supplements?id=" + id;
    }


    @GetMapping("/technical/system/remove-system")
    @ResponseBody
    public ResponseEntity<Void> removeSystemById(@RequestParam Integer id) {
        systemService.removeSystemById(id);
        return ResponseEntity.ok().build();
    }
}

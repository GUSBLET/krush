package krush.ua.system;


import krush.ua.classifier.computer.systems.ClassifierComputerSystem;
import krush.ua.classifier.computer.systems.ClassifierComputerSystemService;
import krush.ua.country.Country;
import krush.ua.country.CountryService;
import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifierService;
import krush.ua.system.dto.EnteringSystemMainInformationDTO;
import krush.ua.system.dto.UpdatingSystemDescriptionDTO;
import krush.ua.system.dto.UpdatingSystemSupplementsDTO;
import krush.ua.system.expert.function.ExpertFunction;
import krush.ua.system.expert.function.ExpertFunctionService;
import krush.ua.system.literature.Literature;
import krush.ua.system.literature.LiteratureService;
import krush.ua.system.software.type.SoftwareTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SystemService {
    private final SystemRepository systemRepository;
    private final CountryService countryService;
    private final SoftwareTypeService softwareTypeService;
    private final LiteratureService literatureService;
    private final ExpertFunctionService expertFunctionService;
    private final EnvironmentalMonitoringClassifierService environmentalMonitoringClassifierService;
    private final ClassifierComputerSystemService classifierComputerSystemService;

    public Page<System> getTablePage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return systemRepository.findAllWithCountryAndType(pageable);
    }

    public boolean systemNameIsExist(String name) {
        return systemRepository.findByName(name).isPresent();
    }

    public System createSystem(EnteringSystemMainInformationDTO dto) {
        System system = dto.toEntity(dto);
        Country country = countryService.findByName(dto.getCountry());
        if (country == null) {
            country = countryService.save(dto.getCountry());
        }
        system.setCountry(country);
        system.setType(softwareTypeService.findByName(system.getType().getName()));

        return systemRepository.save(system);
    }

    public UpdatingSystemDescriptionDTO updateSystem(UpdatingSystemDescriptionDTO dto) {
        Optional<System> system = systemRepository.findByName(dto.getName());
        if (system.isEmpty())
            return dto;
        System updatedSystem = dto.toEntity(dto);

        if (!Objects.equals(system.get().getCountry().getName(), dto.getCountry().getName())) {
            Country country = countryService.findByName(dto.getCountry().getName());
            if (country == null) {
                country = countryService.save(dto.getCountry().getName());
            }
            updatedSystem.setCountry(country);
        } else {
            updatedSystem.setCountry(system.get().getCountry());
        }

        if (dto.getType() != null) {
            updatedSystem.setType(softwareTypeService.findById(updatedSystem.getType().getId()));
        } else {
            updatedSystem.setType(system.get().getType());
        }
        updatedSystem.setId(system.get().getId());
        systemRepository.updateSystemById(updatedSystem.getId(), updatedSystem.getName(), updatedSystem.getDeveloper(), updatedSystem.isAvailabilityOfDecisionMakingClassifier(), updatedSystem.getReleaseDate(), updatedSystem.getDescription(), updatedSystem.getType(), updatedSystem.getCountry());
        return dto;
    }

    public void updateSystem(UpdatingSystemSupplementsDTO dto) {
        Optional<System> system = systemRepository.findByName(dto.getName());
//        if (system.isEmpty())
//            return dto;
        System updatedSystem = dto.toEntity(dto);
    }

    @Cacheable(value = "systems", key = "#id", unless = "#result == null")
    @Scheduled(fixedDelay = 300000)
    public System getSystemById(Integer id) {
        return systemRepository.findByIdWithAssociations(id).orElse(null);
    }

    public UpdatingSystemDescriptionDTO getUpdatingSystemById(Integer id) {
        System system = systemRepository.findByIdWithAssociations(id).orElse(null);
        UpdatingSystemDescriptionDTO dto = new UpdatingSystemDescriptionDTO();
        if (system != null)
            return dto.toDto(system);
        return null;
    }

    public UpdatingSystemSupplementsDTO getUpdatingSystemSupplementsById(Integer id) {
        System system = systemRepository.findByIdWithAssociations(id).orElse(null);
        UpdatingSystemSupplementsDTO dto = new UpdatingSystemSupplementsDTO();
        if (system != null)
            return dto.toDto(system);
        return null;
    }


    public void removeSystemById(Integer id) {
        systemRepository.deleteById(id);
    }

    public void addLiterature(int id, Literature record) {
        Literature literature = literatureService.findByName(record.getName());
        if (literature == null)
            record = literatureService.save(record);
        else{
            literature.setAuthor(record.getAuthor());
            literature.setDateOfLastAccess(record.getDateOfLastAccess());
            literature.setDescription(record.getDescription());
            literature.setUrl(record.getUrl());
            literatureService.update(literature);
            record = literature;
        }

        var sys = systemRepository.findById(id);
        if (sys.isEmpty())
            return;

        if(sys.get().getLiteratures().contains(literature))
            return;
        sys.get().getLiteratures().add(record);
        systemRepository.save(sys.get());
    }

    public void addExpertFunction(Integer id, ExpertFunction record) {
        ExpertFunction expertFunction = expertFunctionService.findByName(record.getName());
        if(expertFunction == null)
            record = expertFunctionService.save(record);
        else
            record = expertFunction;

        var sys = systemRepository.findById(id);
        if (sys.isEmpty())
            return;

        if(sys.get().getExpertFunctions().contains(record))
            return;
        sys.get().getExpertFunctions().add(record);
        systemRepository.save(sys.get());
    }

    public void addEnvironmental(Integer id, EnvironmentalMonitoringClassifier record) {
        EnvironmentalMonitoringClassifier environmentalMonitoringClassifier = environmentalMonitoringClassifierService.findByName(record.getName());
        if(environmentalMonitoringClassifier == null)
            record = environmentalMonitoringClassifierService.save(record);
        else
            record = environmentalMonitoringClassifier;

        var sys = systemRepository.findById(id);
        if (sys.isEmpty())
            return;

        if(sys.get().getEnvironmentalMonitoringClassifiers().contains(record))
            return;
        sys.get().getEnvironmentalMonitoringClassifiers().add(record);
        systemRepository.save(sys.get());
    }

    public void addClass(Integer id, ClassifierComputerSystem record) {
        ClassifierComputerSystem computerSystem = classifierComputerSystemService.findByName(record.getName());
        if(computerSystem == null)
            record = classifierComputerSystemService.save(record);
        else
            record = computerSystem;

        var sys = systemRepository.findById(id);
        if (sys.isEmpty())
            return;

        if(sys.get().getClassifierComputerSystems().contains(record))
            return;
        sys.get().getClassifierComputerSystems().add(record);
        systemRepository.save(sys.get());
    }

    public void removeItem(String type, Integer itemId,Integer id){
        switch (type){
            case "lit" -> removeLib(itemId, id);
            case "class" -> removeClass(itemId, id);
            case "func" -> removeFunc(itemId, id);
            case "env" -> removeEnv(itemId, id);
        }
    }

    private void removeLib(Integer itemId, Integer id){
        var sys = systemRepository.findById(id);
        if(sys.isEmpty())
            return;
        sys.get().setLiteratures(sys.get().getLiteratures().stream()
                .filter(literature -> !literature.getId().equals(itemId))
                .collect(Collectors.toSet()));
        systemRepository.save(sys.get());
    }

    private void removeClass(Integer itemId, Integer id){
        var sys = systemRepository.findById(id);
        if(sys.isEmpty())
            return;
        sys.get().setClassifierComputerSystems(sys.get().getClassifierComputerSystems().stream()
                .filter(classifierComputerSystem -> !classifierComputerSystem.getId().equals(itemId))
                .collect(Collectors.toSet()));
        systemRepository.save(sys.get());

    }

    private void removeFunc(Integer itemId, Integer id){
        var sys = systemRepository.findById(id);
        if(sys.isEmpty())
            return;
        sys.get().setExpertFunctions(sys.get().getExpertFunctions().stream()
                .filter(expertFunction -> !expertFunction.getId().equals(itemId))
                .collect(Collectors.toSet()));
        systemRepository.save(sys.get());
    }

    private void removeEnv(Integer itemId, Integer id){
        var sys = systemRepository.findById(id);
        if(sys.isEmpty())
            return;
        sys.get().setEnvironmentalMonitoringClassifiers(sys.get().getEnvironmentalMonitoringClassifiers().stream()
                .filter(classifier -> !classifier.getId().equals(itemId))
                .collect(Collectors.toSet()));
        systemRepository.save(sys.get());

    }
}

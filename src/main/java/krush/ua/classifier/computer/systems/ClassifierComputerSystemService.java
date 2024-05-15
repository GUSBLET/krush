package krush.ua.classifier.computer.systems;

import krush.ua.classifier.computer.systems.type.ClassifierComputerSystemTypeService;
import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassifierComputerSystemService {
    private final ClassifierComputerSystemRepository classifierComputerSystemRepository;
    private final ClassifierComputerSystemTypeService classifierComputerSystemTypeService;

    public ClassifierComputerSystem findByName(String name){
        return classifierComputerSystemRepository.findByName(name).orElse(null);
    }

    public ClassifierComputerSystem save(ClassifierComputerSystem computerSystem) {
        var type = classifierComputerSystemTypeService.findByName(computerSystem.getClassifierComputerSystemType().getName());
        if(type == null)
            computerSystem.setClassifierComputerSystemType(classifierComputerSystemTypeService.save(computerSystem.getClassifierComputerSystemType()));
        else
            computerSystem.setClassifierComputerSystemType(type);


        return classifierComputerSystemRepository.save(computerSystem);
    }
}

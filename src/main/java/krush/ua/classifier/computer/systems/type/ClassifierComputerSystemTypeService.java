package krush.ua.classifier.computer.systems.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassifierComputerSystemTypeService {
    private final ClassifierComputerSystemTypeRepository classifierComputerSystemRepository;
    public ClassifierComputerSystemType findByName(String name) {
        return classifierComputerSystemRepository.findByName(name).orElse(null);
    }

    public ClassifierComputerSystemType save(ClassifierComputerSystemType classifierComputerSystemType) {
        return classifierComputerSystemRepository.save(classifierComputerSystemType);
    }
}

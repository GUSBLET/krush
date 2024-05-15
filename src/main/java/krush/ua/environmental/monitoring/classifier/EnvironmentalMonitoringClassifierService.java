package krush.ua.environmental.monitoring.classifier;

import krush.ua.system.literature.Literature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentalMonitoringClassifierService {
    private final EnvironmentalMonitoringClassifierRepository environmentalMonitoringClassifierRepository;
    public EnvironmentalMonitoringClassifier findByName(String name) {
        return environmentalMonitoringClassifierRepository.findByName(name).orElse(null);
    }

    public EnvironmentalMonitoringClassifier save(EnvironmentalMonitoringClassifier environmentalMonitoringClassifier) {
        return environmentalMonitoringClassifierRepository.save(environmentalMonitoringClassifier);
    }
}

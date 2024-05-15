package krush.ua.classifier.computer.systems.type;

import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassifierComputerSystemTypeRepository extends JpaRepository<ClassifierComputerSystemType, Integer> {
    Optional<ClassifierComputerSystemType> findByName(String name);
}

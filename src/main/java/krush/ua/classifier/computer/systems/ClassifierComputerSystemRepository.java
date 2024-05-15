package krush.ua.classifier.computer.systems;

import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassifierComputerSystemRepository extends JpaRepository<ClassifierComputerSystem, Integer> {
    Optional<ClassifierComputerSystem> findByName(String name);
}

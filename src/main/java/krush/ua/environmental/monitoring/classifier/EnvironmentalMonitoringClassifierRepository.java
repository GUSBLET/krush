package krush.ua.environmental.monitoring.classifier;

import krush.ua.system.literature.Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvironmentalMonitoringClassifierRepository extends JpaRepository<EnvironmentalMonitoringClassifier, Integer> {
    Optional<EnvironmentalMonitoringClassifier> findByName(String name);
}

package krush.ua.environmental.monitoring.classifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentalMonitoringClassifierRepository extends JpaRepository<EnvironmentalMonitoringClassifier, Integer> {
}
